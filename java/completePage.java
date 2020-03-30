package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class completePage extends AppCompatActivity {
    private Button button_next;

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back button has been disabled to prevent errors during the sign-up process...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_page);

        this.button_next = findViewById(R.id.button_next);

        Bundle USER_SIGN_UP = getIntent().getExtras();
        String user_details = USER_SIGN_UP.getString("username") + "," + USER_SIGN_UP.getString("password") + "," +
                USER_SIGN_UP.getString("age") + "," +
                USER_SIGN_UP.getString("firstName") + "," +
                USER_SIGN_UP.getString("lastName") + "," +
                USER_SIGN_UP.getString("favouriteSports") + "," +
                USER_SIGN_UP.getString("favouriteCableNetworks") + ",\n";

        String database = "USERS_DATABASE.txt";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(database, Context.MODE_APPEND);
            outputStream.write(user_details.getBytes());
            outputStream.close();
        }
        catch (Exception e2) {
        }

        final Intent showingOtherUsersIntent = new Intent(this, showingOtherUsers.class);
        showingOtherUsersIntent.putExtras(USER_SIGN_UP);

        this.button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(showingOtherUsersIntent);
            }
        });
    }
}
