package com.ssst.stree.financial;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
    private FirebaseUser currentUser;
    private DatabaseReference orderDatabase;
    private String pid;
    private Button addButton;

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

        addButton = (Button) findViewById(R.id.addorder);
        addButton.setEnabled(true);

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


        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        orderDatabase = FirebaseDatabase.getInstance().getReference("CustomerOrders");
        pid = getIntent().getStringExtra("pid");
        String data1 = pid + "_" + currentUser.getEmail();
        Query query = orderDatabase.orderByChild("id_email").equalTo(data1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot da: dataSnapshot.getChildren()){
                    if (da.child("id_email").exists()) {
                        //Already Present
                        Log.d("TAG", "onDataChange: --------------------------------Present");
                        addButton.setText("Produact Already in Order!");
                        addButton.setEnabled(false);

                    } else {
                        //do something if not exists
                        Log.d("TAG", "onDataChange: --------------------------------Not Added ");

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    public void addOrder(View view){
        String id = orderDatabase.push().getKey();
        String data = pid + "_" + currentUser.getEmail();
        if(id != null) {
            CustomerOrders order = new CustomerOrders(data,pid,currentUser.getEmail());
            orderDatabase.child(id).setValue(order);
        }
        Toast.makeText(this,"Product Added to Your Orders",Toast.LENGTH_SHORT).show();

        addButton.setText("Product Already in Order!");
        addButton.setEnabled(false);
    }
}