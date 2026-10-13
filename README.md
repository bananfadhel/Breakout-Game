# Breakout Game – CCSW 413 Design Patterns Project

This is our group project for **CCSW 413 Software Design Patterns** at the University of Jeddah.
We took an open-source Java Breakout game and we're refactoring it using design patterns, two from each category (Creational, Structural, Behavioral), one stage at a time.

Original game by Jan Bodnar: https://github.com/janbodnar/Java-Breakout-Game (BSD 2-Clause License)

![Breakout game screenshot](breakout_game.png)

## Team
- Banan Fadhel
- Enas Alqarni
- Dana Alshahrani
- Jana Akkad
- Joury Ghorab

## Progress
- [x] Stage 1 – Creational: Factory Method + Builder
- [ ] Stage 2 – Structural
- [ ] Stage 3 – Behavioral

## Stage 1 – What we did

### Factory Method (creating bricks)
Before, `Board` created every brick directly with `new Brick(x, y)`.
Now bricks are created through `BrickFactory`, and `ClassicBrickFactory` decides which brick to make:
- `'N'` → normal `Brick` (breaks in 1 hit)
- `'S'` → `StrongBrick` (needs 2 hits, cracks after the first one)

### Builder (building levels)
Before, the whole level was hard-coded inside `Board.gameInit()`.
Now we followed the Robot example from the lecture:
- `Level` is the product (bricks + speed)
- `LevelBuilder` is the builder interface
- `ClassicLevelBuilder` and `HardLevelBuilder` build two different levels
- `LevelEngineer` is the director that calls the building steps
- `Board` just asks for the finished level

The two patterns work together: each builder gets a `BrickFactory` and uses it to create the bricks.

To try the hard level, change this line in `Board.gameInit()`:
```java
new ClassicLevelBuilder(new ClassicBrickFactory())
```
to:
```java
new HardLevelBuilder(new ClassicBrickFactory())
```

## How to run
Run these from the project folder:
```bash
javac -d out src/com/zetcode/*.java
java -cp out com.zetcode.Breakout
```
Use the left and right arrow keys to move the paddle.
