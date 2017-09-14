package com.amlidaz.restaurantapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.security.ProviderInstaller;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView linkSignin;
    EditText txtName, txtEmail, txtPassword, txtConPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button btnSignup;
    String email,password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        linkSignin = (TextView) findViewById(R.id.link_signin);
        txtName = (EditText) findViewById(R.id.txt_name);
        txtEmail = (EditText) findViewById(R.id.txt_email);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        txtConPassword = (EditText) findViewById(R.id.txt_confirm_pwd);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        linkSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtName.getText().toString().trim().isEmpty()){
                    txtName.setError("This field is required");
                }else if(txtEmail.getText().toString().trim().isEmpty()){
                    txtEmail.setError("This filed is required");
                }else if(!isValidEmail(txtEmail.getText().toString().trim())){
                    txtEmail.setError("Not a valid email");
                }else if(txtPassword.getText().toString().trim().isEmpty()){
                    txtPassword.setError("This field is required");
                }else if(txtConPassword.getText().toString().trim().isEmpty()){
                    txtConPassword.setError("This field is required");
                }else if(txtPassword.getText().toString().trim().compareTo(txtConPassword.getText().toString().trim()) != 0){
                    txtConPassword.setError("Not matched with the Password");
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    email = txtEmail.getText().toString();
                    password = txtPassword.getText().toString();
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();

                            }
                            else {
                                Log.e(TAG,task.getException().getMessage());
                            }
                        }


                    });

                    progressBar.setVisibility(View.GONE);

                }

//                mAuth.createUserWithEmailAndPassword(name,email, password,conpassword)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                                // If sign in fails, display a message to the user. If sign in succeeds
//                                // the auth state listener will be notified and logic to handle the
//                                // signed in user can be handled in the listener.
//                                if (!task.isSuccessful()) {
//                                    Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
//                                            Toast.LENGTH_SHORT).show();
//                                }
//
//                                // ...
//                            }
//                        });
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

        public final static boolean isValidEmail(CharSequence target){
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}