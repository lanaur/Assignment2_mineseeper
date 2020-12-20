package com.example.minesweeper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Tile {
    private int _id;
    private boolean _isMine;
    private boolean _state;
    private boolean _flag;
    private int _mineAround;
    private int _size;

    private Rect _bound;

    private Paint _blackCell;
    private Paint _greyCell;
    private Paint _redCell;
    private Paint _mineFont;
    private Paint _hintFont;

    public Tile(int id, boolean isMine, boolean state, Rect bound) {
        _id = id;
        _isMine = isMine;
        _state = state;
        _bound = bound;
        _flag = false;
        _size = 50;

        _blackCell = new Paint();
        _blackCell.setStyle(Paint.Style.FILL);
        _blackCell.setAntiAlias(true);
        _blackCell.setColor(Color.BLACK);

        _greyCell = new Paint();
        _greyCell.setStyle(Paint.Style.FILL);
        _greyCell.setAntiAlias(true);
        _greyCell.setColor(Color.GRAY);

        _redCell = new Paint();
        _redCell.setStyle(Paint.Style.FILL);
        _redCell.setAntiAlias(true);
        _redCell.setColor(Color.RED);

        _mineFont = new Paint();
        _mineFont.setStyle(Paint.Style.FILL);
        _mineFont.setAntiAlias(true);
        _mineFont.setColor(Color.BLACK);
        _mineFont.setTextSize(50);

        _hintFont = new Paint();
        _hintFont.setStyle(Paint.Style.FILL);
        _hintFont.setAntiAlias(true);
        _hintFont.setColor(Color.BLUE);
        _hintFont.setTextSize(50);
        _mineAround = 0;
    }

    public int get_id() {
        return _id;
    }

    public boolean containing(float x, float y) {
        return _bound.contains((int)x, (int)y);
    }

    public void set_bound(Rect bound) {
        this._bound = bound;
    }

    public boolean get_isMine() {
        return _isMine;
    }

    public void set_isMine(boolean isMine) {
        this._isMine = isMine;
    }

    public int get_mineAround() {
        return _mineAround;
    }

    public void set_mineAround(int mineAround) {
        this._mineAround = mineAround;
    }

    public void add_mineAround(int mineAround) {
        this._mineAround += mineAround;
    }

    public void set_state(boolean _state) {
        this._state = _state;
    }

    public boolean get_state() {
        return _state;
    }

    public void update_font_size(int size) {
        _mineFont.setTextSize(size);
        _hintFont.setTextSize(size);
        _size = size;
    }

    public void draw(Canvas c) {
        if (_state) {
            if (_isMine) {
                c.drawRect(_bound, _redCell);
                int bot =  _bound.centerY() + _size/2;
                int left = _bound.centerX() - _size/2;
                c.drawText("M",left +_size/15, bot - _size/10, _mineFont);
            } else if (_mineAround > 0) {
                c.drawRect(_bound, _greyCell);
                int bot =  _bound.centerY() + _size/2;
                int left = _bound.centerX() - _size/2;
                c.drawText(String.valueOf(_mineAround),left +_size/5, bot - _size/10, _hintFont);
            } else {
                c.drawRect(_bound, _greyCell);
            }
        } else {
            c.drawRect(_bound, _blackCell);
        }
    }
}
