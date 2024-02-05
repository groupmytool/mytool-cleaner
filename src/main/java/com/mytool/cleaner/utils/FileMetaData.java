package com.mytool.cleaner.utils;

/**
 * 通过native方法获取文件元数据
 */
public class FileMetaData {
  static {
    System.loadLibrary("FileMetaData");
  }

  public native String getMetadata(String path);
}