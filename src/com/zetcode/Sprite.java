package com.zetcode;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

/**
 * Base class of every game object.
 *
 * Refactoring (Stage 2 - Flyweight): a Sprite no longer owns a copy of its
 * picture. It keeps a reference to a shared SpriteImage (intrinsic state)
 * obtained from SpriteImageFactory, and keeps only its own position x, y
 * (extrinsic state).
 */
public class Sprite {

    int x;
    int y;
    private SpriteImage spriteImage;

    /** Gets the picture from the factory (loaded once, then shared). */
    protected void loadImage(String path) {

        spriteImage = SpriteImageFactory.getSpriteImage(path);
    }

    /** Draws the shared picture at this sprite's own position. */
    public void draw(Graphics2D g2d, ImageObserver observer) {

        spriteImage.draw(g2d, x, y, observer);
    }

    protected void setX(int x) {

        this.x = x;
    }

    int getX() {

        return x;
    }

    protected void setY(int y) {

        this.y = y;
    }

    int getY() {

        return y;
    }

    int getImageWidth() {

        return spriteImage.getWidth();
    }

    int getImageHeight() {

        return spriteImage.getHeight();
    }

    Rectangle getRect() {

        return new Rectangle(x, y, getImageWidth(), getImageHeight());
    }
}
