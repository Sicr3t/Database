package com.example.paint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.UUID;

public class PaintActivity extends AppCompatActivity {
    private static final String TAG = "PaintActivity";
    private FrameLayout frame;
    private PaintView paintView;
    private Button button;
    private String fileName;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        button = findViewById(R.id.btnPoint);
        editText = findViewById(R.id.etSaveName);
        frame = findViewById(R.id.frm);
        paintView = new PaintView(this);
        frame.addView(paintView);
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                paintView.pathRemove();
                return true;
            }
        });
    }

    public void addLine(View view) {
        paintView.addLine();
    }
    public void addRect(View view) {
        paintView.addRect();
    }
    public void addPath(View view) {
        paintView.addPath();
    }
    public void addCircle(View view) {
        paintView.addCircle();
    }

    public void changeColor(View view)
    {
        String color = view.getTag().toString();
        paintView.setColor(color);
    }
    
    public void clear(View view) {
        paintView.undo();
    }
    public void fill(View view)
    {
        paintView.fill();
    }
    public void changeWidth(View view){
        paintView.changeWidth();
    }
    public void leaveBiggest(View view) {
        paintView.leaveBiggest();
    }
    public void saveImage(View view){

        if(editText.getText() != null)
            fileName = editText.getText().toString();
        else
            fileName = UUID.randomUUID().toString();
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "File Saved", Toast.LENGTH_SHORT);
        paintView.setDrawingCacheEnabled(true);
        MediaStore.Images.Media.insertImage(getContentResolver(), paintView.getDrawingCache(), fileName, "drawing");
        paintView.destroyDrawingCache();
        toast.show();
    }
}
