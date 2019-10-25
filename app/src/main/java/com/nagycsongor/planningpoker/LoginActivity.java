package com.nagycsongor.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


    public void login(View view)
    {
        String userName = name.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this,"Enter your name!",Toast.LENGTH_LONG).show();
        }else{
            //INSERT userName into DB
            MyDatabase database =new MyDatabase(this);
            database.insertUser(userName);

            //Go to the MainActivity.
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(MainActivity.USER_NAME,userName);
            startActivity(intent);
        }
    }
}
