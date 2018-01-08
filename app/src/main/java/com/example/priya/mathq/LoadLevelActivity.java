package com.example.priya.mathq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;

public class LoadLevelActivity extends AppCompatActivity {

    private static final String TAG = "";
    ProgressBar pb;
    Button but;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef;
    static String DIFFICULTY;
    static LinkedList<Question> questions = new LinkedList<>();
    private static final int QUESTIONS_IN_LEVEL = 10;
    TextView tv5;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_level);

        pb = (ProgressBar) findViewById(R.id.progressBar3);
        pb.setProgress(10);
        but = (Button) findViewById(R.id.button4);

        if(pb.getProgress()!=100){
            but.setVisibility(View.INVISIBLE);
        }

        tv5 = (TextView) findViewById(R.id.textView5) ;

        setDatabaseRef();

        downloadQuestions();



    }

    private void setDatabaseRef(){

        Bundle extras = getIntent().getExtras();
        if (extras == null) {

        }
        String YEAR = extras.getString("YEAR"); //Year
        DIFFICULTY = extras.getString("DIFFICULTY"); //difficulty

        myRef = database.getReference().child("year").child(YEAR).child(DIFFICULTY);
    }

    private void downloadQuestions(){

        pb = (ProgressBar) findViewById(R.id.progressBar3);

        for(int i=1; i< QUESTIONS_IN_LEVEL+1; i++){

            final Question question = new Question();
            final int no = i;

            Query query = myRef.child(i+"").orderByChild(i+"");

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    question.setA(dataSnapshot.child("a").getValue().toString());
                    question.setQ(dataSnapshot.child("q").getValue().toString());
                    question.setUrl(dataSnapshot.child("url").getValue().toString());

                    question.setO1(dataSnapshot.child("o1").getValue().toString());
                    question.setO2(dataSnapshot.child("o2").getValue().toString());
                    question.setO3(dataSnapshot.child("o3").getValue().toString());
                    question.setO4(dataSnapshot.child("o4").getValue().toString());

                    new DownloadImage().execute(question);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException());
                }
            });

        }


    }

    static Bitmap downloadBitmap(String url) {
        final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        final HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Log.w("ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + url);
                return null;
            }

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    inputStream = entity.getContent();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            // Could provide a more explicit error message for IOException or IllegalStateException
            getRequest.abort();
            Log.w("ImageDownloader", "Error while retrieving bitmap from " + url, e);
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return null;
    }

    private class DownloadImage extends AsyncTask<Question, Void, Question> {
        private String TAG = "DownloadImage";
        Question ques;

        @Override
        protected Question doInBackground(Question... params) {
            Bitmap bitmap =  downloadBitmap(params[0].getUrl());
            if(bitmap!=null) {
                params[0].setqImgStr(convertToBase64(bitmap));
            }

            return params[0];

        }

        protected void onPostExecute(Question result) {

            if(result.getqImgStr()!=null){
                pb.setProgress(pb.getProgress()+9);
            }
            questions.add(result);

            if(pb.getProgress()==100){
                but.setVisibility(View.VISIBLE);
                tv5.setText("Done!");


            }
        }
    }

    private String convertToBase64(Bitmap  bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] byteArrayImage = baos.toByteArray();

        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

        return encodedImage;

    }

    public void putQuestions(View v){

        Intent i = new Intent(LoadLevelActivity.this, QuestionActivity.class);
        Bundle mBundle = new Bundle();

        for(int in=0; in<questions.size();in++){

            int qNo = in+1;
            mBundle.putSerializable("QUESTION"+qNo,questions.get(in));
            i.putExtras(mBundle);

        }

        startActivity(i);

    }
}
