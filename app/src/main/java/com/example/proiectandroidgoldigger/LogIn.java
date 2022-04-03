package com.example.proiectandroidgoldigger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private TextInputEditText LogInEmail;
    private TextInputEditText LogInPassword;
    private TextView GoToSignUpTextView;
    private Button LogIn;
    private Intent intent;
    private FirebaseAuth mfirebase;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initComponents();

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verificareUser()) {
                    Logare();
                }

            }
        });
        GoToSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initComponents() {
        LogInEmail = findViewById(R.id.LogInEmail);
        LogInPassword = findViewById(R.id.LogInPassword);
        LogIn = findViewById(R.id.LogInbutton);
        GoToSignUpTextView = findViewById(R.id.LogIntwGoToSignUp);
        mfirebase = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mfirebase.getCurrentUser();
                if (mFirebaseUser != null) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    private boolean verificareUser() {
        if (LogInEmail.getText() == null || LogInEmail.getText().toString().trim().length() < 3) {
            LogInEmail.setError(getString(R.string.null_email));
            return false;
        }
        if (LogInPassword.getText() == null || LogInPassword.getText().toString().trim().length() < 5) {
            LogInPassword.setError(getString(R.string.parola_null));
            return false;
        }
        return true;
    }

    private void Logare() {
        mfirebase.signInWithEmailAndPassword(String.valueOf(LogInEmail.getText()).trim(), String.valueOf(LogInPassword.getText()).trim()).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.autentificare_reusita, Toast.LENGTH_SHORT).show();
                            intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), R.string.logIn_validare_utilizator, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mfirebase.addAuthStateListener(mAuthStateListener);
    }
}