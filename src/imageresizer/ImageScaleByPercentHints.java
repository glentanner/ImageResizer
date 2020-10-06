/*
* Copyright Glen Tanner. All rights reserved.
* 
* SPDX-License_Identifier: LGPL-3.0
 */
package imageresizer;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Resizes image by percent, also with hints.
 * 
 * @author gtanner
 */
public class ImageScaleByPercentHints {
    
    public static void main(String[] args) throws Exception {
        File originalFile = new File("C:\\Users\\gtanner\\Desktop\\images\\planet.jpg");
        File plainFile50 = new File("C:\\Users\\gtanner\\Desktop\\images\\planetPlain50.jpg");
        File plainFile25 = new File("C:\\Users\\gtanner\\Desktop\\images\\planetPlain25.jpg");
        File plainFile2x = new File("C:\\Users\\gtanner\\Desktop\\images\\planetPlain2x.jpg");
        File plainFile4x = new File("C:\\Users\\gtanner\\Desktop\\images\\planetPlain4x.jpg");
        
        double percent_50 = 0.5;
        double percent_25 = 0.25;
        double percent_200 = 2;
        double percent_400 = 4;
        
        BufferedImage originalImage = ImageIO.read(originalFile);
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        
        printDimensionsAndFileSize(originalImage, originalFile);
        
        // 50%
        BufferedImage resizeImageBmp = resizeImage(originalImage, type, percent_50);
        ImageIO.write(resizeImageBmp, "jpg", plainFile50);
        
        printDimensionsAndFileSize(resizeImageBmp, plainFile50);
        
        // 25%
        resizeImageBmp = resizeImage(originalImage, type, percent_25);
        ImageIO.write(resizeImageBmp, "jpg", plainFile25);
        
        printDimensionsAndFileSize(resizeImageBmp, plainFile25);
        
        // 2X
        resizeImageBmp = resizeImage(originalImage, type, percent_200);
        ImageIO.write(resizeImageBmp, "jpg", plainFile2x);
        
        printDimensionsAndFileSize(resizeImageBmp, plainFile2x);
        
        // 4X
        resizeImageBmp = resizeImage(originalImage, type, percent_400);
        ImageIO.write(resizeImageBmp, "jpg", plainFile4x);
        
        printDimensionsAndFileSize(resizeImageBmp, plainFile4x);
        
//        resizeImageBmp = resizeImageWithHint(originalImage, type);
//        ImageIO.write(resizeImageBmp, "jpg", hintedFile);
//        
//        printDimensionsAndFileSize(resizeImageBmp, plainFile);
    }
    
    private static BufferedImage resizeImage(BufferedImage originalImage, int type, double percent) {
        int IMG_WIDTH = (int)(originalImage.getWidth() * percent);
        int IMG_HEIGHT = (int)(originalImage.getHeight() * percent);
        
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        
        return resizedImage;
    }
    
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {
        int IMG_WIDTH = 640;
        int IMG_HEIGHT = 512;
        
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        return resizedImage;
    }
    
    private static void printDimensionsAndFileSize(BufferedImage image, File file) {
        // Write the pixel properties
        System.out.println("Image width: " + image.getWidth());
        System.out.println("Image height: " + image.getHeight());
        System.out.println("File size: " + file.length()/1024 + "KB");
    }
}
