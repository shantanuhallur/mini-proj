package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Objects;

public class AddBusiness extends AppCompatActivity {
    private Button submit;
    private EditText business_name;
    private EditText business_contact;
    private EditText business_information;
    private EditText business_upi;
    private EditText business_acc;
    private EditText business_IFSC;
    private EditText bank_name;
    private EditText bank_branch;
    private EditText bank_acc_holder_name;
    private EditText bank_address;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        business_name = findViewById(R.id.businessName);
        business_contact = findViewById(R.id.businessContact);
        business_information = findViewById(R.id.businessInformation);
        business_upi = findViewById(R.id.businessUPI);
        business_acc = findViewById(R.id.businessAcc);
        business_IFSC = findViewById(R.id.businessIFSC);
        bank_name = findViewById(R.id.bankName);
        bank_acc_holder_name = findViewById(R.id.bankAccHolderName);
        bank_address = findViewById(R.id.bankAddress);
        bank_branch = findViewById(R.id.bankBranch);

        submit = findViewById(R.id.continueButton);

        db.collection("sellers").whereEqualTo("email",currentUser.getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    boolean flag = false;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            business_name.setText(Objects.requireNonNull(document.get("businessName")).toString());
                            business_contact.setText(Objects.requireNonNull(document.get("businessContact")).toString());
                            business_information.setText(Objects.requireNonNull(document.get("businessInformation")).toString());
                            business_upi.setText(Objects.requireNonNull(document.get("businessUpi")).toString());
                            business_acc.setText(Objects.requireNonNull(document.get("businessAcc")).toString());
                            business_IFSC.setText(Objects.requireNonNull(document.get("businessIFSC")).toString());
                            bank_name.setText(Objects.requireNonNull(document.get("bankName")).toString());
                            bank_branch.setText(Objects.requireNonNull(document.get("bankBranch")).toString());
                            bank_acc_holder_name.setText(Objects.requireNonNull(document.get("bankAccHolderName")).toString());
                            bank_address.setText(Objects.requireNonNull(document.get("bankAddress")).toString());
                            registeredContinue();
                            flag = true;
                            break;
                        }
                    }
                    if(!flag) {
                        Log.d("addBusiness","UNREGISTERED");
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                unregisteredContinue();
                            }
                        });
                    }
                }
                else {
                    Log.w("addBusiness", "Error getting documents.", task.getException());
                }
            }
        });
    }

    private void registeredContinue() {
        Intent intent = new Intent(this ,AddProduct.class);
        startActivity(intent);
    }

    private void unregisteredContinue() {
        HashMap<String,String> seller = new HashMap<>();
        seller.put("email",currentUser.getEmail());
        seller.put("businessName",this.business_name.getText().toString().trim());
        seller.put("businessContact",this.business_contact.getText().toString().trim());
        seller.put("businessInformation",this.business_information.getText().toString().trim());
        seller.put("businessUpi",this.business_upi.getText().toString().trim());
        seller.put("businessAcc",this.business_acc.getText().toString().trim());
        seller.put("businessIFSC",this.business_IFSC.getText().toString().trim());
        seller.put("bankName",this.bank_name.getText().toString().trim());
        seller.put("bankBranch",this.bank_branch.getText().toString().trim());
        seller.put("bankAccHolderName",this.bank_acc_holder_name.getText().toString().trim());
        seller.put("bankAddress",this.bank_address.getText().toString().trim());

        db.collection("sellers").add(seller).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d("addBusiness", "\u001B31;1mDocumentSnapshot added with ID: " + documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("addBusiness", "\u001B31;1mError adding document", e);
            }
        });

        Intent intent = new Intent(this ,AddProduct.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(AddProduct.isAdded) {
            AddProduct.isAdded = false;
            this.finish();
        }
    }
}
