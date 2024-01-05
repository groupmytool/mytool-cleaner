package com.mytool.cleaner.utils;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListFormatException;
import com.dd.plist.PropertyListParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class PlistUtil {

  public static HashMap<String, Object> readPlist(String filePath) throws IOException, ParseException, ParserConfigurationException, SAXException {
    try {
      File file = new File(filePath);
      NSDictionary rootDict = (NSDictionary) PropertyListParser.parse(file);
      return (HashMap<String, Object>) rootDict.toJavaObject();
    } catch (PropertyListFormatException e) {
      throw new ParseException(e.getMessage(), -1);
    }
  }

  public static Object getSpecificSingleNode(HashMap<String, Object> nodes, String key) {
    String[] split = key.split("\\.");
    int i = 0;
    Object node;
    while (true) {
      node = nodes.get(split[i++]);
      if (i == split.length) {
        return node;
      } else if (node instanceof HashMap hmo) {
        nodes = hmo;
      } else {
        return null;
      }
    }
  }

}
