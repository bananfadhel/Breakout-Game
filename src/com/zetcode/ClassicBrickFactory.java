package com.zetcode;

/**
 * Stage 1 - Factory Method (Concrete Creator).
 *
 * Creates the bricks used by the classic game. Adding a new brick type
 * only needs a new Brick subclass and one new case here; Board does not
 * change.
 */
public class ClassicBrickFactory extends BrickFactory {

    @Override
    public Brick createBrick(char type, int x, int y) {

        switch (type) {

            case 'N':
                return new Brick(x, y);

            case 'S':
                return new StrongBrick(x, y);

            default:
                throw new IllegalArgumentException("Unknown brick type: " + type);
        }
    }
}
