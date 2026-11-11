package com.zetcode;

/**
 * Stage 1 - Builder (Concrete Builder).
 *
 * Builds the hard level: rows 1, 3 and 5 are strong bricks and the game
 * runs faster. Every brick is created through the BrickFactory it receives.
 */
public class HardLevelBuilder implements LevelBuilder {

    private Level level;
    private final BrickFactory brickFactory;

    /**
     * The brick factory is passed in (not created here), so the same level
     * layout can be built with any BrickFactory subclass.
     */
    public HardLevelBuilder(BrickFactory brickFactory) {
        this.brickFactory = brickFactory;
        this.level = new Level();
    }

    @Override
    public void buildBricks() {

        // Stage 2 - Composite: the wall is a group of rows, and each row is a
        // group of bricks (wall -> rows -> bricks).
        BrickGroup wall = new BrickGroup();

        for (int i = 0; i < 5; i++) {

            char type = (i % 2 == 0) ? 'S' : 'N';   // rows 1, 3 and 5: strong bricks

            BrickGroup row = new BrickGroup();

            for (int j = 0; j < 6; j++) {

                row.add(brickFactory.createBrick(type, j * 40 + 30, i * 10 + 50));
            }

            wall.add(row);
        }

        level.setWall(wall);
    }

    @Override
    public void buildSpeed() { level.setSpeed(7); }   // faster than the classic level (10 ms)

    @Override
    public Level getLevel() { return this.level; }
}
