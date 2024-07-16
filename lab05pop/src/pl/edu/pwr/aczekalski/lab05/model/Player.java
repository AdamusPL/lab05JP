package pl.edu.pwr.aczekalski.lab05.model;

import pl.edu.pwr.aczekalski.lab05.logic.PlayerLogic;

import java.util.ArrayList;

public class Player extends Thread {
    public char sign; //player's symbol, f.e. 'a'
    int id; //player's id (value from 0)
    public int positionX; //player's X position (value from 0)
    public int positionY; //player's Y position (value from 0)
    public boolean direction; //moving direction: false - up, true - down
    PlayerLogic playerLogic; //player's logic

    public boolean randomDirection() { //random direction
        boolean direction = false;
        if (Math.random() > 0.5) {
            direction = true;
        }
        return direction;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getPositionX() {
        return positionX;
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
