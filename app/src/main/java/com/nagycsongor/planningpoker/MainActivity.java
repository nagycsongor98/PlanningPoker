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


        FragmentManager fragmentManager = getSupportFragmentManager();
        VoteFragment voteFragment = new VoteFragment(this);

        Bundle args = new Bundle();
        args.putString("userName", userName);
        voteFragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.container, voteFragment).commit();

    }
}
