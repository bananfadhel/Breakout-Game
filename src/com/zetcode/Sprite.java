package com.zetcode;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

// base class of every game object
// Flyweight: a Sprite now keeps a shared SpriteImage and only its own x, y
public class Sprite {

    // only the position belongs to each sprite
    int x;
    int y;
    // the shared picture from SpriteImageFactory
    private SpriteImage spriteImage;

    // get the picture from the factory (loaded once, then shared)
    protected void loadImage(String path) {

        spriteImage = SpriteImageFactory.getSpriteImage(path);
    }

    // draw the shared picture at this sprite's own x, y
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
