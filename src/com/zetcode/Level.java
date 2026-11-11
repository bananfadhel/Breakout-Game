package com.zetcode;

/**
 * Stage 1 - Builder (Product).
 *
 * A level of the game: its wall of bricks and its speed (timer period).
 * Stage 2 (Composite): the wall is one BrickComponent (a tree of groups of bricks).
 * It is built step by step by a LevelBuilder.
 */
public class Level {

    private BrickComponent wall;
    private int speed;

    public void setWall(BrickComponent wall) { this.wall = wall; }
    public void setSpeed(int speed) { this.speed = speed; }

    public BrickComponent getWall() { return wall; }
    public int getSpeed() { return speed; }
}
