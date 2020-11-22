package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //Variables are initialized
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private TextView profile;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED )
        {Toast.makeText(this,"Thank you for granting permissions for Lifesaving Security Module...!!!",Toast.LENGTH_LONG).show();}
        else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);

        }


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
    }

     @RequiresApi(api = Build.VERSION_CODES.M)
     public void SoS(View view) {
        if(checkSelfPermission(Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED) {
            String message = "Test";
            String number = "+919665308970";

            SmsManager mySms = SmsManager.getDefault();
            mySms.sendTextMessage(number, null, message, null, null);
        }
        else{
            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
        }


    }


    public void ClickMenu(View view){
        //Open Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Open Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickProfile(){
        //Closes Drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When drawer is open
            //Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //Navigation Drawer Clickable
    public void ClickHome(View view){
        //Recreate Activity
        recreate();
    }

    public void ClickAwareness(View view){
        //Redirect activity to Awareness
        redirectActivity(this, Awareness.class);
    }

    public void ClickSkill(View view){
        //redirect Activity
        redirectActivity(this, SkillDevelopment.class);
    }

    public void ClickFinancial(View view){
        //redirect Activity
        redirectActivity(this, Financial.class);
    }

    public void ClickProblems(View view){
        //redirect Activity
        redirectActivity(this, Problems.class);
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
        Toast.makeText(getApplicationContext(),"Signed Out",Toast.LENGTH_SHORT).show();
        displayName();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize Intent
        Intent intent = new Intent(activity, aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        closeDrawer(drawerLayout);
    }

    public void CardAwareness(View view) {
        redirectActivity(this, Awareness.class);
    }

    public void CardSkill(View view) {
        redirectActivity(this, SkillDevelopment.class);
    }

    public void CardFinancial(View view) {
        redirectActivity(this, Financial.class);
    }

    public void CardProblems(View view) {
        redirectActivity(this, Problems.class);
    }

    @Override
    protected void onResume () {
        super.onResume();
        displayName();
    }

    private void displayName () {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            profile.setText(user.getEmail());
        } else {
            profile.setText("Your Profile");
        }
    }
}

