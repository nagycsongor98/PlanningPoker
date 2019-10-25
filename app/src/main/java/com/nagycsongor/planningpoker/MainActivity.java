package com.nagycsongor.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String USER_NAME = "user_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra(USER_NAME);

        // Connect to database.
        MyDatabase database =new MyDatabase(this);
        database.connectUser(userName);

        String voteTo = database.getNewVote();
        if (voteTo == "") {
            Log.i(TAG,"Don't have vote");
        }
        else {
            Log.i(TAG,"Next vote: " + voteTo);

        }

    }
}
