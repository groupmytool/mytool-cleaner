package com.mytool.cleaner.utils;

import org.junit.jupiter.api.Test;

class FileMetaDataTest {

  @Test
  void getFileMetaData() {

    FileMetaData FileMetaData = new FileMetaData();
    String metadata = FileMetaData.getMetadata("/Applications/IntelliJ IDEA CE.app");
    System.out.println(metadata);

  }
}