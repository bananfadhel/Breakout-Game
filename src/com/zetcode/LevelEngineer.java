package com.zetcode;

// Builder - Director: calls the building steps in order (like RobotEngineer in the lecture)
// it works with any LevelBuilder and does not know how the level is made
public class LevelEngineer {

    private LevelBuilder levelBuilder;

    public LevelEngineer(LevelBuilder levelBuilder) {
        this.levelBuilder = levelBuilder;
    }

    public Level getLevel() {
        return this.levelBuilder.getLevel();
    }

    // the director calls the steps in order: bricks first, then speed
    public void makeLevel() {
        this.levelBuilder.buildBricks();
        this.levelBuilder.buildSpeed();
    }
}
