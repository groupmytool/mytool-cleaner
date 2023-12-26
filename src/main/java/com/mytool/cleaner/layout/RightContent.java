package com.mytool.cleaner.layout;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class RightContent {
  public static final VBox RIGHT_GROUP_LIST = new VBox(10);

  public static final ScrollPane RIGHT_SCROLLABLE_LIST = new ScrollPane(RIGHT_GROUP_LIST);

  static {
    RIGHT_SCROLLABLE_LIST.setFitToWidth(true);
    RIGHT_SCROLLABLE_LIST.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

    ObservableList<Node> groupListChildren = RIGHT_GROUP_LIST.getChildren();
  }

}
