package com.zetcode;

// Builder - Product: one level of the game, with its wall of bricks and its speed
// the wall is one BrickComponent (Composite)
public class Level {

    private BrickComponent wall;
    private int speed;

    public void setWall(BrickComponent wall) { this.wall = wall; }
    public void setSpeed(int speed) { this.speed = speed; }

    public BrickComponent getWall() { return wall; }
    public int getSpeed() { return speed; }
}
