package com.example.priya.mathq;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class QuestionActivity extends AppCompatActivity implements LevelFragment.OnFragmentInteractionListener {

    private TextView Timer;
    private int questionNo=0;

    private Context mContext;
    private Timer timer;
    private Thread mThreadChrono;
    static LinkedList<Question> questions = new LinkedList<>();

    private int correct =0;
    private String totalTime="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mContext = this;

        Bundle bundle = getIntent().getExtras();

        for(int in=0; in<10;in++){

            int qNo = in+1;
            Question question = (Question)bundle.getSerializable("QUESTION"+qNo);

            questions.add(question);

        }
        Timer = (TextView) findViewById(R.id.TimerView);


        setFragment(questions.getFirst());
        start();

    }


    public void updateTimerText(final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Timer.setText(time);
            }
        });

    }

    public void stop() {
        if (timer != null) {

            totalTime= Timer.getText().toString();

            timer.stop();
            mThreadChrono.interrupt();
            mThreadChrono = null;

            timer = null;
        }

    }

    public void start(){
        if(timer == null){
            timer = new Timer(mContext);
            mThreadChrono = new Thread(timer);
            mThreadChrono.start();
            timer.start();
        }
    }

    public void setFragment(Question question){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        LevelFragment frag = LevelFragment.newInstance(question, question.getA());
        fragmentTransaction.add (R.id.fragment, frag);
        fragmentTransaction.commit ();


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private Bitmap decodeFromBase64ToBitmap(String encodedImage) {

        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);

        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decodedByte;

    }

    public void nextQuestion(String option){

        checkAnswer(option,questions.get(questionNo));

        if(questionNo!=9){
            setFragment(questions.get(++questionNo));
        }
        else{
            stop();

            Intent intent = new Intent(QuestionActivity.this, ResultsActivity.class);

            Bundle bundle = new Bundle();
            bundle.putInt("CORRECT",correct);
            bundle.putString("TIME",totalTime);

            intent.putExtras(bundle);
            startActivity(intent);


        }

    }

    private void checkAnswer(String option, Question question){

        if(question.getA().equals(option)){

            question.setCorrect(true);
            correct++;
        }
        else{
            question.setCorrect(false);
        }
    }
}
