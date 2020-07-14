package com.example.application;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyListViewActivity extends AppCompatActivity {

    private Intent intent;
    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_modify);


        // MainActivity에서 보낸 imgRes를 받기위해 getIntent()로 초기화
        intent = getIntent();

        imageView = (ImageView) findViewById(R.id.modify_imageView);
        textView1 = (TextView) findViewById(R.id.modify_textView1);
        textView2 = (TextView) findViewById(R.id.modify_textView2);


        // "imgRes" key로 받은 값은 int 형이기 때문에 getIntExtra(key, defaultValue);
        // 받는 값이 String 형이면 getStringExtra(key);
        final int mod_imgres = (int) intent.getIntExtra("imgRes", 0);
        final int index = (int) intent.getIntExtra("index", 0);
        imageView.setImageResource(mod_imgres);
        textView1.setText(intent.getStringExtra("title"));
        textView2.setText(intent.getStringExtra("content"));


        Button button = (Button) findViewById(R.id.modify_button);

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){

               // Toast.makeText(ModifyListViewActivity.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                String mod_title = textView1.getText().toString();
                String all_content = textView2.getText().toString();

                String[] split_mod_content = all_content.split("\n");

                String mod_content = split_mod_content[0];
                String mod_number = split_mod_content[1].split(":")[1];

                ListViewCustomDTO mod_dto = new ListViewCustomDTO();

                mod_content = mod_content +"\n"+"Phone num :"+ mod_number ;

                mod_dto.setResId(mod_imgres);
                mod_dto.setTitle(mod_title);
                mod_dto.setContent(mod_content);
                mod_dto.setNumber(mod_number);

                Intent new_dto = new Intent();
                new_dto.putExtra("dto", mod_dto);
                new_dto.putExtra("index", index);
                setResult(0, new_dto);

                Toast.makeText(ModifyListViewActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });



    }
}
