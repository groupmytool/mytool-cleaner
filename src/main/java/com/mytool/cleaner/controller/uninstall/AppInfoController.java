package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.utils.IconUtil;
import com.mytool.cleaner.utils.PlistUtil;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.text.ParseException;
import java.util.HashMap;

import static com.mytool.cleaner.utils.CacheUtil.CACHE_PATH;
import static com.mytool.cleaner.utils.CacheUtil.filePathCheckAndCreate;

public class AppInfoController extends BaseController {

  @FXML
  private ScrollPane rightScrollableList;
  @FXML
  private HBox appDetailGroup;
  @FXML
  private TreeView<Text> appFileListView;
  @FXML
  private TreeItem<Text> appFileListItem;
  @FXML
  private ImageView appIcon;

  @FXML
  protected void onHelloButtonClick() {
    System.out.println(this);
  }

  @Override
  public void initialize() throws IOException {
    appFileListView.prefHeightProperty().bind(
        Bindings.subtract(
            rightScrollableList.heightProperty(),
            appDetailGroup.getBoundsInLocal().getHeight()
        )
    );

    // app file list
    appFileListItem.setExpanded(true);
    ObservableList<TreeItem<Text>> titleGroup = appFileListItem.getChildren();

    String appPathName = "/Applications/IntelliJ IDEA CE.app";
    File file = new File(appPathName);
    System.out.println(file);
    System.out.println(file.isDirectory());

    // 解析"/Applications/IntelliJ IDEA CE.app"应用plist文件，获取：应用名称、版本号、大小、安装时间、更新时间、开发者、签名、是否是64位应用、是否是沙盒
    try {
      HashMap<String, Object> plist = PlistUtil.readPlist(appPathName + "/Contents/Info.plist");
      Object appName = plist.get("CFBundleName");
      Object appVersion = plist.get("CFBundleShortVersionString");
      System.out.println(appName);
      System.out.println(appVersion);
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


    String sourceIcons = appPathName + "/Contents/Resources/idea.icns";

    filePathCheckAndCreate(CACHE_PATH);
    String outPath = CACHE_PATH + File.separator + "idea.png";

    IconUtil.transform(sourceIcons, "png", outPath);

    Image icon = new Image("file:" + outPath);
    appIcon.setImage(icon);

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


  private TreeItem<Text> initTreeItem(String title, boolean expanded) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mytool/cleaner/views/uninstall/app-info-view-file.fxml"));
    TreeItem<Text> item = fxmlLoader.load();
    AppInfoNodeController controller = fxmlLoader.getController();
    controller.setData(title, expanded);
    return item;
  }

  private TreeItem<Text> initClumpedTreeItem(String title) throws IOException {
    return initTreeItem(title, false);
  }

  private TreeItem<Text> initExpandedTreeItem(String title) throws IOException {
    return initTreeItem(title, true);
  }

}
