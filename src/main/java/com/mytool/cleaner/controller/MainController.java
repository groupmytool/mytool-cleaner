package com.mytool.cleaner.controller;

import com.mytool.cleaner.controller.menu.MenuController;
import com.mytool.cleaner.view.component.HgrowSplitPane;
import com.mytool.cleaner.view.main.MainScene;
import com.mytool.cleaner.view.main.MainWindow;
import javafx.scene.Scene;

/**
 * 首页布局控制器
 */
public class MainController {

  public MainController(){

  }

  public Scene getScene() {
    HgrowSplitPane hgrowSplitPane = new HgrowSplitPane();
    // 主窗口
    MainWindow root = new MainWindow();
    // 菜单栏
    MenuController menu = new MenuController(hgrowSplitPane);
    root.getChildren().add(menu.getView());
    // 右侧内容栏
    root.getChildren().add(hgrowSplitPane);
    return new MainScene(root);
  }

}
