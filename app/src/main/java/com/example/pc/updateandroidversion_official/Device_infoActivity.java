package com.example.pc.updateandroidversion_official;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import github.nisrulz.easydeviceinfo.base.EasyConfigMod;
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod;
import github.nisrulz.easydeviceinfo.base.EasyFingerprintMod;
import github.nisrulz.easydeviceinfo.base.EasyMemoryMod;
import github.nisrulz.easydeviceinfo.base.NetworkType;
import github.nisrulz.easydeviceinfo.base.RingerMode;
import github.nisrulz.easydeviceinfo.base.EasyNetworkMod;


public class Device_infoActivity extends AppCompatActivity {

    ActionBar actionBar;
    private float totalram, totalinternal, totalexternal, totalinternalAvailable, totalexternalAvailable;
    String devicename, hasfingerprint, hasfingerprintenabled, hasSDcard, ringermodetype,
            manufacturer, model, osname, osversion, rooted, brand, product, board;
    TextView mdevicename, mhasfingerprint, mhasfingerprintenabled, mhasSDcard, mringermode,
            mtotalram, mtotalinternal, mtotalexternal, mtotalinternalAvailable, mtotalexternalAvailable,
            mmanfacturer, mmodel, mosname, mosversion, mrooted, mbrand, mproduct, mboard;

    Context context = Device_infoActivity.this;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);


        actionBar = getSupportActionBar();
        actionBar.setTitle("Update Successful");

        devicename = Build.BOARD.toUpperCase() + " " + Build.MODEL;

        mtotalram = findViewById(R.id.mtotalram);
        mtotalexternal = findViewById(R.id.mtotalexternal);
        mtotalinternal = findViewById(R.id.mtotalinternal);
        mtotalexternalAvailable = findViewById(R.id.mtotalexternalAvailable);
        mtotalinternalAvailable = findViewById(R.id.mtotalinternalAvailable);


        mringermode = findViewById(R.id.mringermode);
        mhasSDcard = findViewById(R.id.mhasSDcard);
        mhasfingerprint = findViewById(R.id.mhasfingerprint);
        mdevicename = findViewById(R.id.mdevicename);
        mhasfingerprintenabled = findViewById(R.id.mhasfingerprintenabled);


        mmanfacturer = findViewById(R.id.mmanufacturer);
        mmodel = findViewById(R.id.mmodel);
        mosname = findViewById(R.id.mosname);
        mosversion = findViewById(R.id.mosversion);

        mrooted = findViewById(R.id.mrooted);
        mbrand = findViewById(R.id.mbrand);
        mproduct = findViewById(R.id.mproduct);
        mboard = findViewById(R.id.mboard);


        mdevicename.setText(devicename);

        EasyFingerprintMod easyFingerprintMod = new EasyFingerprintMod(context);

        if (easyFingerprintMod != null) {

            if (easyFingerprintMod.isFingerprintSensorPresent()) {
                mhasfingerprint.setText("Fingerprint Sensor : Present");

                if (easyFingerprintMod.areFingerprintsEnrolled()) {
                    mhasfingerprintenabled.setText("Finger Print Status : FingerPrints in use.");
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


        EasyMemoryMod easyMemoryMod = new EasyMemoryMod(context);

        if (easyMemoryMod != null) {
            totalram = easyMemoryMod.convertToMb(easyMemoryMod.getTotalRAM());
            totalinternalAvailable = easyMemoryMod.convertToGb(easyMemoryMod.getAvailableInternalMemorySize());
            totalexternalAvailable = easyMemoryMod.convertToGb(easyMemoryMod.getAvailableExternalMemorySize());
            totalinternal = easyMemoryMod.convertToGb(easyMemoryMod.getTotalInternalMemorySize());
            totalexternal = easyMemoryMod.convertToGb(easyMemoryMod.getTotalExternalMemorySize());

            String s = String.valueOf(totalinternalAvailable);
            String upToNCharacters1 = s.substring(0, Math.min(s.length(), 4));

            String s1 = String.valueOf(totalexternalAvailable);
            String upToNCharacters12 = s1.substring(0, Math.min(s.length(), 4));


            mtotalram.setText("Ram : " + String.valueOf(totalram) + " MB");
            mtotalexternalAvailable.setText("Available External Memory Size : " + upToNCharacters12 + " GB");
            mtotalinternal.setText("Total Internal Memory Size : " + String.valueOf(totalinternal) + " GB");
            mtotalinternalAvailable.setText("Available Internal Memory Size : " + upToNCharacters1 + " GB");
            mtotalexternal.setText("Total External Memory Size : " + String.valueOf(totalexternal) + " GB");
        }


        EasyDeviceMod easyDeviceMod = new EasyDeviceMod(context);
        if (easyDeviceMod != null) {

            Log.d("Inside", "In");
            manufacturer = easyDeviceMod.getManufacturer();
            model = easyDeviceMod.getModel();
            osname = easyDeviceMod.getOSCodename();
            osversion = easyDeviceMod.getOSVersion();


            if (easyDeviceMod.isDeviceRooted()) {
                rooted = "Yes";
            } else {
                rooted = "NO";
            }

            brand = easyDeviceMod.getBuildBrand();
            product = easyDeviceMod.getProduct();
            board = easyDeviceMod.getBoard();

            Log.d("manu", manufacturer);

            mmanfacturer.setText("Manufacturer : " + manufacturer.toUpperCase());
            mmodel.setText("Model : " + model);
            mosname.setText("OS : " + osname);
            mosversion.setText("OS Version : " + osversion);

            mrooted.setText("Rooted : " + rooted);
            mbrand.setText("Brand : " + brand.toUpperCase());
            mproduct.setText("Product : " + product);
            mboard.setText("Board : " + board.toUpperCase());


        }


    }

    @Override
    public void onBackPressed() {


        if (i > 0) {
            i = 0;
            startActivity(new Intent(Device_infoActivity.this, MainActivity.class));
        } else {
            Toast.makeText(this, "Press again to go back", Toast.LENGTH_SHORT).show();
            i++;
        }
    }


}
