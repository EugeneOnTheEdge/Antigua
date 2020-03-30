package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class setNameOfUsers extends AppCompatActivity {
    private EditText edittext_firstName;
    private EditText edittext_lastName;
    private Button button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name_of_users);

        this.edittext_firstName = findViewById(R.id.edittext_firstName);
        this.edittext_lastName = findViewById(R.id.edittext_lastName);
        this.button_next = findViewById(R.id.button_next);

        final Intent favouriteSportIntent = new Intent(this, favouriteSport.class);

        this.button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edittext_firstName.getText().toString().length() == 0 || edittext_lastName.getText().toString().length() == 0)
                    Toast.makeText(setNameOfUsers.this, "Please enter both first and last names...", Toast.LENGTH_SHORT).show();
                else {
                    Bundle currentUser = getIntent().getExtras();
                    currentUser.putString("firstName", edittext_firstName.getText().toString());
                    currentUser.putString("lastName", edittext_lastName.getText().toString());

                    favouriteSportIntent.putExtras(currentUser);

                    startActivity(favouriteSportIntent);
                }
            }
        });
    }
}
