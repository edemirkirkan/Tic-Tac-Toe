package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SuppressLint("SetTextI18n")

public class TwoPlayerActivity extends AppCompatActivity {

    // Properties
    private Board boardObject;
    private TextView[][] board;
    private ImageView[] lines;
    private final int ROWS = 3;
    private final int COLS = 3;
    private final Player xPlayer = new Player("X", Color.parseColor("#545454"));
    private final Player oPlayer = new Player("O", Color.parseColor("#f2ebd3") );
    private TextView  turnLabel, turnView, xScoreLabel, oScoreLabel, drawsLabel, playAgainLabel;
    private boolean keepPlaying = true;
    private boolean isXWon, isOWon, isDraw = false;
    private int xScore, oScore, drawScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.two_player_activity);
        board = new TextView[ROWS][COLS];

        board[0][0] = findViewById(R.id.topLeft);
        board[0][1] = findViewById(R.id.top);
        board[0][2] = findViewById(R.id.topRight);
        board[1][0] = findViewById(R.id.left);
        board[1][1] = findViewById(R.id.mid);
        board[1][2] = findViewById(R.id.right);
        board[2][0] = findViewById(R.id.botLeft);
        board[2][1] = findViewById(R.id.bot);
        board[2][2] = findViewById(R.id.botRight);

        boardObject = new Board(board, this);


        xPlayer.setTurn(true);
        oPlayer.setTurn(false);
        xPlayer.setStart(true);


        initComponents();

        game();
    }

    // Methods
    public void game() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                final int finalJ = j;
                final int finalI = i;
                board[i][j].setOnClickListener(v -> turnClick(board[finalI][finalJ]));
            }
        }
    }

    public void turnClick (TextView position) {
        if (xPlayer.getTurn()) {

            boardObject.makeMove(xPlayer, position);

            setCurrentPlayer(oPlayer);

            MediaPlayer ring = MediaPlayer.create(TwoPlayerActivity.this, R.raw.x);
            ring.start();

            setLabels(oPlayer);

        }
        else {
            boardObject.makeMove(oPlayer, position);

            setCurrentPlayer(xPlayer);

            MediaPlayer ring = MediaPlayer.create(TwoPlayerActivity.this, R.raw.o);
            ring.start();

            setLabels(xPlayer);

        }
        if (playAgainLabel.getVisibility() == View.VISIBLE)
            playAgainLabel.setVisibility(View.INVISIBLE);
        checkWinner();
    }

    public void checkWinner () {
        List<TextView> topRow = Arrays.asList(board[0][0], board[0][1], board[0][2]);
        List<TextView> midRow = Arrays.asList(board[1][0], board[1][1], board[1][2]);
        List<TextView> botRow = Arrays.asList(board[2][0], board[2][1], board[2][2]);
        List<TextView> leftCol = Arrays.asList(board[0][0], board[1][0], board[2][0]);
        List<TextView> midCol = Arrays.asList(board[0][1], board[1][1], board[2][1]);
        List<TextView> rightCol = Arrays.asList(board[0][2], board[1][2], board[2][2]);
        List<TextView> crossLeftBotToRightTop = Arrays.asList(board[2][0], board[1][1], board[0][2]);
        List<TextView> crossRightBotToLeftTop = Arrays.asList(board[2][2], board[1][1], board[0][0]);
        List<TextView> magicList = leftCol;

        List<List<TextView>> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(crossLeftBotToRightTop);
        winning.add(crossRightBotToLeftTop);

        if (keepPlaying) {
            for (List<TextView> l : winning) {
                if (xPlayer.getPositions().containsAll(l))
                {
                    keepPlaying = false;
                    isXWon = true;
                    updateLabels();

                    magicList = l;
                    if(magicList == topRow) {
                        lines[1].setVisibility(View.VISIBLE);
                    }
                    if(magicList == midRow) {
                        lines[3].setVisibility(View.VISIBLE);
                    }
                    if(magicList == botRow) {
                        lines[5].setVisibility(View.VISIBLE);
                    }
                    if(magicList == leftCol) {
                        lines[7].setVisibility(View.VISIBLE);
                    }
                    if(magicList == midCol) {
                        lines[9].setVisibility(View.VISIBLE);
                    }
                    if(magicList == rightCol) {
                        lines[11].setVisibility(View.VISIBLE);
                    }
                    if(magicList == crossLeftBotToRightTop) {
                        lines[13].setVisibility(View.VISIBLE);
                    }
                    if(magicList == crossRightBotToLeftTop) {
                        lines[15].setVisibility(View.VISIBLE);
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(this::startNewRound, 500);

                }
                else if (oPlayer.getPositions().containsAll(l))
                {
                    keepPlaying = false;
                    isOWon = true;
                    updateLabels();

                    magicList = l;

                    if(magicList == topRow) {
                        lines[0].setVisibility(View.VISIBLE);
                    }
                    if(magicList == midRow) {
                        lines[2].setVisibility(View.VISIBLE);
                    }
                    if(magicList == botRow) {
                        lines[4].setVisibility(View.VISIBLE);
                    }
                    if(magicList == leftCol) {
                        lines[6].setVisibility(View.VISIBLE);
                    }
                    if(magicList == midCol) {
                        lines[8].setVisibility(View.VISIBLE);
                    }
                    if(magicList == rightCol) {
                        lines[10].setVisibility(View.VISIBLE);
                    }
                    if(magicList == crossLeftBotToRightTop) {
                        lines[12].setVisibility(View.VISIBLE);
                    }
                    if(magicList == crossRightBotToLeftTop) {
                        lines[14].setVisibility(View.VISIBLE);
                    }

                    final Handler handler = new Handler();
                    handler.postDelayed(this::startNewRound, 500);
                }
            }

            if (xPlayer.getPositions().size() + oPlayer.getPositions().size() == 9 &&
                    !oPlayer.getPositions().containsAll(magicList) && !xPlayer.getPositions().containsAll(magicList)) {

                keepPlaying = false;
                isDraw = true;
                updateLabels();

                final Handler handler = new Handler();
                handler.postDelayed(this::startNewRound, 500);
            }
        }
    }

    public void startNewRound () {
        updateTheScoreBoard();

        isDraw = false;
        isXWon = false;
        isOWon = false;

        keepPlaying = true;

        xPlayer.getPositions().clear();
        oPlayer.getPositions().clear();

        for(ImageView i : lines) {
            if (i.getVisibility() == View.VISIBLE)
                i.setVisibility(View.INVISIBLE);
        }

        if(playAgainLabel.getVisibility() == View.INVISIBLE)
            playAgainLabel.setVisibility(View.VISIBLE);

        boardObject.resetTheBoard();

        setStartingPlayer();
    }

    public void setStartingPlayer() {
        if(xPlayer.getStart()) {
            setCurrentPlayer(oPlayer);
            xPlayer.setStart(false);
        }
        else {
            setCurrentPlayer(xPlayer);
            xPlayer.setStart(true);
        }
    }

    public void restartGame() {
        startNewRound();
        setCurrentPlayer(xPlayer);

        xScore = 0;
        xScoreLabel.setText("" + xScore);

        oScore = 0;
        oScoreLabel.setText("" + oScore);

        drawScore = 0;
        drawsLabel.setText("" + drawScore);

        setLabels(xPlayer);
    }

    public void setTurnLabel () {
        String oStart = "<font color='#f2ebd3'>O</font>";
        String xStart = "<font color='#545454'>X</font>";
        String startSecond = "<font color='#0da192'>Starts The New Round</font>";
        turnLabel.setTextSize(25);
        if(xPlayer.getStart())
            turnLabel.setText(Html.fromHtml(oStart + "     " + startSecond));
        else
            turnLabel.setText(Html.fromHtml(xStart + "     " + startSecond));
    }

    public void setLabels(Player p) {
        if (p == oPlayer) {
            turnView.setText(p.getName());
            turnLabel.setText(p.getName());

            turnView.setTextColor(p.getColor());
            turnLabel.setTextColor(p.getColor());

            turnView.setTextSize(50);
            turnLabel.setTextSize(50);
        }

        if (p == xPlayer) {
            turnView.setText(p.getName());
            turnLabel.setText(p.getName());

            turnView.setTextColor(p.getColor());
            turnLabel.setTextColor(p.getColor());

            turnView.setTextSize(50);
            turnLabel.setTextSize(50);
        }

    }

    public void updateLabels () {
        if(isXWon) {
            String first = "<font color='#545454'>X</font>";
            String second = "<font color='#0da192'>Won This Round!</font>";

            MediaPlayer ring= MediaPlayer.create(TwoPlayerActivity.this,R.raw.win);
            ring.start();

            turnView.setTextSize(35);
            turnView.setText(Html.fromHtml(first + " " + second));
            setTurnLabel();
        }

        if(isOWon) {
            String first = "<font color='#f2ebd3'>O</font>";
            String second = "<font color='#0da192'>Won This Round!</font>";

            MediaPlayer ring= MediaPlayer.create(TwoPlayerActivity.this,R.raw.win);
            ring.start();

            turnView.setTextSize(35);
            turnView.setText(Html.fromHtml(first + " " + second));
            setTurnLabel();
        }

        if(isDraw) {
            MediaPlayer ring = MediaPlayer.create(TwoPlayerActivity.this, R.raw.win);
            ring.start();

            turnView.setTextSize(35);
            turnView.setTextColor(Color.parseColor("#0da192"));
            turnView.setText("DRAW!");

            setTurnLabel();
        }
    }

    public void updateTheScoreBoard () {
        if (isXWon) {
            xScore++;
            xScoreLabel.setText("" + xScore);
        }

        if (isOWon) {
            oScore++;
            oScoreLabel.setText("" + oScore);
        }

        if (isDraw) {
            drawScore++;
            drawsLabel.setText("" + drawScore);
        }
    }

    public void initComponents() {
        lines = new ImageView[16];
        lines[0] = findViewById(R.id.imageView);
        lines[1] = findViewById(R.id.imageView2);
        lines[2] = findViewById(R.id.imageView3);
        lines[3]  = findViewById(R.id.imageView4);
        lines[4]  = findViewById(R.id.imageView5);
        lines[5]  = findViewById(R.id.imageView6);
        lines[6]  = findViewById(R.id.imageView7);
        lines[7]  = findViewById(R.id.imageView8);
        lines[8]  = findViewById(R.id.imageView9);
        lines[9]  = findViewById(R.id.imageView10);
        lines[10]  = findViewById(R.id.imageView11);
        lines[11]  = findViewById(R.id.imageView12);
        lines[12] = findViewById(R.id.imageView13);
        lines[13] = findViewById(R.id.imageView14);
        lines[14] = findViewById(R.id.imageView15);
        lines[15] = findViewById(R.id.imageView16);


        Button menuButton = findViewById(R.id.menuButton);
        Button restartButton = findViewById(R.id.restartButton);

        TextView xox = findViewById(R.id.topLabel);
        String x = "<font color='#545454'>X</font>";
        String o = "<font color='#f2ebd3'>O</font>";
        String x2 = "<font color='#545454'>X</font>";
        xox.setText(Html.fromHtml(x + "               " + o + "               " + x2));


        TextView xBoard = findViewById(R.id.xWins);
        String first = "<font color='#545454'>X</font>";
        String second = " Wins";
        xBoard.setText(Html.fromHtml(first + second));

        TextView oBoard = findViewById(R.id.oWins);
        first = "<font color='#f2ebd3'>O</font>";
        oBoard.setText(Html.fromHtml(first + second));

        turnLabel = findViewById(R.id.turnLabel);
        turnView = findViewById(R.id.turn);
        playAgainLabel = findViewById(R.id.playAgain);

        xScoreLabel = findViewById(R.id.xScore);
        oScoreLabel = findViewById(R.id.oScore);
        drawsLabel = findViewById(R.id.drawScore);


        xScore = Integer.parseInt(xScoreLabel.getText().toString());
        oScore = Integer.parseInt(oScoreLabel.getText().toString());
        drawScore = Integer.parseInt(drawsLabel.getText().toString());

        menuButton.setOnClickListener(view -> {
            MediaPlayer ring= MediaPlayer.create(TwoPlayerActivity.this,R.raw.button);
            ring.start();
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent);
        });

        restartButton.setOnClickListener(view -> {
            MediaPlayer ring= MediaPlayer.create(TwoPlayerActivity.this,R.raw.button);
            ring.start();
            restartGame();
        });
    }

    public void setCurrentPlayer(Player p) {
        if(p == xPlayer) {
            oPlayer.setTurn(false);
            xPlayer.setTurn(true);
        }

        if(p == oPlayer) {
            xPlayer.setTurn(false);
            oPlayer.setTurn(true);
        }
    }
}