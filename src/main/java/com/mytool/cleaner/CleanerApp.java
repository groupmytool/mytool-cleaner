package com.mytool.cleaner;

import com.mytool.cleaner.layout.MainLayout;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.mytool.cleaner.util.Constants.LAYOUT_MIN_HEIGHT;
import static com.mytool.cleaner.util.Constants.LAYOUT_MIN_WIDTH;

public class CleanerApp extends Application {

  @Override
  public void start(Stage stage) {
    stage.setScene(MainLayout.init());
    stage.setMinWidth(LAYOUT_MIN_WIDTH);
    stage.setMinHeight(LAYOUT_MIN_HEIGHT);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}