package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class Financial extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        //Open Drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickProfile(View view){
        //Close Drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        //Redirect activity to Home
        MainActivity.redirectActivity(this, MainActivity.class);
    }

    public void ClickAwareness(View view){
        //redirect Activity
        MainActivity.redirectActivity(this, Awareness.class);
    }

    public void ClickSkill(View view){
        //Redirect activity to SkillDevelopment
        MainActivity.redirectActivity(this, SkillDevelopment.class);
    }

    public void ClickFinancial(View view){
        //Recreate this activity
        recreate();
    }

    public void ClickProblems(View view){
        //redirect Activity
        MainActivity.redirectActivity(this, Problems.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}