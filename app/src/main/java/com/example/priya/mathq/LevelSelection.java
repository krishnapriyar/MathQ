package com.example.priya.mathq;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class LevelSelection extends AppCompatActivity {
    String YEAR="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            YEAR = extras.getString("YEAR"); //Year


            if(!YEAR.equals("1")){

                showAlert();
            }
        }


        final Button hard = findViewById(R.id.hard);
        hard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LevelSelection.this, LoadLevelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", YEAR);
                bundle.putString("DIFFICULTY", "hard");
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        final Button easy = findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LevelSelection.this, LoadLevelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", YEAR);
                bundle.putString("DIFFICULTY", "easy");
                intent.putExtras(bundle);


                startActivity(intent);
            }
        });

        final Button medium = findViewById(R.id.medium);
        medium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LevelSelection.this, LoadLevelActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("YEAR", YEAR);
                bundle.putString("DIFFICULTY", "medium");
                intent.putExtras(bundle);


                startActivity(intent);
            }
        });

    }


    private void showAlert(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(LevelSelection.this);
        builder1.setMessage("Oops, this section is not currently available. ");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Go Back to Year Selection",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        Intent intent = new Intent(LevelSelection.this, YearSelection.class);
                        startActivity(intent);

                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
