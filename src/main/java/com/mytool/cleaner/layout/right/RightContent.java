package com.mytool.cleaner.layout.right;

import com.mytool.cleaner.model.AppDetailModel;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

public class RightContent {

  private static final VBox RIGHT_GROUP_LIST = new VBox(10);

  // App info
  private static final Pane APP_PANE = new Pane();
  private static final VBox APP_CONTENT = new VBox(10);
  private static final AppDetailModel APP_INFO = new AppDetailModel();


  // App file list
  private static final TreeItem<Text> RIGHT_TREE_LIST = initExpandedTreeItem("详细文件列表");
  private static final TreeView<Text> RIGHT_TREE_VIEW = new TreeView<>(RIGHT_TREE_LIST);

  public static final ScrollPane RIGHT_SCROLLABLE_LIST = new ScrollPane(RIGHT_GROUP_LIST);

  static {
    // 绑定TreeView的高度属性到ScrollPane的高度属性，然后减去Text节点的高度
    RIGHT_TREE_VIEW.prefHeightProperty().bind(
        Bindings.subtract(
            RIGHT_SCROLLABLE_LIST.heightProperty(),
            APP_PANE.getBoundsInLocal().getHeight()
        )
    );

    APP_PANE.getChildren().add(APP_CONTENT);

    // 填充VBox子节点
    RIGHT_GROUP_LIST.getChildren().add(APP_PANE);
    RIGHT_GROUP_LIST.getChildren().add(RIGHT_TREE_VIEW);

    RIGHT_SCROLLABLE_LIST.setFitToWidth(true);
    RIGHT_SCROLLABLE_LIST.setFitToHeight(true);
    RIGHT_SCROLLABLE_LIST.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

    // app info
    HBox appTitle = new HBox(10);
    Text appIcon = new Text("图标");

    VBox appTitleInfo = new VBox(10);
    Text appName = new Text("名称");
    Text appSize = new Text("大小");
    appTitleInfo.getChildren().addAll(appName, appSize);

    appTitle.getChildren().addAll(appIcon, appTitleInfo);

    APP_CONTENT.getChildren().add(appTitle);


    // app file list
    RIGHT_TREE_LIST.setExpanded(true);
    ObservableList<TreeItem<Text>> titleGroup = RIGHT_TREE_LIST.getChildren();

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

  private static TreeItem<Text> initTreeItem(String title, boolean expanded) {
    Text text = new Text(title);
    TreeItem<Text> item = new TreeItem<>(text);
    item.setExpanded(expanded);
    return item;
  }

  private static TreeItem<Text> initClumpedTreeItem(String title) {
    return initTreeItem(title, false);
  }

  private static TreeItem<Text> initExpandedTreeItem(String title) {
    return initTreeItem(title, true);
  }

}
