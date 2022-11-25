package com.example.deneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Set;

public class SetupActivity extends AppCompatActivity {
    Button uplus, uminus, downplus, downuminus;
    EditText upValue, lowValue;
    CheckBox upVib, upSound, lowVib, lowSound;

    Setup setClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        uplus =(Button) findViewById(R.id.up_plus);
        uminus =(Button) findViewById(R.id.up_minus);
        upValue = (EditText) findViewById(R.id.upperLimit);
        downplus =(Button) findViewById(R.id.low_plus);
        downuminus =(Button) findViewById(R.id.low_minus);
        lowValue = (EditText) findViewById(R.id.lowerLimit);

        upVib = (CheckBox) findViewById(R.id.up_vib);
        upSound =(CheckBox) findViewById(R.id.up_sound);
        lowVib = (CheckBox)  findViewById(R.id.low_vib);
        lowSound =(CheckBox) findViewById(R.id.low_sound);

        setClass = Setup.getSetup(getApplicationContext());

        uplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClass.upperLimit++;
                upValue.setText(String.valueOf(setClass.upperLimit));
            }
        });

        uminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClass.upperLimit--;
                upValue.setText(String.valueOf(setClass.upperLimit));
            }
        });

        upValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (upValue.getText().toString().length() != 0){
                    setClass.upperLimit = Integer.parseInt(upValue.getText().toString());
                }
            }
        });

        upVib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setClass.upperVib = b;
            }
        });
        upSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setClass.upperSound = b;
            }
        });
        downuminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClass.lowerLimit--;
                lowValue.setText(String.valueOf(setClass.lowerLimit));
            }
        });
        downplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClass.lowerLimit++;
                lowValue.setText(String.valueOf(setClass.lowerLimit));
            }
        });
        lowValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (lowValue.getText().toString().length() != 0){
                    setClass.lowerLimit = Integer.parseInt(lowValue.getText().toString());
                }
            }
        });
        lowVib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setClass.lowerVib = b;
            }
        });
        lowSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setClass.lowerSound = b;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        upValue.setText(String.valueOf(setClass.upperLimit));
        lowValue.setText(String.valueOf(setClass.lowerLimit));
        upVib.setChecked(setClass.upperVib);
        lowVib.setChecked(setClass.lowerVib);
        upSound.setChecked(setClass.upperSound);
        lowSound.setChecked(setClass.lowerSound);
    }

    @Override
    protected void onPause() {
        super.onPause();
        setClass.saveValues();
    }
}