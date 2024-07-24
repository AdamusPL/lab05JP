package pl.edu.pwr.aczekalski.lab05.model.field;

import javax.swing.*;

public class Score {
    public int x;
    public int y;
    public JLabel label;

    public Score(int x, int y) {
        this.x = x;
        this.y = y;
        this.label = new JLabel("00");
    }
}
