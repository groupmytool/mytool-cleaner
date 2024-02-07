package com.mytool.cleaner.utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class DOMTest {


  static String filePath = System.getProperty("user.dir") + File.separator
      + "src" + File.separator
      + "test" + File.separator
      + "resources" + File.separator
      + "Info.plist";

  public static void main(String[] args) throws IOException, ParseException, ParserConfigurationException, SAXException {
    HashMap<String, Object> map = PlistUtil.readPlist(filePath);
    for (String key : map.keySet()) {
      System.out.println(key + " : " + map.get(key));
    }
  }






}
