/*
 * U.S. Fish and Wildlife Service
 */
package imageresizer;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

/**
 *
 * @author gtanner
 */
public class ImageResizer {
    
    /**
     * Resizes an image to the percent given.
     * 
     * @param originalFile
     * @param percent
     * @return File containing the resized jpeg
     */
    public File resizeImage(String originalFile, double percent) {
        return null;
    }
    
    /**
     * Resizes an image to the max width. Aspect ratio is preserved, so height 
     * gets calculated automatically.
     * 
     * @param originalFile
     * @param maxWidth
     * @return File containing the resized jpeg
     */
    public File resizeImage(File imageFile, int width) throws IOException {
        Image img = ImageIO.read(imageFile);
        BufferedImage newJpg = resizeImage(img, width);
        
        return null;
    }
    
    /**
     * Resizes all images in a given folder to a max width, 
     * preserving the aspect ratio.
     * 
     * @param directoryName
     * @param maxWidth
     * @return File folder containing the resized jpegs
     */
    public File resizeAll(String directoryName, int maxWidth) throws IOException {
        
        // create Path object based on user input (String)
        Path path = Paths.get(directoryName);
        
        if(Files.isDirectory(path)) {
            // output directory listing
            System.out.printf("%n Directory contents:%n"); 

            // object for iterating through a driectory's contents
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);

            for(Path p : directoryStream) {
                System.out.println(p);
            }
        }
        else {
            // not a file or directory, output error message
            System.out.printf("%s does not exist%n", path);
        }
        
        
        
        return null;
    }
}
