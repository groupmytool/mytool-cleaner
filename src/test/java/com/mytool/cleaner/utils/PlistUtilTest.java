package com.mytool.cleaner.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

class PlistUtilTest {

  String filePath = System.getProperty("user.dir") + File.separator
      + "src" + File.separator
      + "test" + File.separator
      + "resources" + File.separator
      + "Info.plist";

  private static void foreachCheck(Object val) {
    if (val instanceof String) {

    } else if (val instanceof Boolean) {

    } else if (val instanceof Integer) {

    } else if (val instanceof HashMap) {
      for (Object o : ((HashMap) val).keySet()) {
        foreachCheck(((HashMap) val).get(o));
      }
    } else if (val.getClass().isArray()) {
      for (Object o : (Object[]) val) {
        foreachCheck(o);
      }
    } else {
      System.err.println("未知类型：" + val.getClass());
    }
  }

  @Test
  void readPlist() {
    try {
      HashMap<String, Object> parameters = PlistUtil.readPlist(filePath);
      Set<String> keySet = parameters.keySet();
      for (String key : keySet) {
        foreachCheck(parameters.get(key));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}