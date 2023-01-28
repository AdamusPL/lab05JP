package pl.edu.pwr.aczekalski.lab05.model;

import pl.edu.pwr.aczekalski.lab05.logic.PlayerLogic;

import java.util.ArrayList;

public class Player extends Thread {
    public char sign; //oznaczenie gracza np. a
    int id; //id gracza (wartość od 0)
    public int positionX; //pozycja X gracza (wartość od 0)
    public int positionY; //pozycja Y gracza (wartość od 0)
    public boolean direction; //kierunek gracza false - idzie do góry, true - idzie w dół
    PlayerLogic playerLogic; //logika gracza

    ArrayList<Player> players; //arraylista graczy z głównej metody

    public boolean randomDirection(){ //losowy kierunek
        boolean direction = false;
        if(Math.random()>0.5){
            direction=true;
        }
        return direction;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public Player(char sign, int id, int positionX, int positionY, PlayerLogic playerLogic, ArrayList<Player> players){
        this.sign=sign;
        this.id=id;
        this.positionX=positionX;
        this.positionY=positionY;
        this.direction=randomDirection();
        this.players=players;
        this.playerLogic=playerLogic;
    }

    @Override
    public void run() {
        while (true) {
            playerLogic.move(this); //porusz graczem
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
