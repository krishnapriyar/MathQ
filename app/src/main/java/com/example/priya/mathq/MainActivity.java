package com.example.priya.mathq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    Button button;
    static LinkedList<Question> questions = new LinkedList<>();
    private FirebaseAuth mAuth;
    private static final String TAG = "AnonymousAuth";
    private static final int QUESTIONS_IN_LEVEL = 10;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef;
    ProgressBar pb;
    Button but;
    AsyncTask diTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadGame(v);
            }
        });

        setDatabaseRef();
        populateQuestions();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadGame(View v){
        Intent i = new Intent(this, GameLoading.class);

        Bundle mBundle = new Bundle();

        String diff = "easy";
        String year = "1";
        mBundle.putString("DIFFICULTY", diff);
        mBundle.putString("YEAR", year);
        i.putExtras(mBundle);

        startActivity(i);

    }

    public void saveImage(Context context, Bitmap b, String imageName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private String TAG = "DownloadImage";
        private Bitmap downloadImageBitmap(String sUrl) {
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, "Exception 1, Something went wrong!");
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImageBitmap(params[0]);
        }

        protected void onPostExecute(Bitmap result) {
            saveImage(getApplicationContext(), result, "my_image.png");
            ImageView imageView2 = (ImageView)findViewById(R.id.imageView2) ;
            imageView2.setImageBitmap(loadImageBitmap(getApplicationContext(), "my_image.jpeg"));
        }
    }

    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }

    private void setDatabaseRef(){

        myRef = database.getReference().child("year").child("1").child("medium");
    }

    private void populateQuestions(){

        pb = (ProgressBar) findViewById(R.id.progressBar2);

        final Question question = new Question();

        Query phoneQuery = myRef.child("1").orderByChild("1");
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    question.setA(dataSnapshot.child("a").getValue().toString());
                    question.setQ(dataSnapshot.child("q").getValue().toString());
                    question.setUrl(dataSnapshot.child("url").getValue().toString());
                    question.setqImgStr("Q"+1);
                    //new DownloadImageTask(pb, but, question,"Q"+dataSnapshot.getKey() ).execute(question);
                    new DownloadImage().execute(question.url);
                    question.setO1(dataSnapshot.child("o1").getValue().toString());
                    question.setO2(dataSnapshot.child("o2").getValue().toString());
                    question.setO3(dataSnapshot.child("o3").getValue().toString());
                    question.setO4(dataSnapshot.child("o4").getValue().toString());

                    questions.add(question);

                TextView yhj = (TextView)findViewById(R.id.yhj);
                yhj.setText(question.getO1()+" "+question.getO2()+" "+question.getA()+" "+question.getQ());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });

    }

}
