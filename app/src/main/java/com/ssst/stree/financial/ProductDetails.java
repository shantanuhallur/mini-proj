package com.ssst.stree.financial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.ssst.stree.R;

public class ProductDetails extends AppCompatActivity {
    private Product product;
    private ImageView wishlistButton;
    private RatingBar ratingbar;
    int tag;
    private FirebaseUser currentUser;
    private DatabaseReference wishlistDatabase;
    private String Pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        TextView name = findViewById(R.id.pr_name);
        TextView price = findViewById(R.id.pr_price);
        TextView seller_name = findViewById(R.id.pr_seller_name);
        TextView info = findViewById(R.id.pr_info);
        ImageView image = findViewById(R.id.pr_img);

        ratingbar = findViewById(R.id.pr_rating);
        float a = (float) (Math.random()*5);
        ratingbar.setNumStars((int) a);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        wishlistDatabase = FirebaseDatabase.getInstance().getReference("CustomerWishlist");

        wishlistButton = (ImageView) findViewById(R.id.wishlistButton);

        product = (Product) getIntent().getSerializableExtra("product");

        name.setText(product.getName());
        price.setText(product.getPrice());
        seller_name.setText(product.getEmail());
        info.setText(product.getInfo());
        Picasso.get().load(product.getImageUri()).fit().centerCrop().into(image);

        wishlistButton.setTag(R.drawable.wishlist);
        wishlistButton.setImageResource(R.drawable.wishlist);

        Pid = product.getId();
        String data1 = Pid + "_" + currentUser.getEmail();
        Query query = wishlistDatabase.orderByChild("id_email").equalTo(data1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot da: dataSnapshot.getChildren()){
                    if (da.child("id_email").exists()) {
                        //Already Present
                        Log.d("TAG", "onDataChange: --------------------------------Present");
                        wishlistButton.setTag(R.drawable.wishlist_added);
                        wishlistButton.setImageResource(R.drawable.wishlist_added);
                    } else {
                        //do something if not exists
                        Log.d("TAG", "onDataChange: --------------------------------Not Added ");
                        wishlistButton.setTag(R.drawable.wishlist);
                        wishlistButton.setImageResource(R.drawable.wishlist);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void sellerDetails(View view){
        Intent intent = new Intent(this, SellerDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("email",product.getEmail());
        intent.putExtra("pid",product.getId());
        startActivity(intent);
    }

    public void addWishlist(View view){
        tag = (int) wishlistButton.getTag();
        if(tag==R.drawable.wishlist)
        {
            wishlistButton.setImageResource(R.drawable.wishlist_added);
            wishlistButton.setTag(R.drawable.wishlist_added);

            String Pid = product.getId();
            String id = wishlistDatabase.push().getKey();
            String data = Pid + "_" + currentUser.getEmail();
            if(id != null) {
                CustomerWishlist wish = new CustomerWishlist(data,Pid,currentUser.getEmail());
                wishlistDatabase.child(id).setValue(wish);
            }
            Toast.makeText(this,"Product Added to WishList",Toast.LENGTH_SHORT).show();
        }
        else{
            wishlistButton.setImageResource(R.drawable.wishlist);
            wishlistButton.setTag(R.drawable.wishlist);
            String Pid = product.getId();
            String data = Pid + "_" + currentUser.getEmail();
            Query query1 = wishlistDatabase.orderByChild("id_email").equalTo(data);
            Log.d("TAG", "onDataChange: HIIIII------------------------------------");
            query1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot wishSnapshot: dataSnapshot.getChildren()) {
                        wishSnapshot.getRef().removeValue();
                        Log.d("TAG", "onDataChange: Data deleted------------------------------------");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("TAG", "onCancelled", databaseError.toException());
                }
            });

            Toast.makeText(this,"Product Removed from WishList",Toast.LENGTH_SHORT).show();
        }
    }
}