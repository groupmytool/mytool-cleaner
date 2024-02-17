package com.mytool.cleaner.controller.uninstall;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.model.AppViewModel;
import com.mytool.cleaner.view.component.VgrowVBox;
import com.mytool.cleaner.view.uninstall.info.AppInfoDetailHBox;
import com.mytool.cleaner.view.uninstall.info.AppInfoRootScrollPane;
import com.mytool.cleaner.view.uninstall.info.AppUninstallButton;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * App详细信息控制器
 */
public class AppInfoController extends BaseController {

  private final AppViewModel appViewModel;
  private VBox introContent;
  private VBox detailContent;

  public AppInfoController() {
    this.appViewModel = new AppViewModel();
  }

  public AppViewModel getAppViewModel() {
    return appViewModel;
  }

  public AppInfoRootScrollPane getView() {
    AppInfoRootScrollPane infoScrollPane = new AppInfoRootScrollPane();

    VBox infoVBox = new VBox();

    infoScrollPane.setContent(infoVBox);
    // 父节点容器
    // ├── 引导页
    introContent = new VgrowVBox(Pos.CENTER);

    infoVBox.getChildren().add(introContent);
    introContent.getChildren().add(new Text("\uD83E\uDEF2点击左侧列表选择要卸载的应用"));

    // ├── 应用信息
    detailContent = new VgrowVBox(Pos.CENTER, false);
    infoVBox.getChildren().add(detailContent);
    // │    ├── 基本信息
    AppInfoDetailHBox detailInfo = new AppInfoDetailHBox(appViewModel);
    detailContent.getChildren().add(detailInfo);
    // │    ├── 文件列表
    TreeView<String> treeFiles = new TreeView<>();
    detailContent.getChildren().add(treeFiles);
    TreeItem<String> treeItem = new TreeItem<>();
    treeFiles.setRoot(treeItem);
    // │    ├── 卸载按钮
    AppUninstallButton uninstallBtn = new AppUninstallButton();
    detailContent.getChildren().add(uninstallBtn);

    treeFiles.prefHeightProperty().bind(
        Bindings.subtract(
            detailContent.heightProperty(),
            detailInfo.getBoundsInLocal().getHeight() + uninstallBtn.getBoundsInLocal().getHeight()
        )
    );

    return infoScrollPane;
  }

  public void showDetailContent() {
    // TODO：上下翻页效果；不仅是intro和detail的切换，还包括不同detail的切换
    if (introContent.isVisible()) {
      introContent.setVisible(false);
      introContent.setManaged(false);
      detailContent.setVisible(true);
      detailContent.setManaged(true);
    }
  }

}
