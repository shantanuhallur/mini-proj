package com.ssst.stree;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.ssst.stree.classes.Product;

import java.util.List;

public class SellerAdapter extends RecyclerView.Adapter<SellerAdapter.ProductViewHolder> {
    //this context we will use to inflate the layout
    private final Context mCtx;

    //we are storing all the products in a list
    private final List<Product> productList;
    public static String id;

    //getting the context and product list with constructor
    public SellerAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.seller_view_card, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Product product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getName());
        holder.textViewShortDesc.setText(product.getInfo());
        holder.textViewRating.setText(product.getCategory());
        holder.textViewPrice.setText(product.getPrice());
        holder.id = product.getId();

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private String id;
        private final TextView textViewTitle;
        private final TextView textViewShortDesc;
        private final TextView textViewRating;
        private final TextView textViewPrice;
        private final ImageView imageView;

        public ProductViewHolder(final View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View view) {
                    SellerAdapter.id = id;
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), ProductDetails.class));
                }
            });
        }
    }
}
