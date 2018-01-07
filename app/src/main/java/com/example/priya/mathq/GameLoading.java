package com.example.priya.mathq;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;

public class GameLoading extends AppCompatActivity {

    static LinkedList<Question> questions = new LinkedList<>();
    private FirebaseAuth mAuth;
    private static final String TAG = "AnonymousAuth";
    private static final int QUESTIONS_IN_LEVEL = 10;



    FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef;
    static String DIFFICULTY;
    ProgressBar pb;
    Button but;
    AsyncTask diTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_loading);



        pb = (ProgressBar) findViewById(R.id.progressBar2);
        but = (Button) findViewById(R.id.button3);

        Bundle bundle = getIntent().getExtras();

        for(int in=0; in<10;in++){

            int qNo = in+1;
            Question question = (Question)bundle.getSerializable("QUESTION"+qNo);

            questions.add(question);

        }



    }

    protected void onStart() {
        super.onStart();

        if(pb.getProgress()!=100){
            but.setVisibility(View.INVISIBLE);
        }

    };

    private void setDatabaseRef(){

        Bundle extras = getIntent().getExtras();
        if (extras == null) {

        }
        String YEAR = extras.getString("YEAR"); //Year
        DIFFICULTY = extras.getString("DIFFICULTY"); //difficulty

        myRef = database.getReference().child("year").child(YEAR).child(DIFFICULTY);
    }

    private void populateQuestions(){

        pb = (ProgressBar) findViewById(R.id.progressBar2);

        for(int i=1; i< QUESTIONS_IN_LEVEL+1; i++){

            final Question question = new Question();
            final int no = i;

            Query phoneQuery = myRef.child(i+"").orderByChild(i+"");
            phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    question.setA(dataSnapshot.child("a").getValue().toString());
                    question.setQ(dataSnapshot.child("q").getValue().toString());
                    question.setUrl(dataSnapshot.child("url").getValue().toString());
                    question.setqImgStr("Q"+no);
                    //new DownloadImageTask(pb, but, question,"Q"+dataSnapshot.getKey() ).execute(question);
                    new DownloadImage().execute(question);
                    question.setO1(dataSnapshot.child("o1").getValue().toString());
                    question.setO2(dataSnapshot.child("o2").getValue().toString());
                    question.setO3(dataSnapshot.child("o3").getValue().toString());
                    question.setO4(dataSnapshot.child("o4").getValue().toString());

                    questions.add(question);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });



        }


    }

    public void loadGame(View v){

        Intent i = new Intent(GameLoading.this, QuestionActivity.class);
        Bundle mBundle = new Bundle();

        for(int in=0; in<questions.size();in++){

            int qNo = in+1;
            mBundle.putSerializable("QUESTION"+qNo,questions.get(in));
            i.putExtras(mBundle);

        }
        startActivity(i);



    }

    private class DownloadImageTask extends AsyncTask<Question, Void, Question> {

        ProgressBar pBarIs;
        Button but;
        String name;
        Question question;


        public DownloadImageTask(ProgressBar pBarIs, Button but, Question question, String name){
            this.pBarIs = pBarIs;
            this.but = but;
            this.name = name;
            this.question = question;
        }

        protected Question doInBackground(Question... urls) {
            Question q = urls[0];
            String urldisplay = q.getUrl();
            String filename="";
            Bitmap mIcon11 = null;
            try {

                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
                filename = createImageFromBitmap(mIcon11);
                question.setqImgStr(filename);
                in.close();



            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return q;
        }

        protected void onPostExecute(Question q) {
            pBarIs.setProgress(pBarIs.getProgress()+9);
            question = q;

            if(pBarIs.getProgress()==100){


                but.setVisibility(View.VISIBLE);

            }


        }

        public String createImageFromBitmap(Bitmap bitmap) {
            String fileName = name;//no .png or .jpg needed
            try {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                getApplicationContext();
                FileOutputStream fo = openFileOutput(fileName, Context.MODE_PRIVATE);
                fo.write(bytes.toByteArray());
                // remember close file output
                fo.close();
            } catch (Exception e) {
                e.printStackTrace();
                fileName = null;
            }
            return fileName;
        }


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

    private class DownloadImage extends AsyncTask<Question, Void, Bitmap> {
        private String TAG = "DownloadImage";
        Question ques;
        private Bitmap downloadImageBitmap(Question q) {

            ques=q;

            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(q.getUrl()).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, "Something went wrong!");
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected Bitmap doInBackground(Question... params) {
            return downloadImageBitmap(params[0]);
        }

        protected void onPostExecute(Bitmap result) {
            saveImage(getApplicationContext(), result, ques.getqImgStr());
            pb.setProgress(pb.getProgress()+9);


            if(pb.getProgress()==100){

                but.setVisibility(View.VISIBLE);
                ImageView imv = (ImageView)findViewById(R.id.imageView);
                imv.setImageBitmap(loadImageBitmap(getApplicationContext(), "Q1"+".PNG"));

            }
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




}
