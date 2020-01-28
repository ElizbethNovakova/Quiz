package com.lizoknovakova.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        Window w = getWindow(); //убрали строку состояния(заряд и тд)
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);//открываем новое окно
                    finish();//закрываем работающее окно
                }catch (Exception e){

                }
            }
        });

        //кнопка для 1 уровня
        TextView textView1 = findViewById(R.id.textView1);
        textView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameLevels.this, Level1.class);
                startActivity(intent);
                finish();
            }
        });
    }


    //системная кнопка назад
    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);
            finish();//закрываем работающее окно
        }catch (Exception e){

        }
    }

}
