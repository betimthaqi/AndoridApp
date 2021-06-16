package com.example.lejlekustore;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button MainjoinNowButton, MainloginButton;
    private ImageView imageCenter;
    long animationDuration = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MainjoinNowButton = (Button) findViewById (R.id.main_joinnow_btn);
        MainloginButton = (Button) findViewById (R.id.main_login_btn);
        imageCenter = findViewById(R.id.imageViewCenter);


        MainloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        MainjoinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });


    }

    public void handleAnimation(View view){
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageCenter, "y", 600f);
        animatorX.setDuration(animationDuration);

        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(imageCenter, View.ALPHA, 1.0f, 0.0f);
        alphaAnimation.setDuration(animationDuration);

        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(imageCenter, "rotation", 0f, 360f);
        rotateAnimation.setDuration(animationDuration);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX);
        animatorSet.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        handleAnimation(imageCenter);
    }
}
