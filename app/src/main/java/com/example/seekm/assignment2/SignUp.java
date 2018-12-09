package com.example.seekm.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText name, username, email, password, confirmPassword;
    Button register, viewAllBtn;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.signupUsername);
        email = (EditText) findViewById(R.id.signupEmail);
        Email = email.toString();
        password = (EditText) findViewById(R.id.signupPassword);
        confirmPassword = (EditText) findViewById(R.id.signupConfirm);
        register = (Button) findViewById(R.id.register);

        myDB = new DatabaseHelper(this);

        addData();
    }



    public void addData() {
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String S1 = name.getText().toString();
                        String S2 = username.getText().toString();
                        String S3 = email.getText().toString();
                        String S4 = password.getText().toString();
                        String S5 = confirmPassword.getText().toString();
                        if (S1.equals("") || S2.equals("") || S3.equals("") || S4.equals("") || S5.equals("")) {
                            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
                        } else {
                            if (S4.equals(S5)) {
                                boolean checkMail = myDB.checkEmail(S3);
                                if (checkMail == true) {
                                    boolean isInserted = myDB.insertData(S1, S2, S3, S4);
                                    if (isInserted = true) {
                                        Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(SignUp.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                } else if (checkMail == false) {
                                    Toast.makeText(SignUp.this, "Email already existed", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(SignUp.this, "Password didn't match", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                }
        );

    }
}