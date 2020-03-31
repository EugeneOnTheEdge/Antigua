package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class showingOtherUsers extends AppCompatActivity {

    TextView userName;
    TextView infoOfUser;
    Button previousFriend;
    Button nextFriend;
    private Button button_addFriend;
    private Button button_viewFriendRequest;
    TextView counter;
    private int index;
    private String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_other_users);

        final Bundle USER_SIGNED_IN = getIntent().getExtras();
        final Intent friendRequestListIntent = new Intent(this, friendRequestList.class);

        //VARIABLES
        userName = (TextView) findViewById(R.id.TextView_userName);
        infoOfUser = (TextView) findViewById(R.id.TextView_infoOfUser);
        previousFriend = (Button) findViewById(R.id.button_previousFriend);
        nextFriend = (Button) findViewById(R.id.button_nextFriend);
        this.button_addFriend = findViewById(R.id.button_addFriend);
        this.button_viewFriendRequest = findViewById(R.id.button_viewFriendRequest);

        this.button_viewFriendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(friendRequestListIntent);
            }
        });

        String database = "USERS_DATABASE.txt";
        FileOutputStream outputStream;

        String USERS = "jackstutter,jstutter01,63,Jack,Stutterman,Golf-Frisbee,News,\n" +
                "jul.dementie,dementia101,50,Julia,Dementia,Walking,Talk Shows,\n" +
                "diashley,ashdie1950,89,Ashley,Diabetie,,Cooking Channel,\n";

        // Try opening the database, if it doesn't exist then create a new one
        try {
            FileInputStream fis = openFileInput(database);
        }
        catch (Exception e) {
            try {
                outputStream = openFileOutput(database, Context.MODE_APPEND);
                outputStream.write(USERS.getBytes());
                outputStream.close();
                Toast.makeText(showingOtherUsers.this, "New database has been created.", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e2) {
                // do nothing
            }
        }

        index = 0;
        ArrayList<String> lines = new ArrayList<String>();

        //defines the title of the file output.txt
        String filename = "USERS_DATABASE.txt";

        //puts the lines of output.txt into the lines array list
        try {
            String filename2 = "USERS_DATABASE.txt";
            String line;

            FileInputStream fis = openFileInput(filename2);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ( (line = br.readLine()) != null ) {
                lines.add(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        items = lines.get(index).split(",");
        userName.setText(items[3] + " " + items[4]);
        infoOfUser.setText(items[3] + " is " + items[2] + " and enjoys " + items[5] + " and " + items[6]);

        this.button_addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String database = "FRIEND_REQUESTS.txt";
                    FileOutputStream outputStream;

                    outputStream = openFileOutput(database, Context.MODE_APPEND);
                    outputStream.write((USER_SIGNED_IN.get("username") + "," + items[0] + ",\n").getBytes());
                    outputStream.close();
                }
                catch (Exception e2) {
                    // do nothing
                }
            }
        });

        final int size = lines.size();
        final ArrayList<String> tempLines = lines;

        previousFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Reached Beginning", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    index--;
                    counter.setText(index + 1 + "");
                    ArrayList<String> theLines = tempLines;
                    items = theLines.get(index).split(",");
                    userName.setText(items[3] + " " + items[4]);
                    infoOfUser.setText(items[3] + " is " + items[2] + " and enjoys " + items[5] + " and " + items[6]);
                }

                button_addFriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String database = "FRIEND_REQUESTS.txt";
                            FileOutputStream outputStream;

                            outputStream = openFileOutput(database, Context.MODE_APPEND);
                            outputStream.write((USER_SIGNED_IN.get("username") + "," + items[0] + ",\n").getBytes());
                            outputStream.close();
                        }
                        catch (Exception e2) {
                            // do nothing
                        }
                    }
                });
            }
        });

        nextFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == size - 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Reached End", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    index++;
                    counter.setText(index + 1 + "");
                    ArrayList<String> theLines = tempLines;
                    String[] items = theLines.get(index).split(",");
                    userName.setText(items[3] + " " + items[4]);
                    infoOfUser.setText(items[3] + " is " + items[2] + " and enjoys " + items[5] + " and " + items[6]);
                }

                button_addFriend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            String database = "FRIEND_REQUESTS.txt";
                            FileOutputStream outputStream;

                            outputStream = openFileOutput(database, Context.MODE_APPEND);
                            outputStream.write((USER_SIGNED_IN.get("username") + "," + items[0] + ",\n").getBytes());
                            outputStream.close();
                        }
                        catch (Exception e2) {
                            // do nothing
                        }
                    }
                });
            }
        });
    }
}