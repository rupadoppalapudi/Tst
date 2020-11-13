package com.example.vijaya.androidhardware;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class StorageActivity extends AppCompatActivity {
    TextView lblData;
    EditText txtData;
    private File pathSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txtData = findViewById(R.id.txtData);
        lblData = findViewById(R.id.lblData);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filename = "sample" + timeStamp + ".txt";

        pathSave = new File(getFilesDir(), filename);
    }

//    save the data entered by the user: appended data
    public void save(View view) {
        String text = txtData.getText().toString();
        text = text + " ";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathSave, true);
            fos.write(text.getBytes());
            txtData.getText().clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    load the save data which is viewable to the user
    public void load(View view) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(pathSave);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            lblData.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}