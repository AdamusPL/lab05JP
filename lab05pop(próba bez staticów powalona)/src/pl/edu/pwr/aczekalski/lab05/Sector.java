package pl.edu.pwr.aczekalski.lab05;

import java.util.ArrayList;

public class Sector {
    ArrayList<Sector> sectors = new ArrayList<>();
    int x;
    int y;
    private boolean isOccupied = false;

    public synchronized boolean set(){
        if(isOccupied) return false;

        isOccupied = true;
        return true;
    }

    public synchronized void unSet(){
        if(!isOccupied) return;

        isOccupied = false;
    }

    public Sector(int x, int y, boolean isOccupied){
        this.x=x;
        this.y=y;
        this.isOccupied=isOccupied;
    }
}
