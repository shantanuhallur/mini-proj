package com.ssst.stree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addBusiness extends AppCompatActivity {
    
    private EditText business_name,business_contact,business_information,business_upi,business_acc,business_IFSC;
    private EditText bank_name,bank_branch,bank_acc_holder_name,bank_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

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

        Button submit = findViewById(R.id.continueButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                con();
            }
        });
    }

    private void con() {
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

        MainActivity.redirectActivity(this, addProduct.class);
    }
    
}