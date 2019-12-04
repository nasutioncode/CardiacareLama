package com.nasution.cardiacare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nasution.cardiacare.R;

import static maes.tech.intentanim.CustomIntent.customType;

public class MainActivity extends AppCompatActivity {

    EditText usernamex, emailx, passwordx;
    Button butonloginx;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setTheme(R.style.AppThemeLauncher);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        mFirebaseAuth = FirebaseAuth.getInstance();
        usernamex = findViewById(R.id.username);
        emailx = findViewById(R.id.emaillogin);
        passwordx = findViewById(R.id.passwordlogin);
        butonloginx = findViewById(R.id.buttonlogin);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null ) {
                    Toast.makeText(MainActivity.this, "You Are Loged In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CreateFormJantungActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Welcome, Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        butonloginx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emails = emailx.getText().toString();
                String passwords = passwordx.getText().toString();

                if (emails.isEmpty()) {
                    emailx.setError("Please Enter Email");
                    emailx.requestFocus();
                }else if (passwords.isEmpty()) {
                    passwordx.setError("Please Enter Password");
                    passwordx.requestFocus();
                }else if (emails.isEmpty() && passwords.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Are Empty Area !", Toast.LENGTH_SHORT).show();
                }else if (!(emails.isEmpty() && passwords.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(emails,passwords).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Email and Password not Registered", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent inTohome = new Intent(MainActivity.this, CreateFormJantungActivity.class);
                                startActivity(inTohome);
                            }
                        }

                    });
                }else {
                    Toast.makeText(MainActivity.this, "Error Ocurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public void Register(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        customType(MainActivity.this, "bottom-to-up");
    }
}
