package com.zetcode;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private Timer timer;
    private String message = "Game Over";
    private Ball ball;
    private Paddle paddle;
    private BrickComponent wall;   // Stage 2 - Composite: the whole wall is one component
    private boolean inGame = false;
    private boolean inMenu = true;     // the game starts on the level menu

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        // the game now starts on the menu; gameInit() runs when the player picks a level
    }

    // the player picked a level in the menu, so we start the game with that builder
    private void gameInit(LevelBuilder builder) {

        ball = new Ball();
        paddle = new Paddle();

        // Stage 1 - Builder: the level is built by LevelEngineer (director)
        // using the LevelBuilder the player chose in the menu.
        LevelEngineer engineer = new LevelEngineer(builder);
        engineer.makeLevel();
        Level level = engineer.getLevel();

        wall = level.getWall();

        message = "Game Over";
        inMenu = false;
        inGame = true;

        timer = new Timer(level.getSpeed(), new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inMenu) {

            drawMenu(g2d);
        } else if (inGame) {

            drawObjects(g2d);
        } else {

            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {

        // Stage 2 - Flyweight: each sprite draws the shared picture at its own x, y.
        // Stage 2 - Composite: one call draws the whole wall.
        ball.draw(g2d, this);
        paddle.draw(g2d, this);
        wall.draw(g2d, this);
    }

    // the start screen where the player picks the level
    private void drawMenu(Graphics2D g2d) {

        g2d.setColor(Color.BLACK);
        drawCentered(g2d, "BREAKOUT", new Font("Verdana", Font.BOLD, 26), 130);
        drawCentered(g2d, "Choose a level", new Font("Verdana", Font.PLAIN, 14), 185);
        drawCentered(g2d, "1  -  Classic", new Font("Verdana", Font.BOLD, 16), 225);
        drawCentered(g2d, "2  -  Hard", new Font("Verdana", Font.BOLD, 16), 255);
    }

    private void gameFinished(Graphics2D g2d) {

        g2d.setColor(Color.BLACK);
        drawCentered(g2d, message, new Font("Verdana", Font.BOLD, 18), Commons.WIDTH / 2);
        drawCentered(g2d, "Press ENTER for the menu", new Font("Verdana", Font.PLAIN, 12),
                Commons.WIDTH / 2 + 35);
    }

    // writes a line of text in the middle of the screen
    private void drawCentered(Graphics2D g2d, String text, Font font, int y) {

        FontMetrics fontMetrics = this.getFontMetrics(font);
        g2d.setFont(font);
        g2d.drawString(text, (Commons.WIDTH - fontMetrics.stringWidth(text)) / 2, y);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            if (inGame) {

                paddle.keyReleased(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inMenu) {

                // the player chooses the level: 1 = classic builder, 2 = hard builder
                if (key == KeyEvent.VK_1 || key == KeyEvent.VK_NUMPAD1) {

                    gameInit(new ClassicLevelBuilder(new ClassicBrickFactory()));
                } else if (key == KeyEvent.VK_2 || key == KeyEvent.VK_NUMPAD2) {

                    gameInit(new HardLevelBuilder(new ClassicBrickFactory()));
                }
            } else if (inGame) {

                paddle.keyPressed(e);
            } else if (key == KeyEvent.VK_ENTER) {

                // after game over or victory, Enter goes back to the menu
                inMenu = true;
                repaint();
            }
        }
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private void doGameCycle() {

        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {

        inGame = false;
        timer.stop();
    }

    private void checkCollision() {

        if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {

            stopGame();
        }

        // Stage 2 - Composite: one call asks all the rows and bricks if they are destroyed.
        if (wall.isDestroyed()) {

            message = "Victory";
            stopGame();
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {

                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {

                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {

                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {

                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {

                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        // Stage 2 - Composite: one call checks the ball against every brick.
        wall.checkCollision(ball);
    }
}
