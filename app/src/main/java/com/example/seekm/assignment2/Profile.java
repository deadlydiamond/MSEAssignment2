package com.example.seekm.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView name,username,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView)findViewById(R.id.name);
        username = (TextView)findViewById(R.id.username);
        email = (TextView)findViewById(R.id.email);

        Intent intent = getIntent();
        name.append(intent.getStringExtra( "Name"));
        username.append(intent.getStringExtra("Username"));
        email.append(intent.getStringExtra("Email"));


    }
}
