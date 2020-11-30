package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Awareness extends AppCompatActivity {

    //Initialize variable
    private DrawerLayout drawerLayout;
    private TextView profile;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Awareness");
        setContentView(R.layout.activity_awareness);

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.aware_viewPager);

        //Initialize ArrayList of different Tabs
        ArrayList<String> tabs = new ArrayList<>();

        //Add Tabs name
        tabs.add("HelpLines");
        tabs.add("Schemes");
        
        //Prepare ViewPager
        PrepareViewPager(viewPager, tabs);

        //Setup with vewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    //Tabbed View
    private void PrepareViewPager(ViewPager viewPager, ArrayList<String> tabs) {
        //Initialize Main Adapter
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        //Initializing Awareness Fragment
        AwarenessFragment fragment = new AwarenessFragment();
        for(int i = 0;i<tabs.size();i++){
            //Initializing Bundle
            Bundle bundle = new Bundle(); //Used to pass Data between Activities

            //Put String
            bundle.putString("title", tabs.get(i));

            //Set Argument
            fragment.setArguments(bundle);

            //Adding fragment
            adapter.addFragment(fragment,tabs.get(i));

            //Define new Fragment
            fragment = new AwarenessFragment();
        }
        //Setting Adapter
        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        //Initialize Array List
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        //Constructor
        public void addFragment(Fragment fragment, String title){
            //Title
            arrayList.add(title);
            //Fragment
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //return fragment position
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            //return fragmentList size
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //return arrayList position
            return arrayList.get(position);
        }
    }

    //Drawer Options & Functions
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
        //Recreate this activity
        recreate();
    }

    public void ClickSkill(View view){
        //Redirect activity to SkillDevelopment
        MainActivity.redirectActivity(this, SkillDevelopment.class);
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
        Toast.makeText(getApplicationContext(),"Signed Out",Toast.LENGTH_SHORT).show();
        displayName();
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

    //Updating Profile
    private void displayName() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            profile.setText(user.getEmail());
        }else {
            profile.setText("Your Profile");
        }
    }


}