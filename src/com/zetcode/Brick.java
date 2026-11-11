package com.zetcode;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

// Factory Method - Product: a normal brick, it breaks after one hit
// Composite - Leaf: a single brick, it draws itself and checks its own collision
// Flyweight: its picture comes from SpriteImageFactory (shared)
public class Brick extends Sprite implements BrickComponent {

    // how many hits the brick still needs
    protected int hitsLeft;

    // true while the ball is touching this brick, so one touch counts as one hit
    private boolean touchingBall = false;

    public Brick(int x, int y) {

        this(x, y, 1);
    }

    // subclasses use this to start with more hits
    protected Brick(int x, int y, int hits) {

        this.x = x;
        this.y = y;
        this.hitsLeft = hits;

        loadImage(getImagePath());
    }

    // subclasses override this to use another picture
    protected String getImagePath() {

        return "src/resources/brick.png";
    }

    // the brick is destroyed when it has no hits left
    @Override
    public boolean isDestroyed() {

        return hitsLeft <= 0;
    }

    // the ball hit the brick, so it loses one hit
    void hit() {

        if (hitsLeft > 0) {

            hitsLeft--;
        }
    }

    // a destroyed brick is not drawn
    @Override
    public void draw(Graphics2D g2d, ImageObserver observer) {

        if (!isDestroyed()) {

            super.draw(g2d, observer);
        }
    }

    // count a hit only when the ball first touches the brick, not every frame
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
