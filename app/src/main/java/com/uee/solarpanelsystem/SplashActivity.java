package com.uee.solarpanelsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    Button loginBtn, signUp;
    ImageView appLogoImg;
    TextView logoTitle, logoDesc;
    Animation upperAnimation, bottomAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        loginBtn = findViewById(R.id.loginBtn);
        signUp = findViewById(R.id.signUpBtn);
        appLogoImg = findViewById(R.id.app_logo);
        logoTitle = findViewById(R.id.logoTitle);
        logoDesc = findViewById(R.id.logoDesc);

        upperAnimation = AnimationUtils.loadAnimation(this,R.anim.upper_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        appLogoImg.setAnimation(upperAnimation);
        logoTitle.setAnimation(upperAnimation);
        logoDesc.setAnimation(upperAnimation);
        loginBtn.setAnimation(bottomAnimation);
        signUp.setAnimation(bottomAnimation);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}