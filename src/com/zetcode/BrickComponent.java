package com.zetcode;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

/**
 * Stage 2 - Composite (Component).
 *
 * The common type of a single brick and of a group of bricks (a row, or the
 * whole wall). Board only knows this interface, so it treats one brick and
 * the whole wall in exactly the same way.
 */
public interface BrickComponent {

    /** Draws this component (a group draws all its parts). */
    void draw(Graphics2D g2d, ImageObserver observer);

    /** A brick is destroyed when it has no hits left; a group when all its parts are. */
    boolean isDestroyed();

    /** Checks the ball against this component (a group checks all its parts). */
    void checkCollision(Ball ball);
}
