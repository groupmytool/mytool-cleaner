package com.mytool.cleaner.controller.uninstall;

import com.github.zafarkhaja.semver.Version;
import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppConfig;
import com.mytool.cleaner.model.AppListModel;
import com.mytool.cleaner.service.uninstall.AppInfoService;
import com.mytool.cleaner.utils.AppConfigParser;
import com.mytool.cleaner.utils.PlistUtil;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


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
  public Text appSize;
  @FXML
  public VBox detailContent;
  @FXML
  public VBox introduction;
  @FXML
  private ScrollPane rightScrollableList;
  @FXML
  private HBox appDetailPane;
  @FXML
  private TreeView<String> appFileListView;
  @FXML
  private TreeItem<String> appFileListItem;

  private AppListModel appListModel;

  public void setAppListModel(AppListModel appListModel) {
    this.appListModel = appListModel;
  }

  public void build() throws IOException {
    HashMap<String, Object> plist = null;
    try {
      plist = PlistUtil.readPlist("%s/Contents/Info.plist".formatted(appListModel.file.getAbsolutePath()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 应用图标
    AppInfoService.setAppIcon(appIcon, plist, appListModel);
    // 应用名称
    AppInfoService.setAppName(appName, appListModel);

    // 应用大小
    AppInfoService.setAppSize(appSize, appListModel);
    // 创建时间
    AppInfoService.setCreateTime(appInstallTime, appListModel);

    // app file list
    appFileListView.setShowRoot(false);
    appFileListView.setCellFactory(CheckBoxTreeCell.forTreeView());

    appFileListItem.setExpanded(true);
    ObservableList<TreeItem<String>> titleGroup = appFileListItem.getChildren();
    titleGroup.clear();

    // 准备工作：查找符合条件的配置文件
    AppConfig appConf = null;
    Object cfBundleIdentifier = plist.get("CFBundleIdentifier");
    if (cfBundleIdentifier != null) {
      ArrayList<AppConfig> appConfList = AppConfigParser.appConfMap.get(cfBundleIdentifier);
      if (appConfList != null && plist.get("CFBundleShortVersionString") != null) {
        foundConfig:
        for (AppConfig appConfig : appConfList) {
          String cfBundleShortVersionString = plist.get("CFBundleShortVersionString").toString();
          Version parseVersion = Version.parse(cfBundleShortVersionString);
          for (String v : appConfig.version) {
            if (parseVersion.satisfies(v)) {
              appConf = appConfig;
              System.out.println(cfBundleShortVersionString);
            }
            break foundConfig;
          }
          System.out.println(appConfig);
        }
      }
    }

    TreeItem<String> runnableTitleItem = initExpandedTreeItem("可执行文件");
    titleGroup.add(runnableTitleItem);

    TreeItem<String> runnableChildItem = initExpandedTreeItem(appListModel.file.getName());
    runnableTitleItem.getChildren().add(runnableChildItem);

    // 应用程序支持：~/Library/Application Support/JetBrains
    TreeItem<String> supportTitleItem = initExpandedTreeItem("应用程序支持");
    titleGroup.add(supportTitleItem);

    ArrayList<String> supportList = new ArrayList<>();
    supportList.add(cfBundleIdentifier.toString());

    if (appConf != null) {
      supportList.addAll(appConf.support);
    }
    if (!supportList.isEmpty()) {
      for (String support : supportList) {
        File file = new File(String.format("%s/Library/Application Support/%s", System.getProperty("user.home"), support));
        if (file.exists()) {
          TreeItem<String> supportChildItem = initClumpedTreeItem(file.getName());
          supportTitleItem.getChildren().add(supportChildItem);

          ObservableList<TreeItem<String>> supportChildItemChildren = supportChildItem.getChildren();
          for (File subFile : file.listFiles()) {
            TreeItem<String> subItem = initClumpedTreeItem(subFile.getName());
            supportChildItemChildren.add(subItem);
          }
        }
      }
    }

    TreeItem<String> cacheTitleItem = initExpandedTreeItem("缓存");
    titleGroup.add(cacheTitleItem);

    TreeItem<String> preferenceTitleItem = initExpandedTreeItem("偏好设置");
    titleGroup.add(preferenceTitleItem);

    TreeItem<String> logTitleItem = initExpandedTreeItem("日志");
    titleGroup.add(logTitleItem);

    TreeItem<String> iconTitleItem = initExpandedTreeItem("Dock图标");
    titleGroup.add(iconTitleItem);

    TreeItem<String> fileTypeTitleItem = initExpandedTreeItem("支持的文稿类型");
    titleGroup.add(fileTypeTitleItem);

    showDetail();

  }

  public void showDetail() {
    introduction.setVisible(false);
    introduction.setManaged(false);
    detailContent.setVisible(true);
    detailContent.setManaged(true);
    // 高度自适应
    appFileListView.prefHeightProperty().bind(
        Bindings.subtract(
            rightScrollableList.heightProperty(),
            appDetailPane.getBoundsInLocal().getHeight()
        )
    );
  }

  // 加载APP分组文件列表
  private TreeItem<String> initTreeItem(String title, boolean expanded) {
    CheckBoxTreeItem<String> treeItem = new CheckBoxTreeItem<>();
    treeItem.setExpanded(expanded);
    treeItem.setValue(title);
    return treeItem;
  }

  private TreeItem<String> initClumpedTreeItem(String title) {
    return initTreeItem(title, false);
  }

  private TreeItem<String> initExpandedTreeItem(String title) {
    return initTreeItem(title, true);
  }

}
