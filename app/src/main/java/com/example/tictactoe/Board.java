package com.example.tictactoe;

import android.app.Activity;
import android.widget.TextView;
import java.util.Arrays;


public class Board {
    // Properties
    private TextView[][] board;
    private final int ROWS = 3;
    private final int COLS = 3;
    Activity activity;

    // Constructors
    public Board(TextView[][] board, Activity activity) {
        this.activity = activity;
        this.board = board;
    }

    // Methods
    public void makeMove (final Player player, TextView position) {
        position.setText(player.getName());
        position.setTextColor(player.getColor());
        position.setEnabled(false);
        player.addPosition(position);
    }

    public void undoMove (final Player player, TextView position) {
        position.setText("");
        position.setEnabled(true);
        player.removePosition(position);
    }

    public TextView[][] getCurrentState() {
        return board;
    }


    public void resetTheBoard() {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                board[i][j].setText("");
                board[i][j].setEnabled(true);
            }
        }
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++) {
                str += board[i][j].getText().toString() + " ";
            }
        }
        return str;
    }



    public TextView[][] deepCopy () {
            if(board == null)
                return null;

            final TextView[][] result = new TextView[ROWS][COLS];
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    TextView cloned = new TextView(activity);
                    cloned.setText(board[i][j].getText().toString());
                    result[i][j] = cloned;
                }
            }
            return result;
    }


}
