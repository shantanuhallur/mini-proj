package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import com.ssst.stree.classes.Seller;

import java.util.HashMap;
import java.util.Objects;

public class addBusiness extends AppCompatActivity {
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
    private Seller seller;
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

        db.collection("sellers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        boolean flag = false;
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            if(Objects.equals(document.get("email"), currentUser.getEmail())) {
                                Log.d("addBusiness", document.getId() + " => " + document.getData());
                                Log.d("addBusiness","REGISTERED");
                                String businessName, businessContact, businessInformation, businessUpi, businessAcc, businessIFSC;
                                String bankName, bankBranch, bankAccHolderName, bankAddress;

                                businessName = Objects.requireNonNull(document.get("name")).toString();
                                businessContact = Objects.requireNonNull(document.get("contactNumber")).toString();
                                businessInformation = Objects.requireNonNull(document.get("info")).toString();
                                businessUpi = Objects.requireNonNull(document.get("UPINumber")).toString();
                                businessAcc = Objects.requireNonNull(document.get("accountNumber")).toString();
                                businessIFSC = Objects.requireNonNull(document.get("IFSCCode")).toString();
                                bankName = Objects.requireNonNull(document.get("bankName")).toString();
                                bankBranch = Objects.requireNonNull(document.get("bankBranchName")).toString();
                                bankAccHolderName = Objects.requireNonNull(document.get("bankAccountHolderName")).toString();
                                bankAddress = Objects.requireNonNull(document.get("bankAddress")).toString();

                                seller = new Seller(currentUser.getEmail(), businessName, businessContact, businessInformation, businessUpi, businessAcc, businessIFSC, bankName, bankBranch, bankAccHolderName, bankAddress);

                                business_name.setText(businessName);
                                business_contact.setText(businessContact);
                                business_information.setText(businessInformation);
                                business_upi.setText(businessUpi);
                                business_acc.setText(businessAcc);
                                business_IFSC.setText(businessIFSC);
                                bank_name.setText(bankName);
                                bank_branch.setText(bankBranch);
                                bank_acc_holder_name.setText(bankAccHolderName);
                                bank_address.setText(bankAddress);

                                submit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        registeredContinue();
                                    }
                                });
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
                }
                else {
                    Log.w("addBusiness", "Error getting documents.", task.getException());
                }
            }
        });
    }

    private void registeredContinue() {
        MainActivity.redirectActivity(this, addProduct.class);
    }

    private void unregisteredContinue() {
        String businessName,businessContact,businessInformation,businessUpi,businessAcc,businessIFSC;
        String bankName,bankBranch,bankAccHolderName,bankAddress;

        businessName = this.business_name.getText().toString().trim();
        businessContact = this.business_contact.getText().toString().trim();
        businessInformation = this.business_information.getText().toString().trim();
        businessUpi = this.business_upi.getText().toString().trim();
        businessAcc = this.business_acc.getText().toString().trim();
        businessIFSC = this.business_IFSC.getText().toString().trim();

        bankName = this.bank_name.getText().toString().trim();
        bankBranch = this.bank_branch.getText().toString().trim();
        bankAccHolderName = this.bank_acc_holder_name.getText().toString().trim();
        bankAddress = this.bank_address.getText().toString().trim();

        HashMap<String,String> seller = new HashMap<>();
        seller.put("email",currentUser.getEmail());
        seller.put("name",businessName);
        seller.put("contactNumber",businessContact);
        seller.put("UPINumber",businessUpi);
        seller.put("accountNumber",businessAcc);
        seller.put("IFSCCode",businessIFSC);
        seller.put("bankName",bankName);
        seller.put("bankBranchName",bankBranch);
        seller.put("bankAccountHolderName",bankAccHolderName);
        seller.put("bankAddress",bankAddress);
        seller.put("info",businessInformation);

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

        MainActivity.redirectActivity(this, addProduct.class);
    }


}
