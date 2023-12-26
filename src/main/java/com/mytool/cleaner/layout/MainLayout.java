package com.mytool.cleaner.layout;

import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

import static com.mytool.cleaner.layout.LeftMenu.LEFT_MENU_LIST;
import static com.mytool.cleaner.layout.MiddleContent.MIDDLE_SCROLLABLE_LIST;
import static com.mytool.cleaner.layout.RightContent.RIGHT_SCROLLABLE_LIST;
import static com.mytool.cleaner.util.Constants.LAYOUT_MIN_HEIGHT;
import static com.mytool.cleaner.util.Constants.LAYOUT_MIN_WIDTH;

/**
 * 主布局
 */
public class MainLayout {

  public static Scene init() {

    // 创建一个SplitPane布局
    SplitPane splitPane = new SplitPane();
    // 将左侧菜单、中间应用列表和右侧应用详情添加到SplitPane中
    splitPane.getItems().addAll(LEFT_MENU_LIST, MIDDLE_SCROLLABLE_LIST, RIGHT_SCROLLABLE_LIST);

    // 设置每个元素的最小宽度
    LEFT_MENU_LIST.setMinWidth(LAYOUT_MIN_WIDTH * 0.2);
    LEFT_MENU_LIST.setMaxWidth(LAYOUT_MIN_WIDTH * 0.2);

    // 设置每个子节点的初始宽度：0.2和0.6两个参数表示第一个和第二个分隔线的位置
    splitPane.setDividerPositions(0.2, 0.5); // 0.2 + 0.4 = 0.6

    return new Scene(splitPane, LAYOUT_MIN_WIDTH, LAYOUT_MIN_HEIGHT);
  }

}
