package com.mytool.cleaner.view.diskspace;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

public class DiskSpaceRootScrollPane extends ScrollPane {
  public DiskSpaceRootScrollPane() {
    super();
    this.setFitToWidth(true);
    this.setFitToHeight(true);
    this.setPadding(new Insets(5, 5, 5, 5));
  }
}
