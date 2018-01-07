package com.example.priya.mathq;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainPageLoading extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_loading2);
        try {

            GifImageView givImageView = (GifImageView) findViewById(R.id.p1);
            GifDrawable gifFromResource = new GifDrawable( getResources(), R.drawable.octoloader );
            givImageView.setImageDrawable(gifFromResource);


        }
        catch(Exception ex){}

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainPageLoading.this, YearSelection.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
