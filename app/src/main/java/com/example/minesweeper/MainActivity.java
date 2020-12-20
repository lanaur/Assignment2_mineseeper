package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GameBoard _board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _board = (GameBoard)findViewById(R.id.custView);
    }

    public void reset_game(View view) {
        _board.reset();
    }

    public void flag_mode(View view) {
        _board.toggle_flag();
    }
}