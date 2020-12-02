package com.ssst.stree;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssst.stree.classes.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellerView extends AppCompatActivity {
    private List<Product> productList;
    private RecyclerView recyclerView;
    private FirebaseUser currentUser;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_view);
        //animation.start
        //initializing the productlist
        productList = new ArrayList<>();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").whereEqualTo("email",currentUser.getEmail()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    Product product = new Product(
                            queryDocumentSnapshot.getId(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("name")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("price")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("category")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("info")).toString()
                    );
                    productList.add(product);
                }

                recyclerView = findViewById(R.id.recyclerSellerView);
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
        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        //Open Drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickProfile(View view){
        //Close Drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}