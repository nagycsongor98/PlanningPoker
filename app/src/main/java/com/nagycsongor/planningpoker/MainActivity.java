package com.nagycsongor.planningpoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    private static final String TAG = "MainActivity";
    public static final String USER_NAME = "user_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        String userName = intent.getStringExtra("userName");

        MyDatabase database = new MyDatabase(this);
        database.insertUser(userName);
        String newVoteName = database.getNewVote();
        database.votedTo(newVoteName);
        database.connectUser(userName);

        String voteTo = database.getNewVote();
        if (voteTo == "") {
            Log.i(TAG,"Don't have vote");
        }
        else {
            Log.i(TAG,"Next vote: " + voteTo);

        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        VoteFragment voteFragment = new VoteFragment();
        fragmentManager.beginTransaction().replace(R.id.container,voteFragment).commit();

    }
}
