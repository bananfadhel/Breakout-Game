package com.zetcode;

// Factory Method - Concrete Product: a strong brick that needs two hits
public class StrongBrick extends Brick {

    public StrongBrick(int x, int y) {

        // a strong brick starts with 2 hits
        super(x, y, 2);
    }

    @Override
    protected String getImagePath() {

        return "src/resources/brick_strong.png";
    }

    @Override
    void hit() {

        super.hit();

        // after the first hit, show the cracked picture
        if (hitsLeft == 1) {

            loadImage("src/resources/brick_strong_cracked.png");
        }
    }
}
