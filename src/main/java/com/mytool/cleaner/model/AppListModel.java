package com.mytool.cleaner.model;

import java.util.Date;

/**
 * App列表信息模型
 */
public class AppListModel {

  // 应用名称
  public String name;
  // 中文名称
  public String chineseName;
  // 英文名称
  public String englishName;
  // 应用大小
  public String size;
  // 安装时间
  public Date installTime;
  // 其他详细信息
  public AppDetailModel detailModel = new AppDetailModel();

}
