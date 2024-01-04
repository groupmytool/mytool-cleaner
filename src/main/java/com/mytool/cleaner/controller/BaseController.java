package com.mytool.cleaner.controller;

import java.io.IOException;

import static com.mytool.cleaner.utils.Constants.LAYOUT_DEFAULT_WIDTH;
import static com.mytool.cleaner.utils.Constants.LAYOUT_MIN_WIDTH;

public class BaseController {

  public double getMaxValue() {
    return Double.MAX_VALUE;
  }

  public double getMenuWidth() {
    return LAYOUT_MIN_WIDTH * 0.2;
  }

  public double getAppListMinWidth() {
    return LAYOUT_MIN_WIDTH * 0.3;
  }

  public double getAppListMaxWidth() {
    return LAYOUT_DEFAULT_WIDTH * 0.5;
  }

  public void initialize() throws IOException {
  }

}
