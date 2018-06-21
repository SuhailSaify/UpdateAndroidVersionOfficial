package com.example.pc.updateandroidversion_official;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Context context;
    CardView cardView;
    TextView devicetext, brandtext, versiontext, updated;
    private RippleBackground rippleBackground;
    ImageView imageView;
    private String device, brand, version;
    int i = 0;
    String MY_PREFS_NAME = "update";
    Boolean restoredText = false;
    SharedPreferences.Editor editor;
    String valid_until = "23/6/2018";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        checkconnection();


     /*   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = null;
        try {
            strDate = sdf.parse(valid_until);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (new Date().after(strDate)) {

            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(MainActivity.this);
            }
            builder.setTitle("Cannot Continue")
                    .setMessage("Contact Developer to Continue")
                    .setCancelable(false)
                    .setPositiveButton("Close App", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            closeapp();

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }
*/

        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

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
        } else {
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


                                editor.putBoolean("Updated", true);
                                editor.apply();
                                startActivity(new Intent(MainActivity.this, ScanActivity.class));
                                finish();
                            }
                        }
        );

    }


    public void closeapp()

    {
        finish();
        this.finishAffinity();
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

    public void checkconnection() {

        Boolean isConnected;
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            startActivity(new Intent(MainActivity.this, NoConnectionActivity.class));
        } else {
          //nothing
           // Toast.makeText(context, "NOT CONNECTED", Toast.LENGTH_SHORT).show();
        }
    }

}

