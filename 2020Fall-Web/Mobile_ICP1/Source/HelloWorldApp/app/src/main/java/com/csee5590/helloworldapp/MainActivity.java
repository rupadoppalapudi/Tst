package com.csee5590.helloworldapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button Loginbutton;
    String uname;
    String pwd;
    EditText ControlUsername;
    EditText ControlPassword;
    TextView ControlStatus;
    boolean flag = false;

    //Validates the login credentials by comparing with the given credentials
    //if success then it redirects to welcome page else it displays error message
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ControlUsername = findViewById(R.id.editTextTextPersonName);
        ControlPassword = findViewById(R.id.editTextTextPassword);
        ControlStatus = findViewById(R.id.textView);
        uname = ControlUsername.getText().toString();
        pwd = ControlPassword.getText().toString();
        Loginbutton = findViewById(R.id.button);
        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ControlUsername.getText().toString().isEmpty() && !ControlPassword.getText().toString().isEmpty()) {
                    if (ControlUsername.getText().toString().equals("rupa") && ControlPassword.getText().toString().equals("sri"))
                    { flag = true; }
                }
                if (!flag)
                { ControlStatus.setText("Incorrect Username or Password. Please re-enter!"); }
                else
                {
                    ControlStatus.setText("Success");
                    reDirectToWelcomePage();
                }
            }
        });
    }

    public void reDirectToWelcomePage () {
        Intent intent = new Intent(MainActivity.this, MainPage.class);
        startActivity(intent);
    }
}

