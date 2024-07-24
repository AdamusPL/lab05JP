package pl.edu.pwr.aczekalski.lab05;

import pl.edu.pwr.aczekalski.lab05.model.ball.Ball;
import pl.edu.pwr.aczekalski.lab05.model.ball.BallGenerator;
import pl.edu.pwr.aczekalski.lab05.model.field.Field;
import pl.edu.pwr.aczekalski.lab05.model.field.Score;
import pl.edu.pwr.aczekalski.lab05.model.player.MakePlayers;
import pl.edu.pwr.aczekalski.lab05.model.player.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class UserInterface extends JFrame {
    private ChooseParameters chooseParameters;
    private JPanel contentPane;
    private JPanel simPanel;

    public ChooseParameters getChooseParameters() {
        return chooseParameters;
    }

    private ArrayList<Ball> balls; //ball arraylist
    private ArrayList<Player> playersTeam1; //team 1 players arraylist
    private ArrayList<Player> playersTeam2; //team 2 players arraylist
    private MakePlayers makePlayers;
    private BallGenerator ballGenerator;
    private ArrayList<ArrayList<Field>> fieldLabelsArray; //field arraylist
    private ArrayList<ArrayList<Score>> scoreLabelsArray; //score arraylist

    private int numberOfPlayers, numberOfBalls, numberOfColumns, numberOfRows;

    public UserInterface() {
        runChoosingParameterWindow();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //frame in the middle of the screen
        setAlwaysOnTop(true);
        setResizable(false);
        setVisible(true);
        setBounds(0, 0, 700, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); //to move panels wherever you want in frame

        simPanel = new JPanel();
        simPanel.setBounds(350, 250, 340, 240);
        simPanel.setLayout(null);

        setupField();
        setupObjects();

        contentPane.add(simPanel, BorderLayout.CENTER);
        setContentPane(contentPane);
    }

    private void setupField(){
        this.fieldLabelsArray = new ArrayList<>();
        this.scoreLabelsArray = new ArrayList<>();

        int y = 5;
        for (int i = 0; i < numberOfRows; i++) {
            int x = 5;
            ArrayList<Score> rowScore = makeRowScore(x, y, numberOfColumns);
            scoreLabelsArray.add(rowScore);
            y += 10;
        }

        for (ArrayList<Score> rowArray : scoreLabelsArray) {
            for (Score s : rowArray) {
                JLabel label = s.label;
                label.setBounds(s.x, s.y, 15, 10);
                simPanel.add(label);
            }
        }

        y = 5;
        for (int i = 0; i < numberOfRows; i++) {
            int x = 5;
            ArrayList<Field> rowLabel = makeRowField(x, y, numberOfColumns);
            fieldLabelsArray.add(rowLabel);
            y += 10;
        }

        for (ArrayList<Field> rowArray : fieldLabelsArray) {
            for (Field f : rowArray) {
                JLabel label = f.getLabel();
                label.setBounds(f.getX(), f.getY(), 10, 10);
                simPanel.add(label);
            }
        }
    }

    private void setupObjects(){
        makePlayers = new MakePlayers(fieldLabelsArray);

        balls = new ArrayList<>();
        ballGenerator = new BallGenerator(fieldLabelsArray, scoreLabelsArray, balls, numberOfBalls);
        ballGenerator.start();
        playersTeam1 = makePlayers.putPlayers(numberOfPlayers, numberOfRows, 0);
        playersTeam2 = makePlayers.putPlayers(numberOfPlayers, numberOfRows, numberOfColumns - 1);
    }

    private ArrayList<Score> makeRowScore(int x, int y, int numberOfColumns) {
        ArrayList<Score> rowScore = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Score score = new Score(x, y);
            rowScore.add(score);
            x += (numberOfColumns + 1) * 15;
        }
        return rowScore;
    }

    private ArrayList<Field> makeRowField(int x, int y, int numberOfColumns) {
        ArrayList<Field> rowField = new ArrayList<>();
        for (int i = 0; i < numberOfColumns; i++) {
            x += 15;
            boolean isOccupied = false;
            Field field = new Field(x, y, isOccupied);
            rowField.add(field);
        }
        return rowField;
    }

    private Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                simPanel.repaint(); //update UI
                simPanel.revalidate();
                try {
                    Thread.sleep(1000); //wait, to synchronize everything
                } catch (Exception e) {
                }
            }
        }
    });

    public void startT() {
        t.start();
    }

    private void runChoosingParameterWindow(){
        chooseParameters = new ChooseParameters();
        chooseParameters.setModal(true); //in order that animation won't start before disappearing JDialog
        chooseParameters.setVisible(true);
        this.numberOfColumns = chooseParameters.getNumberOfColumns(); //take parameters from JDialog
        this.numberOfRows = chooseParameters.getNumberOfRows();
        this.numberOfBalls = chooseParameters.getNumberOfBalls();
        this.numberOfPlayers = chooseParameters.getNumberOfPlayers();
    }
}
