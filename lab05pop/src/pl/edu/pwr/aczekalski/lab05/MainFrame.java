/**
 * @author Adam Czekalski
 *
 * dir /b /s *.java > sources.txt <- skopiowanie ścieżek klas do pliku txt
 * javac -d bin --module-path (sciezka do modulu) @sources.txt <- kompilacja
 * jar cvf lab05pop.jar -C bin . <- stworzenie jara
 * java -p (sciezka do modulu) -m pl.edu.pwr.aczekalski.lab05/pl.edu.pwr.aczekalski.lab05.MainFrame <- uruchomienie programu
 *
 */
package pl.edu.pwr.aczekalski.lab05;

import pl.edu.pwr.aczekalski.lab05.model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame{
        private JPanel contentPane;

        int numberOfPlayers, numberOfBalls, numberOfColumns, numberOfRows;

        public void setNumberOfPlayers(int numberOfPlayers) {
            this.numberOfPlayers = numberOfPlayers;
        }

        public void setNumberOfBalls(int numberOfBalls) {
            this.numberOfBalls = numberOfBalls;
        }

        public void setNumberOfColumns(int numberOfColumns) {
            this.numberOfColumns = numberOfColumns;
        }

        public void setNumberOfRows(int numberOfRows) {
            this.numberOfRows = numberOfRows;
        }

        public static void main(String[] args) {
            ChooseParameters chooseParameters = new ChooseParameters();
            chooseParameters.setModal(true); //żeby animacja nie wystartowała zanim zniknie JDialog
            chooseParameters.setVisible(true);
            int numberOfPlayers, numberOfBalls, numberOfColumns, numberOfRows;
            numberOfColumns = chooseParameters.getNumberOfColumns(); //pobranie parametrów z JDialoga
            numberOfRows = chooseParameters.getNumberOfRows();
            numberOfBalls = chooseParameters.getNumberOfBalls();
            numberOfPlayers = chooseParameters.getNumberOfPlayers();

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainFrame mainFrame = new MainFrame(numberOfColumns, numberOfRows, numberOfBalls, numberOfPlayers); //przekazanie parametrów do konstruktora ramki
                    mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    mainFrame.setLocationRelativeTo(null); //ustawienie ramki na środek ekranu
                    mainFrame.setAlwaysOnTop(true);
                    mainFrame.setResizable(false);
                    mainFrame.setVisible(true);
                    mainFrame.setNumberOfBalls(numberOfBalls);
                    mainFrame.setNumberOfPlayers(numberOfPlayers);
                    mainFrame.setNumberOfRows(numberOfRows);
                    mainFrame.setNumberOfColumns(numberOfColumns);
                    if (!chooseParameters.isVisible()) { //jeśli JDialog zniknie, niech animacja się zacznie
                        mainFrame.startT();
                    }
                }
            });
        }

        JPanel simPanel;
        ArrayList<Ball> balls; //arraylista piłek

        ArrayList<Player> playersTeam1; //arraylista graczy z teamu 1
        ArrayList<Player> playersTeam2; //arraylista graczy z teamu 2
        MakePlayers makePlayers;
        BallGenerator ballGenerator;

    ArrayList<ArrayList<Field>> fieldLabelsArray=new ArrayList<>(); //arraylista pola gry
    ArrayList<ArrayList<Score>> scoreLabelsArray=new ArrayList<>(); //arraylista wyników

    public ArrayList<Score> makeRowScore(int x, int y, int numberOfColumns){
        ArrayList<Score> rowScore = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Score score = new Score(x,y);
            rowScore.add(score);
            x+=(numberOfColumns+1)*15;
        }
        return rowScore;
    }

    public ArrayList<Field> makeRowField(int x, int y, int numberOfColumns) {
        ArrayList<Field> rowField = new ArrayList<>();
        for (int i = 0; i < numberOfColumns; i++) {
            x += 15;
            boolean isOccupied=false;
            Field field = new Field(x,y,isOccupied);
            rowField.add(field);
        }
        return rowField;
    }

        public MainFrame(int numberOfColumns, int numberOfRows, int numberOfBalls, int numberOfPlayers) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(0, 0, 700, 500);

            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setLayout(null); //zeby mozna bylo dowolnie ustawiac miejsca panelow w ramce

            simPanel = new JPanel();
            simPanel.setBounds(350, 250, 340, 240);
            simPanel.setLayout(null);

            int y=5;
            for(int i=0; i<numberOfRows; i++) {
                int x=5;
                ArrayList<Score>rowScore=makeRowScore(x,y,numberOfColumns);
                scoreLabelsArray.add(rowScore);
                y += 10;
            }

            for (ArrayList<Score> rowArray: scoreLabelsArray) {
                for (Score s: rowArray){
                    JLabel label = s.label;
                    label.setBounds(s.x,s.y,15,10);
                    simPanel.add(label);
                }
            }

            y=5;
            for(int i=0; i<numberOfRows; i++) {
                int x=5;
                ArrayList<Field>rowLabel= makeRowField(x,y,numberOfColumns);
                fieldLabelsArray.add(rowLabel);
                y += 10;
            }

            for (ArrayList<Field> rowArray: fieldLabelsArray) {
                for (Field f: rowArray){
                    JLabel label = f.label;
                    label.setBounds(f.x,f.y,10,10);
                    simPanel.add(label);
                }
            }

            makePlayers = new MakePlayers(fieldLabelsArray);

            balls=new ArrayList<>();
            ballGenerator= new BallGenerator(fieldLabelsArray,scoreLabelsArray,balls,numberOfBalls);
            ballGenerator.start();
            playersTeam1=makePlayers.putPlayers(numberOfPlayers,numberOfColumns,numberOfRows,0);
            playersTeam2=makePlayers.putPlayers(numberOfPlayers,numberOfColumns,numberOfRows,numberOfColumns-1);

            contentPane.add(simPanel, BorderLayout.CENTER);
            setContentPane(contentPane);
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    simPanel.repaint(); //odmalowanie i zrevalidowanie panelu
                    simPanel.revalidate();
                    try {
                        Thread.sleep(1000); //wait, żeby powstała "blokada" na sekundę żeby się wszystko zsynchronizowało
                    } catch (Exception e) {
                    }
                }
            }
        });
        public void startT(){
            t.start();
        }
}
