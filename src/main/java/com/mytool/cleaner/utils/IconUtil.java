package com.mytool.cleaner.utils;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static com.mytool.cleaner.utils.CacheUtil.ICNS_CACHE_PATH;
import static com.mytool.cleaner.utils.CacheUtil.filePathCheck;
import static com.mytool.cleaner.utils.CacheUtil.filePathCheckAndCreate;

/**
 * macOS icon util
 */
public class IconUtil {

  public static Image getIcon(String sourceIcons, String cacheName) throws FileNotFoundException {
    if (!sourceIcons.endsWith(".icns")) {
      sourceIcons += ".icns";
    }
    if (filePathCheck(sourceIcons)) {
      filePathCheckAndCreate(ICNS_CACHE_PATH);
      String outPath = STR."\{ICNS_CACHE_PATH}/\{cacheName}.png";
      File out = new File(outPath);
      if (!out.exists()) {
        IconUtil.transform(new File(sourceIcons), "png", out);
      }
      return new Image(new FileInputStream(out));
    } else {
      InputStream icon = IconUtil.class.getResourceAsStream("/images/default-icon.png");
      return new Image(Objects.requireNonNull(icon));
    }
  }

  /**
   * transform icns to png
   *
   * @param input      source app icon path
   * @param formatName output image format name
   * @param out        output image path
   */
  public static void transform(File input, String formatName, File out) {
    try {
      ImageReader reader = ImageIO.getImageReadersByFormatName("icns").next();
      reader.setInput(ImageIO.createImageInputStream(input));
      ImageReadParam param = reader.getDefaultReadParam();
      int picIndex = getIndex(reader, param);
      BufferedImage pic = reader.read(picIndex, param);
      ImageIO.write(pic, formatName, out);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * chose picture of ppi(256*256) or close to ppi(256*256)
   */
  private static int getIndex(ImageReader reader, ImageReadParam param) throws IOException {

    // ppi: 256*256
    final int size = 256;

    int numImages = reader.getNumImages(true);
    int curWidth = 0;
    int curHeight = 0;
    int curIndex = 0;

    for (int i = 0; i < numImages; i++) {
      BufferedImage image = reader.read(i, param);
      // check this image is more close to ppi or not
      if ((curWidth == 0 || curHeight == 0) || // first image init
          (curWidth < size && image.getWidth() > curWidth) || // too small
          ((curWidth > size && image.getWidth() >= size) && image.getWidth() < curWidth)  // too big
      ) {
        // update image info
        curWidth = image.getWidth();
        curHeight = image.getHeight();
        curIndex = i;
      }
    }
    return curIndex;
  }

}
