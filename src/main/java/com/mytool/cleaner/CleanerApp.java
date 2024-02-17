package com.mytool.cleaner;

import com.mytool.cleaner.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_HEIGHT;
import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_WIDTH;

public class CleanerApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    stage.setTitle("麦图卸载");
    MainController controller = new MainController();
    stage.setScene(controller.getScene());
    stage.setMinWidth(LAYOUT_MIN_WIDTH);
    stage.setMinHeight(LAYOUT_MIN_HEIGHT);
    stage.show();
  }

}