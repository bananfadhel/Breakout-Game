package com.zetcode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

/**
 * Stage 2 - Flyweight (Flyweight).
 *
 * A picture that is loaded once and shared by many sprites. It keeps only
 * the INTRINSIC state (the image and its size), which never changes and is
 * the same for every sprite that uses it. The EXTRINSIC state (x, y) belongs
 * to the sprite and is passed in when the picture is drawn.
 */
public class SpriteImage {

    private final Image image;
    private final int width;
    private final int height;

    SpriteImage(Image image) {

        this.image = image;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    /** Draws the shared picture at the position given by the sprite (extrinsic state). */
    public void draw(Graphics2D g2d, int x, int y, ImageObserver observer) {

        g2d.drawImage(image, x, y, width, height, observer);
    }

    public Image getImage() { return image; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
}
