package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppListModel;
import com.mytool.cleaner.utils.AppSizeCalculator;
import com.mytool.cleaner.utils.PlistUtil;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;

import static com.mytool.cleaner.utils.IconUtil.getIcon;

/**
 * App详细信息控制器
 */
public class AppInfoController extends BaseController {

  @FXML
  public ImageView appIcon;
  @FXML
  public Text appName;
  @FXML
  public Text appInstallTime;
  @FXML
  public Text appVersion;
  @FXML
  public Text appSize;
  @FXML
  private ScrollPane rightScrollableList;
  @FXML
  private HBox appDetailPane;
  @FXML
  private TreeView<Text> appFileListView;
  @FXML
  private TreeItem<Text> appFileListItem;

  private AppListModel appListModel;

  public void setAppListModel(AppListModel appListModel) {
    this.appListModel = appListModel;
  }

  public void build() throws IOException {

    // app file list
    appFileListItem.setExpanded(true);
    ObservableList<TreeItem<Text>> titleGroup = appFileListItem.getChildren();

    // 应用大小
    long sizeKb = AppSizeCalculator.calculateAppSize(appListModel.file.toPath());
    String sizeText;
    double sizeMb = sizeKb / 1000.0 / 1000.0;
    if (sizeMb < 1000.0) {
      sizeText = String.format("%.1f MB", sizeMb);
    } else {
      sizeText = String.format("%.2f GB", sizeMb / 1000.0);
    }
    appSize.setText(sizeText);

    BasicFileAttributes attrs = Files.readAttributes(appListModel.file.toPath(), BasicFileAttributes.class);
    // 创建时间
    FileTime creationTime = attrs.creationTime();
    appInstallTime.setText(creationTime.toString());

    // 应用名称
    appName.setText(appListModel.file.getName());


    // 应用图标
    HashMap<String, Object> plist = null;
    try {
      plist = PlistUtil.readPlist(STR."\{appListModel.file.getAbsolutePath()}/Contents/Info.plist");
    } catch (Exception e) {
      e.printStackTrace();
    }

    String icnsName;
    if (plist != null) {
      icnsName = plist.getOrDefault("CFBundleIconFile", "AppIcon").toString();
    } else {
      icnsName = "AppIcon";
    }
    String sourceIcons = STR."\{appListModel.path}/Contents/Resources/\{icnsName}";
    appIcon.setImage(getIcon(sourceIcons, appListModel.name));


    // 高度自适应
    appFileListView.prefHeightProperty().bind(
        Bindings.subtract(
            rightScrollableList.heightProperty(),
            appDetailPane.getBoundsInLocal().getHeight()
        )
    );


    TreeItem<Text> runnableTitleItem = initExpandedTreeItem("可执行文件");
    titleGroup.add(runnableTitleItem);

    TreeItem<Text> runnableChildItem = initExpandedTreeItem(appListModel.file.getName());
    runnableTitleItem.getChildren().add(runnableChildItem);

    // 应用程序支持：~/Library/Application Support/JetBrains
    TreeItem<Text> supportTitleItem = initExpandedTreeItem("应用程序支持");
    titleGroup.add(supportTitleItem);


    TreeItem<Text> cacheTitleItem = initExpandedTreeItem("缓存");
    titleGroup.add(cacheTitleItem);

    TreeItem<Text> preferenceTitleItem = initExpandedTreeItem("偏好设置");
    titleGroup.add(preferenceTitleItem);

    TreeItem<Text> logTitleItem = initExpandedTreeItem("日志");
    titleGroup.add(logTitleItem);

    TreeItem<Text> iconTitleItem = initExpandedTreeItem("Dock图标");
    titleGroup.add(iconTitleItem);

    TreeItem<Text> fileTypeTitleItem = initExpandedTreeItem("支持的文稿类型");
    titleGroup.add(fileTypeTitleItem);

  }

  // 加载APP分组文件列表
  private TreeItem<Text> initTreeItem(String title, boolean expanded) {
    TreeItem<Text> treeItem = new TreeItem<>();
    treeItem.setExpanded(expanded);
    treeItem.setGraphic(new Text(title));
    return treeItem;
  }

  private TreeItem<Text> initClumpedTreeItem(String title) {
    return initTreeItem(title, false);
  }

  private TreeItem<Text> initExpandedTreeItem(String title) {
    return initTreeItem(title, true);
  }

}
