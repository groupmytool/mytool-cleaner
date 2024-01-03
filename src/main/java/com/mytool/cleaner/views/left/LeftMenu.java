package com.mytool.cleaner.views.left;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftMenu {
  public static final VBox LEFT_MENU_LIST = new VBox(10);
  private static final Button LEFT_UNINSTALL = initButton("应用卸载");
  private static final Button LEFT_DISK_SPACE = initButton("空间透视");

  static {
    // 设置VBox的对齐方式为居中
    LEFT_MENU_LIST.setAlignment(Pos.TOP_CENTER);
    // 填充子节点
    ObservableList<Node> list = LEFT_MENU_LIST.getChildren();
    list.add(LEFT_UNINSTALL);
    list.add(LEFT_DISK_SPACE);
  }

  private static Button initButton(String text) {
    Button menuButton = new Button(text);
    // Double.MAX_VALUE来使其自适应父节点的宽度；高度固定80
    menuButton.setPrefSize(Double.MAX_VALUE, 80);
    return menuButton;
  }

}
