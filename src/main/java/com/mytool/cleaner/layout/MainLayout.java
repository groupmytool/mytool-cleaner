package com.mytool.cleaner.layout;

import javafx.scene.Scene;
import javafx.scene.control.SplitPane;

import static com.mytool.cleaner.layout.left.LeftMenu.LEFT_MENU_LIST;
import static com.mytool.cleaner.layout.middle.MiddleContent.MIDDLE_SCROLLABLE_LIST;
import static com.mytool.cleaner.layout.right.RightContent.RIGHT_SCROLLABLE_LIST;
import static com.mytool.cleaner.utils.Constants.LAYOUT_DEFAULT_HEIGHT;
import static com.mytool.cleaner.utils.Constants.LAYOUT_DEFAULT_WIDTH;
import static com.mytool.cleaner.utils.Constants.LAYOUT_MIN_WIDTH;

/**
 * 主布局
 */
public class MainLayout {

  public static Scene init() {

    // 创建一个SplitPane布局
    SplitPane splitPane = new SplitPane();

    // 左侧菜单栏：固定宽度为最小布局宽度的0.2
    LEFT_MENU_LIST.setMinWidth(LAYOUT_MIN_WIDTH * 0.2);
    LEFT_MENU_LIST.setMaxWidth(LAYOUT_MIN_WIDTH * 0.2);

    // 中间应用栏：最小宽度是最小布局宽度的0.3；最大宽度是默认布局宽度的0.3
    MIDDLE_SCROLLABLE_LIST.setMinWidth(LAYOUT_MIN_WIDTH * 0.3);
    MIDDLE_SCROLLABLE_LIST.setMaxWidth(LAYOUT_DEFAULT_WIDTH * 0.3);

    // 将左侧菜单、中间应用列表和右侧应用详情添加到SplitPane中
    splitPane.getItems().addAll(LEFT_MENU_LIST, MIDDLE_SCROLLABLE_LIST, RIGHT_SCROLLABLE_LIST);

    // 设置初始化默认布局和大小
    return new Scene(splitPane, LAYOUT_DEFAULT_WIDTH, LAYOUT_DEFAULT_HEIGHT);
  }

}
