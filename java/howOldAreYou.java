package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class howOldAreYou extends AppCompatActivity {
    private SeekBar seekbar_age;
    private EditText edittext_age;
    private Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_old_are_you);

        this.seekbar_age = findViewById(R.id.seekbar_age);
        this.edittext_age = findViewById(R.id.edittext_age);
        this.button_next = findViewById(R.id.button_next);

        final Intent setNameOfUsersIntent = new Intent(this, setNameOfUsers.class);

        this.seekbar_age.setProgress(50);
        this.seekbar_age.incrementProgressBy(1);
        this.seekbar_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edittext_age.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // nothing
            }
        });

        this.button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = seekbar_age.getProgress();
                if (age < 50) {
                    Toast.makeText(howOldAreYou.this, "Sorry, you gotta be at least 50 years old to register...", Toast.LENGTH_SHORT).show();
                }
                else {
                    Bundle currentUser = getIntent().getExtras();
                    String username = currentUser.getString("username");
                    String password = currentUser.getString("password");

                    Bundle USER_SIGN_UP = new Bundle();
                    USER_SIGN_UP.putString("username", username);
                    USER_SIGN_UP.putString("password", password);
                    USER_SIGN_UP.putString("age", Integer.toString(age));

                    setNameOfUsersIntent.putExtras(USER_SIGN_UP);

                    startActivity(setNameOfUsersIntent);
                }
            }
        });
    }
}
