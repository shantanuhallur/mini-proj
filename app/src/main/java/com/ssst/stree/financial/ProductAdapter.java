package com.ssst.stree.financial;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;
import com.ssst.stree.R;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product, ProductAdapter.ProductViewHolder> {

    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card_view,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product product) {
        holder.textViewTitle.setText(product.getName());
        holder.textViewShortDesc.setText(product.getInfo());
        holder.textViewRating.setText(product.getCategory());
        holder.textViewPrice.setText(product.getPrice());
        holder.product = product;
        Picasso.get().load(product.getImageUri()).fit().centerCrop().into(holder.imageView);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private Product product;
        private final TextView textViewTitle;
        private final TextView textViewShortDesc;
        private final TextView textViewRating;
        private final TextView textViewPrice;
        private final ImageView imageView;

        public ProductViewHolder(@NonNull final View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    showProductDetails();
                }
            });
            textViewTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProductDetails();
                }
            });
            textViewShortDesc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProductDetails();
                }
            });
            textViewRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProductDetails();
                }
            });
            textViewPrice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProductDetails();
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showProductDetails();
                }
            });
        }

        private void showProductDetails() {
            Intent intent = new Intent(itemView.getContext(), ProductDetails.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("product",product);
            itemView.getContext().startActivity(intent);
        }
    }
}
