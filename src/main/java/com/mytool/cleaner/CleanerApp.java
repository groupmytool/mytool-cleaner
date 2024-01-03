package com.mytool.cleaner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.mytool.cleaner.utils.Constants.LAYOUT_MIN_HEIGHT;
import static com.mytool.cleaner.utils.Constants.LAYOUT_MIN_WIDTH;

public class CleanerApp extends Application {

  @Override
  public void start(Stage stage) throws IOException {
//    FXMLLoader fxmlLoader = new FXMLLoader(CleanerApp.class.getResource("main-view.fxml"));
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mytool/cleaner/views/main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.setMinWidth(LAYOUT_MIN_WIDTH);
    stage.setMinHeight(LAYOUT_MIN_HEIGHT);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

}