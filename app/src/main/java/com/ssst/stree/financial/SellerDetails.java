package com.ssst.stree.financial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.ssst.stree.R;

import java.util.Objects;

public class SellerDetails extends AppCompatActivity {
    private TextView sellName,sellCon,sellUPI,bankSellBranch,bankSellHolderName,accNoSell,IFSCSell,infoSell,bankSellName;
    private Seller seller;
    private ImageView sellLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_details);

        sellName = findViewById(R.id.sellName);
        sellCon = findViewById(R.id.sellCon);
        sellUPI = findViewById(R.id.sellUPI);
        bankSellName = findViewById(R.id.bankSellName);
        bankSellBranch = findViewById(R.id.bankSellBranch);
        bankSellHolderName = findViewById(R.id.bankSellHolderName);
        accNoSell = findViewById(R.id.accNoSell);
        IFSCSell = findViewById(R.id.IFSCSell);
        infoSell = findViewById(R.id.infoSell);
        sellLogo = findViewById(R.id.sellLogo);

        String b_email = getIntent().getStringExtra("email");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("sellers").whereEqualTo("email", b_email).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
                    seller = new Seller(
                            Objects.requireNonNull(queryDocumentSnapshot.get("email")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("businessName")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("businessContact")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("businessInformation")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("businessAcc")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("businessIFSC")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("bankName")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("bankBranch")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("bankAccHolderName")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("bankAddress")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("businessUpi")).toString(),
                            Objects.requireNonNull(queryDocumentSnapshot.get("businesslogo")).toString()
                    );
                }
                sellName.setText(seller.getBusinessName());
                sellCon.setText(seller.getBusinessContact());
                sellUPI.setText(seller.getBusinessUpi());
                bankSellName.setText(seller.getBankName());
                bankSellBranch.setText(seller.getBankBranch());
                bankSellHolderName.setText(seller.getBankAccHolderName());
                accNoSell.setText(seller.getBusinessAcc());
                IFSCSell.setText(seller.getBusinessIFSC());
                infoSell.setText(seller.getBusinessInformation());

                //Uri myuri = Uri.parse(seller.getBusinesslogo());
                Picasso.get().load(seller.getBusinesslogo()).fit().centerCrop().into(sellLogo);
            }
        });
    }
}