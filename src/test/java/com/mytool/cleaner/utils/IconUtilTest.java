package com.mytool.cleaner.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Comparator;

import static com.mytool.cleaner.utils.Constants.ROOT_PATH;

class IconUtilTest {

  @Test
  void transform() {
  }

  @Test
  void findIcons() throws IOException {
    File applicationsDir = new File(ROOT_PATH);
    File[] apps = applicationsDir.listFiles();
    Arrays.stream(apps).sorted(Comparator.comparing(File::getName));
    for (File app : apps) {
      if (app.isDirectory() && app.getName().endsWith(".app")) {
        System.out.println(app.getAbsolutePath());
        File[] content = app.listFiles();
        for (File con : content) {
          File[] resource = con.listFiles((dir, name) -> name.equals("Resources"));
          for (File res : resource) {
            File[] icons = res.listFiles((dir, name) -> name.endsWith(".icns"));
            for (File icon : icons) {
              BasicFileAttributes attrs = Files.readAttributes(icon.toPath(), BasicFileAttributes.class);
              String size = String.format("%.1f MB", attrs.size() / 1000.0 / 1000.0);
              System.out.println("\t\t %s MB => %s".formatted(size, icon.getName()));
            }
          }
        }

      }
    }
  }
}