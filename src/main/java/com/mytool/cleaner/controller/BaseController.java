package com.mytool.cleaner.controller;

import java.io.IOException;

import static com.mytool.cleaner.utils.base.Constants.LAYOUT_MIN_WIDTH;

public class BaseController {

  public double getMaxValue() {
    return Double.MAX_VALUE;
  }

  // 菜单模块固定宽度
  public double getMenuWidth() {
    return LAYOUT_MIN_WIDTH * 0.2;
  }

  // 列表最小宽度
  public double getListMinWidth() {
    return LAYOUT_MIN_WIDTH * 0.2;
  }

  // 列表最大宽度
  public double getListMaxWidth() {
    return LAYOUT_MIN_WIDTH * 0.7;
  }

  public void initialize() throws IOException {
  }

}
