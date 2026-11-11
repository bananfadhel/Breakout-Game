package com.zetcode;

import java.awt.Point;
import java.awt.Rectangle;

public class Ball extends Sprite {

    private int xdir;
    private int ydir;

    public Ball() {

        initBall();
    }

    private void initBall() {
        
        xdir = 1;
        ydir = -1;

        loadImage("src/resources/ball.png");
        resetState();
    }

    void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {

            setXDir(1);
        }

        if (x == Commons.WIDTH - getImageWidth()) {

            System.out.println(getImageWidth());
            setXDir(-1);
        }

        if (y == 0) {

            setYDir(1);
        }
    }

    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
    }

    void setXDir(int x) {

        xdir = x;
    }

    void setYDir(int y) {

        ydir = y;
    }

    int getYDir() {

        return ydir;
    }

    /**
     * Stage 2 (moved from Board.checkCollision): changes the direction of the
     * ball when it hits the given rectangle, depending on the side it touched.
     */
    void bounceOff(Rectangle target) {

        int ballLeft = (int) getRect().getMinX();
        int ballHeight = (int) getRect().getHeight();
        int ballWidth = (int) getRect().getWidth();
        int ballTop = (int) getRect().getMinY();

        var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
        var pointLeft = new Point(ballLeft - 1, ballTop);
        var pointTop = new Point(ballLeft, ballTop - 1);
        var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

        if (target.contains(pointRight)) {

            setXDir(-1);
        } else if (target.contains(pointLeft)) {

            setXDir(1);
        }

        if (target.contains(pointTop)) {

            setYDir(1);
        } else if (target.contains(pointBottom)) {

            setYDir(-1);
        }
    }
}
