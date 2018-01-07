package com.example.priya.mathq;

import android.content.Context;

/**
 * Created by Lysan Chen on 1/1/2018.
 */

public class Timer implements Runnable {

    public static final long HILLIS_TO_MIN = 60000;

    private Context mContext;
    private long startTime;
    private boolean isRunning;

    public Timer(Context context){

        mContext = context;
    }

    public void start(){
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    public void stop(){
        isRunning = false;
    }

    @Override
    public void run() {

        while (isRunning){

            long since = System.currentTimeMillis() - startTime;

            int second = (int) ((since/1000) % 60);
            int minutes = (int) ((since/HILLIS_TO_MIN) % 60);
            int milli = (int) since % 1000;

            ((QuestionActivity)mContext).updateTimerText(String.format("%02d:%02d:%03d", minutes, second, milli));
        }


    }
}
