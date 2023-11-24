package com.mytool.cleaner;

import com.mytool.cleaner.layout.main.MainLayout;
import javafx.application.Application;
import javafx.stage.Stage;

public class CleanerApp extends Application {

  @Override
  public void start(Stage stage) {
    stage.setScene(MainLayout.init());
    stage.setMinWidth(MainLayout.minWidth);
    stage.setMinHeight(MainLayout.minHeight);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}