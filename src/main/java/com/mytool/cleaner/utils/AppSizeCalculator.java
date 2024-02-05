package com.mytool.cleaner.utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class AppSizeCalculator {
  public static void main(String[] args) {
    Path appPath = Path.of("/Applications/App Cleaner 8.app");

    FileMetaData fileMetaData = new FileMetaData();
    String metadata = fileMetaData.getMetadata("/Applications/App Cleaner 8.app");
    System.out.println(metadata);


    try {
      long appSize = calculateAppSize(appPath);
      double appSizeInMB = (double) appSize / 1048576; // Convert bytes to MB
      System.out.println("App size: " + appSizeInMB + " MB");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static long calculateAppSize(Path appPath) throws IOException {
    AppSizeVisitor visitor = new AppSizeVisitor();
    Files.walkFileTree(appPath, visitor);
    return visitor.getTotalSize();
  }

  private static class AppSizeVisitor extends SimpleFileVisitor<Path> {
    private long totalSize = 0;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
      totalSize += attrs.size();
      return FileVisitResult.CONTINUE;
    }

    public long getTotalSize() {
      return totalSize;
    }
  }
}