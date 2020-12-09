package com.ssst.stree.financial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ssst.stree.security.MainActivity;
import com.ssst.stree.R;
import com.ssst.stree.support.Product;

public class SellerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseUser currentUser;
    private DrawerLayout drawerLayout;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_view);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        drawerLayout = findViewById(R.id.drawer_layout);

        recyclerView = findViewById(R.id.recyclerSellerView);
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

    private void populate() {
        Query query = FirebaseDatabase.getInstance()
                .getReference().child("products").orderByChild("email").equalTo(currentUser.getEmail());

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