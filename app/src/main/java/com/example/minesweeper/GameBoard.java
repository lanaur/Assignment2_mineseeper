package com.example.minesweeper;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameBoard extends View {
    private int boardCol;
    private Paint rectPaint;
    private Rect rect;
    private boolean lost;
    private boolean flag;
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
        lost = false;
        flag = false;
        init_tiles();
        set_mines();

    }

    private void set_mines() {
        for (int i = 0; i < 20; i++) {
            int rnd = new Random().nextInt(100);
            if (!_tile_arr[rnd].get_isMine()) {
                _tile_arr[rnd].set_isMine(true);
                if (rnd%10 != 0) {
                    _tile_arr[rnd-1].add_mineAround(1);
                }
                if (rnd%10 != 9) {
                    _tile_arr[rnd+1].add_mineAround(1);
                }
                if (rnd > 9) {
                    _tile_arr[rnd-10].add_mineAround(1);
                    if (rnd % 10 != 0) {
                        _tile_arr[rnd-11].add_mineAround(1);
                    }
                    if (rnd % 10 != 9) {
                        _tile_arr[rnd-9].add_mineAround(1);
                    }
                }
                if (rnd < 90) {
                    _tile_arr[rnd+10].add_mineAround(1);
                    if (rnd % 10 != 0) {
                        _tile_arr[rnd+9].add_mineAround(1);
                    }
                    if (rnd % 10 != 9) {
                        _tile_arr[rnd+11].add_mineAround(1);
                    }
                }
            } else {
                i--;
            }
        }
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
        update_bound();
        setMeasuredDimension((int)sizetotal, (int)sizetotal+100);
    }

    private void update_bound(){
        int top = 0;
        int left = 0;
        int bottom = (int)size;
        int right = (int)size;

        int count = 0;

        for (Tile tile : _tile_arr) {
            tile.set_bound(new Rect(left, top, right, bottom));
            tile.update_font_size((int) size);
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

        for (Tile tile : _tile_arr) {
            tile.draw(canvas);
        }
    }

    public void uncover_cell(int id) {
        _tile_arr[id].set_state(true);
        if (_tile_arr[id].get_mineAround() > 0 || _tile_arr[id].get_isMine()) {
            return;
        }
        if (id%10 != 0) {
            if (!_tile_arr[id-1].get_state() )
                uncover_cell(id-1);
        }
        if (id%10 != 9) {
            if (!_tile_arr[id+1].get_state() )
                uncover_cell(id +1);
        }
        if (id > 9) {
            if (!_tile_arr[id-10].get_state() )
                uncover_cell(id - 10);
        }
        if (id < 90) {
            if (!_tile_arr[id+10].get_state() )
                uncover_cell(id +10);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && !lost) {
            for (Tile t : _tile_arr) {
                if (t.containing(event.getX(), event.getY())) {
                    uncover_cell(t.get_id());
                    if (t.get_isMine())
                        lost = true;
                }
            }
            invalidate();
        }
        return (true);
    }

    public void reset() {
        for (Tile t : _tile_arr) {
            t.set_state(false);
            t.set_isMine(false);
            t.set_mineAround(0);
        }
        lost = false;
        set_mines();
        invalidate();
    }

    public void toggle_flag() {
        flag = true;
    }
}
