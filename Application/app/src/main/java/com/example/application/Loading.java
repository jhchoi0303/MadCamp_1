package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class Loading extends AppCompatActivity {


    private static int SPLASH_SCREEN=5000;





    TypeWriter tab1, tab2,tab3,tab4,tab5;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.loading_page);

        //Animations


//Hooks



        tab1=findViewById(R.id.textView1);
        tab2=findViewById(R.id.textView2);
        tab3=findViewById(R.id.textView3);
        tab4=findViewById(R.id.textView4);
        tab5=findViewById(R.id.textView5);

        ImageView Loading = (ImageView) findViewById(R.id.loading);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(Loading);
        Glide.with(this).load(R.drawable.loading).into(gifImage);


        tab4.setText("");
        tab4.setCharacterDelay(100);
        tab4.animateText("WELCOME TO PIXEL ");



        tab1.setText("");
        tab1.setCharacterDelay(150);
        tab1.animateText("PIXEL\\Tab1> Phonebook ");
        tab2.setText("");
        tab2.setCharacterDelay(150);
        tab2.animateText("PIXEL\\Tab2> Gallery ");
        tab3.setText("");
        tab3.setCharacterDelay(150);
        tab3.animateText("PIXEL\\Tab3> Quiz ");

        tab5.setText("");
        tab5.setCharacterDelay(100);
        tab5.animateText("Copyrights all right reserved ");

        new Handler().postDelayed(new Runnable(){
            @Override

            public void run(){
                Intent intent = new Intent(Loading.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);







    }
}