package pl.edu.pwr.aczekalski.lab05.model;

import pl.edu.pwr.aczekalski.lab05.logic.BallLogic;

public class Ball extends Thread {
    int id; //ball's id (value from 0)
    int startPositionX; //ball's starting position X
    public int positionX; //ball's X position (value from 0)
    public int positionY; //ball's Y position (value from 0)
    BallLogic ballLogic; //ball's logic
    public boolean direction; //turning direction: false - right, true - left
    public boolean ended;

    public Ball(int id, boolean direction, int startPosition, int positionX, int positionY, BallLogic ballLogic) {
        this.id = id;
        this.startPositionX = startPosition;
        this.positionX = positionX;
        this.positionY = positionY;
        this.ballLogic = ballLogic;
        this.direction = direction;
        this.ended = false;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ballLogic.move(this);
        }

    }

}
