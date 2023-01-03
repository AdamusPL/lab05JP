package pl.edu.pwr.aczekalski.lab05;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MakeBalls {
    ArrayList<ArrayList<Field>> fieldLabelsArray;

    public MakeBalls(ArrayList<ArrayList<Field>> fieldLabelsArray){
        this.fieldLabelsArray=fieldLabelsArray;
    }

    public ArrayList<Ball> putBalls(int numberOfBalls, int numberOfColumns, int numberOfRows) {
        ArrayList<Ball> balls = new ArrayList<>();
        ArrayList<Integer> picked = new ArrayList<>(); //arraylista do której będą wkładane wszystkie wartości poprzednio wylosowane, żeby się nie powtarzały
        for (int i = 0; i < numberOfBalls; i++) {
            BallLogic ballLogic = new BallLogic(fieldLabelsArray);
            int randomNum = ThreadLocalRandom.current().nextInt(0, numberOfRows); //losowe miejsce w którym pojawi się piłka
            if (!picked.contains(randomNum)) {
                picked.add(randomNum);
                int middle = numberOfColumns / 2; //środkowa kolumna
                Ball ball = new Ball(i,middle,middle,randomNum,ballLogic);
                balls.add(ball);
            }
            else i--;
        }
        return balls;
    }
}
