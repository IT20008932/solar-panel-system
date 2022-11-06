package com.uee.solarpanelsystem.bill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.uee.solarpanelsystem.R;

public class Bill extends AppCompatActivity {

    //declaring variables
    Button calculateBtn;
    TextInputEditText no_days, no_units;
    int days, units;
    float value, service;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        
        no_days = findViewById(R.id.Txt1);
        no_units = findViewById(R.id.Txt2);
        calculateBtn = findViewById(R.id.button1);

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    days = Integer.parseInt(no_days.getText().toString());
                    units = Integer.parseInt(no_units.getText().toString());

                    value = 0;
                    service = 0;

                    if (units <= 2 * days) {
                        if (units <= days) {
                            value += (float) (units * 2.50);
                            service = 30f;
                        } else {
                            value += (float) ((units - days) * 4.85);
                            value += (float) (days * 2.50);
                            service = 60f;
                        }
                    } else {
                        if (units <= 3 * days) {
                            value += (float) ((2 * days) * 7.85);
                            value += (float) ((units - (2 * days)) * 10.00);
                            service = 90f;
                        } else if (units <= 4 * days) {
                            value += (float) ((2 * days) * 7.85);
                            value += (float) ((days) * 10.00);
                            value += (float) ((units - (3 * days)) * 27.75);
                            service = 480f;
                        } else if (units <= 6 * days) {
                            value += (float) ((2 * days) * 7.85);
                            value += (float) ((days) * 10.00);
                            value += (float) ((days) * 27.75);
                            value += (float) ((units - (4 * days)) * 32.00);
                            service = 480f;
                        } else {
                            value += (float) ((2 * days) * 7.85);
                            value += (float) ((days) * 10.00);
                            value += (float) ((days) * 27.75);
                            value += (float) ((2 * days) * 32.00);
                            value += (float) ((units - (6 * days)) * 45.00);
                            service = 540f;
                        }
                    }
                    String val = Float.toString(value);
                    String ser = Float.toString(service);
                    Intent intent = new Intent(Bill.this,BillSummary.class);
                    intent.putExtra("calculatedValue", val);
                    intent.putExtra("serviceValue", ser);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(Bill.this, "You have to enter information first", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}