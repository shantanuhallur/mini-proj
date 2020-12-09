package com.ssst.stree.skilldev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ssst.stree.problems.Problems;
import com.ssst.stree.R;
import com.ssst.stree.auth.SignIn;
import com.ssst.stree.auth.SignUp;
import com.ssst.stree.awareness.Awareness;
import com.ssst.stree.financial.Financial;
import com.ssst.stree.security.MainActivity;

public class SkillDevelopment extends AppCompatActivity {

    //Initialize Variable
    private DrawerLayout drawerLayout;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Skill Development");
        setContentView(R.layout.activity_skill_development);

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
    }


    public void CookingIndian(View view) {
        String url = "https://www.youtube.com/playlist?list=PL2TRHkIbO05bWHElf-onofRyMTxD6z0mP";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void CookingItalian(View view) {
        String url = "https://www.youtube.com/playlist?list=PLjMg8wIdiWauxhlKzPO_-t_9g5InC5LhP";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void CookingContinental(View view) {
        String url = "https://www.youtube.com/playlist?list=PLAb0Zi6EX80t0rV5VYgTIPnYEK-_88OQy";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void CookingGourmet(View view) {
        String url = "https://www.youtube.com/playlist?list=PL_saUPZ53QaWdyhCnRQL6jH2hUjOif50Z";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void Sewing(View view) {
        String url = "https://www.youtube.com/playlist?list=PLgIWvTIgBXeTpl2i9P9yoVb5xj-Oq1zgw";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void DigitalMarketingB(View view) {
        String url = "https://youtu.be/SHI5X6Tre8g";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void AI(View view) {
        String url = "https://www.youtube.com/playlist?list=PLxCzCOWd7aiHGhOHV-nwb0HR5US5GFKFI";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void ML(View view) {
        String url = "https://youtu.be/GwIo3gDZCVQ";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void BlockChain(View view) {
        String url = "https://youtu.be/nvw27RCTaEw";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void ARVR(View view) {
        String url = "https://www.youtube.com/playlist?list=PLb1h4A0yB97_TeFuf9TAEah3b-VyIYzF9\n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void SelfDefenseCourse(View view) {
        String url = "https://www.youtube.com/watch?v=ZUrr5MU4Z4Y&list=PLHfTPxnG4fWq1Wa1vAt8NXnsr9pGmGvQ3 \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }


    public void Taekwondo(View view) {
        String url = "https://www.youtube.com/watch?v=F1kXuQ3QB-Y&list=PL59sCVifcXA2AT19RR60BLn3XC-mtGtEs \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void Karate(View view) {
        String url = "https://www.youtube.com/watch?v=VvsQDHshXm4&list=PL1dljzh80tJ960zAEnsgoCGLjpKeuTXcp \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
    public void English(View view) {
        String url = "https://www.youtube.com/watch?v=nWE-qmDmy20&list=PLpuxPG4TUOR5-DVxgV3EzxtecZVjmkPp9 \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void Japanese(View view) {
        String url = "https://www.youtube.com/watch?v=dwd_Z917XGI&list=PLbMVogVj5nJRmUlVaKDlcYHdecA_WFLVv \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
    public void German(View view) {
        String url = "https://www.youtube.com/watch?v=PyZtAlK9bb8&list=PLyqSpQzTE6M9JyZwer9Z6C8BfbGCWeWJK&index=1 \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    public void Spanish(View view) {
        String url = "https://www.youtube.com/watch?v=4FB1nO6ckZI&list=PLKQEL9j11yiUQ5SWdkc6ZT6A1NRoaskst \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
    public void French(View view) {
        String url = "https://www.youtube.com/watch?v=p1b0Wb-1DLc&list=PLQQkuSmBHMY3DnZ92-jA_u35UiXNGAcye \n";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Verify that the intent will resolve to an activity
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
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

    private void displayName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            profile.setText(user.getEmail());
        }else {
            profile.setText("Your Profile");
        }
    }
}