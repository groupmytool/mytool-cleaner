package com.mytool.cleaner.layout;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftMenu {
  public static final VBox LEFT_MENU_LIST = new VBox(10);
  public static final Button LEFT_UNINSTALL = new Button("应用卸载");
  public static final Button LEFT_DISK_SPACE = new Button("空间透视");

  static {
    ObservableList<Node> list = LEFT_MENU_LIST.getChildren();
    list.add(LEFT_UNINSTALL);
    list.add(LEFT_DISK_SPACE);
  }

}
