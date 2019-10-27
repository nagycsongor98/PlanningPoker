package com.nagycsongor.planningpoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        VoteFragment voteFragment = new VoteFragment();
        fragmentManager.beginTransaction().replace(R.id.container,voteFragment).commit();

    }
}
