package com.mytool.cleaner.utils.file;

import javafx.scene.text.Font;

class FontUtilTest {

  public static void main(String[] args) {
    System.out.println("Font Families:");
    for (String f : Font.getFamilies()) {
      System.out.println(f);
    }

    System.out.println("\n=========================\n");

    System.out.println("Font Names:");
    for (String f : Font.getFontNames()) {
      System.out.println(f);
    }

  }

}