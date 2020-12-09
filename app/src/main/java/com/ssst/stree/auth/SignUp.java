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

public class SignUp extends AppCompatActivity {
    EditText email;
    EditText password;
    FirebaseAuth mAuth;
    Button signUp;
    private LoadingBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.ettEmail);
        password = findViewById(R.id.ettPassword);
        signUp = findViewById(R.id.btnRegister);

        loadingBar = new LoadingBar(SignUp.this);

        signUp.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        }));
    }

    public void signUp() {
        loadingBar.showDialog();
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("SignUp","sign up : " + task.isSuccessful());
                if(!task.isSuccessful()) {
                    Log.d("SignUp","Something wrong");
                    loadingBar.hideDialog();
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("SignUp","Awesome");
                    loadingBar.hideDialog();
                    Toast.makeText(getApplicationContext(),"Your Email has Been Registered !",Toast.LENGTH_SHORT).show();
                    UpdateUI();
                }
            }
        });
    }

    private void UpdateUI() {
        this.finish();
    }
}