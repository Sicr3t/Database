package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class LineShape extends Shape {

    private int xEnd;
    private int yEnd;
    private int thickness;

    public LineShape(int x, int y, String color, int width) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        thickness = width;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        paint.setStrokeWidth(thickness);
        canvas.drawLine(x,y,xEnd,yEnd,paint);
    }
}
