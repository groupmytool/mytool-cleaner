package com.mytool.cleaner.utils.base;

public enum EnumListFile {

  APP_VERSION("应用版本", "version"),
  APP_IDENTIFIER("应用标志符", "identifier"),
  APP_RUNNABLE("可执行文件", "runnable"),
  APP_SUPPORT("应用程序支持", "support", Constants.SUPPORT_PATH),
  APP_CACHE("应用程序缓存", "cache", Constants.CACHE_PATH),
  APP_LOG("应用程序日志", "log", Constants.LOG_PATH),
  APP_PREFERENCE("应用程序偏好设置", "preference"),
  APP_FILE_TYPE("支持的文稿类型", "fileType"),
  ;

  private final String appDisplayName;
  private final String appConfigName;
  private final String appDefaultPath;

  EnumListFile(String appDisplayName, String appConfigName) {
    this.appDisplayName = appDisplayName;
    this.appConfigName = appConfigName;
    this.appDefaultPath = null;
  }

  EnumListFile(String appDisplayName, String appConfigName, String appDefaultPath) {
    this.appDisplayName = appDisplayName;
    this.appConfigName = appConfigName;
    this.appDefaultPath = appDefaultPath;
  }

  public String getAppConfigName() {
    return appConfigName;
  }

  public String getAppDisplayName() {
    return appDisplayName;
  }

  public String getAppDefaultPath() {
    return appDefaultPath;
  }

}
