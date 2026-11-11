package com.zetcode;

/**
 * Stage 1 - Builder (Concrete Builder).
 *
 * Builds the classic level: the original 5 x 6 wall with a top row of
 * strong bricks, at the normal speed. Every brick is created through the
 * BrickFactory it receives (Factory Method).
 */
public class ClassicLevelBuilder implements LevelBuilder {

    private Level level;
    private final BrickFactory brickFactory;

    /**
     * The brick factory is passed in (not created here), so the same level
     * layout can be built with any BrickFactory subclass.
     */
    public ClassicLevelBuilder(BrickFactory brickFactory) {
        this.brickFactory = brickFactory;
        this.level = new Level();
    }

    @Override
    public void buildBricks() {

        // Stage 2 - Composite: the wall is a group of rows, and each row is a
        // group of bricks (wall -> rows -> bricks).
        BrickGroup wall = new BrickGroup();

        for (int i = 0; i < 5; i++) {

            char type = (i == 0) ? 'S' : 'N';   // top row: strong bricks

            BrickGroup row = new BrickGroup();

            for (int j = 0; j < 6; j++) {

                row.add(brickFactory.createBrick(type, j * 40 + 30, i * 10 + 50));
            }

            wall.add(row);
        }

        level.setWall(wall);
    }

    @Override
    public void buildSpeed() { level.setSpeed(Commons.PERIOD); }

    @Override
    public Level getLevel() { return this.level; }
}
