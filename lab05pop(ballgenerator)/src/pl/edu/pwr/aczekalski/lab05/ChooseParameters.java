package pl.edu.pwr.aczekalski.lab05;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseParameters extends JDialog {
    private int numberOfPlayers, numberOfBalls, numberOfColumns, numberOfRows;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumberOfBalls() {
        return numberOfBalls;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    private JPanel contentPane;
    private JTextArea jTextAreaColumns, jTextAreaRow, jTextAreaPlayers, jTextAreaBalls;
    private JLabel labelColumns, labelRow, labelPlayers, labelBalls;

    public ChooseParameters(){
        setBounds(0, 0, 500, 500); //wielkość dialogu

        contentPane = new JPanel(); //panel na content
        contentPane.setBounds(20,10,70,150);

        labelRow = new JLabel("number of rows=");
        labelRow.setBounds(20,10,100,10);

        jTextAreaRow=new JTextArea("10"); //pod napisem pole tekstowe do wprowadzenia parametru
        jTextAreaRow.setBounds(20,25,70,20);

        contentPane.setLayout(null); //żeby można było dobrowolnie ustawić miejsce pojawienia się tekstów/pól itd...
        contentPane.add(jTextAreaRow); //dodanie do panelu
        contentPane.add(labelRow);

        ///////////////////////////////////////////////////


        labelColumns = new JLabel("number of columns="); //analogicznie z innymi parametrami
        labelColumns.setBounds(20,70,140,10);

        jTextAreaColumns=new JTextArea("9");
        jTextAreaColumns.setBounds(20,85,70,20);

        contentPane.add(jTextAreaColumns);
        contentPane.add(labelColumns);

        //////////////////////////////////////////////////

        labelPlayers = new JLabel("number of players=");
        labelPlayers.setBounds(20,130,140,15);

        jTextAreaPlayers=new JTextArea("2");
        jTextAreaPlayers.setBounds(20,145,70,20);

        contentPane.add(jTextAreaPlayers);
        contentPane.add(labelPlayers);

//        /////////////////////////////////////////////////
//
        labelBalls = new JLabel("number of balls=");
        labelBalls.setBounds(20,190,140,10);

        jTextAreaBalls=new JTextArea("3");
        jTextAreaBalls.setBounds(20,205,70,20);

        contentPane.add(jTextAreaBalls);
        contentPane.add(labelBalls);
//
//
//        /////////////////////////////////////////////////
//

        JButton jButton = new JButton("Apply"); //przycisk
        jButton.setBounds(20,300,70,20);
        jButton.addActionListener(new ActionListener() { //dodanie warunków żeby dopiero po kliknięciu przycisku
            @Override
            public void actionPerformed(ActionEvent e) {

                if(isNumber(jTextAreaRow) && isNumber(jTextAreaColumns) && isNumber(jTextAreaPlayers) && isNumber(jTextAreaBalls)) { //sprawdzenie czy w polach są wpisane liczby
                    numberOfRows=Integer.parseInt(jTextAreaRow.getText());
                    numberOfColumns=Integer.parseInt(jTextAreaColumns.getText());
                    numberOfPlayers = Integer.parseInt(jTextAreaPlayers.getText());
                    numberOfBalls = Integer.parseInt(jTextAreaBalls.getText());

                    if(numberOfColumns %2==1 && numberOfPlayers>=2 && numberOfBalls>=3 && numberOfBalls<=numberOfRows){
                        dispose(); //zniknięcie dialogu
                        System.out.println("Simulation began");
                    }

                    if(numberOfColumns%2==0) System.out.println("Uwaga: wybrano parzystą liczbę kolumn, wybierz inną wartość");
                    if(numberOfPlayers<2) System.out.println("Uwaga: za mała liczba graczy, wybierz inną wartość");
                    if(numberOfBalls<3 && numberOfBalls>numberOfRows) System.out.println("Uwaga: zła liczba piłek, wybierz inną wartość"); //żeby d i h nie były poza ramieniem l2
                }
                else System.out.println("Niepoprawne wartości");
            }
        });

        contentPane.add(jButton);

        setContentPane(contentPane);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); //ustawienie ramki na środek ekranu

    }

    public static boolean isNumber(JTextArea input){ //metoda do sprawdzania czy w polu tekstowym użytkownik wprowadził liczbę
        try{
            Integer.parseInt(input.getText());
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

}
