package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RectShape extends AreaShapes {

    private int xEnd;
    private int yEnd;
    private boolean isFilled;

    public RectShape(int x, int y, String color, boolean filled) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        isFilled = filled;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
    }
    @Override
    public double getArea(){
        return Math.abs(xEnd - x) * Math.abs(yEnd - y);
    }
    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        paint.setStrokeWidth(8);
        if(isFilled == true) {
            paint.setStyle(Paint.Style.FILL);
        }if(isFilled == false) {
            paint.setStyle(Paint.Style.STROKE);
        }
        canvas.drawRect(x,y,xEnd,yEnd,paint);
    }
}
