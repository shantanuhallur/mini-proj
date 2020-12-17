package com.ssst.stree.financial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ssst.stree.R;

public class ProductDetails extends AppCompatActivity {
    private Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        TextView name = findViewById(R.id.pr_name);
        TextView price = findViewById(R.id.pr_price);
        TextView seller_name = findViewById(R.id.pr_seller_name);
        TextView info = findViewById(R.id.pr_info);
        ImageView image = findViewById(R.id.pr_img);

        product = (Product) getIntent().getSerializableExtra("product");

        name.setText(product.getName());
        price.setText(product.getPrice());
        seller_name.setText(product.getEmail());
        info.setText(product.getInfo());
        Picasso.get().load(product.getImageUri()).fit().centerCrop().into(image);
    }

    public void sellerDetails(View view){
        Intent intent = new Intent(this, SellerDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email",product.getEmail());
        startActivity(intent);
    }

}