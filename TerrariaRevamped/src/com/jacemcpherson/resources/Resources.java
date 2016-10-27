package com.jacemcpherson.resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {

    public static BufferedImage getImage(String fileName) throws IOException {
        return ImageIO.read(new File(fileName));
    }

}
