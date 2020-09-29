/*
* Copyright Glen Tanner. All rights reserved.
* 
* SPDX-License_Identifier: LGPL-3.0
 */
package imageresizer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author gtanner
 */
public class ImageResize2 extends Application {
    
    ImageView imageView;
    StackPane contentPane;
    BorderPane layout;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        layout = new BorderPane();
        contentPane = new StackPane();
        
        contentPane.setOnDragOver((final DragEvent event) -> {
            mouseDragOver(event);
        });
        
        contentPane.setOnDragDropped((final DragEvent event) -> {
           mouseDragDropped(event); 
        });
        
        contentPane.setOnDragExited((final DragEvent event)-> {
            contentPane.setStyle("-fx-border-color: #C6C6C6");
        });
        
        layout.setCenter(contentPane);
        
        Scene scene = new Scene(layout, 800, 600, Color.WHITE);
        primaryStage.setTitle("Image Resizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addImage(Image image, StackPane pane) {
        imageView = new ImageView();
        imageView.setImage(image);
        // Resizes the image in the view, but doesn't change the file at all.
//        imageView.setFitWidth(350);
//        imageView.setPreserveRatio(true);
//        imageView.setSmooth(true);
//        imageView.setCache(true);
        pane.getChildren().add(imageView);
        imageInfo(image);
    }
    
    private void imageInfo(Image image) {
        
        PixelReader reader;
        
        try {
            // Get the pixel reader from the image
            reader = image.getPixelReader();
        } catch (NullPointerException npe) {
            Logger.getLogger(ImageResize2.class.getName()).log(Level.SEVERE, null, npe);
        }
        
        // Get Image properties
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        
        // Write the pixel properties
        System.out.println("Image width: " + width);
        System.out.println("Image height: " + height);
    }
    
    private void mouseDragOver(final DragEvent event) {
        final Dragboard db = event.getDragboard();
        
        final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg")
                || db.getFiles().get(0).getName().toLowerCase().endsWith(".gif");
        if(db.hasFiles()) {
            if(isAccepted) {
                contentPane.setStyle("-fx-border-color:red;"
                            + "-fx-border-width: 5;"
                            + "-fx-background-color: #C6C6C6;"
                            + "-fx-border-style: solid;");
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        }        
    }

    private void mouseDragDropped(final DragEvent event) {
        final Dragboard db = event.getDragboard();
        
        boolean success = false;
        if(db.hasFiles()) {
            success = true;
            // Only get the first file from the list
            final File file = db.getFiles().get(0);
            
            Platform.runLater(() -> {
                System.out.println("File location: " + file.getAbsolutePath());
                System.out.println("File size: " + file.length());
                
                try {
                    if(!contentPane.getChildren().isEmpty()) {
                        contentPane.getChildren().remove(0);
                    }
                    
                    Image image = new Image(new FileInputStream(file.getAbsolutePath()));
                    
                    // Display the current image info
                    this.imageInfo(image);
                    addImage(image, contentPane);
                    
                    // Create a buffered image from the original image
                    // Create a resized buffered image
                    // Resize
                    // Save as PNG
                    //File newFile = new File("resizedImage.png");
                    //  ImageIO.write(bufferedImage, "png", newFile);
                    // Add the image
                    //addImage(resizedImage);
                    System.out.println("File size: " + (file.length()));
                } catch(FileNotFoundException fnf) {
                    Logger.getLogger(ImageResize2.class.getName()).log(Level.SEVERE, null, fnf);
                }
            });
        }
    } 
}
