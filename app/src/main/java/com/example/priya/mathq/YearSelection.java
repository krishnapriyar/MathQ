package com.example.priya.mathq;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class YearSelection extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_selection);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.year, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ProfileSetting) {

            Intent i = new Intent(YearSelection.this, MenuItemActivity.class);
            startActivity(i);

            return true;

        }else if(id == R.id.Profile){

            Intent i = new Intent(YearSelection.this, HahaActivity.class);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }



}
