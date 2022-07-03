package com.example.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Stack;

public class PaintView extends View {

    private Paint paint;
    private Paint bgPaint;
    private String currentShape = "Rect";
    private String currentColor = "#FFFFFFFF";
    private boolean filled = false;
    private int width = 12;

    private Stack<Shape> shapes;

    public PaintView(Context context) {
        super(context);
        shapes = new Stack<>();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        bgPaint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(12);
        bgPaint.setColor(Color.rgb(255,255,255));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(bgPaint);
        for (int i = 0; i < shapes.size(); i++)
            shapes.get(i).draw(canvas, paint);
    }

    Shape shape;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            if(currentShape.equals("Rect"))
            {
                shape = new RectShape((int)event.getX(),(int)event.getY(),currentColor, filled);
            }
            else if(currentShape.equals("Line"))
            {
                shape = new LineShape((int)event.getX(),(int)event.getY(),currentColor, width);
            }
            else if(currentShape.equals("Circle"))
            {
                shape = new CircleShape((int)event.getX(),(int)event.getY(),currentColor, filled);
            }
            else if(currentShape.equals("Path"))
            {
                shape = new PathShape((int)event.getX(),(int)event.getY(),currentColor);
            }
            shapes.push(shape);
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            shape.updatePoint((int)event.getX(),(int)event.getY());
            invalidate();
        }
        return true;
    }

    public void addLine() {
        currentShape = "Line";
    }

    public void addRect() {
        currentShape = "Rect";
    }

    public void addCircle() {
        currentShape = "Circle";
    }
    public void addPath() {
        currentShape = "Path";
    }

    public void setColor(String color)
    {
        currentColor = color;
    }

    public void undo()
    {
        if(!shapes.empty()) {
            shapes.pop();
            invalidate();
        }
    }
    public void pathRemove()
    {
        Stack<Shape> temp = new Stack<Shape>();
        while(!shapes.empty())
        {
            if(shapes.peek()instanceof PathShape){
                shapes.pop();
            }
            else{
                temp.push(shapes.pop());
            }
        }
        while (!temp.empty())
        {
            shapes.push(temp.pop());
        }
        invalidate();
    }
    public void fill(){
        filled = !filled;
    }
    public void changeWidth(){
        width += 24;
    }
    public void leaveBiggest(){

        double area = 0;
        AreaShapes temp = null;
        while(!shapes.empty()){
            if(shapes.peek()instanceof AreaShapes){
                if(area < ((AreaShapes)shapes.peek()).getArea()){
                    area = ((AreaShapes)shapes.peek()).getArea();
                    temp = (AreaShapes)shapes.peek();
                }
            }
            shapes.pop();
        }
        if (temp != null){
            shapes.push(temp);
        }
        invalidate();
    }
}