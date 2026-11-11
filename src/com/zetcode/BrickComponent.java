package com.zetcode;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

// Composite - Component: the common type for one brick and a group of bricks
// Board only uses this type, so it treats one brick and the whole wall the same way
public interface BrickComponent {

    // draw it (a group draws all its children)
    void draw(Graphics2D g2d, ImageObserver observer);

    // a brick is destroyed when it has no hits left, a group when all its children are
    boolean isDestroyed();

    // check the ball against it (a group checks all its children)
    void checkCollision(Ball ball);
}
