package Detection;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

public class crop {
    public static void main(String[] args) throws IOException {

        Set<Integer> colors = new HashSet<Integer>();
        BufferedImage image = ImageIO.read(new File("source/result.jpg"));
        int w = image.getWidth();
        int h = image.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);
                colors.add(pixel);
            }
        }
        System.out.println("There are " + colors.size() + " colors");
    }
}