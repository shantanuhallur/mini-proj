package com.ssst.stree.security;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import com.ssst.stree.R;

public class sendMessage extends AppCompatActivity {

    private double lat1,lon1;
    private int st=0;
    MediaPlayer player;
    MediaRecorder recorder;
    float volume = 1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            String resp;
           try {
                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                    lat1 = getIntent().getExtras().getDouble("lat");
                    lon1 = getIntent().getExtras().getDouble("lon");
                    Log.d("security", "Service Started :" + lat1 + "*******************************************************************************************************************");
                    Log.d("TAG", "SoS: ----------------------------------------------------------------------------");

                    String url = "https://maps.google.com/?q=" + lat1 + ","+lon1;

//                    String message = "!!!@@@~~~EMERGENCY~~~@@@!!!\n" +
//                            "HELP ME ...!!! I AM IN DANGER AND PLEASE COME AS SOON AS YOU CAN !!! MY LOCATION IS :- \n" +
//                            "Latitude :- \n" +
//                            "Longitude :-\n";

                    String message = "Help!!" + "\n" + url;
                    String number = "+919422429871";
                    SmsManager mySms = SmsManager.getDefault();
                    mySms.sendTextMessage(number, null, message, null, null);
                    st=1;

                } else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }

                //buzzer sound
            if (player == null) {
                player = MediaPlayer.create(this, R.raw.buzzer);
            }
            player.start();
            player.setLooping(true);
            player.setVolume(volume, volume);


            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage(); }

    }
}