package pl.edu.pwr.aczekalski.lab05.model;

import pl.edu.pwr.aczekalski.lab05.logic.BallLogic;

import java.util.ArrayList;
import java.util.Random;

public class BallGenerator extends Thread {

    ArrayList<ArrayList<Field>> fieldLabelsArray;
    ArrayList<ArrayList<Score>> scoreLabelsArray;
    ArrayList<Ball> balls;
    public ArrayList<Integer> picked; //arraylista do której będą wkładane wszystkie wartości poprzednio wylosowane, żeby się nie powtarzały

    int numberOfBalls;
    public BallGenerator(ArrayList<ArrayList<Field>> fieldLabelsArray, ArrayList<ArrayList<Score>> scoreLabelsArray, ArrayList<Ball> balls, int numberOfBalls){
        this.fieldLabelsArray=fieldLabelsArray;
        this.scoreLabelsArray=scoreLabelsArray;
        this.balls=balls;
        this.numberOfBalls=numberOfBalls;
    }

    public boolean randomDirection(){ //wylosowanie kierunku poruszania się piłki
        boolean direction = false;
        if(Math.random()>0.5){
            direction=true;
        }
        return direction;
    }

    public void putBalls(int numberOfColumns, int numberOfRows) {
        picked=new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            BallLogic ballLogic = new BallLogic(fieldLabelsArray,scoreLabelsArray,this);
            Random random = new Random();
            int randomNum = random.nextInt(numberOfRows); //losowe miejsce w którym pojawi się piłka
            if (!picked.contains(randomNum)) {
                picked.add(randomNum);
                int middle = numberOfColumns / 2; //środkowa kolumna
                Ball ball = new Ball(i,randomDirection(),middle,middle,randomNum,ballLogic);
                ball.start();
                balls.add(ball);
            }
            else i--;
        }
    }

    synchronized void putBall(int numberOfColumns, int numberOfRows) {
        int middle = numberOfColumns / 2; //środkowa kolumna
        for (Ball ball: balls) {
            if(ball.ended) {
                Random random = new Random();
                int randomNum = random.nextInt(numberOfRows); //losowe miejsce w którym pojawi się piłka
                while(picked.contains(randomNum)) {
                    randomNum = random.nextInt(numberOfRows); //losowe miejsce w którym pojawi się piłka
                }
                picked.add(randomNum);
                ball.positionX = middle;
                ball.positionY = randomNum;
                ball.ended = false;
            }
        }
    }


    @Override
    public void run(){
        putBalls(fieldLabelsArray.get(0).size(), fieldLabelsArray.size());
        while(true) {
            synchronized (this) {
                    try {
                        wait(); //wątek czeka
                } catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                putBall(fieldLabelsArray.get(0).size(), fieldLabelsArray.size());
            }
        }
    }

}
