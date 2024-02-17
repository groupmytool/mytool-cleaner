package com.mytool.cleaner.view.component;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * 用于HBox的子节点的SplitPane
 */
public class HgrowSplitPane extends SplitPane {
  public HgrowSplitPane() {
    super();
    // 设置HBox.hgrow="ALWAYS"，占用HBox剩余所有空间
    HBox.setHgrow(this, Priority.ALWAYS);
  }
}
