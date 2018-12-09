package com.example.seekm.assignment2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login, signUp,drop;
    DatabaseHelper myDB;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.loginUserName);
        password = (EditText) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signupButton);
        login = (Button) findViewById(R.id.loginButton2);
        myDB = new DatabaseHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });
        auth();
        drop();
    }


    public void auth (){
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String S1 = email.getText().toString();
                        String S2 = password.getText().toString();
                        if (S1.equals("") || S2.equals("") ) {
                            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String auth = myDB.authenticate(S1,S2);
                            if (auth.equals("invalid")) {
//                            Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                                box();
                            }

                            else{
                                Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this,welcome.class);
                                intent.putExtra("name",auth);
                                startActivity(intent);
                            }
                        }
                    }
                }
        );
    }

    public void box() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);
        a_builder.setMessage("Incorrect Username OR Password").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert =  a_builder.create();
        alert.setTitle("Alert!");
        alert.show();
    }


    public void drop(){
        drop = (Button)findViewById(R.id.drop);
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.drop();
            }
        });
    }
}

