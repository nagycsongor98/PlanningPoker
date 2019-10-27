package com.nagycsongor.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    private EditText name;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.loginName);
        loginButton = findViewById(R.id.loginButton);

    }


    public void login(View view) {

        String userName = name.getText().toString();

        if (!userName.isEmpty()) {
            //INSERT userName into DB
            MyDatabase database = new MyDatabase(this);
            //database.insertProblem("bajvanhey");
            //database.getP();
            database.insertUser(userName);
            String newVoteName = database.getNewVote();
            database.votedTo(newVoteName);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userName",userName);
            startActivity(intent);
        }


    }
}
