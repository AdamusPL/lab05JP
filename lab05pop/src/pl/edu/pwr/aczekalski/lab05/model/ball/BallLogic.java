package pl.edu.pwr.aczekalski.lab05.model.ball;

import pl.edu.pwr.aczekalski.lab05.model.field.Field;
import pl.edu.pwr.aczekalski.lab05.model.field.Score;

import java.util.ArrayList;

public class BallLogic {
    private ArrayList<ArrayList<Field>> fieldLabelsArray; //field arraylist from main method
    private ArrayList<ArrayList<Score>> scoreLabelsArray; //field arraylist from main method
    private final BallGenerator ballGenerator;

    public BallLogic(ArrayList<ArrayList<Field>> fieldLabelsArray, ArrayList<ArrayList<Score>> scoreLabelsArray, BallGenerator ballGenerator) {
        this.fieldLabelsArray = fieldLabelsArray;
        this.scoreLabelsArray = scoreLabelsArray;
        this.ballGenerator = ballGenerator;
    }

    protected void move(Ball ball) {
        if (!ball.direction && canMove(ball.positionX + 1)) {
            if (fieldLabelsArray.get(ball.positionY).get(ball.positionX + 1).set()) {
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                ball.positionX += 1;
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).getLabel().setText("#");
                fieldLabelsArray.get(ball.positionY).get(ball.positionX - 1).getLabel().setText(".");

                if (ball.positionX == fieldLabelsArray.get(0).size() - 1) {
                    System.out.println("Goal!");
                    String score = scoreLabelsArray.get(ball.positionY).get(1).label.getText();
                    int scoreInt = Integer.parseInt(score);
                    scoreInt++;
                    String updatedScore = Integer.toString(scoreInt);
                    scoreLabelsArray.get(ball.positionY).get(1).label.setText(updatedScore);

                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).getLabel().setText(".");

                    ball.ended = true;

                    for (int i = 0; i < ballGenerator.getPicked().size(); i++) {
                        if (ballGenerator.getPicked().get(i) == ball.positionY) {
                            ballGenerator.getPicked().remove(i);
                            break;
                        }
                    }

                    synchronized (ballGenerator) {
                        ballGenerator.notify();
                    }
                }
                return;
            }
        }
        if (ball.direction && canMove(ball.positionX - 1)) {
            if (fieldLabelsArray.get(ball.positionY).get(ball.positionX - 1).set()) {
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                ball.positionX -= 1;
                fieldLabelsArray.get(ball.positionY).get(ball.positionX).getLabel().setText("#");
                fieldLabelsArray.get(ball.positionY).get(ball.positionX + 1).getLabel().setText(".");

                if (ball.positionX == 0) {
                    System.out.println("Goal!");
                    String score = scoreLabelsArray.get(ball.positionY).get(0).label.getText();
                    int scoreInt = Integer.parseInt(score);
                    scoreInt++;
                    String updatedScore = Integer.toString(scoreInt);
                    scoreLabelsArray.get(ball.positionY).get(0).label.setText(updatedScore);

                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).unSet();
                    fieldLabelsArray.get(ball.positionY).get(ball.positionX).getLabel().setText(".");

                    ball.ended = true;

                    for (int i = 0; i < ballGenerator.getPicked().size(); i++) {
                        if (ballGenerator.getPicked().get(i) == ball.positionY) {
                            ballGenerator.getPicked().remove(i);
                            break;
                        }
                    }

                    synchronized (ballGenerator) {
                        ballGenerator.notify();
                    }
                }
                return;
            }
        }
        ball.direction = !ball.direction;
    }

    private synchronized boolean canMove(int pos) {
        return !(pos < 0 || pos >= fieldLabelsArray.get(0).size());
    }
}
