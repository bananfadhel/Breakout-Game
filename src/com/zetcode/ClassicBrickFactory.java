package com.zetcode;

// Factory Method - Concrete Creator: decides which brick class to create
// a new brick type only needs a new Brick subclass and a new case here
public class ClassicBrickFactory extends BrickFactory {

    @Override
    public Brick createBrick(char type, int x, int y) {

        // 'N' = normal brick, 'S' = strong brick
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
