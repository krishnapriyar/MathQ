package com.example.priya.mathq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView textView1 = findViewById(R.id.textView2);
        TextView textView2 = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();

        int correct = bundle.getInt("CORRECT");
        String time = bundle.getString("TIME");
        textView1.setText("You have answered "+correct+"/ 10 questions correctly.");
        textView2.setText("Time taken : "+ time);



    }

    public void ret(View v){
        Intent intent = new Intent(ResultsActivity.this, YearSelection.class);
        startActivity(intent);
    }
}
