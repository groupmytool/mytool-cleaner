package com.mytool.cleaner.controller;

import com.mytool.cleaner.controller.uninstall.AppListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * 首页布局控制器
 */
public class MainController extends BaseController {

  @FXML
  public HBox contentPane;

  /**
   * 卸载模块
   */
  @FXML
  protected void onUninstallButtonClick() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mytool/cleaner/views/uninstall/app-list-view.fxml"));
      ScrollPane appList = fxmlLoader.load();
      AppListController controller = fxmlLoader.getController();
      controller.setContentPane(contentPane);
      contentPane.getChildren().setAll(appList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 磁盘分析模块
   */
  @FXML
  protected void onDiskSpaceButtonClick() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mytool/cleaner/views/diskspace/disk-list-view.fxml"));
      ScrollPane diskSpace = fxmlLoader.load();
      contentPane.getChildren().setAll(diskSpace);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
