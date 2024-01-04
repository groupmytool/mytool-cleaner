package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class AppInfoController extends BaseController {

  @FXML
  private ScrollPane rightScrollableList;
  @FXML
  private HBox appDetailInfo;
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
            appDetailInfo.getBoundsInLocal().getHeight()
        )
    );

    // app file list
    appFileListItem.setExpanded(true);
    ObservableList<TreeItem<Text>> titleGroup = appFileListItem.getChildren();

    TreeItem<Text> runnableTitleItem = initExpandedTreeItem("可执行文件");
    titleGroup.add(runnableTitleItem);
    TreeItem<Text> runnableChildItem = initExpandedTreeItem("IntelliJ IDEA CE.app");
    runnableTitleItem.getChildren().add(runnableChildItem);


    TreeItem<Text> supportTitleItem = initExpandedTreeItem("应用程序支持");
    titleGroup.add(supportTitleItem);
    File file = new File("/Applications/IntelliJ IDEA CE.app");
    System.out.println(file);
    System.out.println(file.isDirectory());

    // set appIcon image
    //    appIcon.setImage(new Image("file:/Users/adolphor/Downloads/idea.png"));
    String path = "/Users/adolphor/Library/Mobile Documents/com~apple~CloudDocs/gitRepository/javafx/mytool-cleaner/src/main/resources/images";
    Image icon = new Image("file:" + path + "/idea.png");
    appIcon.setImage(icon);
    //    appIcon.setImage(new Image("file:" + path + "/idea.icns"));

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
