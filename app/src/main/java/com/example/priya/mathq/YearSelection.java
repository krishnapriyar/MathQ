package com.example.priya.mathq;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class YearSelection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_selection);

        final Button y1 = findViewById(R.id.y1);
        y1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YearSelection.this, LevelSelection.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", "1");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button y2 = findViewById(R.id.y2);
        y2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YearSelection.this, LevelSelection.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", "2");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button y3 = findViewById(R.id.y3);
        y3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YearSelection.this, LevelSelection.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", "3");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button y6 = findViewById(R.id.y6);
        y6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YearSelection.this, LevelSelection.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", "6");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button y5 = findViewById(R.id.y5);
        y5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YearSelection.this, LevelSelection.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", "5");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button y4 = findViewById(R.id.y4);
        y4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(YearSelection.this, LevelSelection.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", "4");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }
}
