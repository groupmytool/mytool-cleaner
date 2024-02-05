package com.mytool.cleaner.utils;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class AppSizeCalculator {

  /**
   * 计算给定应用的大小
   *
   * @param appPath 应用的路径
   */
  public static long calculateAppSize(Path appPath) throws IOException {
    AppSizeVisitor visitor = new AppSizeVisitor();
    Files.walkFileTree(appPath, visitor);
    return visitor.getTotalSize();
  }

  /**
   * A {@link SimpleFileVisitor} that calculates the total size of all files visited.
   */
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