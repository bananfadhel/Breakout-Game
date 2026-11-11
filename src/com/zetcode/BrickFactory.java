package com.zetcode;

// Factory Method - Creator: declares the factory method createBrick()
// the level builders call createBrick() and never see the real brick classes
public abstract class BrickFactory {

    // this is the factory method: subclasses decide which brick to create
    public abstract Brick createBrick(char type, int x, int y);
}
