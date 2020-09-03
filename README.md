# Image Resizer

Resizes an image

## Description
This is just a utility to drag and drop an image to a window, and have it resize to a file no bigger than 500KB

## Issues

There was an issue getting the project to start.

It was some kind of Ant build error.
com.sun.javafx.tools.packager.PackagerException: Error: -includedt requires the java deployment toolkit, which is not included in this distribution

In the project File tab, open the "nbproject" folder, then open "project.properties".  Find javafx.deploy.includeDT=true and change it to false.