package com.uee.solarpanelsystem.bill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uee.solarpanelsystem.R;

public class BillSummary extends AppCompatActivity {

    TextView tv_label_consumed, tv_label_service, tv_label_total, tv_Result_consumed, tv_Result_service, tv_Result_total;
    Button button_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_summary);

        String Value_Calculated = getIntent().getStringExtra("calculatedValue");
        String Service_Calculated = getIntent().getStringExtra("serviceValue");
        assert Value_Calculated != null;
        float valueCal = Float.parseFloat(Value_Calculated);
        assert Service_Calculated != null;
        float serviceCal = Float.parseFloat(Service_Calculated);
        float total = valueCal + serviceCal;

        tv_label_total = findViewById(R.id.textView1);
        tv_label_service = findViewById(R.id.textView3);
        tv_label_consumed = findViewById(R.id.textView);
        tv_Result_consumed = findViewById(R.id.textView2);
        tv_Result_service = findViewById(R.id.textView5);
        tv_Result_total = findViewById(R.id.textView4);
        button_back = findViewById(R.id.back);

        String s = Float.toString(total);

        tv_Result_consumed.setText(Value_Calculated);
        tv_Result_service.setText(Service_Calculated);
        tv_Result_total.setText(s);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BillSummary.this,Bill.class);
                startActivity(intent);
                finish();
            }
        });
    }
}