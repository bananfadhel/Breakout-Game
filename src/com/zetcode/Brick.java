package com.zetcode;

import javax.swing.ImageIcon;

/**
 * A normal brick: disappears after one hit.
 *
 * Refactoring (Stage 1 - Factory Method): Brick is now the base "Product"
 * type. Instead of a single destroyed flag it keeps the number of hits
 * left, so subclasses such as StrongBrick can need more than one hit.
 * Bricks are no longer created directly by Board; they are created by a
 * BrickFactory.
 */
public class Brick extends Sprite {

    protected int hitsLeft;

    public Brick(int x, int y) {

        this(x, y, 1);
    }

    protected Brick(int x, int y, int hits) {

        this.x = x;
        this.y = y;
        this.hitsLeft = hits;

        loadImage(getImagePath());
        getImageDimensions();
    }

    /** Subclasses override this to use a different picture. */
    protected String getImagePath() {

        return "src/resources/brick.png";
    }

    protected void loadImage(String path) {

        var ii = new ImageIcon(path);
        image = ii.getImage();
    }

    boolean isDestroyed() {

        return hitsLeft <= 0;
    }

    /** Called by Board when the ball hits this brick. */
    void hit() {

        if (hitsLeft > 0) {

            hitsLeft--;
        }
    }
}
