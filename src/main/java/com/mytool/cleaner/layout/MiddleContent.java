package com.mytool.cleaner.layout;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;

public class MiddleContent {
  private static final VBox MIDDLE_GROUP_LIST = new VBox(10);
  public static final ScrollPane MIDDLE_SCROLLABLE_LIST = new ScrollPane(MIDDLE_GROUP_LIST);

  static {
    MIDDLE_SCROLLABLE_LIST.setFitToWidth(true);
    MIDDLE_SCROLLABLE_LIST.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

    ObservableList<Node> groupListChildren = MIDDLE_GROUP_LIST.getChildren();

    File applicationsDir = new File("/Applications");
    File[] files = applicationsDir.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.isDirectory() && file.getName().endsWith(".app")) {
          Button appNode = new Button();
          BorderPane appButtonGraphic = new BorderPane();
          appButtonGraphic.setTop(new Text(file.getName()));
          appNode.setGraphic(appButtonGraphic);
          appNode.setMaxWidth(Double.MAX_VALUE);
          groupListChildren.add(appNode);
        }
      }
    } else {
      System.out.println("No applications found.");
    }

  }

}
