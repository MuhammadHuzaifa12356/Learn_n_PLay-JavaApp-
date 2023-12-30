package com.example.learn_n_play;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class HomeScreen extends AppCompatActivity {
    private  Button ShapeButton;
    private  Button ColorButton;
    private Button appsettingbtn;
    private  Button quizbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ShapeButton=findViewById(R.id.buttonShape);
        ColorButton=findViewById(R.id.buttonColor);
        appsettingbtn=findViewById(R.id.appsettingbtn);
        quizbutton=findViewById(R.id.quizbutton);
        ShapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,ShapesRecognition.class));
                finish();
            }
        });
        ColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,ColorsRecognition.class));
                finish();
            }
        });
        appsettingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,AppSetting.class));
                finish();
            }
        });
        quizbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeScreen.this,Game.class));
                finish();
            }
        });
    }

}