package com.mytool.cleaner.layout;

import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;

public class RightContent {

  private static final Text rootNode = new Text("详细文件列表");
  private static final TreeItem<Text> RIGHT_GROUP_LIST = new TreeItem<>(rootNode);
  private static final TreeView<Text> RIGHT_TREE_VIEW = new TreeView<>(RIGHT_GROUP_LIST); // 创建TreeView对象
  public static final ScrollPane RIGHT_SCROLLABLE_LIST = new ScrollPane(RIGHT_TREE_VIEW); // 将TreeView放入ScrollPane

  static {
    RIGHT_SCROLLABLE_LIST.setFitToWidth(true);
    RIGHT_SCROLLABLE_LIST.setFitToHeight(true);
    RIGHT_SCROLLABLE_LIST.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

    ObservableList<TreeItem<Text>> groupListChildren = RIGHT_GROUP_LIST.getChildren();

    TreeItem<Text> appNode = new TreeItem<>(initNode("App Node"));
    groupListChildren.add(appNode);

    // 创建一个新的TreeItem对象，并添加到appNode的子节点列表中
    TreeItem<Text> subNode = new TreeItem<>(initNode("Sub Node"));
    appNode.getChildren().add(subNode);
  }

  private static Text initNode(String text) {
    return new Text(text);
  }

}
