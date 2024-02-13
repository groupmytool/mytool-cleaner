package com.mytool.cleaner.service.uninstall;

import com.mytool.cleaner.model.AppListModel;
import com.mytool.cleaner.utils.AppSizeCalculator;
import com.mytool.cleaner.utils.IconUtil;
import com.mytool.cleaner.utils.PlistUtil;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class AppInfoService {

  public static void setAppIcon(ImageView appIcon,HashMap<String,Object> plist, AppListModel appListModel) throws FileNotFoundException {
    // 应用图标
    String icnsName;
    if (plist != null) {
      icnsName = plist.getOrDefault("CFBundleIconFile", "AppIcon").toString();
    } else {
      icnsName = "AppIcon";
    }
    String sourceIcons = "%s/Contents/Resources/%s".formatted(appListModel.file.getAbsolutePath(), icnsName);
    appIcon.setImage(IconUtil.getIcon(sourceIcons, appListModel.name));
  }

  public static void setAppName(Text appName, AppListModel appListModel){
    appName.setText(appListModel.name);
  }


  public static void setAppSize(Text appSize, AppListModel appListModel) throws IOException {
    long sizeKb = AppSizeCalculator.calculateAppSize(appListModel.file.toPath());
    String sizeText;
    double sizeMb = sizeKb / 1000.0 / 1000.0;
    if (sizeMb < 1000.0) {
      sizeText = String.format("%.1f MB", sizeMb);
    } else {
      sizeText = String.format("%.2f GB", sizeMb / 1000.0);
    }
    appSize.setText(sizeText);
  }

  private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());
  public static void setCreateTime(Text appInstallTime, AppListModel appListModel) throws IOException {
    BasicFileAttributes attr = Files.readAttributes(appListModel.file.toPath(), BasicFileAttributes.class);
    appInstallTime.setText(formatter.format(attr.creationTime().toInstant()));
  }

}
