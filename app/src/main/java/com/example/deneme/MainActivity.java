package com.example.deneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView value;
    Button minus, plus, ayar;

    Setup setClass;
    Vibrator vibrator= null;
    MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = (TextView) findViewById(R.id.value);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        ayar = (Button) findViewById(R.id.setup);

        Context context = getApplicationContext();
        setClass = Setup.getSetup(context);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        player = MediaPlayer.create(context, R.raw.ses);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(1);

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(-1);
            }
        });

        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SetupActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {

        super.onResume();
        value.setText(String.valueOf(setClass.currentValue));

    }


    @Override
    protected void onPause() {

        super.onPause();
        setClass.saveValues();
    }

    //ses tuslarını kullanma
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keycode = event.getKeyCode();
        switch (keycode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(action ==KeyEvent.ACTION_DOWN)
                    valueUpdate(5);
                return true;

            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(action ==KeyEvent.ACTION_DOWN)
                    valueUpdate(-5);
                return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void valueUpdate(int step){
        if (step < 0){
            if (setClass.currentValue + step < setClass.lowerLimit){
                setClass.currentValue = setClass.lowerLimit;

                if(setClass.lowerSound){
                    alertSound();
                }
                if (setClass.lowerVib){
                    alertVib();
                }
            }
            else
                setClass.currentValue += step;
        }
        else {
            if (setClass.currentValue + step > setClass.upperLimit){
                setClass.currentValue = setClass.upperLimit;
                if(setClass.upperSound){
                    alertSound();
                }
                if (setClass.upperVib){
                    alertVib();
                }
            }
            else
                setClass.currentValue += step;
        }
        value.setText(String.valueOf(setClass.currentValue));
    }

    private  void alertSound(){
        player.seekTo(0);
        player.start();
    }
    private  void alertVib(){
        if(vibrator.hasVibrator()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Toast.makeText(this,"Titreşim",Toast.LENGTH_SHORT).show();
                vibrator.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else
                vibrator.vibrate(1000);
        }
    }

}