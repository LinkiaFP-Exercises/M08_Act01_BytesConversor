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

/**
 * Una aplicación para Android que permite a los usuarios convertir valores entre diferentes unidades de medida de datos,
 * como bytes, kilobytes, megabytes, terabytes, etc. La aplicación también admite la inversión de unidades para
 * realizar conversiones bidireccionales. Los resultados se muestran en notación científica si es necesario.
 * La aplicación proporciona una interfaz de usuario intuitiva con selección de unidades y entrada de valores.
 *
 * @author <a href="https://about.me/prof.guazina">Fauno Guazina</a>
 * @version 1.1
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectVariableWithElements();
        fillTheSpinnersWithMetrics();
        applyListenersToButtons();
    }

    /**
     * La función {@code connectVariableWithElements} es un método dentro de la clase MainActivity en una
     * aplicación Android que se encarga de vincular las variables de la actividad con los elementos
     * de la interfaz de usuario (UI) que se han definido en el archivo de diseño XML (activity_main.xml).
     * Esta vinculación es necesaria para que la actividad pueda interactuar y trabajar con los elementos
     * de la interfaz de usuario, como botones, cuadros de texto, vistas de texto, etc.
     * En resumen, Conecta las variables de la actividad con los elementos de la interfaz de usuario.
     */
    private void connectVariableWithElements() {
        spinnerUnitFrom = findViewById(R.id.spinnerUnitFrom);
        spinnerUnitTo = findViewById(R.id.spinnerUnitTo);
        editTextNumber = findViewById(R.id.editTextNumber);
        viewTextResult = findViewById(R.id.viewTextResult);
        buttonConvert = findViewById(R.id.buttonConvert);
        buttonInvert = findViewById(R.id.buttonInvert);
    }

    /**
     * La función {@code fillTheSpinnersWithMetrics} se encarga de llenar dos componentes Spinner con nombres de unidades
     * de medida relacionadas con el almacenamiento de datos. Esto prepara los Spinner para que los usuarios seleccionen
     * las unidades de medida de entrada y salida cuando realicen conversiones de datos en la aplicación.
     */
    private void fillTheSpinnersWithMetrics() {
        final String[] unitsNames = {"", "Bytes", "Kilobytes", "Megabytes", "Terabytes",
                "Petabytes", "Exabytes", "Zettabytes", "Yottabytes", "Brontobytes", "Geobytes"};

        final ArrayAdapter<String> unitsNames_arrayAdapter = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, unitsNames);

        spinnerUnitFrom.setAdapter(unitsNames_arrayAdapter);
        spinnerUnitTo.setAdapter(unitsNames_arrayAdapter);
    }

    /**
     * La función {@code applyListenersToButtons} en la aplicación Android establece listeners (escuchadores) para los botones
     * en la interfaz de usuario. Estos escuchadores están diseñados para responder cuando los botones son presionados
     * por el usuario. Por ejemplo, el botón de conversión y el botón de inversión de métricas tienen escuchadores
     * configurados para realizar acciones específicas cuando se hace clic en ellos. Esto permite que la aplicación
     * reaccione a las interacciones del usuario y realice las operaciones correspondientes, como la conversión de
     * unidades o la inversión de las métricas mostradas.
     */
    private void applyListenersToButtons() {
        buttonConvert.setOnClickListener((v) -> verifyIsPossibleDoTheConvertion());
        buttonInvert.setOnClickListener((v) -> invertMetrics());
    }

    /**
     * La función {@code verifyIsPossibleDoTheConvertion} verifica si es posible realizar una conversión de unidades.
     * Para ello, comprueba si se han seleccionado unidades válidas en los spinners y si el valor ingresado por
     * el usuario es válido. Si ambas condiciones se cumplen, la conversión es posible y se llama a la función
     * {@code doTheConvertion} para realizarla. De lo contrario, se muestra un mensaje al usuario indicando
     * qué acción debe tomar para que la conversión sea válida.
     */
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

    /**
     * La función {@code doTheConvertion} realiza la conversión de unidades. Utiliza factores de conversión
     * predefinidos para convertir el valor ingresado por el usuario de una unidad de medida a otra.
     * Luego, muestra el resultado de la conversión en un formato legible en la interfaz de usuario.
     */
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

    /**
     * La función {@code scientificNotationFormat} toma un número y lo formatea en notación científica si tiene
     * más de dos decimales, de lo contrario, lo deja en formato decimal normal. La notación científica
     * es un formato que representa números grandes o pequeños de manera más compacta, utilizando un exponente.
     *
     * @param valueConverted El número que se formateará en notación científica si es necesario.
     * @return Un String que representa el número formateado en notación científica o decimal.
     */
    private static String scientificNotationFormat(Double valueConverted) {
        return (hasMoreTwoDecimals(valueConverted))
                ? new DecimalFormat("0.##E0").format(valueConverted)
                : Double.toString(valueConverted);
    }

    /**
     * La función {@code hasMoreTwoDecimals} verifica si un número tiene más de dos decimales.
     * Si los tiene, devuelve {@code true}; de lo contrario, devuelve {@code false}.
     *
     * @param numero El número a verificar.
     * @return true si el número tiene más de dos decimales, false en caso contrario.
     */
    private static boolean hasMoreTwoDecimals(double numero) {
        String numeroStr = Double.toString(numero);
        int puntoIndex = numeroStr.indexOf(".");

        if (puntoIndex == -1) {
            return false; // No tiene decimales
        }

        int decimales = numeroStr.length() - puntoIndex - 1;
        return decimales > 2;
    }

    /**
     * La función {@code invertMetrics} se encarga de intercambiar los valores seleccionados en dos componentes Spinner
     * en la aplicación. Primero, guarda la posición del elemento seleccionado en el {@code Spinner} de origen y luego
     * cambia las selecciones en ambos Spinners. Finalmente, llama a la función {@code verifyIsPossibleDoTheConversion}
     * para actualizar el resultado en función de las nuevas selecciones de unidades de medida. En resumen,
     * esta función permite al usuario invertir las unidades de medida para realizar conversiones en dirección contraria.
     */
    private void invertMetrics() {
        final int positionSpinnerUnitFrom = spinnerUnitFrom.getSelectedItemPosition();
        spinnerUnitFrom.setSelection(spinnerUnitTo.getSelectedItemPosition());
        spinnerUnitTo.setSelection(positionSpinnerUnitFrom);
        verifyIsPossibleDoTheConvertion();
    }

    // Variables Globales de uso colectivo para las funciones de la aplicación.
    private Spinner spinnerUnitFrom, spinnerUnitTo;
    private EditText editTextNumber;
    private TextView viewTextResult;
    private Button buttonConvert, buttonInvert;
    private int numberUnitFrom, numberUnitTo;
    private String valueFromUser;
}