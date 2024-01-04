package com.mytool.cleaner.controller.diskspace;

import com.mytool.cleaner.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

public class DiskListController extends BaseController {

  @FXML
  private VBox diskList;

  @FXML
  protected void onHelloButtonClick() {
    System.out.println(diskList);
  }

  public void initialize(){
    File applicationsDir = new File("/Applications");
    File[] files = applicationsDir.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory() && file.getName().endsWith(".app")) {
          Button appNode = new Button();
          BorderPane appButtonGraphic = new BorderPane();
          appButtonGraphic.setTop(new Text(file.getName()));
          appNode.setGraphic(appButtonGraphic);
          appNode.setMaxWidth(Double.MAX_VALUE);
          diskList.getChildren().add(appNode);
        }
      }
    } else {
      System.out.println("No applications found.");
    }
  }

}
