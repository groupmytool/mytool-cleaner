package com.mytool.cleaner.utils;

public class FileMetaData {
  static {
    System.load("/Users/adolphor/IdeaProjects/javafx/mytool-cleaner/lib/native/libFileMetaData.dylib");
  }
  public native String getMetadata(String path);
}