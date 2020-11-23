package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ssst.stree.classes.Product;

import java.util.ArrayList;
import java.util.List;


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


            //adding some items to our list
            productList.add(
                    new Product(
                            "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                            "60000",
                            "ABC",
                            "ABC"));

            productList.add(
                    new Product(
                            "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                            "60000",
                            "Categories123",
                            "INFO"));

            productList.add(
                    new Product(
                            "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                            "60000",
                            "ABC",
                            "ABC"));

            //getting the recyclerview from xml
            recyclerView = (RecyclerView) findViewById(R.id.recyclerSellerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

             RecyclerView.Adapter adapter;
            //creating recyclerview adapter
            adapter = new seller_adapter(this, productList);

            //setting adapter to recyclerview
            recyclerView.setAdapter(adapter);
        }


}