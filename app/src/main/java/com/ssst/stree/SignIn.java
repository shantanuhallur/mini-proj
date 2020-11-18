package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private  FirebaseUser currentUser;
    private TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();



        View v = getLayoutInflater().inflate(R.layout.main_nav_drawer,null);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        profile = v.findViewById(R.id.profile);
        Button signIn = findViewById(R.id.btnLogin);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    public void signIn() {
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("SignIn","sign in : " + task.isSuccessful());
                if(!task.isSuccessful()) {
                    Log.d("SignIn","Something wrong");
                    // TODO : Make the page interactive on failure
                } else {
                    Log.d("SignIn","Awesome");
                    // TODO : Make the page interactive on success
                    UpdateUI();
                }
            }
        });
    }

    private void UpdateUI() {
        FirebaseUser user = mAuth.getCurrentUser();
        Log.d("SignIn",String.valueOf(profile!=null));
        //Complete username should be appeared in Profile.....
//        profile.append("\n");
        profile.setText(currentUser.getDisplayName());

//        profile.append("\n");
//        profile.append(user.getDisplayName());
        this.finish();
    }
}