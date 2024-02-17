package com.mytool.cleaner.view.uninstall.info;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class AppUninstallButton extends HBox {

  public AppUninstallButton() {
    super();
    // 先渲染组件
    Button button = new Button();
    this.getChildren().add(button);
    // HBox样式
    this.setPadding(new Insets(15.0, 0, 15.0, 0));
    this.setAlignment(Pos.CENTER);
    // Button样式
    button.setText("卸载");
    button.setMinWidth(200);
    button.setMinHeight(40);
    button.setStyle("-fx-background-radius: 15;");
  }

}
