package com.mytool.cleaner.view.uninstall.list;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_WIDTH;

public class AppListRootScrollPane extends ScrollPane {

  private static final Double MIN_WIDTH = LAYOUT_MIN_WIDTH * 0.2;
  private static final Double MAX_WIDTH = LAYOUT_MIN_WIDTH * 0.7;

  public AppListRootScrollPane() {
    super();
    this.setMinWidth(MIN_WIDTH);
    this.setMaxWidth(MAX_WIDTH);
    this.setPrefWidth(MIN_WIDTH);
    this.setPadding(new Insets(5, 5, 5, 5));
    // 最大化可用空间 & 子元素自适应宽度和高度
    this.setFitToWidth(true);
    this.setFitToHeight(true);
  }
}
