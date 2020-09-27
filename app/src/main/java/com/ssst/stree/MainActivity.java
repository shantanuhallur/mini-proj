package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SoS(View view) {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage("997529484", null, "hello javatpoint", pi,null);



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