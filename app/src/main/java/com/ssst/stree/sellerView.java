package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssst.stree.classes.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class sellerView extends AppCompatActivity {

        List<Product> productList;
        //the recyclerview
        RecyclerView recyclerView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_seller_view);
            //initializing the productlist
            productList = new ArrayList<>();

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                        Log.d("sellerView", queryDocumentSnapshot.getId() + " => " + queryDocumentSnapshot.getData());
                        Product product = new Product(
                                Objects.requireNonNull(queryDocumentSnapshot.get("name")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("price")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("category")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("info")).toString()
                        );
                        Log.d("sellerView",product.toString());
                        productList.add(product);
                    }

                    recyclerView = findViewById(R.id.recyclerSellerView);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    seller_adapter adapter;
                    //creating recyclerview adapter
                    adapter = new seller_adapter(getApplicationContext(), productList);

                    //setting adapter to recyclerview
                    recyclerView.setAdapter(adapter);
                }
            });
        }
}