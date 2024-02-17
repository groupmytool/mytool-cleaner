package com.mytool.cleaner.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * App列表信息模型
 */
public class AppViewModel {

  private final SimpleStringProperty name = new SimpleStringProperty("未知名称");
  private final SimpleStringProperty icon = new SimpleStringProperty("/images/default-icon.png");
  private final SimpleStringProperty installTime = new SimpleStringProperty("未知时间");
  private final SimpleStringProperty size = new SimpleStringProperty("未知大小");

  public SimpleStringProperty getName() {
    return name;
  }

  public SimpleStringProperty getIcon() {
    return icon;
  }

  public SimpleStringProperty getInstallTime() {
    return installTime;
  }

  public SimpleStringProperty getSize() {
    return size;
  }

}
