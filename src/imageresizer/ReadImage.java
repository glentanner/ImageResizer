/*
* Copyright Glen Tanner. All rights reserved.
* 
* SPDX-License_Identifier: LGPL-3.0
 */
package imageresizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author gtanner
 */
public class ReadImage {
    public static void main(String[] args) {
        
        // ImageIO.read() chooses the decoder automatically, 
        // so just use whatever is easiest. It shouldn't matter.
        try {
            readFromFile("src\\imageresizer\\frog.jpg");
            readFromUrl("https://www.fws.gov/sacramento/es_species/Accounts/Amphibians-Reptiles/ca_red_legged_frog/images/cal_red_legged_frog.jpg");
            readFromStream("src\\imageresizer\\frog.jpg");
        } catch(IOException ioe) {
            ioe.getMessage();
        }
    }
    
    private static void readFromUrl(String url) throws IOException {
        // Read image from URL
        URL urlInput = new URL(url);
        BufferedImage urlImage = ImageIO.read(urlInput);
        printDims(urlImage);
    }
    
    private static void readFromFile(String filename) throws IOException {
        // Read image from a local file
        File fileInput = new File(filename);
        System.out.println(fileInput.getAbsolutePath());
        BufferedImage folderImage = ImageIO.read(fileInput);
        printDims(folderImage);
    }
    
    private static void readFromStream(String stream) throws IOException {
        // Read image from InputStream
        FileInputStream fis = new FileInputStream(stream);
        BufferedImage fisImage = ImageIO.read(fis);
        printDims(fisImage);
    }
    
    private static void printDims(BufferedImage image) {
        int width = 0;
        int height = 0;
        System.out.println("Width: " + image.getWidth());
        System.out.println("Height: " + image.getHeight());
    }
}
