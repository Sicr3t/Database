package com.example.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CircleShape extends AreaShapes {

    private int xEnd;
    private int yEnd;
    private float xCenter;
    private float yCenter;
    private double radius;
    private boolean isFilled;

    public CircleShape(int x, int y, String color, boolean filled) {
        super(x, y, color);
        xEnd = x;
        yEnd = y;
        isFilled = filled;
    }

    @Override
    public void updatePoint(int xe, int ye) {
        xEnd = xe;
        yEnd = ye;
        xCenter = (xe + x) / 2;
        yCenter = (ye + y) / 2;
        radius = Math.sqrt((xEnd-x)*(xEnd-x)+(yEnd-y)*(yEnd - y));
    }
    public double getArea(){
        return 3.14 * Math.pow(radius, 2);
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
        canvas.drawCircle(xCenter,yCenter,(float)radius/2,paint);
    }
}
