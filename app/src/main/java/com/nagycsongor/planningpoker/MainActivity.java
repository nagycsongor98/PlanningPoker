package com.nagycsongor.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDatabase database =new MyDatabase(this);
        //database.insertProblem("bajvanhey");
        //database.getP();
        database.insertUser("Csongi");
        String newVoteName = database.getNewVote();
        database.votedTo(newVoteName);
    }
}
