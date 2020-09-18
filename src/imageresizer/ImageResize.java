/*
* Copyright Glen Tanner. All rights reserved.
* 
* SPDX-License_Identifier: LGPL-3.0
 */
package imageresizer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * To reduce the file size of an image, we have two options (for JPEG/JPG):
 * 1) Change the format to use a higher compression.
 * 2) Change the image size, preserving the aspect ratio.
 * 
 * For instance, assume we have an 10MP camera.
 * Compression options for a 10MP image are basic, normal, and fine.
 * File sizes range from basic(1.3 MB), normal(2.4MB), and fine(4.7MB).
 * 
 * An 8MP(8 million pixels) image typically is 3264 x 2448
 * 2MP = 1632 x 1224
 * 500KB = 816 x 612
 * 250KB = 600 x 400
 * 
 * To fit two images per row in an A4 document like a standard .docx,
 * a 500KB image should be 816 x 612 max, and
 * a 250KB image should be 612 x 408 max.
 * 
 * @author gtanner
 */
public class ImageResize {
    
    public static void main(String[] args) {
        // New file to store the new scaled image
        File scaledMallard = new File("C:\\Users\\gtanner\\Desktop\\images\\scaledMallard.jpg");
        File scaledPercentMallard = new File("C:\\Users\\gtanner\\Desktop\\images\\scaledPercentMallard.jpg");

        try {
            // Read the image
            BufferedImage testImage = readFromFile(new File("src\\imageresizer\\mallard.jpg"));
            
            // Scale the image down using a fixed size
            BufferedImage scaledImage = getScaledImage(testImage, 800, 600);
            // Write the buffered image (scaledImage) to a new file (scaledMallard.jpg)
            ImageIO.write(scaledImage, "jpg", scaledMallard);
            printDimensionsAndFileSize(scaledImage, scaledMallard);
            
            // Scale the image down using a fixed size
            BufferedImage scaledPercentImage = getScaledImageByPercent(testImage, .5);
            // Write the buffered image (scaledImage) to a new file (scaledMallard.jpg)
            ImageIO.write(scaledPercentImage, "jpg", scaledPercentMallard);
            printDimensionsAndFileSize(scaledPercentImage, scaledPercentMallard);
        } catch(IOException ioe) {
            ioe.getMessage();
        }     
    }
    
    // Returns a BufferedImage from a File
    private static BufferedImage readFromFile(File filename) throws IOException {
        // Read image from a local file
        BufferedImage originalImage = ImageIO.read(filename);
        printDimensionsAndFileSize(originalImage, filename);
        
        return originalImage;
    }
    
    private static BufferedImage getScaledImageByPercent(BufferedImage originalImage, double percent) {
        int IMG_WIDTH = (int)(originalImage.getWidth() * percent);
        int IMG_HEIGHT = (int)(originalImage.getHeight() * percent);
        
        // Preserve type if we can
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        
        return resizedImage;
    }
    
    private static BufferedImage getScaledImage(BufferedImage src, int w, int h){
        int original_width = src.getWidth();
        int original_height = src.getHeight();
        int bound_width = w;
        int bound_height = h;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        // Preserve the type if we can
        int type = src.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : src.getType();
        
        BufferedImage resizedImg = new BufferedImage(new_width, new_height,type);
        
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setBackground(Color.WHITE);
        g2.clearRect(0,0,new_width, new_height);
        g2.drawImage(src, 0, 0, new_width, new_height, null);
        g2.dispose();
        return resizedImg;
    }
    
    // For debugging: prints width, height of a BufferedImage and file size 
    private static void printDimensionsAndFileSize(BufferedImage image, File file) {
        // Write the pixel properties
        System.out.println("Image width: " + image.getWidth());
        System.out.println("Image height: " + image.getHeight());
        System.out.println("Image values: "+ image.toString());
        System.out.println("File size: " + file.length()/1024 + "KB");
    }
}
