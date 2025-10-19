package com.example.bmicalculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // xml file & java file is connected due to this line

        //variable declarations, all hv null value rn
        TextView txtResult;
        Button btnSubmit;
        EditText etWeight,etHeightcm;
        LinearLayout main;

        // initialize with values = id in xml file by using findViewById()
        // id is a HIDDEN file in res(R) folder, hence called in findViewById() as R.id.(idAsDeclaredInXML)

        txtResult = findViewById(R.id.txtResult);
        btnSubmit = findViewById(R.id.btnCalculateBMI);
        etWeight = findViewById(R.id.etWeight);
        etHeightcm = findViewById(R.id.etHeightcm);
        main = findViewById(R.id.main);

        // logic for button onclick to calculate BMI

        btnSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int wt = Integer.parseInt(etWeight.getText().toString());
                int htCm = Integer.parseInt(etHeightcm.getText().toString());
                double htM = htCm/100;
                double BMI = (double) wt / ( htM * htM );

                if(BMI>=25) {
                    txtResult.setText("You are overweight");
                    txtResult.setTextColor(Color.RED);
                    main.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.ow));
                } else if (BMI<=18) {
                    txtResult.setText("You are underweight");
                    txtResult.setTextColor(Color.RED);
                    main.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.uw));
                }
                else {
                    txtResult.setText("You are healthy");
                    txtResult.setTextColor(Color.GREEN);
                    main.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.hw));
                }
            }
        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}