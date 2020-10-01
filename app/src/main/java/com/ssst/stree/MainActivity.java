package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        navigation = (NavigationView)findViewById(R.id.navigate);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpDrawerContent(navigation);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
    }

    //navigation to different modules after selecting onr of the item from drawer
    public void selectItemDrawer(MenuItem menuItem){
        Fragment fragment = null;
        //checking which item is selected from navigation drawer
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.awareness:
                fragmentClass = Awareness.class;
                break;
            case R.id.skilldev:
                fragmentClass = SkillDev.class;
                break;
            case R.id.financial:
                fragmentClass = Financial.class;
                break;
            case R.id.problems:
                fragmentClass = Problems.class;
                break;
            default:
                fragmentClass = MainActivity.class;
        }
        menuItem.setChecked(true);   // Selected module will be highlighted
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private void setUpDrawerContent(NavigationView navigation){
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void SoS(View view) {

        String message = "Test";
        String number = "+919665308970";

        SmsManager mySms = SmsManager.getDefault();
        mySms.sendTextMessage(number,null,message,null,null);


    }


}