package com.faunog.conversor_bytes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectVariableWithElements();
        fillTheSpinnersWithMetrics();
        applyListenersToButtons();
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
        final String[] unitsNames = {"", "Bytes", "Kilobytes", "Megabytes", "Terabytes",
                "Petabytes", "Exabytes", "Zettabytes", "Yottabytes", "Brontobytes", "Geobytes"};

        final ArrayAdapter<String> unitsNames_arrayAdapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, unitsNames);

        spinnerUnitFrom.setAdapter(unitsNames_arrayAdapter);
        spinnerUnitTo.setAdapter(unitsNames_arrayAdapter);
    }

    private void applyListenersToButtons() {
        buttonConvert.setOnClickListener((v) -> verifyIsPossibleDoTheConvertion());
        buttonInvert.setOnClickListener((v) -> invertMetrics());
    }

    private void verifyIsPossibleDoTheConvertion() {
        numberUnitFrom = spinnerUnitFrom.getSelectedItemPosition();
        numberUnitTo = spinnerUnitTo.getSelectedItemPosition();
        final boolean unitsGreaterThanZero = numberUnitFrom > 0 && numberUnitTo > 0;

        valueFromUser = editTextNumber.getText().toString();
        final boolean valueFromUserIsValid = valueFromUser.length() > 0;

        if (unitsGreaterThanZero && valueFromUserIsValid) {
            doTheConvertion();
        } else {
            final String message = (unitsGreaterThanZero)
                    ? "Inserte valor a convertir" : "Elige una medida valida";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private void doTheConvertion() {
        final double[] conversionFactors = {0, // to match with spinner positions
                1.0, // Bytes to Bytes
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

        final double valueToConvert = Double.parseDouble(valueFromUser);

        final double valueConverted = (numberUnitFrom == numberUnitTo) ? valueToConvert
                : valueToConvert * conversionFactors[numberUnitFrom]
                / conversionFactors[numberUnitTo];

        viewTextResult.setText(scientificNotationFormat(valueConverted));
    }

    private static String scientificNotationFormat(Double valueConverted) {
        return (hasMoreTwoDecimals(valueConverted))
                ? new DecimalFormat("0.##E0").format(valueConverted)
                : Double.toString(valueConverted);
    }

    private static boolean hasMoreTwoDecimals(double numero) {
        String numeroStr = Double.toString(numero);
        int puntoIndex = numeroStr.indexOf(".");

        if (puntoIndex == -1) {
            return false; // No tiene decimales
        }

        int decimales = numeroStr.length() - puntoIndex - 1;
        return decimales > 2;
    }

    private void invertMetrics() {
        final int positionSpinnerUnitFrom = spinnerUnitFrom.getSelectedItemPosition();
        spinnerUnitFrom.setSelection(spinnerUnitTo.getSelectedItemPosition());
        spinnerUnitTo.setSelection(positionSpinnerUnitFrom);
        verifyIsPossibleDoTheConvertion();
    }

    private Spinner spinnerUnitFrom, spinnerUnitTo;
    private EditText editTextNumber;
    private TextView viewTextResult;
    private Button buttonConvert, buttonInvert;
    private int numberUnitFrom, numberUnitTo;
    private String valueFromUser;
}