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

/**
 * Info.plist 解析工具，当前只解析第一层内容，因为用不到其他内容，暂不支持递归解析
 */
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


}
