package com.mytool.cleaner.controller;

import com.mytool.cleaner.controller.uninstall.AppInfoController;
import com.mytool.cleaner.controller.uninstall.AppListController;
import com.mytool.cleaner.utils.file.AppConfigParser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页布局控制器
 */
public class MainController extends BaseController {

  @FXML
  public SplitPane contentPane;

  /**
   * 卸载模块缓存的视图
   */
  public List<Node> uninstallView = new ArrayList<>();
  /**
   * 磁盘分析模块缓存的视图
   */
  public List<Node> diskSpaceView = new ArrayList<>();

  /**
   * 卸载模块
   */
  @FXML
  protected void onUninstallButtonClick() {
    if (uninstallView.isEmpty()) {
      try {
        AppConfigParser.parseAppConfig();

        FXMLLoader appListFxmlLoader = new FXMLLoader(getClass().getResource("/views/uninstall/app-list-view.fxml"));
        ScrollPane appList = appListFxmlLoader.load();
        uninstallView.add(appList);
        FXMLLoader detailFxmlLoader = new FXMLLoader(getClass().getResource("/views/uninstall/app-detail-view.fxml"));
        ScrollPane appDetail = detailFxmlLoader.load();
        uninstallView.add(appDetail);
        AppInfoController infoController = detailFxmlLoader.getController();
        AppListController appListController = appListFxmlLoader.getController();
        appListController.setAppInfoController(infoController);
        appListController.build();
        // 设置初始化的分割位置
        contentPane.setDividerPosition(0, 0.3);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    contentPane.getItems().setAll(uninstallView);
  }

  /**
   * 磁盘分析模块
   */
  @FXML
  protected void onDiskSpaceButtonClick() {
    if (diskSpaceView.isEmpty()) {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/diskspace/disk-list-view.fxml"));
        ScrollPane diskSpace = fxmlLoader.load();
        diskSpaceView.add(diskSpace);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    contentPane.getItems().setAll(diskSpaceView);
  }

}
