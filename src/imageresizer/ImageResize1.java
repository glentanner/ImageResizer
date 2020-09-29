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
 * This class scales images by percent.
 * 
 * The file size for images is determined by image resolution and bit depth.
 *  file size = resolution * bit depth
 * Bit Depth is the number of colors an image can store.
 * A typical JPEG displayed on computers and phones has a 24 bit depth (16 million colors).
 * 
 * When a JPEG is compressed, bit depth is reduced. It follows that file size
 * can be reduced by reducing bit depth (i.e. the number of colors).
 * 
 * Resolution is the number of pixels in an image (width * height)
 * Since 1 byte = 8 bits
 * If bit depth = 24:
 * Image file size(bits) = (resolution) * (bit depth) = resolution * 24
 * Image file size(bytes) = (resolution) * (bit depth / 8) = resolution * 3
 * 
 * Image file size(kilobytes) = resolution * 3/1024
 * Image file size(megabytes) = resolution * 3/1024*1024
 * 
 * 
 * (Number of pixels)*(bit depth) / 8 = number of bytes
 * (Number of pixels)*(bit depth) / 1024 = number of kilobytes
 * (Number of pixels)*(bit depth) / (1024*1024) = number of megabytes
 * 
 * If we want to insert one row of two images side-by-side into a Word document,
 * the max width of each image should be 235 pixels. Assuming a 4:3 aspect ratio,
 * as is the size of most images taken with a Getac V110 rear camera,
 * the image would be 235x176.25, or about 41418.75 pixels (122KB). 
 * 
 * @author gtanner
 */
public class ImageResize1 {
    
    public static void main(String[] args) {
        // New file to store the new scaled image
        File scaledMallard = new File("C:\\Users\\gtanner\\Desktop\\images\\scaledMallard.jpg");
        File scaledPercentMallard = new File("C:\\Users\\gtanner\\Desktop\\images\\scaledPercentMallard.jpg");

        try {
            // Read the image
            File imageFile = new File("src\\imageresizer\\mallard.jpg");
            BufferedImage testImage = readFromFile(imageFile);
            
            // Get the file size in KB
            System.out.println("Original File size: " + imageFile.length()/1024);
            
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
