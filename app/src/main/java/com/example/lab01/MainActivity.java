package com.example.lab01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

// Name: [Nuraiym Yerzhanova]
// Student ID: [47151]
// Lab: 1
public class MainActivity extends AppCompatActivity {
    private EditText nameInput;
    private Button greetBtn;
    private TextView greetingText;

    private EditText numberInput;
    private Button guessBtn;
    private TextView resultText;
    private TextView attemptsLabel;

    private int numberToGuess;
    private int tries = 0;

    private Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.inputName);
        greetBtn = findViewById(R.id.btnWitaj);
        greetingText = findViewById(R.id.textResultB);

        greetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredName = nameInput.getText().toString().trim();

                if (enteredName.length() == 0) {
                    greetingText.setText("Przedstaw się.");
                } else {
                    greetingText.setText("Witaj " + enteredName);
                }
            }
        });

        numberInput = findViewById(R.id.inputNumber);
        guessBtn = findViewById(R.id.btnGuess);
        resultText = findViewById(R.id.textResultC);
        attemptsLabel = findViewById(R.id.textAttemptCount);

        initGame();

        guessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rawInput = numberInput.getText().toString().trim();

                if (rawInput.equals("")) {
                    resultText.setText("Please enter an integer number.");
                    return;
                }

                int userGuess = 0;

                try {
                    userGuess = Integer.parseInt(rawInput);
                } catch (Exception ex) {
                    resultText.setText("Please enter an integer number.");
                    return;
                }

                if (userGuess < 1 || userGuess > 10) {
                    resultText.setText("Number must be in range 1..10.");
                    return;
                }

                tries = tries + 1;
                attemptsLabel.setText("Attempt: " + tries);

                if (userGuess < numberToGuess) {
                    resultText.setText("Value too small");
                } else if (userGuess > numberToGuess) {
                    resultText.setText("Value too large");
                } else {

                    if (tries == 2) {
                        resultText.setText("Correct — achieved on the 2nd attempt");
                        guessBtn.setEnabled(false);
                    } else {
                        resultText.setText("Correct, but not on the 2nd attempt. Try again.");
                        initGame();
                    }
                }
            }
        });
    }

    private void initGame() {

        numberToGuess = rng.nextInt(10) + 1;

        tries = 0;

        attemptsLabel.setText("Attempt: " + tries);
        numberInput.setText("");
    }
}
