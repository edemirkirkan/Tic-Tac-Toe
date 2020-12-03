package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        button.setOnClickListener(view -> {
            MediaPlayer ring= MediaPlayer.create(MenuActivity.this,R.raw.button);
            ring.start();
            Intent intent = new Intent(getApplicationContext(), OnePlayerActivityEasy.class);
            startActivity(intent);
        });
        button2.setOnClickListener(view -> {
            MediaPlayer ring= MediaPlayer.create(MenuActivity.this,R.raw.button);
            ring.start();
            Intent intent = new Intent(getApplicationContext(), TwoPlayerActivity.class);
            startActivity(intent);
        });
    }
}
