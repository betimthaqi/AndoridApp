package com.example.lejlekustore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        Button loginButton = findViewById(R.id.login_btn);
        final EditText usernameInput = findViewById(R.id.login_username_input);
        final EditText passwordInput = findViewById(R.id.login_password_input);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(usernameInput.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Please write your email address...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(passwordInput.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this, "Please write your password...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    login(usernameInput.getText().toString(), passwordInput.getText().toString());
                }

            }
        });

    }

    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(LoginActivity.this, "Authentication success. " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();

                            checkUserAccessLevel(user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        //data extract from document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d(TAG, "onSuccess " + documentSnapshot.getData());
                // user access lvl
                if(documentSnapshot.getString("isAdmin") != null){
                    //admin
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                }
                if(documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    }
}