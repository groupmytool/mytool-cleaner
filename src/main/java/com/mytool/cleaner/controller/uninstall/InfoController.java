package com.mytool.cleaner.controller.uninstall;

import com.github.zafarkhaja.semver.Version;
import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppListModel;
import com.mytool.cleaner.service.uninstall.InfoService;
import com.mytool.cleaner.utils.base.EnumListFile;
import com.mytool.cleaner.utils.common.CollectionUtils;
import com.mytool.cleaner.utils.file.AppConfigParser;
import com.mytool.cleaner.utils.file.PlistUtil;
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
import java.util.List;


/**
 * App详细信息控制器
 */
public class InfoController extends BaseController {

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
  public HBox appUninstallBtn;
  @FXML
  private ScrollPane rightScrollPane;
  @FXML
  private HBox appDetailPane;
  @FXML
  private TreeView<String> appFileListView;
  @FXML
  private TreeItem<String> appFileListItem;

  private AppListModel appListModel;

  // 查找符合条件的配置文件
  private static HashMap<String, List<String>> getAppConfig(Object cfBundleIdentifier, HashMap<String, Object> plist) {
    if (cfBundleIdentifier == null) {
      return null;
    }
    HashMap<String, List<String>> appConf = null;

    ArrayList<HashMap<String, List<String>>> appConfList = AppConfigParser.appConfMap.get(String.valueOf(cfBundleIdentifier));
    if (appConfList == null) {
      return null;
    }

    Object cfBundleShortVersion = plist.get("CFBundleShortVersionString");
    Version parseVersion = cfBundleShortVersion != null ? Version.parse(cfBundleShortVersion.toString()) : null;

    HashMap<String, List<String>> commonConfig = null;
    // 优先找匹配版本的配置文件
    foundConfig:
    for (HashMap<String, List<String>> appConfig : appConfList) {
      List<String> versionList = appConfig.get(EnumListFile.APP_VERSION.getAppConfigName());
      if (CollectionUtils.isEmpty(versionList)) {
        commonConfig = appConfig;
      } else if (parseVersion != null) {
        for (String v : versionList) {
          if (parseVersion.satisfies(v)) {
            appConf = appConfig;
          }
          break foundConfig;
        }
      }
    }
    // 判断是否找到匹配版本的配置文件，如果没有找到则使用通用配置文件
    if (appConf == null && commonConfig != null) {
      appConf = commonConfig;
    }
    return appConf;
  }

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
    InfoService.setAppIcon(appIcon, plist, appListModel);
    // 应用名称
    InfoService.setAppName(appName, appListModel);

    // 应用大小
    InfoService.setAppSize(appSize, appListModel);
    // 创建时间
    InfoService.setCreateTime(appInstallTime, appListModel);

    // app file list
    appFileListView.setShowRoot(false);
    appFileListView.setCellFactory(CheckBoxTreeCell.forTreeView());

    appFileListItem.setExpanded(true);
    ObservableList<TreeItem<String>> titleTreeItem = appFileListItem.getChildren();
    titleTreeItem.clear();

    Object cfBundleIdentifier = plist != null ? plist.get("CFBundleIdentifier") : null;

    HashMap<String, List<String>> appConf = getAppConfig(cfBundleIdentifier, plist);

    // 可执行文件
    listRunnableFiles(titleTreeItem);
    // 应用程序支持
    listFiles(EnumListFile.APP_SUPPORT, titleTreeItem, cfBundleIdentifier, appConf);
    // 缓存
    listFiles(EnumListFile.APP_CACHE, titleTreeItem, cfBundleIdentifier, appConf);
    // 日志
    listFiles(EnumListFile.APP_LOG, titleTreeItem, cfBundleIdentifier, appConf);


    TreeItem<String> preferenceTitleItem = initExpandedTreeItem("偏好设置");
    titleTreeItem.add(preferenceTitleItem);

    // TreeItem<String> iconTitleItem = initExpandedTreeItem("Dock图标");
    // titleTreeItem.add(iconTitleItem);

    TreeItem<String> fileTypeTitleItem = initExpandedTreeItem("支持的文稿类型");
    titleTreeItem.add(fileTypeTitleItem);

    showDetail();

  }

  private void listFiles(EnumListFile listFile, ObservableList<TreeItem<String>> titleTreeItem,
                         Object cfBundleIdentifier, HashMap<String, List<String>> appConf) {
    TreeItem<String> cacheTitleItem = initExpandedTreeItem(listFile.getAppDisplayName());
    titleTreeItem.add(cacheTitleItem);
    ArrayList<String> pathNameList = new ArrayList<>();
    if (cfBundleIdentifier != null) {
      pathNameList.add(cfBundleIdentifier.toString());
    }
    if (appConf != null) {
      List<String> identifierList = appConf.get(EnumListFile.APP_IDENTIFIER.getAppConfigName());
      if (CollectionUtils.isNotEmpty(identifierList)) {
        pathNameList.addAll(identifierList);
      }
      List<String> configList = appConf.get(listFile.getAppConfigName());
      if (CollectionUtils.isNotEmpty(configList)) {
        pathNameList.addAll(configList);
      }
    }
    if (CollectionUtils.isNotEmpty(pathNameList)) {
      for (String pathName : pathNameList) {
        // TODO: 区分处理绝对路径和相对路径
        File file = new File(listFile.getAppDefaultPath() + pathName);
        listPackageFiles(cacheTitleItem, file);
      }
    }
  }

  private void listPackageFiles(TreeItem<String> titleItem, File packageFile) {
    if (!packageFile.exists()) {
      return;
    }
    TreeItem<String> childItem = initClumpedTreeItem(packageFile.getName());
    titleItem.getChildren().add(childItem);
    File[] files = packageFile.listFiles();
    if (files == null) {
      return;
    }
    ObservableList<TreeItem<String>> childItemChildren = childItem.getChildren();
    for (File subFile : files) {
      TreeItem<String> subItem = initClumpedTreeItem(subFile.getName());
      childItemChildren.add(subItem);
    }
  }

  private void listRunnableFiles(ObservableList<TreeItem<String>> titleTreeItem) {
    TreeItem<String> runnableTitleItem = initExpandedTreeItem("可执行文件");
    titleTreeItem.add(runnableTitleItem);

    TreeItem<String> runnableChildItem = initExpandedTreeItem(appListModel.file.getName());
    runnableTitleItem.getChildren().add(runnableChildItem);
  }

  public void showDetail() {
    introduction.setVisible(false);
    introduction.setManaged(false);
    detailContent.setVisible(true);
    detailContent.setManaged(true);
    // 高度自适应
    appFileListView.prefHeightProperty().bind(
        Bindings.subtract(
            rightScrollPane.heightProperty(),
            appDetailPane.getBoundsInLocal().getHeight() + appUninstallBtn.getBoundsInLocal().getHeight()
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
