package com.zetcode;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Stage 2 - Composite (Composite).
 *
 * A group of BrickComponents: a row of bricks, or the whole wall (a group of
 * rows). Its children can be single bricks or other groups. Each operation is
 * forwarded to all the children, so the tree is handled with one call.
 * add / remove / getChild exist only here (the "safe" Composite): a single
 * Brick does not need them.
 */
public class BrickGroup implements BrickComponent {

    private final List<BrickComponent> children = new ArrayList<>();

    public void add(BrickComponent component) {
        children.add(component);
    }

    public void remove(BrickComponent component) {
        children.remove(component);
    }

    public BrickComponent getChild(int i) {
        return children.get(i);
    }

    @Override
    public void draw(Graphics2D g2d, ImageObserver observer) {

        for (BrickComponent child : children) {
            child.draw(g2d, observer);
        }
    }

    /** The group is destroyed when every child is destroyed. */
    @Override
    public boolean isDestroyed() {

        for (BrickComponent child : children) {

            if (!child.isDestroyed()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void checkCollision(Ball ball) {

        for (BrickComponent child : children) {
            child.checkCollision(ball);
        }
    }
}
