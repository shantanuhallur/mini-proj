//package com.ssst.stree;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class Financial extends AppCompatActivity {
//
//    //Initialize variable
//    private DrawerLayout drawerLayout;
//    private TextView profile;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_financial);
//
//        //Assign Variable
//        drawerLayout = findViewById(R.id.drawer_layout);
//        profile = findViewById(R.id.profile);
//    }
//
//    public void ClickMenu(View view){
//        //Open Drawer
//        MainActivity.openDrawer(drawerLayout);
//    }
//
//    public void ClickProfile(View view){
//        //Close Drawer
//        MainActivity.closeDrawer(drawerLayout);
//    }
//
//    public void ClickHome(View view){
//        //Redirect activity to Home
//        MainActivity.redirectActivity(this, MainActivity.class);
//    }
//
//    public void ClickLanguage(View view){
//        //Recreate this activity with different Language
//        //recreate();
//    }
//
//    public void ClickOrders(View view){
//        //Redirect activity to Your Orders xml
//        //MainActivity.redirectActivity(this, SkillDevelopment.class);
//    }
//
//    public void ClickCart(View view){
//        //Redirect activity to Your Cart xml
//        //MainActivity.redirectActivity(this, SkillDevelopment.class);
//    }
//
//    public void ClickSell(View view){
//        //redirect Activity Seller
//        MainActivity.redirectActivity(this, Problems.class);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //Close Drawer
//        MainActivity.closeDrawer(drawerLayout);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        displayName();
//    }
//
//    private void displayName() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user != null) {
//            profile.setText(user.getEmail());
//        }else {
//            profile.setText("Your Profile");
//        }
//    }
//}


package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Financial extends AppCompatActivity {

    //Initialize Variable
    private DrawerLayout drawerLayout;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            MainActivity.redirectActivity(this,SignIn.class);
        }

        Log.d("Financial","" + FirebaseAuth.getInstance().getCurrentUser());

        setContentView(R.layout.activity_financial);

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
    }

    public void ClickMenu(View view){
        //Open Drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickProfile(View view){
        //Close Drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickLanguage(View view){
        //Recreate this activity with different Language
        //recreate();
    }

    public void ClickOrders(View view){
        //Redirect activity to Your Orders xml
        //MainActivity.redirectActivity(this, SkillDevelopment.class);
    }

    public void ClickCart(View view){
        //Redirect activity to Your Cart xml
        //MainActivity.redirectActivity(this, SkillDevelopment.class);
    }

    public void ClickSell(View view){
        //redirect Activity Seller
        MainActivity.redirectActivity(this, addBusiness.class);
    }

    public void ClickAllProducts(View view){
        //redirect Activity Seller
        MainActivity.redirectActivity(this, sellerView.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayName();
    }

    private void displayName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            profile.setText(user.getEmail());
        }else {
            profile.setText("Your Profile");
        }
    }
}