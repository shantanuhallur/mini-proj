package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
    }

    public void SoS(View view) {

        String message = "Test";
        String number = "+919665308970";

        SmsManager mySms = SmsManager.getDefault();
        mySms.sendTextMessage(number,null,message,null,null);


    }

    public void AwarenessActivity(View view) {
        Intent intent = new Intent(this, AwarenessActivity.class);
        startActivity(intent);
    }

    public void SkillDevActivity(View view) {
        Intent intent = new Intent(this, SkillDevActivity.class);
        startActivity(intent);
    }

    public void FinancialActivity(View view) {
        Intent intent = new Intent(this, FinancialActivity.class);
        startActivity(intent);
    }

    public void MentalHealthActivity(View view) {
        Intent intent = new Intent(this, MentalHealthActivity.class);
        startActivity(intent);
    }
}