package com.mytool.cleaner.controller.menu;

import com.mytool.cleaner.controller.diskspace.DiskSpaceController;
import com.mytool.cleaner.controller.uninstall.AppInfoController;
import com.mytool.cleaner.controller.uninstall.AppListController;
import com.mytool.cleaner.view.component.HgrowSplitPane;
import com.mytool.cleaner.view.menu.MenuButton;
import com.mytool.cleaner.view.menu.MenuScrollPane;
import com.mytool.cleaner.view.menu.MenuVBox;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MenuController {

  private final HgrowSplitPane contentPane;

  /**
   * 卸载模块缓存的视图
   */
  public List<Node> uninstallView = new ArrayList<>();
  /**
   * 磁盘分析模块缓存的视图
   */
  public List<Node> diskSpaceView = new ArrayList<>();

  public MenuController(HgrowSplitPane hgrowSplitPane) {
    this.contentPane = hgrowSplitPane;
  }

  public MenuScrollPane getView() {
    MenuScrollPane menu = new MenuScrollPane();
    MenuVBox menuVBox = new MenuVBox();
    menu.setContent(menuVBox);
    MenuButton uninstallBtn = new MenuButton("应用卸载", appListEventHandler());
    menuVBox.getChildren().add(uninstallBtn);
    MenuButton diskSpaceBtn = new MenuButton("磁盘分析", diskSpaceEventHandler());
    menuVBox.getChildren().add(diskSpaceBtn);
    return menu;
  }

  private EventHandler<MouseEvent> appListEventHandler() {
    return e -> {
      if (uninstallView.isEmpty()) {
        AppInfoController infoController = new AppInfoController();
        AppListController listController = new AppListController(infoController);
        uninstallView.add(listController.getView());
        uninstallView.add(infoController.getView());
        // 设置初始化的分割位置
        contentPane.setDividerPosition(0, 0.3);
      }
      contentPane.getItems().setAll(uninstallView);
    };
  }

  private EventHandler<MouseEvent> diskSpaceEventHandler() {
    return e -> {
      if (diskSpaceView.isEmpty()) {
        DiskSpaceController diskSpaceController = new DiskSpaceController();
        diskSpaceView.add(diskSpaceController.getView());
      }
      contentPane.getItems().setAll(diskSpaceView);
    };
  }

}
