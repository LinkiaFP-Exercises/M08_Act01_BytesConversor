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
        buttonConvert.setOnClickListener((v) -> doTheMagicConversion());
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

    private void doTheMagicConversion() {
        final double[] conversionFactors = {1.0, // Bytes to Bytes
                Math.pow(2, 10), // Kilobytes to Bytes
                Math.pow(2, 20), // Megabytes to Bytes
                Math.pow(2, 30), // Terabytes to Bytes
                Math.pow(2, 40), // Petabytes to Bytes
                Math.pow(2, 50), // Exabytes to Bytes
                Math.pow(2, 60), // Zettabytes to Bytes
                Math.pow(2, 70), // Yottabytes to Bytes
                Math.pow(2, 80), // Brontobytes to Bytes
                Math.pow(2, 90)  // Geobytes to Bytes
        };
        final String valueFromUser = editTextNumber.getText().toString();
        final int numberUnitFrom = spinnerUnitFrom.getSelectedItemPosition();
        final int numberUnitTo = spinnerUnitTo.getSelectedItemPosition();

        final double valueToConvert = Double.parseDouble(valueFromUser);

        final double valueConverted = (numberUnitFrom == numberUnitTo) ? valueToConvert
                : valueToConvert * conversionFactors[numberUnitFrom]
                / conversionFactors[numberUnitTo];

        viewTextResult.setText(String.valueOf(valueConverted));
    }


    private Spinner spinnerUnitFrom, spinnerUnitTo;
    private EditText editTextNumber;
    private TextView viewTextResult;
    private Button buttonConvert, buttonInvert;

}