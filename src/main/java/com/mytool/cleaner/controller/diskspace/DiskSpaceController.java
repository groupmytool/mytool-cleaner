package com.mytool.cleaner.controller.diskspace;

import com.mytool.cleaner.controller.BaseController;
import com.mytool.cleaner.view.diskspace.DiskSpaceRootScrollPane;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DiskSpaceController extends BaseController {

  public DiskSpaceController() {
  }

  public ScrollPane getView() {
    DiskSpaceRootScrollPane diskListRootScrollPane = new DiskSpaceRootScrollPane();
    VBox vBox = new VBox();
    vBox.setAlignment(Pos.CENTER);
    vBox.getChildren().add(new Text("Disk Space"));
    diskListRootScrollPane.setContent(vBox);
    return diskListRootScrollPane;
  }

}
