package com.mytool.cleaner;

import com.mytool.cleaner.controller.MainController;
import com.mytool.cleaner.utils.log.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_HEIGHT;
import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_WIDTH;

public class CleanerApp extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("麦图卸载");
    MainController controller = new MainController();
    stage.setScene(controller.getScene());
    stage.setMinWidth(LAYOUT_MIN_WIDTH);
    stage.setMinHeight(LAYOUT_MIN_HEIGHT);
    stage.show();
    Logger.init(System.getProperty("user.home") + "/logs/cleaner", true);
    Logger.debug("Application started");
  }

}