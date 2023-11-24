package com.mytool.cleaner.layout.left;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftMenu {

  public static VBox getMenu() {
    VBox vBox = new VBox(10);
    ObservableList<Node> list = vBox.getChildren();
    list.add(new Button("应用卸载"));
    list.add(new Button("空间透视"));
    return vBox;
  }


}
