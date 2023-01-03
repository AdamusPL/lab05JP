package pl.edu.pwr.aczekalski.lab05;

import javax.swing.*;
import java.util.ArrayList;

public class BallLogic {
    ArrayList<ArrayList<Field>> fieldLabelsArray; //arraylista pola przekazana z głównej metody
    ArrayList<Player> players;

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public BallLogic(ArrayList<ArrayList<Field>> fieldLabelsArray){
        this.fieldLabelsArray=fieldLabelsArray;
    }

    synchronized void move(Ball ball) {
        if (ball.direction == -1) {
            if (ball.positionX > 0) {
                ball.positionX--; //ruszaj w lewo
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText("#");
                fieldLabelsArray.get(ball.positionY).get(ball.positionX+1).label.setText(".");
            } else {
                ball.direction = 1; //zmień kierunek lotu
                ball.positionX++;
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText("#");
                fieldLabelsArray.get(ball.positionY).get(ball.positionX-1).label.setText(".");
            }
        } else {
            if (ball.positionX < fieldLabelsArray.get(0).size()-1) {
                ball.positionX++; //ruszaj w prawo
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText("#");
                fieldLabelsArray.get(ball.positionY).get(ball.positionX-1).label.setText(".");
            } else {
                ball.direction = -1; //zmień kierunek lotu
                ball.positionX--;
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText("#");
                fieldLabelsArray.get(ball.positionY).get(ball.positionX+1).label.setText(".");
            }
        }
    }
}
