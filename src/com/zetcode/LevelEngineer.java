package com.zetcode;

/**
 * Stage 1 - Builder (Director).
 *
 * Calls the building steps in order, like RobotEngineer in the lecture.
 * It works with any LevelBuilder and does not know how a level is made.
 */
public class LevelEngineer {

    private LevelBuilder levelBuilder;

    public LevelEngineer(LevelBuilder levelBuilder) {
        this.levelBuilder = levelBuilder;
    }

    public Level getLevel() {
        return this.levelBuilder.getLevel();
    }

    public void makeLevel() {
        this.levelBuilder.buildBricks();
        this.levelBuilder.buildSpeed();
    }
}
