package com.example.priya.mathq;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by owner on 6/1/2018.
 */

public class SecondFragment extends Fragment {
  View myView;
  String selected_pic;
  ImageButton profileImage,profileImage1,profileImage2,profileImage3;
  EditText userName;

  public SecondFragment() {
  }

  @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       myView = inflater.inflate(R.layout.second_layout, container, false);

      userName = (EditText) getActivity().findViewById(R.id.userName);
      profileImage = (ImageButton) getActivity().findViewById(R.id.pooh);
      profileImage1 = (ImageButton) getActivity().findViewById(R.id.cute);
      profileImage2 = (ImageButton) getActivity().findViewById(R.id.elephant);
      profileImage3 = (ImageButton) getActivity().findViewById(R.id.blue);

      Button button = (Button) getActivity().findViewById(R.id.button1);


      profileImage.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          ((MenuItemActivity)getActivity()).setSelectedPooh();

        }
      });

      profileImage1.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          ((MenuItemActivity)getActivity()).setSelectedCute();

        }
      });
      profileImage2.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          ((MenuItemActivity)getActivity()).setSelectedElephant();

        }
      });
      profileImage3.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          ((MenuItemActivity)getActivity()).setSelectedBlue();

        }
      });

      profileImage3.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          ((MenuItemActivity)getActivity()).setSelectedBlue();

        }
      });


      button.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
          ((MenuItemActivity)getActivity()).onClick(userName.getText().toString());

        }
      });



      return myView;
    }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


    //View hView = getActivity()..inflateHeaderView(R.layout.nav_header_main);

  }






}
