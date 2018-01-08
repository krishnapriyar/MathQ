package com.example.priya.mathq;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuItemActivity extends AppCompatActivity {

    String selected_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
    }

    public void setSelectedPooh(){
        selected_pic="pooh";

    }
    public void setSelectedCute(){
        selected_pic="cute";

    }
    public void setSelectedElephant(){
        selected_pic="elephant";

    }
    public void setSelectedBlue(){
        selected_pic="blue";

    }

    public void onClick( String username){

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
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

        editor.putString("userName", username);
        editor.apply();

        //Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();

    /*String image = sharedPreferences.getString("selected_pic", "");
    String username = sharedPreferences.getString("username", "");*/



    }
}
