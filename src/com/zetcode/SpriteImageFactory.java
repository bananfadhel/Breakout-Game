package com.zetcode;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

/**
 * Stage 2 - Flyweight (Flyweight Factory).
 *
 * Creates and shares SpriteImage objects. getSpriteImage(path) returns the
 * existing SpriteImage when the picture was already loaded, and loads it from
 * disk only the first time. (Swing code runs on one thread, so a plain
 * HashMap is enough, like ShapeFactory in the lecture.)
 */
public class SpriteImageFactory {

    private static final Map<String, SpriteImage> images = new HashMap<>();

    private SpriteImageFactory() { }

    public static SpriteImage getSpriteImage(String path) {

        SpriteImage spriteImage = images.get(path);

        if (spriteImage == null) {

            Image image = new ImageIcon(path).getImage();
            spriteImage = new SpriteImage(image);
            images.put(path, spriteImage);

            System.out.println("Loading image (first time): " + path);
        }

        return spriteImage;
    }

    /** Number of different pictures loaded so far. */
    public static int getImageCount() {

        return images.size();
    }
}
