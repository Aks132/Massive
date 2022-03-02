package Detection;// Java Program to Crop Image Using BufferedImage Class

// Importing required packages
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// Main class
public class crop {

    // Main driver method
    public static void main(String[] args)
    {
        // Try block to check for exceptions
        try {

            // Reading original image from local path by
            // creating an object of BufferedImage class
            BufferedImage originalImg = ImageIO.read(
                    new File("source/source1.jpg"));


            // Fetching and printing alongside the
            // dimensions of original image using getWidth()
            // and getHeight() methods
            System.out.println("Original Image Dimension: "
                    + originalImg.getWidth()
                    + "x"
                    + originalImg.getHeight());

            // Creating a subimage of given dimensions
            BufferedImage SubImg
                    = originalImg.getSubimage(600, 400, 2800, 2200);

            // Printing Dimensions of new image created
            System.out.println("Cropped Image Dimension: "
                    + SubImg.getWidth() + "x"
                    + SubImg.getHeight());

            // Creating new file for cropped image by
            // creating an object of File class
            File outputfile
                    = new File("ImageCropped.jpeg");

            // Writing image in new file created
            ImageIO.write(SubImg, "jpg", outputfile);

            // Display message on console representing
            // proper execution of program
            System.out.println(
                    "Cropped Image created successfully");
        }

        // Catch block to handle the exceptions
        catch (IOException e) {

            // Print the exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
    }
}
