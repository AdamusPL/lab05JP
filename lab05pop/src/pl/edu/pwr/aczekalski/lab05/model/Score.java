package pl.edu.pwr.aczekalski.lab05.model;

import javax.swing.*;

public class Score {
    public int x;
    public int y;
    public JLabel label = new JLabel("00");

    public Score(int x, int y){
        this.x=x;
        this.y=y;
    }
}
