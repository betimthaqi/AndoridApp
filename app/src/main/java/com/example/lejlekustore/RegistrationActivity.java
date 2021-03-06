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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Button registerButton = findViewById(R.id.register_btn);
        Button backloginButton = findViewById(R.id.backlogin_btn);
        final EditText usernameInput = findViewById(R.id.register_username_input);
        final EditText passwordInput = findViewById(R.id.register_password_input);

        backloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationActivity.this, "You have gone back to login page", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(usernameInput.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please write your email address...", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(passwordInput.getText().toString()))
                {
                    Toast.makeText(RegistrationActivity.this, "Please write your password...", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            singUp(usernameInput.getText().toString(), passwordInput.getText().toString());
                        }
                    });

                }

            }
        });


    }

    private void singUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                            Toast.makeText(RegistrationActivity.this, "Authentication success." + user.getEmail(), Toast.LENGTH_SHORT).show();

                            DocumentReference df = fStore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("FullName", email);
                            userInfo.put("isUser", "1");  //isAdmin or not
                            df.set(userInfo);

                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}