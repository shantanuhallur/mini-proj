package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SkillDevelopment extends AppCompatActivity {

    //Initialize Variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Skill Development");
        setContentView(R.layout.activity_skill_development);

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
        //Redirect to Awareness
        MainActivity.redirectActivity(this, Awareness.class);
    }

    public void ClickSkill(View view){
        //Recreate this activity
        recreate();
    }

    public void ClickFinancial(View view){
        //redirect Activity
        MainActivity.redirectActivity(this, Financial.class);
    }

    public void ClickProblems(View view){
        //redirect Activity
        MainActivity.redirectActivity(this, Problems.class);
    }

    public void ClickSignIn(View view) {
        //redirect Activity
        MainActivity.redirectActivity(this,SignIn.class);
    }

    public void ClickSignUp(View view) {
        //redirect Activity
        MainActivity.redirectActivity(this,SignUp.class);
    }

    public void ClickSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}