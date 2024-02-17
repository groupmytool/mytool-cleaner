package com.mytool.cleaner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.mytool.cleaner.utils.base.Constants.LAYOUT_DEFAULT_HEIGHT;
import static com.mytool.cleaner.utils.base.Constants.LAYOUT_DEFAULT_WIDTH;
import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_HEIGHT;
import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_WIDTH;

public class CleanerApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/main.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), LAYOUT_DEFAULT_WIDTH, LAYOUT_DEFAULT_HEIGHT);
    scene.getStylesheets().add(getClass().getResource("/css/scroll-bar.css").toExternalForm());
    stage.setTitle("麦图卸载");
    stage.setScene(scene);
    stage.setMinWidth(LAYOUT_MIN_WIDTH);
    stage.setMinHeight(LAYOUT_MIN_HEIGHT);
    stage.show();
  }

}