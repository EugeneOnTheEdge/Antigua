package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class favouriteSport extends AppCompatActivity {
    private Button button_golf;
    private Button button_walking;
    private Button button_frisbee;
    private Button button_skip;

    private boolean golf;
    private boolean walking;
    private boolean frisbee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_sport);

        this.button_golf = findViewById(R.id.button_golf);
        this.button_walking = findViewById(R.id.button_walking);
        this.button_frisbee = findViewById(R.id.button_frisbee);
        this.button_skip = findViewById(R.id.button_skip);

        this.golf = false;
        this.walking = false;
        this.frisbee = false;

        this.button_skip.setText("SKIP →");
        this.button_skip.setTextSize(20);
        this.button_skip.setWidth(150);
        this.button_skip.setBackgroundColor(0xFF9900FF);
        this.button_skip.setTextColor(0xFFFFFFFF);

        this.button_golf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!golf) {
                    button_golf.setBackgroundColor(0xFF008577); // GREEN Background
                    button_golf.setTextColor(0xFFFFFFFF); // WHITE text color
                }
                else {
                    button_golf.setBackgroundResource(android.R.drawable.btn_default); // DEFAULT background
                    button_golf.setTextColor(0xFF000000); // BLACK text color
                }
                golf = !golf;

                if (golf || walking || frisbee) {
                    button_skip.setText("→");
                    button_skip.setWidth(85);
                }
                else {
                    button_skip.setText("SKIP →");
                    button_skip.setWidth(150);
                }
            }
        });

        this.button_walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!walking) {
                    button_walking.setBackgroundColor(0xFF008577); // GREEN Background
                    button_walking.setTextColor(0xFFFFFFFF); // WHITE text color
                }
                else {
                    button_walking.setBackgroundResource(android.R.drawable.btn_default); // DEFAULT background
                    button_walking.setTextColor(0xFF000000); // BLACK text color
                }
                walking = !walking;

                if (golf || walking || frisbee) {
                    button_skip.setText("→");
                    button_skip.setWidth(85);
                }
                else {
                    button_skip.setText("SKIP →");
                    button_skip.setWidth(150);
                }
            }
        });

        this.button_frisbee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!frisbee) {
                    button_frisbee.setBackgroundColor(0xFF008577); // GREEN Background
                    button_frisbee.setTextColor(0xFFFFFFFF); // WHITE text color
                }
                else {
                    button_frisbee.setBackgroundResource(android.R.drawable.btn_default); // DEFAULT background
                    button_frisbee.setTextColor(0xFF000000); // BLACK text color
                }
                frisbee = !frisbee;

                if (golf || walking || frisbee) {
                    button_skip.setText("→");
                    button_skip.setWidth(85);
                }
                else {
                    button_skip.setText("SKIP →");
                    button_skip.setWidth(150);
                }
            }
        });
    }
}
