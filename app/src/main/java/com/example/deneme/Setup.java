package com.example.deneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Setup {
    int upperLimit;
    int lowerLimit;
    int currentValue;

    boolean upperVib;
    boolean upperSound;
    boolean lowerVib;
    boolean lowerSound;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static Setup setClass = null;

    private Setup(Context context) {
        sharedPreferences = context.getSharedPreferences("setup",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loadValues();
    }

    public static Setup getSetup(Context context){
        if(setClass == null){
            setClass = new Setup(context);
        }
        return setClass;
    }


    public void  saveValues(){
        editor.putInt("upperLimit", upperLimit);
        editor.putInt("lowerLimit", lowerLimit);
        editor.putInt("currentValue",currentValue);
        editor.putBoolean("upperVib",upperVib);
        editor.putBoolean("upperSound", upperSound);
        editor.putBoolean("lowerVib",lowerVib);
        editor.putBoolean("lowerSound",lowerSound);
        editor.commit();

    }
    public void loadValues(){
        upperLimit = sharedPreferences.getInt("upperLimit",20);
        lowerLimit = sharedPreferences.getInt("lowerLimit",0);
        currentValue =sharedPreferences.getInt("currentValue",0);
        upperVib = sharedPreferences.getBoolean("upperVib",true);
        upperSound = sharedPreferences.getBoolean("upperSound",true);
        lowerVib =sharedPreferences.getBoolean("lowerVib",true);
        lowerSound = sharedPreferences.getBoolean("lowerSound",true);


    }

}