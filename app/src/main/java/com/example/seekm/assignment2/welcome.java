package com.example.seekm.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class welcome extends AppCompatActivity {
    DatabaseHelper myDB;
    TextView name;
    Button viewBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        myDB = new DatabaseHelper(this);

        name = (TextView)findViewById(R.id.welcomeName);
        Intent intent = getIntent();
        name.append(intent.getStringExtra( "name"));

        viewBtn = (Button)findViewById(R.id.viewAll);
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcome.this,viewUsers.class);
                startActivity(intent);
            }
        });

    }
}
