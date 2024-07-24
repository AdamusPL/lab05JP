package pl.edu.pwr.aczekalski.lab05.model.ball;

import pl.edu.pwr.aczekalski.lab05.model.field.Field;
import pl.edu.pwr.aczekalski.lab05.model.field.Score;

import java.util.ArrayList;
import java.util.Random;

public class BallGenerator extends Thread {

    private ArrayList<ArrayList<Field>> fieldLabelsArray;
    private ArrayList<ArrayList<Score>> scoreLabelsArray;
    private ArrayList<Ball> balls;
    private ArrayList<Integer> picked; //arraylist which stores all values previously random-generated in order not to repeat them

    public ArrayList<Integer> getPicked() {
        return picked;
    }

    private int numberOfBalls;

    public BallGenerator(ArrayList<ArrayList<Field>> fieldLabelsArray, ArrayList<ArrayList<Score>> scoreLabelsArray, ArrayList<Ball> balls, int numberOfBalls) {
        this.fieldLabelsArray = fieldLabelsArray;
        this.scoreLabelsArray = scoreLabelsArray;
        this.balls = balls;
        this.numberOfBalls = numberOfBalls;
    }

    private boolean randomDirection() { //random turning direction for ball
        boolean direction = false;
        if (Math.random() > 0.5) {
            direction = true;
        }
        return direction;
    }

    private void putBalls(int numberOfColumns, int numberOfRows) {
        picked = new ArrayList<>();
        for (int i = 0; i < numberOfBalls; i++) {
            BallLogic ballLogic = new BallLogic(fieldLabelsArray, scoreLabelsArray, this);
            Random random = new Random();
            int randomNum = random.nextInt(numberOfRows); //random place in which ball will appear
            if (!picked.contains(randomNum)) {
                picked.add(randomNum);
                int middle = numberOfColumns / 2; //middle column
                Ball ball = new Ball(i, randomDirection(), middle, middle, randomNum, ballLogic);
                ball.start();
                balls.add(ball);
            } else i--;
        }
    }

    private synchronized void putBall(int numberOfColumns, int numberOfRows) {
        int middle = numberOfColumns / 2; //middle column
        for (Ball ball : balls) {
            if (ball.ended) {
                Random random = new Random();
                int randomNum = random.nextInt(numberOfRows); //random place where ball will appear
                while (picked.contains(randomNum)) {
                    randomNum = random.nextInt(numberOfRows); //random place where ball will appear
                }
                picked.add(randomNum);
                ball.positionX = middle;
                ball.positionY = randomNum;
                ball.ended = false;
            }
        }
    }


    @Override
    public void run() {
        putBalls(fieldLabelsArray.get(0).size(), fieldLabelsArray.size());
        while (true) {
            synchronized (this) {
                try {
                    wait(); //thread waits
                } catch (InterruptedException e) {
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
