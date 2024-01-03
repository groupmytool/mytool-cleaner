package com.mytool.cleaner.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MenuController {

  @FXML
  private VBox menuContainer;

  public void initialize() {
    try {
      VBox menuView = FXMLLoader.load(getClass().getResource("/com/mytool/cleaner/views/left/menu-view.fxml"));
      VBox appListView = FXMLLoader.load(getClass().getResource("/com/mytool/cleaner/views/middle/app-list-view.fxml"));
      VBox appInfoView = FXMLLoader.load(getClass().getResource("/com/mytool/cleaner/views/right/app-info-view.fxml"));
      menuContainer.getChildren().addAll(menuView, appListView, appInfoView);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
