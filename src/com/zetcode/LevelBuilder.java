package com.zetcode;

/**
 * Stage 1 - Builder (Builder interface).
 *
 * Declares the steps needed to build a Level, like RobotBuilder in the lecture.
 */
public interface LevelBuilder {

    public void buildBricks();
    public void buildSpeed();
    public Level getLevel();
}
