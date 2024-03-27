package com.example.myapplicationunit_c;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner sourceUnitSpinner, destinationUnitSpinner;
    private EditText valueEditText;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceUnitSpinner = findViewById(R.id.sourceUnitSpinner);
        destinationUnitSpinner = findViewById(R.id.destinationUnitSpinner);
        valueEditText = findViewById(R.id.valueEditText);
        convertButton = findViewById(R.id.convertButton);
        resultTextView = findViewById(R.id.resultTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceUnitSpinner.setAdapter(adapter);
        destinationUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void performConversion() {
        String sourceUnit = sourceUnitSpinner.getSelectedItem().toString();
        String destinationUnit = destinationUnitSpinner.getSelectedItem().toString();
        double value = Double.parseDouble(valueEditText.getText().toString());
        double result;

        // Perform conversion based on source and destination units
        if (sourceUnit.equals(destinationUnit)) {
            resultTextView.setText(String.valueOf(value)); // Same unit
            return;
        }

        switch (sourceUnit) {
            case "Inches":
                result = convertLength(value, "Inches", destinationUnit);
                break;
            case "Feet":
                result = convertLength(value, "Feet", destinationUnit);
                break;
            case "Yards":
                result = convertLength(value, "Yards", destinationUnit);
                break;
            case "Miles":
                result = convertLength(value, "Miles", destinationUnit);
                break;
            case "Pounds":
                result = convertWeight(value, "Pounds", destinationUnit);
                break;
            case "Ounces":
                result = convertWeight(value, "Ounces", destinationUnit);
                break;
            case "Kilograms":
                result = convertWeight(value, "Kilograms", destinationUnit);
                break;
            case "Grams":
                result = convertWeight(value, "Grams", destinationUnit);
                break;
            case "Tons":
                result = convertWeight(value, "Tons", destinationUnit);
                break;
            case "Celsius":
                result = convertTemperature(value, "Celsius", destinationUnit);
                break;
            case "Fahrenheit":
                result = convertTemperature(value, "Fahrenheit", destinationUnit);
                break;
            case "Kelvin":
                result = convertTemperature(value, "Kelvin", destinationUnit);
                break;
            default:
                result = value; // No conversion
        }

        resultTextView.setText(String.valueOf(result));
    }

    private double convertLength(double value, String sourceUnit, String destinationUnit) {
        switch (destinationUnit) {
            case "Inches":
                return value;
            case "Feet":
                return value * 12;
            case "Yards":
                return value * 36;
            case "Miles":
                return value * 63360; // 1 mile = 63360 inches
            default:
                return value; // No conversion
        }
    }

    private double convertWeight(double value, String sourceUnit, String destinationUnit) {
        switch (destinationUnit) {
            case "Pounds":
                return value;
            case "Ounces":
                return value * 16;
            case "Kilograms":
                return value * 0.453592;
            case "Grams":
                return value * 453.592;
            case "Tons":
                return value * 2000;
            default:
                return value; // No conversion
        }
    }

    private double convertTemperature(double value, String sourceUnit, String destinationUnit) {
        switch (destinationUnit) {
            case "Celsius":
                return value;
            case "Fahrenheit":
                return (value * 9 / 5) + 32;
            case "Kelvin":
                return value + 273.15;
            default:
                return value; // No conversion
        }
    }
}
