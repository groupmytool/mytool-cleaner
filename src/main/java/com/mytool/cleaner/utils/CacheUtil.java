package com.mytool.cleaner.utils;

import java.io.File;

import static com.mytool.cleaner.utils.Constants.VENDOR;

public class CacheUtil {

  public static final String CACHE_PATH = STR."\{System.getProperty("user.home")}/Library/Caches/\{VENDOR}";
  public static final String ICNS_CACHE_PATH = STR."\{CACHE_PATH}/icns";

  public static boolean filePathCheck(String pathName) {
    File file = new File(pathName);
    if (file.exists()) {
      return true;
    }
    return false;
  }

  public static boolean filePathCheckAndCreate(String pathName) {
    File file = new File(pathName);
    if (file.exists()) {
      return true;
    }
    return file.mkdirs();
  }

}