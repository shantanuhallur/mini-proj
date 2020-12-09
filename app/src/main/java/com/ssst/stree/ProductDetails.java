package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ssst.stree.classes.Product;

public class ProductDetails extends AppCompatActivity {
    public static Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        TextView name = findViewById(R.id.pr_name);
        TextView price = findViewById(R.id.pr_price);
        TextView seller_name = findViewById(R.id.pr_seller_name);
        TextView info = findViewById(R.id.pr_info);

        name.setText(product.getName());
        price.setText(product.getPrice());
        seller_name.setText(product.getEmail());
        info.setText(product.getInfo());
    }

    public void sellerDetails(View view){

        Intent intent = new Intent(this, SellerDetails.class);
        startActivity(intent);
    }

}