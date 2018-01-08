package com.example.priya.mathq;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by owner on 6/1/2018.
 */

public class ThirdFragment extends Fragment {
  View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.third_layout, container,false);

        ImageView im = (ImageView) getView().findViewById(R.id.imageView6);
        TextView tx4 = (TextView) getView().findViewById(R.id.textView4);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        int res = sharedPreferences.getInt("profileImage", R.drawable.image1);
        int correct = sharedPreferences.getInt("correct", 28);
        String name = sharedPreferences.getString("userName", "User");

        tx4.setText("Congratulations, " + name+". "+correct +" questions answered correctly!");

        im.setImageResource(res);

        return myView;
    }


}
