package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppListModel;
import com.mytool.cleaner.utils.IconUtil;
import com.mytool.cleaner.utils.PlistUtil;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Objects;

import static com.mytool.cleaner.utils.CacheUtil.ICNS_CACHE_PATH;
import static com.mytool.cleaner.utils.CacheUtil.filePathCheck;
import static com.mytool.cleaner.utils.CacheUtil.filePathCheckAndCreate;

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

    // 解析系统文件基础信息
    BasicFileAttributes attrs = Files.readAttributes(appListModel.file.toPath(), BasicFileAttributes.class);
    long size = attrs.size();
    FileTime creationTime = attrs.creationTime();
    FileTime lastAccessTime = attrs.lastAccessTime();


    HashMap<String, Object> plist;
    try {
      plist = PlistUtil.readPlist(STR."\{appListModel.path}/Contents/Info.plist");
    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (ParserConfigurationException e) {
      throw new RuntimeException(e);
    } catch (SAXException e) {
      throw new RuntimeException(e);
    }

    // 解析配置文件中的基础信息
    Object cfBundleName = plist.get("CFBundleName");
    if (Objects.isNull(cfBundleName)) {
      appName.setText(appListModel.file.getName());
    } else {
      appName.setText(cfBundleName.toString());
    }


    appVersion.setText(plist.get("CFBundleShortVersionString").toString());
    // 应用图标
    appIcon.setImage(getIcon(plist));

    // 高度自适应
    appFileListView.prefHeightProperty().bind(
        Bindings.subtract(
            rightScrollableList.heightProperty(),
            appDetailPane.getBoundsInLocal().getHeight()
        )
    );

    plist.get("CFBundleIdentifier").toString();


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

  private Image getIcon(HashMap<String, Object> plist) throws FileNotFoundException {
    String icnsName = plist.getOrDefault("CFBundleIconFile", STR."\{appListModel.name}").toString();
    String sourceIcons = STR."\{appListModel.path}/Contents/Resources/\{icnsName}";
    if (!sourceIcons.endsWith(".icns")) {
      sourceIcons += ".icns";
    }
    if (filePathCheck(sourceIcons)) {
      filePathCheckAndCreate(ICNS_CACHE_PATH);
      String outPath = STR."\{ICNS_CACHE_PATH}/\{appListModel.name}.png";
      File out = new File(outPath);
      if (!out.exists()) {
        IconUtil.transform(new File(sourceIcons), "png", out);
      }
      return new Image(new FileInputStream(out));
    } else {
      InputStream icon = getClass().getResourceAsStream("/images/default-icon.png");
      return new Image(Objects.requireNonNull(icon));
    }
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
