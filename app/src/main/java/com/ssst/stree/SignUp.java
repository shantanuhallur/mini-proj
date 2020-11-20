package com.ssst.stree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    EditText email;
    EditText password;
    FirebaseAuth mAuth;
    Button signUp;
//    TextView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

//        View v = getLayoutInflater().inflate(R.layout.main_nav_drawer,null);
        email = findViewById(R.id.ettEmail);
        password = findViewById(R.id.ettPassword);
//        profile = v.findViewById(R.id.profile);
        signUp = findViewById(R.id.btnRegister);

        signUp.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        }));
    }

    public void signUp() {
        String email = this.email.getText().toString().trim();
        String password = this.password.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("SignUp","sign up : " + task.isSuccessful());
                if(!task.isSuccessful()) {
                    Log.d("SignUp","Something wrong");
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT);
                    // TODO : Make the page interactive on failure
                } else {
                    Log.d("SignUp","Awesome");
                    // TODO : Make the page interactive on success
                    Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT);
                    UpdateUI();
                }
            }
        });
    }

    private void UpdateUI() {
//        FirebaseUser user = mAuth.getCurrentUser();
//        Log.d("SignIn", String.valueOf(profile!=null));
//        profile.append("\n");
//        profile.setText("Hi logged in");
//        profile.append("\n");
//        profile.append(user.getDisplayName());
        this.finish();
    }
}