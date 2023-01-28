package pl.edu.pwr.aczekalski.lab05.logic;

import pl.edu.pwr.aczekalski.lab05.model.Ball;
import pl.edu.pwr.aczekalski.lab05.model.BallGenerator;
import pl.edu.pwr.aczekalski.lab05.model.Field;
import pl.edu.pwr.aczekalski.lab05.model.Score;

import java.util.ArrayList;

public class BallLogic {
    ArrayList<ArrayList<Field>> fieldLabelsArray; //arraylista pola przekazana z głównej metody
    ArrayList<ArrayList<Score>> scoreLabelsArray; //arraylista pola przekazana z głównej metody
    final BallGenerator ballGenerator;

    public BallLogic(ArrayList<ArrayList<Field>> fieldLabelsArray, ArrayList<ArrayList<Score>> scoreLabelsArray, BallGenerator ballGenerator){
        this.fieldLabelsArray=fieldLabelsArray;
        this.scoreLabelsArray=scoreLabelsArray;
        this.ballGenerator=ballGenerator;
    }

    public void move(Ball ball) {
        if (!ball.direction && canMove(ball.positionX + 1)) {
            if (fieldLabelsArray.get(ball.positionY).get(ball.positionX+1).set()) {
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                ball.positionX += 1;
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText("#");
                fieldLabelsArray.get(ball.positionY).get(ball.positionX - 1).label.setText(".");

                if(ball.positionX==fieldLabelsArray.get(0).size()-1){
                    System.out.println("Gol!");
                    String score = scoreLabelsArray.get(ball.positionY).get(1).label.getText();
                    int scoreInt = Integer.parseInt(score);
                    scoreInt++;
                    String updatedScore = Integer.toString(scoreInt);
                    scoreLabelsArray.get(ball.positionY).get(1).label.setText(updatedScore);

                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText(".");

                    ball.ended=true;

                    for (int i = 0; i < ballGenerator.picked.size(); i++) {
                        if(ballGenerator.picked.get(i)==ball.positionY){
                            ballGenerator.picked.remove(i);
                            break;
                        }
                    }

                    synchronized(ballGenerator) {
                        ballGenerator.notify();
                    }
                }
                return;
            }
        }
            if (ball.direction && canMove(ball.positionX-1)) {
                if(fieldLabelsArray.get(ball.positionY).get(ball.positionX-1).set()){
                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                    ball.positionX -= 1;
                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText("#");
                    fieldLabelsArray.get(ball.positionY).get(ball.positionX + 1).label.setText(".");

                    if(ball.positionX==0){
                        System.out.println("Gol!");
                        String score = scoreLabelsArray.get(ball.positionY).get(0).label.getText();
                        int scoreInt = Integer.parseInt(score);
                        scoreInt++;
                        String updatedScore = Integer.toString(scoreInt);
                        scoreLabelsArray.get(ball.positionY).get(0).label.setText(updatedScore);

                        fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                        fieldLabelsArray.get(ball.positionY).get(ball.positionX).label.setText(".");

                        ball.ended=true;

                        for (int i = 0; i < ballGenerator.picked.size(); i++) {
                            if(ballGenerator.picked.get(i)==ball.positionY){
                                ballGenerator.picked.remove(i);
                                break;
                            }
                        }

                        synchronized(ballGenerator) {
                            ballGenerator.notify();
                        }
                    }
                    return;
                }
            }
        ball.direction = !ball.direction;
    }
    public synchronized boolean canMove(int pos){
        return !(pos < 0 || pos >= fieldLabelsArray.get(0).size());
    }
}
