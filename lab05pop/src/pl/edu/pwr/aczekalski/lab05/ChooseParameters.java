package pl.edu.pwr.aczekalski.lab05;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    List<JTextArea> jTextAreaList;

    public ChooseParameters() {
        jTextAreaList = new ArrayList<>();
        setBounds(0, 0, 500, 500); //dialog size

        contentPane = new JPanel(); //panel for content
        contentPane.setBounds(20, 10, 70, 150);
        contentPane.setLayout(null); //in order to freely choose place of appearance of text/field

        setupLabel("number of rows=", 20, 10, 100, 10, "10",
                20, 25, 70, 20);

        setupLabel("number of columns=", 20, 70, 140, 10, "9",
                20, 85, 70, 20);

        setupLabel("number of players=", 20, 130, 140, 15, "2",
                20, 145, 70, 20);

        setupLabel("number of balls=", 20, 190, 140, 10, "3",
                20, 205, 70, 20);

        JButton jButton = new JButton("Apply"); //button
        jButton.setBounds(20, 300, 70, 20);
        jButton.addActionListener(new ActionListener() { //add conditions for starting game
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isNumber(jTextAreaList.get(0)) && isNumber(jTextAreaList.get(1)) && isNumber(jTextAreaList.get(2)) && isNumber(jTextAreaList.get(3))) { //check if there are numbers in fields
                    numberOfRows = Integer.parseInt(jTextAreaList.get(0).getText());
                    numberOfColumns = Integer.parseInt(jTextAreaList.get(1).getText());
                    numberOfPlayers = Integer.parseInt(jTextAreaList.get(2).getText());
                    numberOfBalls = Integer.parseInt(jTextAreaList.get(3).getText());

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

    private void setupLabel(String text, int xLabel, int yLabel, int widthLabel, int heightLabel, String defaultValue,
                            int xTextArea, int yTextArea, int widthTextArea, int heightTextArea) {
        JLabel label = new JLabel(text);
        label.setBounds(xLabel, yLabel, widthLabel, heightLabel);

        JTextArea jTextArea = new JTextArea(defaultValue); //under caption - textarea to pass parameter
        jTextArea.setBounds(xTextArea, yTextArea, widthTextArea, heightTextArea);

        contentPane.add(jTextArea); //add panel
        contentPane.add(label);
        jTextAreaList.add(jTextArea);
    }

    private static boolean isNumber(JTextArea input) { //method checking if there's a number in text field
        try {
            Integer.parseInt(input.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
