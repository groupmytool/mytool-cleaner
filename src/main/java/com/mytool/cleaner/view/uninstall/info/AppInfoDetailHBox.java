package com.mytool.cleaner.view.uninstall.info;

import com.mytool.cleaner.model.AppViewModel;
import com.mytool.cleaner.utils.file.IconUtil;
import com.mytool.cleaner.view.component.SplitLine;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppInfoDetailHBox extends HBox {

  private final AppViewModel infoModel;

  public AppInfoDetailHBox(AppViewModel infoModel) {
    super();
    this.infoModel = infoModel;
    setPadding(new Insets(10, 10, 10, 10));
    this.getChildren().add(getAppIcon());
    this.getChildren().add(getAppDetail());
  }

  private ImageView getAppIcon() {
    ImageView icon = new ImageView();
    icon.setFitWidth(128);
    icon.setFitHeight(128);

    icon.imageProperty().bind(Bindings.createObjectBinding(() -> {
      return IconUtil.getIcon(infoModel.getIcon().get(), infoModel.getName().get());
    }, infoModel.getIcon()));

    return icon;
  }

  private VBox getAppDetail() {
    VBox appInfo = new VBox();

    // 默认扩展到父节点最大宽度
    HBox.setHgrow(appInfo, Priority.ALWAYS);
    appInfo.setPadding(new Insets(5, 5, 5, 5));
    appInfo.setSpacing(5);
    // 名称
    Text appName = new Text();
    Font font = new Font("System Bold", 30);
    appName.setFont(font);
    appName.textProperty().bind(infoModel.getName());
    appInfo.getChildren().add(appName);
    // 分割线
    Pane splitLine1 = new SplitLine();
    appInfo.getChildren().add(splitLine1);
    // 安装时间
    HBox timeBox = new HBox();
    appInfo.getChildren().add(timeBox);

    Text timeTitle = new Text("安装时间：");
    timeBox.getChildren().add(timeTitle);
    Text timeValue = new Text();
    timeValue.textProperty().bind(infoModel.getInstallTime());
    timeBox.getChildren().add(timeValue);
    // 分割线
    Pane splitLine2 = new SplitLine();
    appInfo.getChildren().add(splitLine2);
    // 应用大小
    HBox sizeBox = new HBox();
    appInfo.getChildren().add(sizeBox);

    Text sizeTitle = new Text("应用大小：");
    sizeBox.getChildren().add(sizeTitle);
    Text sizeValue = new Text();
    sizeBox.getChildren().add(sizeValue);
    sizeValue.textProperty().bind(infoModel.getSize());

    return appInfo;
  }

}
