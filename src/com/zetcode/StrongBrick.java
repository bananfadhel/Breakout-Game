package com.zetcode;

/**
 * Stage 1 - Factory Method (Concrete Product).
 *
 * A strong brick needs two hits. After the first hit it shows a cracked
 * picture so the player can see it has been damaged.
 */
public class StrongBrick extends Brick {

    public StrongBrick(int x, int y) {

        super(x, y, 2);
    }

    @Override
    protected String getImagePath() {

        return "src/resources/brick_strong.png";
    }

    @Override
    void hit() {

        super.hit();

        if (hitsLeft == 1) {

            loadImage("src/resources/brick_strong_cracked.png");
        }
    }
}
