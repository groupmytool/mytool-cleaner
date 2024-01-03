package com.mytool.cleaner.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppInfoController {

  @FXML
  private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
  }

}
