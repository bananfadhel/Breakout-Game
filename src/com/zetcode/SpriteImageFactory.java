package com.zetcode;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

// Flyweight - Factory: loads each picture once and shares it (like ShapeFactory in the lecture)
public class SpriteImageFactory {

    // each picture is loaded once and saved here by its path
    private static final Map<String, SpriteImage> images = new HashMap<>();

    private SpriteImageFactory() { }

    // give back the saved picture, or load it the first time it is asked for
    public static SpriteImage getSpriteImage(String path) {

        SpriteImage spriteImage = images.get(path);

        // not loaded yet: load it from disk and save it in the map
        if (spriteImage == null) {

            Image image = new ImageIcon(path).getImage();
            spriteImage = new SpriteImage(image);
            images.put(path, spriteImage);

            System.out.println("Loading image (first time): " + path);
        }

        return spriteImage;
    }

    // how many different pictures were loaded so far
    public static int getImageCount() {

        return images.size();
    }
}
