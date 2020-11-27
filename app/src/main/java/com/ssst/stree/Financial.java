package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssst.stree.classes.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Financial extends AppCompatActivity {

    //Initialize Variable
    public static String id;
    private DrawerLayout drawerLayout;
    private TextView profile;
    private List<Product> productList;
    //the recyclerview
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            MainActivity.redirectActivity(this,SignIn.class);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        productList = new ArrayList<>();

        db.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                        Log.d("sellerView", queryDocumentSnapshot.getId() + " => " + queryDocumentSnapshot.getData());
                        Product product = new Product(
                                queryDocumentSnapshot.getId(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("name")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("price")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("category")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("info")).toString()
                        );
                        productList.add(product);
                }

                recyclerView = findViewById(R.id.recyclerCustomerView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                SellerAdapter adapter;
                //creating recyclerview adapter
                adapter = new SellerAdapter(getApplicationContext(), productList);

                //setting adapter to recyclerview
                recyclerView.setAdapter(adapter);

                //animation.stop()
            }
        });

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_layout);
        profile = findViewById(R.id.profile);
    }

//    public void showDetails(View view){
//        //show product details
//        MainActivity.redirectActivity(this, productDetails.class);
//    }

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
        MainActivity.redirectActivity(this, AddBusiness.class);
    }

    public void ClickAllProducts(View view){
        //redirect Activity Seller
        MainActivity.redirectActivity(this, SellerView.class);
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