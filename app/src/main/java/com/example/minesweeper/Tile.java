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
    private Paint _hidPaint;
    private Paint _minedPaint;

    public Tile(int id, boolean isMine, boolean state, Rect bound) {
        _id = id;
        _isMine = isMine;
        _state = state;
        _bound = bound;
        _hidPaint = new Paint();
        _hidPaint.setStyle(Paint.Style.FILL);
        _hidPaint.setAntiAlias(true);
        _hidPaint.setColor(Color.BLACK);
        _minedPaint = new Paint();
        _minedPaint.setStyle(Paint.Style.FILL);
        _minedPaint.setAntiAlias(true);
        _minedPaint.setColor(Color.GRAY);

        _mineAround = 0;
    }

    public int get_id() {
        return _id;
    }

    public boolean containing(float x, float y) {
        return _bound.contains((int)x, (int)y);
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
        if (_state) {
            c.drawRect(_bound, _minedPaint);
        } else {
            c.drawRect(_bound, _hidPaint);
        }
    }
}
