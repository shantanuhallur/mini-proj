package com.ssst.stree.awareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ssst.stree.security.MainActivity;
import com.ssst.stree.problems.Problems;
import com.ssst.stree.R;
import com.ssst.stree.auth.SignIn;
import com.ssst.stree.auth.SignUp;
import com.ssst.stree.skilldev.SkillDevelopment;
import com.ssst.stree.financial.Financial;

public class Awareness extends AppCompatActivity {

    //Initialize variable
    private DrawerLayout drawerLayout;
    private TextView profile;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabHelplines;
    private TabItem tabSchemes;


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
        tabHelplines = findViewById(R.id.tabHelplines);
        tabSchemes = findViewById(R.id.tabSchemes);

        //Initializing & Assigning PagerAdapter
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());

        //tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0 || tab.getPosition()==1){
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Schemes
        ImageView betiBachavPadhav = findViewById(R.id.bet_bachav_padhav);

//        betiBachavPadhav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openBetiBachavPadhav();
//            }
//        });
    }

    //HelpLines Number Dial Functions
    public void dialDomesticAbuse(View view){
        String number = "tel:181";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialDistress(View view){
        String number = "tel:1091";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialPolice(View view){
        String number = "tel:100";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialCommisionWomen1(View view){
        String number = "tel:011-26942369";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialCommisionWomen2(View view){
        String number = "tel:26944754";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMahaWomenHelp1(View view){
        String number = "tel:022-26111103";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMahaWomenHelp2(View view){
        String number = "tel:1298";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMahaWomenHelp3(View view){
        String number = "tel:103";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMahaCommision1(View view){
        String number = "tel:07477722424";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMahaCommision2(View view){
        String number = "tel:022-26592707";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMumbaiPolice1(View view){
        String number = "tel:022-22633333";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMumbaiPolice2(View view){
        String number = "tel:22620111";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMumbaiRailwayPolice(View view){
        String number = "tel:9833331111";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMaljisMaha1(View view){
        String number = "tel:022-26661252";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    public void dialMaljisMaha2(View view){
        String number = "tel:26662394";
        Uri uri = Uri.parse(number);
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }

    //Schemes Open Link Functions
    public void openBetiBachavPadhav(View view) {
        String url = "https://wcd.nic.in/schemes/beti-bachao-beti-padhao-scheme";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openWomenHhelpline(View view) {
        String url = "https://wcd.nic.in/schemes/women-helpline-scheme-2";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openUjwala(View view) {
        String url = "https://wcd.nic.in/schemes/ujjawala-comprehensive-scheme-prevention-trafficking-and-rescue-rehabilitation-and-re";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openMahilaShaktiKendras(View view) {
        String url = "https://wcd.nic.in/schemes/mahila-shakti-kendras-msk";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openSwadharGreh(View view) {
        String url = "https://wcd.nic.in/schemes/swadhar-greh-scheme-women-difficult-circumstances";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openWomenEntrepreneur(View view) {
        String url = "https://www.startupindia.gov.in/content/sih/en/government-schemes/Wep.html";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openStandupIndia(View view) {
        String url = "https://www.standupmitra.in/Home/SubsidySchemesForWomen";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openMahilaPolice(View view) {
        String url = "https://wcd.nic.in/schemes/mahila-police-volunteers";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openWorkingWomenHostel(View view) {
        String url = "https://wcd.nic.in/schemes/working-women-hostel";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void openNirbhaya(View view) {
        String url = "https://wcd.nic.in/schemes/nirbhaya";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
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
        MainActivity.redirectActivity(this, SignIn.class);
    }

    public void ClickSignUp(View view) {
        //redirect Activity
        MainActivity.redirectActivity(this, SignUp.class);
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