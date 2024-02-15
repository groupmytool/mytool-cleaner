package com.mytool.cleaner.utils.common;

import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

  public static String formatTime(FileTime fileTime) {
    return formatter.format(fileTime.toInstant());
  }

}
