package com.mytool.cleaner.view.menu;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MenuButton extends Button {

  public MenuButton(String text, EventHandler<MouseEvent> event) {
    super();
    this.setText(text);
    this.setPrefWidth(Double.MAX_VALUE);
    this.setPrefHeight(80);
    this.setOnMouseClicked(event);
    this.setOnMouseClicked(event);
  }

}
