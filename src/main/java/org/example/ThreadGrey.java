package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public class ThreadGrey implements Runnable{

    private final List<Path> listPath;

    public ThreadGrey(List<Path> listPath) {
        this.listPath = listPath;
    }

    @Override
    public void run() {

        for (Path path : listPath){

        BufferedImage img = null;
        File f = null;

        int nPunto = path.getFileName().toString().lastIndexOf(".");

        // read image
        try {
            f = new File(
                    path.toFile().toString()
            );
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        // get image's width and height
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
        // convert to grayscale
        for (int i = 0; i < pixels.length; i++) {

            // Here i denotes the index of array of pixels
            // for modifying the pixel value.
            int p = pixels[i];

            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;

            // calculate average
            int avg = (r + g + b) / 3;

            // replace RGB value with avg
            p = (a << 24) | (avg << 16) | (avg << 8) | avg;

            pixels[i] = p;
        }
        img.setRGB(0, 0, width, height, pixels, 0, width);
        // write image
        try {
            f = new File(
                    "./src/main/resources/originals_grey/"+path.getFileName().toString().substring(0, nPunto)+"_grey.png"
            );
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    }
}



