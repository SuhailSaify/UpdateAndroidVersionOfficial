package com.example.pc.updateandroidversion_official;

import android.content.Intent;
import android.content.IntentFilter;
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
    TextView devicetext, brandtext, versiontext;
    private RippleBackground rippleBackground;
    ImageView imageView;
    private String device, brand, version;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.card);
        devicetext = findViewById(R.id.device_name);
        brandtext = findViewById(R.id.device_brand);
        versiontext = findViewById(R.id.device_verion);

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
