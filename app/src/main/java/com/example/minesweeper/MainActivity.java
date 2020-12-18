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
}