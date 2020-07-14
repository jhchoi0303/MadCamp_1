package com.example.application;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class ServiceActivity extends AppCompatActivity {


    public static ImageView reward;
    public static TextView rewardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Intent intent = new Intent(this, ShakeService.class);
        //Start Service
        startService(intent);


        rewardText=(TextView)findViewById(R.id.RewardText);
        reward=(ImageView)findViewById(R.id.reward);

        Button exitButton = (Button) findViewById(R.id.exitbutton);
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(ServiceActivity.this);
                builder.setMessage("Really Want to Close?");
                builder.setTitle("Yes")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.finishAffinity(ServiceActivity.this);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("Closing...");
                alert.show();
            }
        });


    }
}