package com.example.priya.mathq;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by owner on 6/1/2018.
 */

public class SecondFragment extends Fragment {
  View myView;
  String selected_pic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      //  myView = inflater.inflate(R.layout.second_layout, container, false);
      return inflater.inflate(R.layout.second_layout, container, false);
    }

    ImageButton profileImage;
    EditText userName;
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    userName = (EditText) getActivity().findViewById(R.id.userName);
    profileImage = (ImageButton) getActivity().findViewById(R.id.pooh);
    profileImage = (ImageButton) getActivity().findViewById(R.id.cute);
    profileImage = (ImageButton) getActivity().findViewById(R.id.elephant);
    profileImage = (ImageButton) getActivity().findViewById(R.id.blue);

    //View hView = getActivity()..inflateHeaderView(R.layout.nav_header_main);

  }


  public void setSelectedPooh(View v){
    selected_pic="pooh";

  }
  public void setSelectedCute(View v){
    selected_pic="cute";

  }
  public void setSelectedElephant(View v){
    selected_pic="elephant";

  }
  public void setSelectedBlue(View v){
    selected_pic="blue";

  }

  public void onClick(View v){

    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    if(selected_pic.equals("pooh")) {
     editor.putInt("profileImage", R.drawable.image1);
    }else if(selected_pic.equals("cute")){
      editor.putInt("profileImage", R.drawable.image2);
    }else if(selected_pic.equals("elephant")){
      editor.putInt("profileImage", R.drawable.image3);
    }else if(selected_pic.equals("blue")){
      editor.putInt("profileImage", R.drawable.image4);
    }

    editor.putString("userName", userName.getText().toString());
    editor.apply();

    //Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();

    /*String image = sharedPreferences.getString("selected_pic", "");
    String username = sharedPreferences.getString("username", "");*/



  }



}
