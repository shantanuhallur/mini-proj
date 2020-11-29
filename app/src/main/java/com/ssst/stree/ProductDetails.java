package com.ssst.stree;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.ssst.stree.classes.Product;

import java.util.Objects;

public class ProductDetails extends AppCompatActivity {

    private String id;
    private Product product;
    public static String email;
    private TextView name;
    private TextView price;
    private TextView seller_name;
    private TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        id = SellerAdapter.id;
        name = findViewById(R.id.pr_name);
        price = findViewById(R.id.pr_price);
        seller_name = findViewById(R.id.pr_seller_name);
        info = findViewById(R.id.pr_info);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    if(Objects.equals(queryDocumentSnapshot.getId(),id)) {
                        product = new Product(
                                queryDocumentSnapshot.getId(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("name")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("price")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("category")).toString(),
                                Objects.requireNonNull(queryDocumentSnapshot.get("info")).toString()
                        );
                        email = Objects.requireNonNull(queryDocumentSnapshot.get("email")).toString();
                    }
                }
                name.setText(product.getName());
                price.setText(product.getPrice());
                seller_name.setText(email);
                info.setText(product.getInfo());
            }
        });
    }

    public void sellerDetails(View view){
        MainActivity.redirectActivity(this, SellerDetails.class);
    }

}