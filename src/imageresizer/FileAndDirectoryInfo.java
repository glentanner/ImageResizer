/*
 * U.S. Fish and Wildlife Service
 */
package imageresizer;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author gtanner
 */
public class FileAndDirectoryInfo {
    public static void main(String[] args) throws IOException {
        // datetime format for Files.getLastModifiedTime()
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter file or directory name:");
        
        // create Path object based on user input (String)
        Path path = Paths.get(input.nextLine());

        // if path exists, output info about it
        if(Files.exists(path)) {
            long time = Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS).toMillis();
            
            // display file or directory information
            System.out.printf("%n%s exists%n", path.getFileName());
            System.out.printf("%s is a directory%n", Files.isDirectory(path) ? "Is" : "Is not");
            System.out.printf("%s is an absolute path%n", path.isAbsolute() ? "Is" : "Is not");
            System.out.printf("Last modified: %s%n", sdf.format(time));
            System.out.printf("Size: %s%n", Files.size(path));
            System.out.printf("Path: %s%n", path);
            System.out.printf("Absolute path: %s%n", path.toAbsolutePath());
        
            if(Files.isDirectory(path)) {
                // output directory listing
                System.out.printf("%n Directory contents:%n"); 

                // object for iterating through a driectory's contents
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);

                for(Path p : directoryStream) {
                    System.out.println(p);
                }
            }
        }
        else {
            // not a file or directory, output error message
            System.out.printf("%s does not exist%n", path);
        }
    }// end main
}// end class
