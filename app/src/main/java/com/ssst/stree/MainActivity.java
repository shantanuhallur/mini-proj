package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;

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