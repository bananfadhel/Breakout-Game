package com.zetcode;

// Builder - Builder: the steps needed to build a level (like RobotBuilder in the lecture)
public interface LevelBuilder {

    public void buildBricks();
    public void buildSpeed();
    public Level getLevel();
}
