package com.zetcode;

/**
 * Stage 1 - Factory Method (Creator).
 *
 * Declares the factory method createBrick(). Code that needs bricks
 * (Board) only knows this abstract class and the Brick type, never
 * the concrete brick classes. Subclasses decide which Brick class to create.
 */
public abstract class BrickFactory {

    /**
     * Factory method.
     *
     * @param type brick type code ('N' normal, 'S' strong)
     * @param x    x position on the board
     * @param y    y position on the board
     */
    public abstract Brick createBrick(char type, int x, int y);
}
