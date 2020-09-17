/*
* Copyright Glen Tanner. All rights reserved.
* 
* SPDX-License_Identifier: LGPL-3.0
 */
package imageresizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * To reduce the file size of an image, we have two options (for JPEG/JPG):
 * 1) Change the format to use a higher compression.
 * 2) Change the image size, preserving the aspect ratio.
 * 
 * For instance, assume we have a Getac V110 with an 10MP camera (it is probably 8MP).
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
        // Read in an image, print the path, width, height.
        try {
            readFromFile(new File("src\\imageresizer\\frog.jpg"));
        } catch(IOException ioe) {
            ioe.getMessage();
        }
        
        // Resize it to a max file size.
        
    }
    
    // Resize it to a max file size.
    // Print the size of the file.
    
    // Returns a BufferedImage from a File
    private static BufferedImage readFromFile(File filename) throws IOException {
        // Read image from a local file
        BufferedImage originalImage = ImageIO.read(filename);
        printDimensionsAndFileSize(originalImage, filename);
        
        return originalImage;
    }
//        System.out.println("Path: " + originalFile.getAbsolutePath());
//        printDimensionsAndFileSize(originalImage, originalFile);
//        
//        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
//        
//        printDimensionsAndFileSize(originalImage, originalFile);
        
        // 50%
        //BufferedImage resizeImageBmp = resizeImage(originalImage, type, percent_50);
        //ImageIO.write(resizeImageBmp, "jpg", plainFile50);
    
    // For debugging: prints width, height of a BufferedImage and file size 
    private static void printDimensionsAndFileSize(BufferedImage image, File file) {
        // Write the pixel properties
        System.out.println("Image width: " + image.getWidth());
        System.out.println("Image height: " + image.getHeight());
        System.out.println("File size: " + file.length()/1024 + "KB");
    }
    
}