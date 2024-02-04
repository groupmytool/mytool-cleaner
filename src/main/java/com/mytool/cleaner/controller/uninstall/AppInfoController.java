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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.util.HashMap;

import static com.mytool.cleaner.utils.CacheUtil.CACHE_PATH;
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

  @Override
  public void initialize() throws IOException {

    // app file list
    appFileListItem.setExpanded(true);
    ObservableList<TreeItem<Text>> titleGroup = appFileListItem.getChildren();

    String appPathName = "/Applications/IntelliJ IDEA CE.app";
    File file = new File(appPathName);

    System.out.println(file);

    BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
    System.out.println("File creation time: " + attrs.creationTime());

    // 解析"/Applications/IntelliJ IDEA CE.app"应用plist文件，获取：应用名称、版本号、大小、安装时间、更新时间、开发者、签名、是否是64位应用、是否是沙盒
    try {
      HashMap<String, Object> plist = PlistUtil.readPlist(appPathName + "/Contents/Info.plist");

      String sourceIcons = appPathName + "/Contents/Resources/idea.icns";

      filePathCheckAndCreate(CACHE_PATH);
      String outPath = CACHE_PATH + File.separator + "idea.png";

      IconUtil.transform(sourceIcons, "png", outPath);

      Image icon = new Image("file:" + outPath);
      appIcon.setImage(icon);
      appName.setText(plist.get("CFBundleName").toString());
      appVersion.setText(plist.get("CFBundleShortVersionString").toString());

      // 高度自适应
      appFileListView.prefHeightProperty().bind(
          Bindings.subtract(
              rightScrollableList.heightProperty(),
              appDetailPane.getBoundsInLocal().getHeight()
          )
      );

      plist.get("CFBundleIdentifier").toString();

    } catch (ParseException e) {
      throw new RuntimeException(e);
    } catch (ParserConfigurationException e) {
      throw new RuntimeException(e);
    } catch (SAXException e) {
      throw new RuntimeException(e);
    }

    TreeItem<Text> runnableTitleItem = initExpandedTreeItem("可执行文件");
    titleGroup.add(runnableTitleItem);

    TreeItem<Text> runnableChildItem = initExpandedTreeItem(file.getName());
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
