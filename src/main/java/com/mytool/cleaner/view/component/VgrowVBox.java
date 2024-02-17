package com.mytool.cleaner.view.component;

import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class VgrowVBox extends VBox {

  public VgrowVBox(Pos pos) {
    super();
    VBox.setVgrow(this, Priority.ALWAYS);
    this.setAlignment(pos);
  }

  public VgrowVBox(Pos pos, boolean visible) {
    super();
    VBox.setVgrow(this, Priority.ALWAYS);
    this.setAlignment(pos);
    this.setVisible(visible);
    this.setManaged(visible);
  }

}
