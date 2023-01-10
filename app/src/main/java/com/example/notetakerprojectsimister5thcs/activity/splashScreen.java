package com.example.notetakerprojectsimister5thcs.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import com.example.notetakerprojectsimister5thcs.MainActivity;
import com.example.notetakerprojectsimister5thcs.R;

import java.util.Objects;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms'
                startActivity(new Intent(splashScreen.this, MainActivity.class));
                finish();
            }
        }, 2000);

    }
}