package com.faunog.conversor_bytes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectVariableWithElements();
    }

    private void connectVariableWithElements() {
        spinnerUnitFrom = findViewById(R.id.spinnerUnitFrom);
        spinnerUnitTo = findViewById(R.id.spinnerUnitTo);
        editTextNumber = findViewById(R.id.editTextNumber);
        viewTextResult = findViewById(R.id.viewTextResult);
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonInvert = findViewById(R.id.buttonInvert);
    }

    private Spinner spinnerUnitFrom, spinnerUnitTo;
    private EditText editTextNumber;
    private TextView viewTextResult;
    private Button buttonConvert, buttonInvert;
    private int numberUnitFrom, getNumberUnitTo;

}