package com.ssst.stree.financial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.ssst.stree.R;
import com.ssst.stree.support.LoadingBar;

import java.util.Objects;

public class AddProduct extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText product_name;
    private EditText product_price;
    private EditText product_information;
    private EditText product_category;
    private ImageView product_image;
    private Uri uri;
    private FirebaseUser currentUser;
    private DatabaseReference productsDatabase;
    private StorageReference productsStorage;
    public static boolean isAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        isAdded = false;
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        productsDatabase = FirebaseDatabase.getInstance().getReference("products");
        productsStorage = FirebaseStorage.getInstance().getReference("products");

        product_name = findViewById(R.id.productName);
        product_price = findViewById(R.id.productPrice);
        product_information = findViewById(R.id.productInformation);
        product_category = findViewById(R.id.productCategory);
        product_image = findViewById(R.id.productImage);

        product_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });

        Button submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });

    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();
            Picasso.get().load(uri).fit().centerCrop().into(product_image);
        }
    }

    private void submit() {
        final String productName = this.product_name.getText().toString().trim();
        final String productPrice = this.product_price.getText().toString().trim();
        final String productInformation = this.product_information.getText().toString().trim();
        final String productCategory = this.product_category.getText().toString().trim();

        final StorageReference storageReference = productsStorage.child(System.currentTimeMillis() + "_product_image." + getFileExtension(uri));
        UploadTask uploadTask = storageReference.putFile(uri);
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()) {
                            throw Objects.requireNonNull(task.getException());
                        }
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Image Uploaded",Toast.LENGTH_SHORT).show();
                            Uri downloadUrl = task.getResult();
                            String id = productsDatabase.push().getKey();
                            if(id != null) {
                                Product product = new Product(productName,productPrice,productCategory,productInformation,currentUser.getEmail(),downloadUrl);
                                productsDatabase.child(id).setValue(product);
                            }
                        }else {
                            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Toast.makeText(getApplicationContext(),"Product Added",Toast.LENGTH_SHORT).show();
        isAdded = true;
        this.finish();
    }
}