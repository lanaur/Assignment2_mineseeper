package com.example.minesweeper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Tile {
    private int _id;
    private boolean _isMine;
    private boolean _state;
    private int _mineAround;
    private Rect _bound;
    private Paint _paint;

    public Tile(int id, boolean isMine, boolean state, Rect bound) {
        _id = id;
        _isMine = isMine;
        _state = state;
        _bound = bound;
        _paint = new Paint();
        _paint.setStyle(Paint.Style.FILL);
        _paint.setAntiAlias(true);
        _paint.setColor(Color.BLACK);
        _mineAround = 0;
    }

    public int get_id() {
        return _id;
    }

    public boolean containing(int x, int y) {
        return _bound.contains(x, y);
    }

    public void set_bound(Rect _bound) {
        this._bound = _bound;
    }

    public boolean get_isMine() {
        return _isMine;
    }

    public void set_isMine(boolean _isMine) {
        this._isMine = _isMine;
    }

    public int get_mineAround() {
        return _mineAround;
    }

    public void set_mineAround(int _mineAround) {
        this._mineAround = _mineAround;
    }

    public void set_state(boolean _state) {
        this._state = _state;
    }

    public boolean get_state() {
        return _state;
    }

    public void draw(Canvas c) {
        c.drawRect(_bound, _paint);
    }
}
