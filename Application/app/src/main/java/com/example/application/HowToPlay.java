package com.example.application;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HowToPlay extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.howtoplay);


        TypeWriter how;
        how=findViewById(R.id.textView11);

        how.setText("");
        how.setCharacterDelay(100);
        how.animateText("Solve a quiz about Android studio and Github. \n \nEvery question is randomly given ");
    }
}
