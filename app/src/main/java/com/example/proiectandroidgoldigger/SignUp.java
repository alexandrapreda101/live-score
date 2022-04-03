package com.example.proiectandroidgoldigger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    private TextInputEditText SignUpEmail;
    private TextInputEditText SignUpPassword;
    private TextInputEditText ConfirmPassword;
    private Button SignUpButton;
    private FirebaseAuth mfirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initcomponents();
        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validari()) {
                    Intent intent= new Intent(getApplicationContext(),LogIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void initcomponents() {
        SignUpEmail=findViewById(R.id.SignUpEmail);
        SignUpPassword=findViewById(R.id.SignUpPassword);
        ConfirmPassword=findViewById(R.id.SignUpConfirmPassword);
        SignUpButton=findViewById(R.id.SignUpbutton);
        mfirebase=FirebaseAuth.getInstance();
    }
    private boolean validari(){
        if(SignUpEmail.getText()==null || !SignUpEmail.getText().toString().trim().contains("@")
                || !SignUpEmail.getText().toString().trim().contains(".com")) {
            SignUpEmail.setError(getString(R.string.eroare_email));
            return false;
        }
        if(SignUpPassword.getText()==null || SignUpPassword.getText().toString().trim().length()<=5) {
            SignUpPassword.setError(getString(R.string.eroare_parola));
            return false;
        }
        if(!ConfirmPassword.getText().toString().trim().equals(SignUpPassword.getText().toString().trim()) ){
            ConfirmPassword.setError(getString(R.string.eroare_confirm_password));
            return false;
        }
        mfirebase.createUserWithEmailAndPassword(SignUpEmail.getText().toString(),SignUpPassword.getText().toString()).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), R.string.autentificare_reusita,Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), R.string.eroare_utilizator,Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        return true;
    }
}