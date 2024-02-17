package com.mytool.cleaner.view.uninstall.info;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

public class AppInfoRootScrollPane extends ScrollPane {

  public AppInfoRootScrollPane() {
    super();
    this.setPadding(new Insets(5, 5, 5, 5));
    // 最大化可用空间 & 子元素自适应宽度和高度
    this.setFitToWidth(true);
    this.setFitToHeight(true);
  }

}
