package pl.edu.pwr.aczekalski.lab05;

public class Ball extends Thread{
    int id; //id piłki (wartość od 0)
    int startPositionX; //pozycja startowa piłki
    int positionX; //pozycja X piłki (wartość od 0)
    int positionY; //pozycja Y piłki (wartość od 0)
    BallLogic ballLogic; //logika piłki
    int direction; //kierunek

    public int getPositionY() {
        return positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public Ball(int id, int startPosition, int positionX, int positionY, BallLogic ballLogic){
        this.id=id;
        this.startPositionX =startPosition;
        this.positionX=positionX;
        this.positionY=positionY;
        this.ballLogic=ballLogic;
        this.direction=randomDirection();
    }

    public int randomDirection(){ //wylosowanie kierunku poruszania się piłki
        int direction = 1;
        if(Math.random()>0.5){
            direction=-1;
        }
        return direction;
    }

    @Override
    public void run() {
        while (true) {
            ballLogic.move(this);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
