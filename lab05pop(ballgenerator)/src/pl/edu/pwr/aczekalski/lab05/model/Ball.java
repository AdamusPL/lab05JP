package pl.edu.pwr.aczekalski.lab05.model;

import pl.edu.pwr.aczekalski.lab05.logic.BallLogic;

public class Ball extends Thread{
    int id; //id piłki (wartość od 0)
    int startPositionX; //pozycja startowa piłki
    public int positionX; //pozycja X piłki (wartość od 0)
    public int positionY; //pozycja Y piłki (wartość od 0)
    BallLogic ballLogic; //logika piłki
    public boolean direction; //kierunek false - prawo, true - lewo
    public boolean ended = false;

    public Ball(int id, boolean direction, int startPosition, int positionX, int positionY, BallLogic ballLogic){
        this.id=id;
        this.startPositionX =startPosition;
        this.positionX=positionX;
        this.positionY=positionY;
        this.ballLogic=ballLogic;
        this.direction=direction;
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
