package com.mytool.cleaner.layout.main;

import com.mytool.cleaner.layout.left.LeftMenu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * 主布局
 */
public class MainLayout {

  public static final int minWidth = 640;
  public static final int minHeight = 480;

  public static Scene init() {

    BorderPane borderPane = new BorderPane();

    Label topLabel = new Label("Top");
    BorderPane.setAlignment(topLabel, Pos.CENTER);
    borderPane.setTop(topLabel);

    Label bottomLabel = new Label("Bottom");
    BorderPane.setAlignment(bottomLabel, Pos.CENTER);
    borderPane.setBottom(bottomLabel);

    VBox menu = LeftMenu.getMenu();
    BorderPane.setAlignment(menu, Pos.CENTER);
    BorderPane.setMargin(menu, new Insets(5));
    borderPane.setLeft(menu);

    Label rightLabel = new Label("Right");
    BorderPane.setAlignment(rightLabel, Pos.CENTER);
    borderPane.setRight(rightLabel);

    Label centerLabel = new Label("Center");
    BorderPane.setAlignment(centerLabel, Pos.CENTER);
    borderPane.setCenter(centerLabel);

    return new Scene(borderPane, minWidth, minHeight);

  }

}
