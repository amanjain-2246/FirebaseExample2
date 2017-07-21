package com.vbv.firebaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button submitBtn;
    EditText emailET;
    EditText passET;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("users");

    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        emailET = (EditText) findViewById(R.id.emailid);
        passET = (EditText) findViewById(R.id.passid);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.i(TAG, "onCreate: current user" + currentUser);
        Log.i(TAG, "onCreate: currentuser" + currentUser);
        if (currentUser != null) {
            startActivity(new Intent(SignUpActivity.this, NotesActivity.class));
            finish();
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: ");
                final String email = emailET.getText().toString();
                final String pass = passET.getText().toString();
                Log.i(TAG, "onClick: email" + email);
                Log.i(TAG, "onClick: pass" + pass);
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        AuthResult result = task.getResult();
                        Log.i(TAG, "onComplete: " + result.toString());
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: success");
                        } else {
                            Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "onComplete: failed");

                        }
                    }
                });
            }
        });


    }
}


//    mAuth.signInWithEmailAndPassword
//                            mAuth.signOut();