package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class AppInfoController extends BaseController {

  @FXML
  private ScrollPane rightScrollableList;
  @FXML
  private Pane appDetailInfo;
  @FXML
  private VBox appContent;
  @FXML
  private TreeView<Text> appFileListView;
  @FXML
  private TreeItem<Text> appFileListItem;

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



    // app info
    HBox appTitle = new HBox(10);
    Text appIcon = new Text("图标");

    VBox appTitleInfo = new VBox(10);
    Text appName = new Text("名称");
    Text appSize = new Text("大小");
    appTitleInfo.getChildren().addAll(appName, appSize);

    appTitle.getChildren().addAll(appIcon, appTitleInfo);

    appContent.getChildren().add(appTitle);




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
