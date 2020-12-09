package com.ssst.stree.financial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ssst.stree.security.MainActivity;
import com.ssst.stree.R;
import com.ssst.stree.auth.SignIn;
import com.ssst.stree.support.Product;

public class Financial extends AppCompatActivity {
    public static String id;
    private DrawerLayout drawerLayout;
    private TextView profile;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Financial Empowerment");
        setContentView(R.layout.activity_financial);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(this , SignIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);

        recyclerView = findViewById(R.id.recyclerCustomerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        populate();
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
        //Financial.redirectActivity(this, SkillDevelopment.class);
    }

    public void ClickCart(View view){
        //Redirect activity to Your Cart xml
        //Financial.redirectActivity(this, SkillDevelopment.class);
    }

    public void ClickSell(View view){
        //redirect Activity Seller
        Financial.redirectActivity(this, AddBusiness.class);
    }

    public void ClickAllProducts(View view){
        //redirect Activity Seller
        Financial.redirectActivity(this, SellerView.class);
    }

    public static void redirectActivity(Activity activity,Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    private void displayName() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            profile.setText(user.getEmail());
        }else {
            profile.setText("Your Profile");
        }
    }

    private void populate() {
        Query query = FirebaseDatabase.getInstance()
                .getReference().child("products");

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, Product.class)
                        .build();

        adapter = new ProductAdapter(options);
        recyclerView.setAdapter(adapter);
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

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}