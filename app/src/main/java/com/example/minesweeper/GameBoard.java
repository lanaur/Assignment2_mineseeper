package com.example.minesweeper;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameBoard extends View {
    private int boardCol;
    private Paint rectPaint;
    private Rect rect;
    int v_half_w;
    int v_half_h;
    float size;

    Tile _tile;

    Tile[] _tile_arr;

    float sizetotal, width, height, square;

    public GameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        rectPaint = new Paint();
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GameBoard, 0, 0);
        try {
            boardCol = a.getInteger(R.styleable.GameBoard_boardCol, 0);
        } finally {
            a.recycle();
        }
        size = 100;
        init_tiles();

    }

    private void init_tiles() {
        _tile_arr = new Tile[100];
        int top = 0;
        int left = 0;
        int bottom = (int)size;
        int right = (int)size;

        int count = 0;

        for (int i = 0; i < _tile_arr.length; i++) {
            _tile_arr[i] = new Tile(i, false, false, new Rect(left, top, right, bottom));
            left += size + size/5;
            right += size +size/5;
            count++;
            if (count == 10 ) {
                left = 0;
                right = (int)size;
                top += size + size/2;
                bottom += size + size/2;
                count = 0;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sizetotal = 0;
        size =0;
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        if (width > height) {
            sizetotal = height;
        } else {
            sizetotal = width;
        }
        size = sizetotal/10;
        rect = new Rect(0, 0,(int) size,(int) size);
        setMeasuredDimension((int)sizetotal , (int)sizetotal);
    }

    private void update_bound(){
        int top = 0;
        int left = 0;
        int bottom = (int)size;
        int right = (int)size;

        int count = 0;

        for (Tile tile : _tile_arr) {
            tile.set_bound(new Rect(left, top, right, bottom));
            left += size + size / 100;
            right += size + size / 100;
            count++;
            if (count == 10) {
                left = 0;
                right = (int) size;
                top += size + size / 50;
                bottom += size + size / 50;
                count = 0;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        update_bound();

        for (Tile tile : _tile_arr) {
            tile.draw(canvas);
        }

    }
}
