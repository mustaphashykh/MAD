package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText inputField;
    private Button button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9,
            buttonAdd, buttonSubtract, buttonMultiply, buttonDivide,
            buttonEquals, buttonClear;

    private double operand1, operand2;
    private String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = findViewById(R.id.inputField);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonClear = findViewById(R.id.buttonClear);

        // Set click listeners for number buttons
        button0.setOnClickListener(v -> appendNumber("0"));
        button1.setOnClickListener(v -> appendNumber("1"));
        button2.setOnClickListener(v -> appendNumber("2"));
        button3.setOnClickListener(v -> appendNumber("3"));
        button4.setOnClickListener(v -> appendNumber("4"));
        button5.setOnClickListener(v -> appendNumber("5"));
        button6.setOnClickListener(v -> appendNumber("6"));
        button7.setOnClickListener(v -> appendNumber("7"));
        button8.setOnClickListener(v -> appendNumber("8"));
        button9.setOnClickListener(v -> appendNumber("9"));

        // Set click listeners for operation buttons
        buttonAdd.setOnClickListener(v -> setOperator("+"));
        buttonSubtract.setOnClickListener(v -> setOperator("-"));
        buttonMultiply.setOnClickListener(v -> setOperator("*"));
        buttonDivide.setOnClickListener(v -> setOperator("/"));

        // Calculate result on equals button click
        buttonEquals.setOnClickListener(v -> calculateResult());

        // Clear input on clear button click
        buttonClear.setOnClickListener(v -> clearInput());
    }

    private void appendNumber(String number) {
        inputField.append(number);
    }

    private void setOperator(String op) {
        operator = op;
        operand1 = Double.parseDouble(inputField.getText().toString());
        inputField.getText().clear(); // Clear input field for next operand
    }

    private void calculateResult() {
        operand2 = Double.parseDouble(inputField.getText().toString());
        double result = 0;

        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    result = operand1 / operand2;
                } else {
                    inputField.setText("Error");
                    return;
                }
                break;
        }

        inputField.setText(String.valueOf(result));
    }

    private void clearInput() {
        inputField.getText().clear();
        operand1 = 0;
        operand2 = 0;
        operator = null;
    }
}
