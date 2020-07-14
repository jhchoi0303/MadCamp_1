package com.example.application;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.viewpager.widget.ViewPager;

public class ButtonClickListener implements View.OnClickListener {
    Context context;

    public ButtonClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context, HowToPlay.class);
        context.startActivity(intent);

    }

}
