package com.mytool.cleaner.utils;

import java.io.File;

import static com.mytool.cleaner.utils.Constants.VENDOR;

public class CacheUtil {

  public static final String CACHE_PATH = "%s/Library/Caches/%s".formatted(System.getProperty("user.home"), VENDOR);
  public static final String ICNS_CACHE_PATH = "%s/icns".formatted(CACHE_PATH);

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