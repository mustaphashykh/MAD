package com.example.lab4app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button nextScreenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        nextScreenButton = findViewById(R.id.buttonNextScreen);

        nextScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the next screen
                Intent intent = new Intent(WelcomeActivity.this, NextScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
