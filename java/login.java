package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class login extends AppCompatActivity {
    private EditText edittext_username;
    private EditText edittext_password;
    private Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.edittext_username = findViewById(R.id.EditText_username);
        this.edittext_password = findViewById(R.id.EditText_password);
        this.button_next = findViewById(R.id.button_next);

        final String database = "users_database.txt";
        final Intent showOtherUsersIntent = new Intent(this, showingOtherUsers.class);
        final Intent howOldAreYouIntent = new Intent(this, howOldAreYou.class);

        this.button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user;
                String[] userArray;
                boolean user_found_in_database = false;
                boolean incorrect_login_info = false;

                // Username is case-INsensitive; Password is case-sensitive
                String username = edittext_username.getText().toString().toLowerCase();
                String password = edittext_password.getText().toString();

                if (password.length() == 0) {
                    Toast.makeText(login.this, "Please fill in both the username and password fields..", Toast.LENGTH_SHORT).show();
                }

                try {
                    FileInputStream fis = openFileInput(database);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    while ( (user = br.readLine()) != null && !incorrect_login_info && !user_found_in_database) {
                        userArray = user.split(",");

                        // Username found on database but password is incorrect
                        if (username.equals(userArray[0]) && !password.equals(userArray[1])) {
                            incorrect_login_info = true;
                            Toast.makeText(login.this, "Whoops, you entered the wrong password... Please try again ;)", Toast.LENGTH_SHORT).show();
                        }
                        // Else, if username and password are found in db
                        else if (username.equals(userArray[0]) && password.equals(userArray[1])) {
                            user_found_in_database = true;

                            Bundle USER_SIGNED_IN = new Bundle();
                            USER_SIGNED_IN.putString("username", username);
                            USER_SIGNED_IN.putString("age", userArray[2]);
                            USER_SIGNED_IN.putString("firstName", userArray[3]);
                            USER_SIGNED_IN.putString("lastName", userArray[4]);
                            USER_SIGNED_IN.putString("favoriteSports", userArray[5]);
                            USER_SIGNED_IN.putString("favoriteCable", userArray[6]);

                            showOtherUsersIntent.putExtra("USER_SIGNED_IN", USER_SIGNED_IN);

                            startActivity(showOtherUsersIntent);
                        }
                    }

                    if (!user_found_in_database && !incorrect_login_info) {
                        if (password.length() < 6) {
                            Toast.makeText(login.this, "I couldn't find your username in the database. I'll creating a new account for you but please enter a password that's at least 6 characters long.", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(login.this, "I couldn't find your username in the database. I'm creating a new account for you with the username " + username + " and your entered password.", Toast.LENGTH_LONG).show();

                            Bundle USER_SIGN_UP = new Bundle();
                            USER_SIGN_UP.putString("username", username);
                            USER_SIGN_UP.putString("password", password);

                            howOldAreYouIntent.putExtra("USER_SIGN_UP", USER_SIGN_UP);

                            startActivity(howOldAreYouIntent);
                        }
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
