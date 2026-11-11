package com.zetcode;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

/**
 * A normal brick: disappears after one hit.
 *
 * Stage 1 (Factory Method): Brick is the base "Product" type created by a
 * BrickFactory; it keeps the number of hits left so subclasses such as
 * StrongBrick can need more than one hit.
 *
 * Stage 2 (Composite): Brick is the Leaf. It implements BrickComponent and
 * now draws itself and checks its own collision with the ball (this logic was
 * in Board before).
 * Stage 2 (Flyweight): the picture comes from SpriteImageFactory (shared).
 */
public class Brick extends Sprite implements BrickComponent {

    protected int hitsLeft;

    /** true while the ball overlaps this brick, so one contact counts only one hit. */
    private boolean touchingBall = false;

    public Brick(int x, int y) {

        this(x, y, 1);
    }

    protected Brick(int x, int y, int hits) {

        this.x = x;
        this.y = y;
        this.hitsLeft = hits;

        loadImage(getImagePath());
    }

    /** Subclasses override this to use a different picture. */
    protected String getImagePath() {

        return "src/resources/brick.png";
    }

    @Override
    public boolean isDestroyed() {

        return hitsLeft <= 0;
    }

    /** Called when the ball hits this brick. */
    void hit() {

        if (hitsLeft > 0) {

            hitsLeft--;
        }
    }

    @Override
    public void draw(Graphics2D g2d, ImageObserver observer) {

        if (!isDestroyed()) {

            super.draw(g2d, observer);
        }
    }

    /**
     * A brick counts a hit only when the ball first touches it, so a
     * StrongBrick loses one hit per collision (not one hit per frame).
     */
    @Override
    public void checkCollision(Ball ball) {

        if (ball.getRect().intersects(getRect())) {

            if (!isDestroyed() && !touchingBall) {

                ball.bounceOff(getRect());
                hit();
            }

            touchingBall = true;
        } else {

            touchingBall = false;
        }
    }
}
