package com.mytool.cleaner.view.main;

import javafx.scene.Parent;
import javafx.scene.Scene;

import static com.mytool.cleaner.utils.base.Constants.LAYOUT_DEFAULT_HEIGHT;
import static com.mytool.cleaner.utils.base.Constants.LAYOUT_DEFAULT_WIDTH;

public class MainScene extends Scene {

  public MainScene(Parent root) {
    super(root, LAYOUT_DEFAULT_WIDTH, LAYOUT_DEFAULT_HEIGHT);
    this.getStylesheets().add(getClass().getResource("/css/scroll-bar.css").toExternalForm());
  }

}
