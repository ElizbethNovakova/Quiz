package com.lizoknovakova.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Level1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        final ImageView imageLeft = findViewById(R.id.imageLeft);
        imageLeft.setClipToOutline(true); //скруглим углы
        final ImageView imageRight = findViewById(R.id.imageRight);
        imageRight.setClipToOutline(true); //скруглим углы

        Window w = getWindow(); //убрали строку состояния(заряд и тд)
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
