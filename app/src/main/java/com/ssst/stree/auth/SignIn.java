package com.ssst.stree.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ssst.stree.R;
import com.ssst.stree.support.LoadingBar;

public class SignIn extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private LoadingBar loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        Button signIn = findViewById(R.id.btnLogin);

        loadingBar = new LoadingBar(SignIn.this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    public void signIn() {
        loadingBar.showDialog();
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("SignIn","sign in : " + task.isSuccessful());
                if(!task.isSuccessful()) {
                    Log.d("SignIn","Something wrong");
                    loadingBar.hideDialog();
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("SignIn","Awesome");
                    loadingBar.hideDialog();
                    Toast.makeText(getApplicationContext(),"Logged In Successfully!!",Toast.LENGTH_SHORT).show();
                    UpdateUI();
                }
            }
        });
    }

    private void UpdateUI() {
        this.finish();
    }
}