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
        size= sizetotal/10;
        rect = new Rect(0, 0,(int) size,(int) size);
        setMeasuredDimension((int)sizetotal - 100, (int)sizetotal);
    }

    @Override
    protected void onDraw(Canvas canvas) {


        v_half_w = this.getMeasuredWidth()/2;
        v_half_h = this.getMeasuredHeight()/2;
        Log.d("Height:", "GameBoard: " + v_half_h);
        Log.d("Width:", "GameBoard: " + v_half_w);
        rect = new Rect( 20, 200 , (int)sizetotal,(int)sizetotal );

        rectPaint.setStyle(Style.FILL);
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(boardCol);
        canvas.drawRect(rect, rectPaint);
    }


}
