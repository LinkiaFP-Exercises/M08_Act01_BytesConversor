package com.faunog.conversor_bytes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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
        fillTheSpinnersWithMetrics();
    }

    private void connectVariableWithElements() {
        spinnerUnitFrom = findViewById(R.id.spinnerUnitFrom);
        spinnerUnitTo = findViewById(R.id.spinnerUnitTo);
        editTextNumber = findViewById(R.id.editTextNumber);
        viewTextResult = findViewById(R.id.viewTextResult);
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonInvert = findViewById(R.id.buttonInvert);
    }

    private void fillTheSpinnersWithMetrics() {
        final String[] unitsNames = {"Bytes", "Kilobytes", "Megabytes", "Terabytes",
                "Petabytes", "Exabytes", "Zettabytes", "Yottabytes", "Brontobytes", "Geobytes"};

        ArrayAdapter<String> unitsNames_arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                                            android.R.layout.simple_list_item_1, unitsNames);

        spinnerUnitFrom.setAdapter(unitsNames_arrayAdapter);
        spinnerUnitTo.setAdapter(unitsNames_arrayAdapter);
    }

    private Spinner spinnerUnitFrom, spinnerUnitTo;
    private EditText editTextNumber;
    private TextView viewTextResult;
    private Button buttonConvert, buttonInvert;
    private int numberUnitFrom, getNumberUnitTo;

}