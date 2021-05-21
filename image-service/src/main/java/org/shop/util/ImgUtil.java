package org.shop.util;

import lombok.extern.log4j.Log4j2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
@Log4j2
public class ImgUtil {
    final static String types = Arrays.toString(ImageIO.getReaderFormatNames());

    public static String DEFAULT_PREVFIX = "thumb_";


    /**
     * @param imgFile
     * @param targetW
     * @param targetH
     * @param force
     * @return filename
     */
    public static byte[] createThumbnail(InputStream imgFile, String filename, int targetW, int targetH, boolean force) {
            try {
                // ImageIO supported types : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String suffix = null;
                // get suffix
                if (filename.contains(".")) {
                    suffix = filename.substring(filename.lastIndexOf(".") + 1);
                }// check
                if (suffix == null || !types.toLowerCase().contains(suffix.toLowerCase())) {
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return null;
                }
                Image img = ImageIO.read(imgFile);
                if (!force) {
                    // find the best size with the original ratio
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if ((width * 1.0) / targetW < (height * 1.0) / targetH) {
                        if (width > targetW) {
                            targetH = Integer.parseInt(new java.text.DecimalFormat("0").format(height * targetW / (width * 1.0)));
                        }
                    } else {
                        if (height > targetH) {
                            targetW = Integer.parseInt(new java.text.DecimalFormat("0").format(width * targetH / (height * 1.0)));
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, targetW, targetH, Color.LIGHT_GRAY, null);
                g.dispose();
                // save the thumb img to where the file is with a new thumb_ started name
//                String destPath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + DEFAULT_PREVFIX + imgFile.getName();
                ByteArrayOutputStream outputStream =new ByteArrayOutputStream();

                ImageIO.write(bi, suffix, outputStream);
                return outputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("generate thumbnail image failed.", e);
            }

    }
}
