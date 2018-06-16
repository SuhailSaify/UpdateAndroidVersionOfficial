package com.example.pc.updateandroidversion_official;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {

    CardView cardView;
    TextView devicetext, brandtext, versiontext, updated;
    private RippleBackground rippleBackground;
    ImageView imageView;
    private String device, brand, version;
    int i = 0;
    String MY_PREFS_NAME = "update";
    Boolean restoredText=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        if (prefs != null) {
             restoredText = prefs.getBoolean("Updated", false);


        }
        updated = findViewById(R.id.updated);
        cardView = findViewById(R.id.card);
        devicetext = findViewById(R.id.device_name);
        brandtext = findViewById(R.id.device_brand);
        versiontext = findViewById(R.id.device_verion);

        if (restoredText) {
            updated.setText("Device up-to-date");
        }else {
            updated.setText("Never updated before");
        }
            rippleBackground = (RippleBackground) findViewById(R.id.ripple);
        imageView = (ImageView) findViewById(R.id.circle_image);
        start();
        setname();

        imageView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, ScanActivity.class));
                        finish();
                    }
                }
        );
        cardView.setOnClickListener(
                new
                        View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

                                editor.putBoolean("Updated", true);
                                editor.apply();
                                startActivity(new Intent(MainActivity.this, ScanActivity.class));
                                finish();
                            }
                        }
        );

    }

    private void setname() {

        device = Build.MODEL;
        brand = Build.BRAND;


        log("1", device);
        log("2", brand);


        devicetext.setText(brand.toUpperCase() + " " + device);


    }

    @Override
    public void onBackPressed() {

        if (i > 0) {
            i = 0;
            finish();
            this.finishAffinity();
        } else {
            Toast.makeText(this, "Press again to quit", Toast.LENGTH_SHORT).show();
            i++;
        }
    }

    void log(String s, String s1) {
        Log.d(s, s1);

    }

    private void start() {


        rippleBackground.startRippleAnimation();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
