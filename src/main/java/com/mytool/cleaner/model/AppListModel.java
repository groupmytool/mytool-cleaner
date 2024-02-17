package com.mytool.cleaner.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class AppListModel {
  private File file;
  // 基本信息
  private String icon;
  private String name;
  private String installTime;
  private String size;
  // 文件列表
  private HashMap<String, HashMap<String, ArrayList<AppListFileModel>>> files;

}
