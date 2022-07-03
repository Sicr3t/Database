package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

public class PathShape extends Shape {

    private int xEnd;
    private int yEnd;
    private Path path;

    public PathShape(int x, int y, String color) {
        super(x, y, color);
        path = new Path();
        xEnd = x;
        yEnd = y;
        path.moveTo(xEnd,yEnd);
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        path.lineTo(xEnd,yEnd);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        canvas.drawPath(path,paint);
    }
}
