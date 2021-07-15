package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WelcomeGuest extends AppCompatActivity {

    private TextView loggedInName;
    private TextView dateTime;
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_guest);

        loggedInName = findViewById(R.id.loggedInGuestName);
        dateTime = findViewById(R.id.DateTime);
        call = findViewById(R.id.call);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = "Welcome " +  extras.getString("loggedInGuestName");
            String temp ="\n Good To see you";
            loggedInName.setText(value);
        }

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        dateTime.setText( timeStamp);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeGuest.this, BookRoomThroughInteraction.class);
                startActivity(i);
            }
        });
    }

    public void goToServices(View view) {
        Intent i = new Intent(WelcomeGuest.this, dayPlan.class);
        startActivity(i);
    }
}