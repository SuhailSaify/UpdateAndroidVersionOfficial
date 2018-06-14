package com.example.pc.updateandroidversion_official;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ScanActivity extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView textView;
TextView textView2;
    ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        textView2=findViewById(R.id.text);
        circularProgressBar = findViewById(R.id.circular_progress);
        textView = findViewById(R.id.progresstext);
        toolbar = getSupportActionBar();
        toolbar.setTitle("Scanning...");

        textView2.setText("Checking for Updates, pleasse wait");
        Handler handler1 = new Handler();
        for (int a = 1; a <= 100; a++) {
            final int finalA = a;
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {
                    circularProgressBar.setProgress(finalA);

                    textView.setText(String.valueOf(finalA) + "%");

                    if(finalA==100)
                    {connecttoserver();};

                }
            }, 300 * a);

        }


    }

    private void connecttoserver() {

        textView2.setText("Scan Successful \n Connecting to server...");
        Handler handler1 = new Handler();
        for (int a = 1; a <= 100; a++) {
            final int finalA = a;
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {
                    circularProgressBar.setProgress(finalA);
                    textView.setText(String.valueOf(finalA) + "%");



                }
            }, 300 * a);

        }

    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();


        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(ScanActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(ScanActivity.this);
        }
        builder.setTitle("Stop Scan ?")
                .setMessage("")
                .setPositiveButton("Stop", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        finish();
                        startActivity(new Intent(ScanActivity.this, MainActivity.class));
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();


    }
}