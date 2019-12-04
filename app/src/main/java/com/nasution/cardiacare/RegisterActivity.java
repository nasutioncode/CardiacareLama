package com.nasution.cardiacare;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import static maes.tech.intentanim.CustomIntent.customType;

public class RegisterActivity extends AppCompatActivity {

    EditText usernamex, emailx, passwordx;
    Button butonregister;
    TextView gologinx;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        usernamex = findViewById(R.id.username);
        emailx = findViewById(R.id.email);
        passwordx = findViewById(R.id.password);
        butonregister = findViewById(R.id.buttonregister);
        gologinx = findViewById(R.id.gologin);

        butonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernames = usernamex.getText().toString();
                String emails = emailx.getText().toString();
                String passwords = passwordx.getText().toString();

                if (usernames.isEmpty()) {
                    usernamex.setError("Please Enter Username");
                    usernamex.requestFocus();
                }else if (emails.isEmpty()) {
                    emailx.setError("Please Enter Email");
                    emailx.requestFocus();
                }else if (passwords.isEmpty()) {
                    passwordx.setError("Please Enter Password");
                    passwordx.requestFocus();
                }else if (usernames.isEmpty() && emails.isEmpty() && passwords.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fields Are Empty Area !", Toast.LENGTH_SHORT).show();
                }else if (!(emails.isEmpty() && passwords.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(emails, passwords).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Register Unsuccessfull !, Please Try Again", Toast.LENGTH_SHORT).show();
                            }else {
                                startActivity(new Intent(RegisterActivity.this, CreateFormJantungActivity.class));
                            }
                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this, "Error Ocurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void Login(View view) {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        customType(RegisterActivity.this, "bottom-to-top");

//        *left-to-right
//                *right-to-left
//                *bottom-to-up
//                *up-to-bottom
//                *fadein-to-fadeout
//                *rotateout-to-rotatein
    }
}
