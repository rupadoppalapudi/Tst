package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

        private static final int REQ_CODE_SPEECH_INPUT = 100;
        private TextView mVoiceInputTv;
        private ImageButton mSpeakBtn;
        private SharedPreferences.Editor editor;
        private SharedPreferences preferences;
        TextToSpeech tts;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Fetch text view and speak button by means of Id
            mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
            mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);

            // Initialize Preferences, Editor
            preferences = getSharedPreferences("namePrefs", 0);
            editor = preferences.edit();

            // on Page Load, First Say and display Hello
            tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        // Set the language
                        tts.setLanguage(Locale.US);
                        tts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
                        mVoiceInputTv.setText(Html.fromHtml("<p style=\"color:#00008B;\">Speaker: Hello!!</p>"));
                    }
                }
            });
            mSpeakBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startVoiceInput();
                }
            });
        }

        private void startVoiceInput() {
            // Starting Voice Input on click of Mic button
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello!!");
            try {
                startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
            } catch (ActivityNotFoundException a) {
                a.printStackTrace();
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case REQ_CODE_SPEECH_INPUT: {
                    if (resultCode == RESULT_OK && null != data) {
                        // Fetch the result
                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (result != null && result.size() > 0) {
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:#006400;\">User: " + result.get(0) + "</p>"));
                            // If user says hello, Ask for User's name
                            //then display the greeting text with users name and ask for service required
                            if (result.get(0).equalsIgnoreCase("hello")) {
                                tts.speak(" What's your name ?", TextToSpeech.QUEUE_FLUSH, null);
                                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:#00008B;\">Speaker: What is your name ?</p>"));
                            } else if (result.get(0).contains("name")) {
                                String name = result.get(0).substring(result.get(0).lastIndexOf(' ') + 1);
                                // Set the Editor - displaying the speaker message along with name
                                editor.putString("name", name).apply();
                                tts.speak("Hello, " + name + ". How could I help you?",
                                        TextToSpeech.QUEUE_FLUSH, null);
                                mVoiceInputTv.append(Html.fromHtml("<p style=\"color: #00008B;\">Speaker: Hello, " + name + ". How could I help you?</p>"));
                            } else if (result.get(0).contains("not feeling well")) {
                                tts.speak("I can understand. Please tell your symptoms in short",
                                        TextToSpeech.QUEUE_FLUSH, null);
                                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:#00008B;\">Speaker: I can understand. Please tell your symptoms in short</p>"));
                            } else if (result.get(0).contains("thank you")) {
                                tts.speak("Thank you too, " + preferences.getString("name", "") + " Take care!", TextToSpeech.QUEUE_FLUSH, null);
                                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:#00008B;\">Speaker: Thank you too, " + preferences.getString("name", "") + " Take care</p>"));
                            } else if (result.get(0).contains("time")) {
                                // Speaking and displaying the Time for the User
                                SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
                                Date now = new Date();
                                String[] strDate = sdfDate.format(now).split(":");
                                if (strDate[1].contains("00")) strDate[1] = "o'clock";
                                tts.speak("The time is: " + sdfDate.format(now), TextToSpeech.QUEUE_FLUSH, null);
                                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:#00008B;\">Speaker: The time is : " + sdfDate.format(now) + "</p>"));
                            } else if (result.get(0).contains("medicine")) {
                                tts.speak("I think you have fever. Please take this medicine.",
                                        TextToSpeech.QUEUE_FLUSH, null);
                                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:#00008B;\">Speaker: I think you have fever. Please take this medicine.</p>"));
                            } else {
                                // if the user asks other questions
                                tts.speak("Sorry, I didn't catch that. Could you try again!", TextToSpeech.QUEUE_FLUSH, null);
                                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:#00008B;\">Speaker: Sorry, I didn't catch that. Could you try that again!</p>"));
                            }
                        }
                    }
                    break;
                }

            }
        }
    }

