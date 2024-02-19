package com.mytool.cleaner.view.component;

import com.mytool.cleaner.utils.file.FontUtil;
import javafx.scene.text.Text;

public class FontText extends Text {

  public FontText() {
    super();
    this.setFont(FontUtil.getFont());
  }

  public FontText(String text) {
    super(text);
    this.setFont(FontUtil.getFont());
  }

}
