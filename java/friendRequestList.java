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
import java.io.InputStreamReader;
import java.util.ArrayList;

public class friendRequestList extends AppCompatActivity {
    private TextView TextView_fullName;
    private TextView TextView_Age;
    private TextView TextView_hobby;

    private Button button_decline;
    private Button button_accept;
    private Button button_done;

    private String requestsDatabase;
    private String usersDatabase;
    private String requester_firstName;
    private String requester_lastName;
    private String requester_age;
    private String requester_favourite_sports;
    private String requester_favourite_cablenetworks;

    private int requestIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request_list);

        this.TextView_fullName = findViewById(R.id.TextView_fullName);
        this.TextView_Age = findViewById(R.id.TextView_Age);
        this.TextView_hobby = findViewById(R.id.TextView_hobby);

        this.button_decline = findViewById(R.id.button_decline);
        this.button_accept = findViewById(R.id.button_accept);
        this.button_done = findViewById(R.id.button_done);

        this.TextView_fullName.setText("You have no requests for now...");
        this.TextView_Age.setText("");
        this.TextView_hobby.setText("");

        this.button_decline.setEnabled(false);
        this.button_accept.setEnabled(false);

        final Bundle USER_SIGNED_IN = getIntent().getExtras();

        final ArrayList<String> friendRequests_database = new ArrayList<>();

        final Intent showingOtherUsersIntent = new Intent(this, showingOtherUsers.class);

        requestsDatabase = "FRIEND_REQUESTS.txt";
        usersDatabase = "USERS_DATABASE.txt";

        // SAMPLE REQUEST IS IN CSV FORMAT; "<sender_username>,<receiver_username>"
        // For example below, jackstutter sends a friend request to diashley
        final String SAMPLE_REQUESTS = "jackstutter,diashley,\n" +
                "jackstutter,jul.dementie,\n" +
                "diashley,jul.dementie,\n";

        FileOutputStream outputStream;

        // Try opening the database, if it doesn't exist then create a new one
        try {
            String line;
            FileInputStream fis = openFileInput(requestsDatabase); // if it gets past line 39, then database already exists
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while ( (line = br.readLine()) != null ) {
                friendRequests_database.add(line);
            }
        }
        catch (Exception e) {
            try { // if it gets past line 47, then database doesn't exist and needs to create a new one
                outputStream = openFileOutput(requestsDatabase, Context.MODE_PRIVATE);
                outputStream.write(SAMPLE_REQUESTS.getBytes());
                outputStream.close();
                Toast.makeText(friendRequestList.this, "New friend requests database has been created.", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e2) {
                // do nothing
            }
        }

        String requestReceiver;
        requester_firstName = null;
        requester_lastName = null;
        requester_age = null;
        requester_favourite_sports = null;
        requester_favourite_cablenetworks = null;

        for (int i = 0 ; i < friendRequests_database.size() ; i++) {
            requestReceiver = friendRequests_database.get(i).split(",")[1];

            if (USER_SIGNED_IN.getString("username").equals(requestReceiver)) {
                requestIndex = i;
                this.button_accept.setEnabled(true);
                this.button_decline.setEnabled(true);

                try {
                    String user;
                    String userArray[];

                    FileInputStream fis = openFileInput(usersDatabase);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);

                    while ( (user = br.readLine()) != null ) {
                        userArray = user.split(",");

                        if (requestReceiver.equals(userArray[0])) {
                            requester_firstName = userArray[3];
                            requester_lastName = userArray[4];
                            requester_age = userArray[2];
                            requester_favourite_sports = userArray[5];
                            requester_favourite_cablenetworks = userArray[6];

                            this.TextView_fullName.setText(requester_firstName + " " + requester_lastName);
                            this.TextView_Age.setText(requester_age);

                            String[] favSports = requester_favourite_sports.split("-");
                            String[] favCableNetworks = requester_favourite_cablenetworks.split("-");
                            String hobby = "";

                            if (favSports.length != 0 || favCableNetworks.length != 0) {
                                hobby += requester_firstName + " loves ";
                                if (favSports.length != 0) {
                                    for (String sport : favSports) {
                                        if (sport.length() != 0)
                                            hobby += sport + ", ";
                                    }
                                }
                                if (favCableNetworks.length != 0 && favCableNetworks[0].length() != 0) {
                                    hobby += " loves to watch ";
                                    for (String cablenetwork : favCableNetworks) {
                                        if (cablenetwork.length() != 0)
                                            hobby += cablenetwork + ", ";
                                    }

                                }
                            }

                            this.TextView_hobby.setText(hobby);

                            this.button_accept.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    friendRequests_database.remove(requestIndex);
                                    String updatedRequests = "";

                                    for (String r:friendRequests_database)
                                        updatedRequests += r;

                                    Toast.makeText(friendRequestList.this, requester_firstName + " " + requester_lastName + " is now your friend.", Toast.LENGTH_LONG).show();
                                    try {
                                        FileOutputStream outStream;

                                        outStream = openFileOutput(requestsDatabase, Context.MODE_PRIVATE);
                                        outStream.write(updatedRequests.getBytes());
                                        outStream.close();
                                    }
                                    catch (Exception e2) {
                                        // do nothing
                                    }
                                    startActivity(showingOtherUsersIntent);
                                }
                            });
                            this.button_decline.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(friendRequestList.this, "You have declined " + requester_firstName + " " + requester_lastName + "'s request.", Toast.LENGTH_LONG).show();
                                    friendRequests_database.remove(requestIndex);

                                    String updatedRequests = "";
                                    for (String r:friendRequests_database)
                                        updatedRequests += r;

                                    try {
                                        FileOutputStream outStream;

                                        outStream = openFileOutput(requestsDatabase, Context.MODE_PRIVATE);
                                        outStream.write(updatedRequests.getBytes());
                                        outStream.close();
                                    }
                                    catch (Exception e2) {
                                        // do nothing
                                    }
                                    startActivity(showingOtherUsersIntent);
                                }
                            });
                        }
                    }
                }
                catch (Exception e) {
                    // nothing
                }
            }
        }

        this.button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(showingOtherUsersIntent);
            }
        });
    }
}
