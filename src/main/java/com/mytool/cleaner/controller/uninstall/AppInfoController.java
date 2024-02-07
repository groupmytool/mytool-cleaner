package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppListModel;
import com.mytool.cleaner.service.uninstall.AppInfoService;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;


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

    // 应用图标
    AppInfoService.setAppIcon(appIcon, appListModel);
    // 应用名称
    AppInfoService.setAppName(appName, appListModel);

    // 应用大小
    AppInfoService.setAppSize(appSize, appListModel);
    // 创建时间
    AppInfoService.setCreateTime(appInstallTime, appListModel);

    // 高度自适应
    appFileListView.prefHeightProperty().bind(
        Bindings.subtract(
            rightScrollableList.heightProperty(),
            appDetailPane.getBoundsInLocal().getHeight()
        )
    );

    // app file list
    appFileListView.setShowRoot(false);
    appFileListView.setCellFactory(CheckBoxTreeCell.forTreeView());

    appFileListItem.setExpanded(true);
    ObservableList<TreeItem<Text>> titleGroup = appFileListItem.getChildren();
    titleGroup.clear();

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
    CheckBoxTreeItem<Text> treeItem = new CheckBoxTreeItem<>();
    treeItem.setExpanded(expanded);
    treeItem.setValue(new Text(title));
    return treeItem;
  }

  private TreeItem<Text> initClumpedTreeItem(String title) {
    return initTreeItem(title, false);
  }

  private TreeItem<Text> initExpandedTreeItem(String title) {
    return initTreeItem(title, true);
  }

}
