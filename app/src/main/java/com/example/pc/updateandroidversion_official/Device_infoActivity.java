package com.example.pc.updateandroidversion_official;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import github.nisrulz.easydeviceinfo.base.EasyConfigMod;
import github.nisrulz.easydeviceinfo.base.EasyFingerprintMod;
import github.nisrulz.easydeviceinfo.base.NetworkType;
import github.nisrulz.easydeviceinfo.base.RingerMode;
import github.nisrulz.easydeviceinfo.base.EasyNetworkMod;


public class Device_infoActivity extends AppCompatActivity {

    String devicename, hasfingerprint, hasfingerprintenabled, hasSDcard, ringermodetype;
    TextView mdevicename, mhasfingerprint, mhasfingerprintenabled, mhasSDcard, mringermode;
    Context context = Device_infoActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        devicename = Build.BOARD.toUpperCase() + " " + Build.MODEL;

        mringermode = findViewById(R.id.mringermode);
        mhasSDcard = findViewById(R.id.mhasSDcard);
        mhasfingerprint = findViewById(R.id.mhasfingerprint);
        mdevicename = findViewById(R.id.mdevicename);
        mhasfingerprintenabled = findViewById(R.id.mhasfingerprintenabled);

        mdevicename.setText(devicename);

        EasyFingerprintMod easyFingerprintMod = new EasyFingerprintMod(context);

        if (easyFingerprintMod != null) {

            if (easyFingerprintMod.isFingerprintSensorPresent()) {
                mhasfingerprint.setText("Fingerprint Sensor : Present");

                if (easyFingerprintMod.areFingerprintsEnrolled()) {
                    mhasfingerprintenabled.setText("Finger Print Status : FingerPrints Present.");
                } else {
                    mhasfingerprintenabled.setText("Finger Print Status :  Not in use ");
                }

            } else {
                mhasfingerprint.setText("Fingerprint Sensor : Absent");
                mhasfingerprintenabled.setVisibility(View.GONE);
            }

        }

        EasyConfigMod easyConfigMod = new EasyConfigMod(context);

        if (easyConfigMod != null) {
            if (easyConfigMod.hasSdCard()) {
                hasSDcard = "SD CARD : Present";


            } else {
                hasSDcard = "SD CARD : Absent";

            }
            mhasSDcard.setText(hasSDcard);
        }


        @RingerMode
        int ringermode = easyConfigMod.getDeviceRingerMode();
        if (ringermode > 0) {

            switch (ringermode) {
                case RingerMode.NORMAL:
                    ringermodetype = "Ringer mode : Normal";
                    break;
                case RingerMode.VIBRATE:
                    ringermodetype = ("Ringer mode : Vibrate");
                    break;
                case RingerMode.SILENT:
                    ringermodetype = ("Ringer mode : Silent");
                    break;
                default:
                    //do nothing
                    break;
            }
            mringermode.setText(ringermodetype);

        }


   

    }
}
