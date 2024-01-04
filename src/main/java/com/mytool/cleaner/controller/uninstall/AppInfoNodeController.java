package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;

public class AppInfoNodeController extends BaseController {

  @FXML
  private TreeItem<Text> fileListItem;
  @FXML
  private Text fileGroupTitle;

  public void setData(String fileGroupTitle, Boolean expanded) {
    this.fileListItem.setExpanded(expanded);
    this.fileGroupTitle.setText(fileGroupTitle);
  }

}
