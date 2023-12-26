package com.mytool.cleaner.layout;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftMenu {
  public static final VBox LEFT_MENU_LIST = new VBox(10);
  public static final Button LEFT_UNINSTALL = initButton("应用卸载");
  public static final Button LEFT_DISK_SPACE = initButton("空间透视");

  static {
    LEFT_MENU_LIST.setAlignment(Pos.TOP_CENTER); // 设置VBox的对齐方式为居中
    ObservableList<Node> list = LEFT_MENU_LIST.getChildren();
    list.add(LEFT_UNINSTALL);
    list.add(LEFT_DISK_SPACE);
  }

  private static Button initButton(String text) {
    Button button = new Button(text);
    button.setPrefSize(120, 80);
    return button;
  }

}
