package com.mytool.cleaner.utils;

import java.io.File;

import static com.mytool.cleaner.utils.Constants.VENDOR;

public class CacheUtil {

  public static final String CACHE_PATH = System.getProperty("user.home") +
      File.separator + "Library" + File.separator + "Caches" + File.separator + VENDOR;

  public static boolean filePathCheckAndCreate(String pathName) {
    File file = new File(pathName);
    if (file.exists()) {
      return true;
    }
    return file.mkdirs();
  }


}