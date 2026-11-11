package com.zetcode;

// Builder - Concrete Builder: builds the classic level (5 x 6 bricks, normal speed)
// every brick is made by the BrickFactory (Factory Method)
public class ClassicLevelBuilder implements LevelBuilder {

    private Level level;
    private final BrickFactory brickFactory;

    // the brick factory is passed in, not created here, so any BrickFactory can be used
    public ClassicLevelBuilder(BrickFactory brickFactory) {
        this.brickFactory = brickFactory;
        this.level = new Level();
    }

    @Override
    public void buildBricks() {

        // Composite: the wall is a group of rows (wall -> rows -> bricks)
        BrickGroup wall = new BrickGroup();

        for (int i = 0; i < 5; i++) {

            char type = (i == 0) ? 'S' : 'N';   // top row: strong bricks

            // each row is a group of bricks
            BrickGroup row = new BrickGroup();

            for (int j = 0; j < 6; j++) {

                // Builder uses Factory Method here: the factory makes each brick
                row.add(brickFactory.createBrick(type, j * 40 + 30, i * 10 + 50));
            }

            // add the row to the wall
            wall.add(row);
        }

        level.setWall(wall);
    }

    // smaller number = faster game (classic uses the normal speed)
    @Override
    public void buildSpeed() { level.setSpeed(Commons.PERIOD); }

    @Override
    public Level getLevel() { return this.level; }
}
