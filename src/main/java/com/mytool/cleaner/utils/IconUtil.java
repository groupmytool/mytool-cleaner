package com.mytool.cleaner.utils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * macOS icon util
 */
public class IconUtil {

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
