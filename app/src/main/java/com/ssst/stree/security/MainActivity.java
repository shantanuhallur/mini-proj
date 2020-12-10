package com.ssst.stree.security;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ssst.stree.support.LoadingBar;
import com.ssst.stree.problems.Problems;
import com.ssst.stree.R;
import com.ssst.stree.auth.SignIn;
import com.ssst.stree.auth.SignUp;
import com.ssst.stree.skilldev.SkillDevelopment;
import com.ssst.stree.awareness.Awareness;
import com.ssst.stree.financial.Financial;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    Location location12;
    float volume = 1;
    MediaPlayer player;
    MediaRecorder recorder;
    //Variables are initialized
    private DrawerLayout drawerLayout;
    private TextView profile;
    private LoadingBar loadingBar;
    double lat, lon;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           Log.d("in if","of permission");
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            location12 = location;
                            Log.d("security", location.toString()+ "*******************************************************************************************************************");
                            Log.d("security",   "*******************************************************************************************************************");

                        }
                    }
                });



        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Thank you for granting permissions for Lifesaving Security Module...!!!", Toast.LENGTH_LONG).show();
        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void SoS(View view) {




        Log.d("security",  "*******************************************************************************************************************");
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();

    }

    private class AsyncTaskRunner extends  AsyncTask<String, String, String> {

        private String resp;



        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected String doInBackground(String... params) {
            //publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {

                //buzzer sound
                if (player == null) {
                    player = MediaPlayer.create(MainActivity.this, R.raw.buzzer);
                }
                player.start();
                player.setLooping(true);
                player.setVolume(volume, volume);


                if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                    Log.d("hi", "hi");

                    String message = "!!!@@@~~~EMERGENCY~~~@@@!!!\n" +
                            "HELP ME ...!!! I AM IN DANGER AND PLEASE COME AS SOON AS YOU CAN !!! MY LOCATION IS :- \n" +
                            "Latitude :- \n" + location12.toString()+
                            "Longitude :-\n"  ;

                    String number = "+919665308970";
                    SmsManager mySms = SmsManager.getDefault();
                    mySms.sendTextMessage(number, null, message, null, null);

                } else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }


        } catch(Exception e)

        {
            e.printStackTrace();
            resp = e.getMessage();
        }
            return resp;
    }


//        @Override
//        protected void onPostExecute(String result) {
//            // execution of result of Long time consuming operation
//            progressDialog.dismiss();
//            finalResult.setText(result);
//        }

}


    public void ClickMenu(View view) {
        //Open Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Open Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickProfile() {
        //Closes Drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //When drawer is open
            //Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //Navigation Drawer Clickable
    public void ClickHome(View view) {
        //Recreate Activity
        recreate();
    }

    public void ClickAwareness(View view) {
        //Redirect activity to Awareness
        redirectActivity(this, Awareness.class);
    }

    public void ClickSkill(View view) {
        //redirect Activity
        redirectActivity(this, SkillDevelopment.class);
    }

    public void ClickFinancial(View view) {
        //redirect Activity
        redirectActivity(this, Financial.class);
    }

    public void ClickProblems(View view) {
        //redirect Activity
        redirectActivity(this, Problems.class);
    }

    public void ClickSignIn(View view) {
        //redirect Activity
        MainActivity.redirectActivity(this, SignIn.class);
    }

    public void ClickSignUp(View view) {
        //redirect Activity
        MainActivity.redirectActivity(this, SignUp.class);
    }

    public void ClickSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(), "Signed Out", Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        displayName();
    }

    private void displayName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            profile.setText(user.getEmail());
        } else {
            profile.setText("Your Profile");
        }
    }
}


//    @RequiresApi(api = Build.VERSION_CODES.M)
//    public void SoS(View view) {
//


//
//
//
//        //buzzer sound
//        if(player==null){
//            player = MediaPlayer.create(this,R.raw.buzzer);
//        }
//        player.start();
//        player.setLooping(true);
//        player.setVolume(volume,volume);
//
//
//        if(checkSelfPermission(Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED&&
//                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {

//fusedLocationClient.getLastLocation()
//        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//@Override
//public void onSuccess(Location location) {
//        // Got last known location. In some rare situations this can be null.
//        if (location != null) {
//        lat = location.getLatitude();
//        lon = location.getLongitude();
//
//
////                                SmsManager smsManager = SmsManager.getDefault();
////                                StringBuffer smsBody = new StringBuffer();
////                                smsBody.append(message);
////                                smsBody.append("http://maps.google.com?q=");
////                                smsBody.append(location.getLatitude());
////                                smsBody.append(",");
////                                smsBody.append(location.getLongitude());
////                                smsManager.sendTextMessage(phoneNumber, null, smsBody.toString(), null, null);
////                             double mylat= location.getLatitude();
////                                double mylon = location.getLongitude();
//        Log.d("security", String.valueOf(lat) + "*******************************************************************************************************************");
//        Log.d("security", String.valueOf(lon) + "*******************************************************************************************************************");
//
//
//        }
//        }
//        });
//
//            Log.d("hi","hi");
//
//            String message = "!!!@@@~~~EMERGENCY~~~@@@!!!\n" +
//                    "HELP ME ...!!! I AM IN DANGER AND PLEASE COME AS SOON AS YOU CAN !!! MY LOCATION IS :- \n" +
//                    "Latitude :- \n" +lat+
//                    "Longitude :-\n"+lon;
//
//            String number = "+919665308970";
//            SmsManager mySms = SmsManager.getDefault();
//            mySms.sendTextMessage(number, null, message, null, null);
//
//        }
//
//
//
//
//        else{
//            requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
//        }
//        }
