package com.example.priya.mathq;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.Serializable;

/**
 * Created by Priya on 12/30/2017.
 */

public class Question implements  Serializable {


    String a;
    String q;
    String url;
    String o1;
    String o2;
    String o3;
    String o4;
    Boolean correct = false;
    String qImgStr;

    public Question() {

    }

    public Question(String question, String ans, String url) {
        this.q = question;
        this.a = ans;
        this.url = url;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String question) {
        this.q = question;
    }

    public String getA() {
        return a;
    }

    public void setA(String ans) {
        this.a = ans;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;


    }

    public String getO1() {
        return o1;
    }

    public void setO1(String o1) {
        this.o1 = o1;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getO4() {
        return o4;
    }

    public void setO4(String o4) {
        this.o4 = o4;
    }


    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getqImgStr() {
        return qImgStr;
    }

    public void setqImgStr(String qImgStr) {
        this.qImgStr = qImgStr;
    }


}
