package com.mytool.cleaner.controller.module;

import com.mytool.cleaner.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ModuleController extends BaseController {

  @FXML
  private Label welcomeText;

  @FXML
  protected void onUninstallButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
  }

  @FXML
  protected void onDiskSpaceButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
  }

}
