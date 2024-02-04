package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppListModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * App列表信息控制器。
 * 1、获取/Applications目录下的所有应用
 * 2、获取应用的名称、大小、安装时间等基础信息并存储到listModelList中
 */
public class AppListController extends BaseController {

  // TODO 排序、搜索
  private static List<AppListModel> listModelList = new ArrayList<>();
  @FXML
  private VBox appList;
  private HBox contentPane;

  public void setContentPane(HBox contentPane) {
    this.contentPane = contentPane;
  }

  public void initialize() {
    File applicationsDir = new File("/Applications");
    File[] apps = applicationsDir.listFiles();
    if (apps != null) {
      for (File app : apps) {
        if (app.isDirectory() && app.getName().endsWith(".app")) {
          Button appNode = createAppNode(app.getName());
          appList.getChildren().add(appNode);
        }
      }
    } else {
      Button appNode = createAppNode("No apps found");
      appList.getChildren().add(appNode);
    }
  }

  private Button createAppNode(String appName) {
    Button appNode = new Button();
    appNode.setText(appName);
    appNode.setMaxWidth(Double.MAX_VALUE);

    AppListModel model = new AppListModel();
    model.name = appName;

    appNode.setOnAction(event -> {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mytool/cleaner/views/uninstall/app-info-view.fxml"));
        ScrollPane appInfoView = fxmlLoader.load();
        AppInfoController controller = fxmlLoader.getController();
        controller.setAppListModel(model);
        ObservableList<Node> children = contentPane.getChildren();
        if (children.size() > 1) {
          children.remove(1);
        }
        children.add(appInfoView);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return appNode;
  }

}
