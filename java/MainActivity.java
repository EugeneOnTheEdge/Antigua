package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private Button button_get_started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button_get_started = findViewById(R.id.button_get_started);

        String database = "users_database.txt";
        FileOutputStream outputStream;

        String USERS = "jackstutter,jstutter01,63,Jack,Stutterman,Golf-Frisbee,News,\n" +
                "jul.dementie,dementia101,50,Julia,Dementia,Walking,Talk Shows,\n" +
                "diashley,ashdie1950,89,Ashley,Diabetie,,Cooking Channel,\n";

        int numberOfUsers = 0;

        try {
            FileInputStream fis = openFileInput(database);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ( (br.readLine()) != null ) {
                numberOfUsers++;
            }

            // Add USERS to database if database is empty
            if (numberOfUsers < 1) {
                outputStream = openFileOutput(database, Context.MODE_APPEND);
                outputStream.write(USERS.getBytes());
                outputStream.close();
                Toast.makeText(MainActivity.this, "User database has been created", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        final Intent loginIntent = new Intent(this, login.class);
        this.button_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loginIntent);
            }
        });
    }
}
