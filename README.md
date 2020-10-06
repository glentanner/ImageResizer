# Image Resizer

Resizes an image

## Description


## Issues

There was an issue getting the project to start.

It was an Ant build error.
"com.sun.javafx.tools.packager.PackagerException: Error: -includedt requires the java deployment toolkit, which is not included in this distribution."

NetBeans: In the project File tab, open the "nbproject" folder, then open "project.properties".  Find javafx.deploy.includeDT=true and change it to false.

#### ImageResizer
This is just a utility to drag and drop an image to a window, and have it resize to a file no bigger than 500KB.

#### AnotherImageResizer
This reduces an image based on percentage.

#### ImageCropWithRubberBand
Allows the user to draw a rectangle and crop an image like in Paint. Right click gives a Crop option, which opens a file chooser to save the file.

#### ReadImage
Show different ways to read in an image file.
Read image from File
Read image from URL
Read image from FileInputStream

