package pl.edu.pwr.aczekalski.lab05;

import javax.swing.*;
import java.util.ArrayList;

public class Field {
    int x;
    int y;
    JLabel label = new JLabel(".");

    private int numberOfPlayers, numberOfBalls, numberOfColumns, numberOfRows;

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

    public Field(int x, int y, boolean isOccupied){
        this.x=x;
        this.y=y;
        this.isOccupied=isOccupied;
    }

    private boolean isOccupied = false; //czy pole jest zajmowane przez jakiegoś gracza

    public synchronized boolean set(){
        if(isOccupied) return false;

        isOccupied = true;
        return true;
    }

    public synchronized void unSet(){
        if(!isOccupied) return;

        isOccupied = false;
    }


}
