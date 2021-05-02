package com.ssst.stree.security;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.ssst.stree.problems.Problems;
import com.ssst.stree.R;
import com.ssst.stree.auth.SignIn;
import com.ssst.stree.auth.SignUp;
import com.ssst.stree.skilldev.SkillDevelopment;
import com.ssst.stree.awareness.Awareness;
import com.ssst.stree.financial.Financial;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    float volume = 1;
    MediaPlayer player;
    //Variables are initialized
    private DrawerLayout drawerLayout;
    private TextView profile;
    private ContactDatabase db;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double latitude;
    private double longitude;
    String AudioSavePathInDevice = null;
    MediaRecorder mediaRecorder ;
    boolean startRecording = true;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDB();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        EmergencyNumbers.contactList = db.contactDao().getContacts();
        if(EmergencyNumbers.contactList.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Add Emergency Contacts");
            alert.setMessage("Your emergency contacts list is empty!!Please add emergency contacts!!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this,EmergencyNumbers.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            alert.show();
        }

        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Thank you for granting permissions for Lifesaving Security Module...!!!", Toast.LENGTH_LONG).show();
        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void SoS(View view) {
        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        ) {

            if(startRecording) {
                AudioSavePathInDevice =
                        Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                                System.currentTimeMillis() + "AudioRecording.3gp";

                MediaRecorderReady();

                try {
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                Toast.makeText(MainActivity.this, "Recording started",
                        Toast.LENGTH_LONG).show();
                startRecording = false;
            }
            else{

                mediaRecorder.stop();
                Toast.makeText(MainActivity.this, "Recording Completed",
                        Toast.LENGTH_LONG).show();
                startRecording = true;
            }
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        String url = "https://maps.google.com/?q=" + latitude + "," + longitude;

                        for (Contact temp : EmergencyNumbers.contactList) {
                            String name = temp.getName();
                            String number = temp.getNumber();
                            String message = "!!!EMERGENCY!!!\n" +
                                    "HELP ME " + name + "!!!\nI AM IN DANGER!!!\nMY LOCATION IS : " + url;

                            SmsManager mySms = SmsManager.getDefault();
                            mySms.sendTextMessage(number, null, message, null, null);
                        }
                    }
                }
            });
        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);
        }

        //buzzer sound
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.buzzer);
        }
        player.start();
        player.setLooping(true);
        player.setVolume(volume, volume);
    }

    private void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }

    public void ClickMenu(View view) {
        //Open Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Open Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
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

    public void ClickEmergency(View view) {
        //redirect Activity
        MainActivity.redirectActivity(this, EmergencyNumbers.class);
    }

    public void ClickSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getApplicationContext(), "Signed Out", Toast.LENGTH_SHORT).show();
        displayName();
    }

    public static void redirectActivity(Activity activity, Class<?> aClass) {
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
            profile.setText(R.string.your_profile);
        }
    }

    private void setDB() {
        db = Room.databaseBuilder(MainActivity.this, ContactDatabase.class, "Contacts").allowMainThreadQueries().build();
    }

}

