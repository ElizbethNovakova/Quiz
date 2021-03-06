package com.lizoknovakova.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;
    public int numRight;
    Numbers numbers = new Numbers();
    Random random = new Random();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //set text Level 1 on text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        final ImageView imageLeft = findViewById(R.id.imageLeft);
        imageLeft.setClipToOutline(true); //скруглим углы
        final ImageView imageRight = findViewById(R.id.imageRight);
        imageRight.setClipToOutline(true); //скруглим углы

        //way to textView
        final TextView textLeft = findViewById(R.id.textLeft);
        final TextView textRight = findViewById(R.id.textRight);

        Window w = getWindow(); //убрали строку состояния(заряд и тд)
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Вызов диалогового окна в начале игры
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок
        dialog.setContentView(R.layout.previewdialog);//путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialog.setCancelable(false);//окно нельзя закріть кнопкой назад


        //кнопка которая закрывает диалоговое окно
        TextView buttonclose = dialog.findViewById(R.id.buttonclose);
        buttonclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки
                try{
                    //вернуться к вібору уровня
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){

                }
                dialog.dismiss();//закрываем диалоговое окно
            }
        });

        Button button_continue = dialog.findViewById(R.id.button_continue);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();//закрываем диалоговое окно
            }
        });

        dialog.show();//показать диалоговое окно

        //_________________________________
        //show dialog in the end
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//скрываем заголовок
        dialogEnd.setContentView(R.layout.dialog_end);//путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);//окно нельзя закріть кнопкой назад


        //кнопка которая закрывает диалоговое окно
        TextView buttonCloseEnd = dialogEnd.findViewById(R.id.buttonclose);
        buttonCloseEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки
                try{
                    //вернуться к вібору уровня
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){

                }
                dialog.dismiss();//закрываем диалоговое окно
            }
        });

        Button buttonContinueEnd = dialogEnd.findViewById(R.id.button_continue);
        buttonContinueEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e){

                }
                dialog.dismiss();//закрываем диалоговое окно
            }
        });

        //_________________________________

        //Кнопка назад
        Button button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });

        //array for points
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };

        //animation
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);

        numLeft = random.nextInt(10);//random num from 0 to 9
        imageLeft.setImageResource(numbers.images[numLeft]);//image from array
        textLeft.setText(numbers.texts[numLeft]);//text for left img

        numRight = random.nextInt(10);//random num from 0 to 9
        //loop equal num
        while (numLeft == numRight){
            numRight = random.nextInt(10);
        }
        imageRight.setImageResource(numbers.images[numRight]);//image from array
        textRight.setText(numbers.texts[numRight]);//text for right img

        //tap left img
        imageLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){ //if touch img
                    imageRight.setEnabled(false);//block right img
                    if(numLeft > numRight){
                        imageLeft.setImageResource(R.drawable.true_img);
                    } else{
                        imageLeft.setImageResource(R.drawable.false_img);
                    }
                } else if(event.getAction() == MotionEvent.ACTION_UP){//if finger is removed
                    if(numLeft > numRight){

                        if(count < 20){
                            count++;
                        }

                        //fill points with grey
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //fill right points with green
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }

                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //fill right points with green
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if(count == 20){
                        //exit from level
                        dialogEnd.show();
                    } else{
                        numLeft = random.nextInt(10);//random num from 0 to 9
                        imageLeft.setImageResource(numbers.images[numLeft]);//image from array
                        imageLeft.startAnimation(a);
                        textLeft.setText(numbers.texts[numLeft]);//text for left img

                        numRight = random.nextInt(10);//random num from 0 to 9
                        //loop equal num
                        while (numLeft == numRight){
                            numRight = random.nextInt(10);
                        }
                        imageRight.setImageResource(numbers.images[numRight]);//image from array
                        imageRight.startAnimation(a);
                        textRight.setText(numbers.texts[numRight]);//text for right img

                        imageRight.setEnabled(true);
                    }

                }
                return true;
            }
        });


        //tap right img
        imageRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){ //if touch img
                    imageLeft.setEnabled(false);//block left img
                    if(numRight > numLeft){
                        imageRight.setImageResource(R.drawable.true_img);
                    } else{
                        imageRight.setImageResource(R.drawable.false_img);
                    }
                } else if(event.getAction() == MotionEvent.ACTION_UP){//if finger is removed
                    if(numLeft < numRight){

                        if(count < 20){
                            count++;
                        }

                        //fill points with grey
                        for(int i = 0; i < 20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //fill right points with green
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else {
                        if(count > 0){
                            if(count == 1){
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }

                        for(int i = 0; i < 19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //fill right points with green
                        for(int i = 0; i < count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if(count == 20){
                        //exit from level
                    } else{
                        numLeft = random.nextInt(10);//random num from 0 to 9
                        imageLeft.setImageResource(numbers.images[numLeft]);//image from array
                        imageLeft.startAnimation(a);
                        textLeft.setText(numbers.texts[numLeft]);//text for left img

                        numRight = random.nextInt(10);//random num from 0 to 9
                        //loop equal num
                        while (numLeft == numRight){
                            numRight = random.nextInt(10);
                        }
                        imageRight.setImageResource(numbers.images[numRight]);//image from array
                        imageRight.startAnimation(a);
                        textRight.setText(numbers.texts[numRight]);//text for right img

                        imageLeft.setEnabled(true);
                    }

                }
                return true;
            }
        });

    }

    //system button back
    @Override
    public  void onBackPressed(){
        try{
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){
        }
    }


}
