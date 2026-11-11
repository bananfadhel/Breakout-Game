package com.zetcode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

// Flyweight - Flyweight: a picture that is loaded once and shared by many sprites
// it keeps only the image and its size (shared), the x, y come from the sprite
public class SpriteImage {

    private final Image image;
    private final int width;
    private final int height;

    SpriteImage(Image image) {

        this.image = image;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    // draw the shared picture at the x, y given by the sprite
    public void draw(Graphics2D g2d, int x, int y, ImageObserver observer) {

        g2d.drawImage(image, x, y, width, height, observer);
    }

    public Image getImage() { return image; }

    public int getWidth() { return width; }

    public int getHeight() { return height; }
}
