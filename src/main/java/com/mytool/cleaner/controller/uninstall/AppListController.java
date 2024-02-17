package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppViewModel;
import com.mytool.cleaner.view.uninstall.list.AppListRootScrollPane;
import com.mytool.cleaner.view.uninstall.list.AppListVBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.mytool.cleaner.utils.base.Constants.APP_ROOT_PATH;

/**
 * App列表信息控制器。
 * 1、获取/Applications目录下的所有应用
 * 2、获取应用的名称、大小、安装时间等基础信息并存储到listModelList中
 */
public class AppListController extends BaseController {

  // TODO 排序、搜索
  private static final List<AppViewModel> listModelList = new ArrayList<>();

  private final AppInfoController infoController;


  public AppListController(AppInfoController controller) {
    this.infoController = controller;
  }

  public AppListRootScrollPane getView() {
    AppListRootScrollPane pane = new AppListRootScrollPane();
    VBox appList = new AppListVBox();
    pane.setContent(appList);
    File applicationsDir = new File(APP_ROOT_PATH);
    File[] apps = applicationsDir.listFiles();
    if (apps != null) {
      // TODO：使用非主线程、多线程处理
      Arrays.stream(apps).sorted(Comparator.comparing(File::getName));
      for (File app : apps) {
        String appName = app.getName();
        if (app.isDirectory() && appName.endsWith(".app")) {
          // TODO：异步处理
          Button appNode = new Button();
          appList.getChildren().add(appNode);

          appNode.setText(appName);

          appNode.setMaxWidth(Double.MAX_VALUE);
          appNode.setOnMouseClicked(event -> {
            infoController.showDetailContent();
            bindData(infoController.getAppViewModel(), app);
          });
        }
      }
    } else {
      Button appNode = new Button();
      appNode.setText("No apps found");
      appNode.setMaxWidth(Double.MAX_VALUE);
      appList.getChildren().add(appNode);
    }
    return pane;
  }

  private void bindData(AppViewModel viewModel, File app) {
    // TODO：切换内容
    viewModel.getName().setValue(app.getName());

  }

}
