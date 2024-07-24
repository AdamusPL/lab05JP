package pl.edu.pwr.aczekalski.lab05.model.player;

import java.util.ArrayList;

public class Player extends Thread {
    private char sign; //player's symbol, f.e. 'a'
    private int id; //player's id (value from 0)
    private int positionX; //player's X position (value from 0)
    private int positionY; //player's Y position (value from 0)
    private boolean direction; //moving direction: false - up, true - down
    private PlayerLogic playerLogic; //player's logic

    public char getSign() {
        return sign;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    private boolean randomDirection() { //random direction
        boolean direction = false;
        if (Math.random() > 0.5) {
            direction = true;
        }
        return direction;
    }

    public Player(char sign, int id, int positionX, int positionY, PlayerLogic playerLogic, ArrayList<Player> players) {
        this.sign = sign;
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = randomDirection();
        this.playerLogic = playerLogic;
    }

    @Override
    public void run() {
        while (true) {
            playerLogic.move(this); //move player
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
