package pl.edu.pwr.aczekalski.lab05.model.field;

import javax.swing.*;

public class Field {
    private int x;
    private int y;
    private JLabel label;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public JLabel getLabel() {
        return label;
    }

    public Field(int x, int y, boolean isOccupied) {
        this.x = x;
        this.y = y;
        this.isOccupied = isOccupied;
        this.label = new JLabel(".");
    }

    private boolean isOccupied; //if field is occupied by different player

    public synchronized boolean set() {
        if (isOccupied) return false;

        isOccupied = true;
        return true;
    }

    public synchronized void unSet() {
        if (!isOccupied) return;

        isOccupied = false;
    }


}
