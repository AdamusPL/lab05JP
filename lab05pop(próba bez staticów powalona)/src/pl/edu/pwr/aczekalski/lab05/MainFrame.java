package pl.edu.pwr.aczekalski.lab05;

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
        MakeBalls makeBalls;

    ArrayList<ArrayList<Field>> fieldLabelsArray=new ArrayList<>();

    public ArrayList<Field> makeRow(int x, int y, int numberOfColumns) {
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
                ArrayList<Field>rowLabel=makeRow(x,y,numberOfColumns);
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

            makeBalls = new MakeBalls(fieldLabelsArray);
            makePlayers = new MakePlayers(fieldLabelsArray);
            balls=makeBalls.putBalls(numberOfBalls, numberOfColumns, numberOfRows);
            playersTeam1=makePlayers.putPlayers(numberOfPlayers,numberOfColumns,numberOfRows,0);
            playersTeam2=makePlayers.putPlayers(numberOfPlayers,numberOfColumns,numberOfRows,numberOfColumns-1);


            contentPane.add(simPanel, BorderLayout.CENTER);
            setContentPane(contentPane);
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
            for(Ball b: balls){
                b.start();
            }
            for (Player p : playersTeam1) {
                p.start();
            }
            for (Player p : playersTeam2) {
                p.start();
            }
                while (true) {
                    simPanel.repaint(); //odmalowanie i zrevalidowanie panelu
                    simPanel.revalidate();
                    try {
                        wait(1000); //wait, żeby powstała "blokada" na sekundę żeby się wszystko zsynchronizowało
                    } catch (Exception e) {
                    }
                }
            }
        });
        public void startT(){
            t.start();
        }
}
