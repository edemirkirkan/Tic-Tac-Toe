package com.example.tictactoe;

import android.widget.TextView;
import java.util.ArrayList;

public class Player {
    // Properties
    private String name;
    private ArrayList<TextView> positions;
    private boolean isStart;
    private boolean turn;
    private int color;

    // Constructors
    public Player (String name, int color) {
        this.name = name;
        this.color = color;
        positions = new ArrayList<>();
    }

    // Methods
    public ArrayList<TextView> getPositions() {
        return positions;
    }

    public void addPosition (TextView position) {
        positions.add(position);
    }

    public void removePosition (TextView position) { positions.remove(position); }

    public String getName() {
        return name;
    }

    public void setStart (boolean t) {
        isStart = t;
    }

    public boolean getStart() {
        return isStart;
    }

    public void setTurn (boolean t) { turn = t; }

    public boolean getTurn() { return turn; }


    public int getColor() {
        return color;
    }
}
