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

    private String voteTo;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = getIntent();
        if (intent.hasExtra("userName")) {
            userName = intent.getStringExtra("userName");
            FragmentManager fragmentManager = getSupportFragmentManager();
            VoteFragment voteFragment = new VoteFragment(this);

            Bundle args = new Bundle();
            args.putString("userName", userName);
            voteFragment.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.container, voteFragment).commit();
        }
        if (intent.hasExtra("voteTo")) {
            voteTo = intent.getStringExtra("voteTo");
            FragmentManager fragmentManager = getSupportFragmentManager();
            ListFragment listFragment = new ListFragment();

            Bundle args = new Bundle();
            args.putString("voteTo", voteTo);
            listFragment.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.container, listFragment).commit();
        }
        intent = null;




    }

    public void setVoteTo(String voteTo) {
        this.voteTo = voteTo;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"szeva");
    }
}
