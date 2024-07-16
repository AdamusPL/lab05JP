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

    public ChooseParameters() {
        setBounds(0, 0, 500, 500); //dialog size

        contentPane = new JPanel(); //panel for content
        contentPane.setBounds(20, 10, 70, 150);

        labelRow = new JLabel("number of rows=");
        labelRow.setBounds(20, 10, 100, 10);

        jTextAreaRow = new JTextArea("10"); //under caption - textarea to pass parameter
        jTextAreaRow.setBounds(20, 25, 70, 20);

        contentPane.setLayout(null); //in order to freely choose place of appearance of text/field
        contentPane.add(jTextAreaRow); //add panel
        contentPane.add(labelRow);

        ///////////////////////////////////////////////////

        labelColumns = new JLabel("number of columns="); //other parameters
        labelColumns.setBounds(20, 70, 140, 10);

        jTextAreaColumns = new JTextArea("9");
        jTextAreaColumns.setBounds(20, 85, 70, 20);

        contentPane.add(jTextAreaColumns);
        contentPane.add(labelColumns);

        //////////////////////////////////////////////////

        labelPlayers = new JLabel("number of players=");
        labelPlayers.setBounds(20, 130, 140, 15);

        jTextAreaPlayers = new JTextArea("2");
        jTextAreaPlayers.setBounds(20, 145, 70, 20);

        contentPane.add(jTextAreaPlayers);
        contentPane.add(labelPlayers);

        /////////////////////////////////////////////////

        labelBalls = new JLabel("number of balls=");
        labelBalls.setBounds(20, 190, 140, 10);

        jTextAreaBalls = new JTextArea("3");
        jTextAreaBalls.setBounds(20, 205, 70, 20);

        contentPane.add(jTextAreaBalls);
        contentPane.add(labelBalls);


        /////////////////////////////////////////////////

        JButton jButton = new JButton("Apply"); //button
        jButton.setBounds(20, 300, 70, 20);
        jButton.addActionListener(new ActionListener() { //add conditions for starting game
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isNumber(jTextAreaRow) && isNumber(jTextAreaColumns) && isNumber(jTextAreaPlayers) && isNumber(jTextAreaBalls)) { //check if there are numbers in fields
                    numberOfRows = Integer.parseInt(jTextAreaRow.getText());
                    numberOfColumns = Integer.parseInt(jTextAreaColumns.getText());
                    numberOfPlayers = Integer.parseInt(jTextAreaPlayers.getText());
                    numberOfBalls = Integer.parseInt(jTextAreaBalls.getText());

                    if (numberOfColumns % 2 == 1 && numberOfPlayers >= 2 && numberOfBalls >= 3 && numberOfBalls <= numberOfRows) {
                        dispose(); //close dialog
                        System.out.println("Simulation began");
                    }

                    if (numberOfColumns % 2 == 0)
                        System.out.println("Warning: Number of columns should be the odd number");
                    if (numberOfPlayers < 2) System.out.println("Warning: There must be at least 2 players");
                    if (numberOfBalls < 3 && numberOfBalls > numberOfRows)
                        System.out.println("Warning: There must be at least 3 balls and there can't be more balls than rows");
                } else System.out.println("Incorrect values");
            }
        });

        contentPane.add(jButton);

        setContentPane(contentPane);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null); //set frame in the middle of the screen

    }

    public static boolean isNumber(JTextArea input) { //method checking if there's a number in text field
        try {
            Integer.parseInt(input.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
