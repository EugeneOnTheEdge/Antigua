package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class favouriteCableNetwork extends AppCompatActivity {
    private Button button_news;
    private Button button_cooking_channel;
    private Button button_talk_shows;
    private Button button_skip;

    private boolean news;
    private boolean cooking_channel;
    private boolean talk_shows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_cable_network);

        this.button_news = findViewById(R.id.button_news);
        this.button_cooking_channel = findViewById(R.id.button_cooking_channel);
        this.button_talk_shows = findViewById(R.id.button_talk_shows);
        this.button_skip = findViewById(R.id.button_skip);

        this.news = false;
        this.cooking_channel = false;
        this.talk_shows = false;

        this.button_skip.setText("SKIP →");
        this.button_skip.setTextSize(20);
        this.button_skip.setWidth(150);
        this.button_skip.setBackgroundColor(0xFF9900FF);
        this.button_skip.setTextColor(0xFFFFFFFF);

        final Intent completePageIntent = new Intent(this, completePage.class);

        this.button_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!news) {
                    button_news.setBackgroundColor(0xFF008577); // GREEN Background
                    button_news.setTextColor(0xFFFFFFFF); // WHITE text color
                }
                else {
                    button_news.setBackgroundResource(android.R.drawable.btn_default); // DEFAULT background
                    button_news.setTextColor(0xFF000000); // BLACK text color
                }
                news = !news;

                if (news || cooking_channel || talk_shows) {
                    button_skip.setText("→");
                    button_skip.setWidth(85);
                }
                else {
                    button_skip.setText("SKIP →");
                    button_skip.setWidth(150);
                }
            }
        });

        this.button_cooking_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cooking_channel) {
                    button_cooking_channel.setBackgroundColor(0xFF008577); // GREEN Background
                    button_cooking_channel.setTextColor(0xFFFFFFFF); // WHITE text color
                }
                else {
                    button_cooking_channel.setBackgroundResource(android.R.drawable.btn_default); // DEFAULT background
                    button_cooking_channel.setTextColor(0xFF000000); // BLACK text color
                }
                cooking_channel = !cooking_channel;

                if (news || cooking_channel || talk_shows) {
                    button_skip.setText("→");
                    button_skip.setWidth(85);
                }
                else {
                    button_skip.setText("SKIP →");
                    button_skip.setWidth(150);
                }
            }
        });

        this.button_talk_shows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!talk_shows) {
                    button_talk_shows.setBackgroundColor(0xFF008577); // GREEN Background
                    button_talk_shows.setTextColor(0xFFFFFFFF); // WHITE text color
                }
                else {
                    button_talk_shows.setBackgroundResource(android.R.drawable.btn_default); // DEFAULT background
                    button_talk_shows.setTextColor(0xFF000000); // BLACK text color
                }
                talk_shows = !talk_shows;

                if (news || cooking_channel || talk_shows) {
                    button_skip.setText("→");
                    button_skip.setWidth(85);
                }
                else {
                    button_skip.setText("SKIP →");
                    button_skip.setWidth(150);
                }
            }
        });

        this.button_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle currentUser = getIntent().getExtras();

                String favCableNetworks = "";

                if (news)
                    favCableNetworks += "NEWS-";
                if (cooking_channel)
                    favCableNetworks += "COOKING CHANNEL-";
                if (talk_shows)
                    favCableNetworks += "TALK SHOWS";

                currentUser.putString("favouriteCableNetworks", favCableNetworks);
                completePageIntent.putExtras(currentUser);

                startActivity(completePageIntent);
            }
        });
    }
}
