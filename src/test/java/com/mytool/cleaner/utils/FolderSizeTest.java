package com.mytool.cleaner.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileMetaDataTest {

  @Test
  void getFileMetaData() {

    FileMetaData FileMetaData = new FileMetaData();
    String FileMetaData1 = FileMetaData.getMetadata("/Applications/App Cleaner 8.app");
    System.out.println(FileMetaData1);

  }
}