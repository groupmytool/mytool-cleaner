package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppListModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.mytool.cleaner.utils.Constants.ROOT_PATH;

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
  private SplitPane contentPane;

  private FXMLLoader detailFxmlLoader;
  private ScrollPane detailView;
  private AppInfoController controller;

  public void setContentPane(SplitPane contentPane) {
    this.contentPane = contentPane;
  }

  @Override
  public void initialize() throws IOException {
    detailFxmlLoader = new FXMLLoader(getClass().getResource("/views/uninstall/app-detail-view.fxml"));
    detailView = detailFxmlLoader.load();
    controller = detailFxmlLoader.getController();

    File applicationsDir = new File(ROOT_PATH);
    File[] apps = applicationsDir.listFiles();
    if (apps != null) {
      Arrays.stream(apps).sorted(Comparator.comparing(File::getName));
      for (File app : apps) {
        if (app.isDirectory() && app.getName().endsWith(".app")) {
          Button appNode = createAppNode(app);
          appList.getChildren().add(appNode);
        }
      }
    } else {
      Button appNode = createNullNode("No apps found");
      appList.getChildren().add(appNode);
    }
  }

  private Button createAppNode(File app) {
    Button appNode = new Button();
    appNode.setText(app.getName());
    appNode.setMaxWidth(Double.MAX_VALUE);

    AppListModel model = new AppListModel();
    model.name = app.getName().split("\\.")[0];
    model.file = app;


    appNode.setOnAction(event -> {
      try {
        controller.setAppListModel(model);
        controller.build();
        ObservableList<Node> children = contentPane.getItems();
        if (children.size() > 1 && children.get(1) != detailView) {
          children.remove(1, children.size());
          children.add(detailView);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return appNode;
  }

  private Button createNullNode(String name) {
    Button appNode = new Button();
    appNode.setText(name);
    appNode.setMaxWidth(Double.MAX_VALUE);
    return appNode;
  }

}
