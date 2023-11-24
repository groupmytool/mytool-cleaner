package com.mytool.cleaner.util;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

public class ComponentUtil {

  /**
   * 设置一个或多个组件的大小和背景颜色
   *
   * @param prefWidth       用于设置组件的宽
   * @param prefHeight      用于设置组件的长
   * @param backgroundColor 用于设置的背景颜色
   * @param controls        一个或多个组件，如果没有，那么该语句相当于不存在
   */
  public static void setComponentSizeAndColor(double prefWidth, double prefHeight, Color backgroundColor, Control... controls) {
    setComponentSize(prefWidth, prefHeight, controls);
    setComponentColor(backgroundColor, controls);
  }

  /**
   * 设置一个或多个组件的大小
   *
   * @param prefWidth  用于设置组件的宽
   * @param prefHeight 用于设置组件的长
   * @param regions    一个或多个组件，如果没有，那么该语句相当于不存在
   */
  public static void setComponentSize(double prefWidth, double prefHeight, Region... regions) {
    for (int i = 0; i < regions.length; i++) {
      if (regions[i] instanceof Label) {
        ((Label) regions[i]).setAlignment(Pos.CENTER);//设置字体居中
      } else if (regions[i] instanceof Button)
        regions[i].setPrefSize(prefWidth, prefHeight);
    }
  }

  /**
   * 设置一个或多个组件的背景颜色
   *
   * @param backgroundColor 用于设置的背景颜色
   * @param regions         一个或多个组件，如果没有，那么该语句相当于不存在
   */
  public static void setComponentColor(Color backgroundColor, Region... regions) {
    for (int i = 0; i < regions.length; i++) {
      regions[i].setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
    }
  }

}
