package com.zetcode;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

// Composite - Composite: a group of bricks, like one row or the whole wall
// every call is passed to all the children, so the whole wall is handled with one call
public class BrickGroup implements BrickComponent {

    // the children can be single bricks or other groups
    private final List<BrickComponent> children = new ArrayList<>();

    // add / remove / getChild are only here, a single brick does not need them
    public void add(BrickComponent component) {
        children.add(component);
    }

    public void remove(BrickComponent component) {
        children.remove(component);
    }

    public BrickComponent getChild(int i) {
        return children.get(i);
    }

    // draw all the children
    @Override
    public void draw(Graphics2D g2d, ImageObserver observer) {

        for (BrickComponent child : children) {
            child.draw(g2d, observer);
        }
    }

    // the group is destroyed only when all its children are destroyed
    @Override
    public boolean isDestroyed() {

        for (BrickComponent child : children) {

            if (!child.isDestroyed()) {
                return false;
            }
        }

        return true;
    }

    // check the ball against all the children
    @Override
    public void checkCollision(Ball ball) {

        for (BrickComponent child : children) {
            child.checkCollision(ball);
        }
    }
}
