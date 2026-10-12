package com.zetcode;

/**
 * Stage 1 - Builder (Product).
 *
 * A level of the game: its wall of bricks and its speed (timer period).
 * It is built step by step by a LevelBuilder.
 */
public class Level {

    private Brick[] bricks;
    private int speed;

    public void setBricks(Brick[] bricks) { this.bricks = bricks; }
    public void setSpeed(int speed) { this.speed = speed; }

    public Brick[] getBricks() { return bricks; }
    public int getSpeed() { return speed; }
}
