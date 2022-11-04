package com.uee.solarpanelsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uee.solarpanelsystem.bill.Bill;
import com.uee.solarpanelsystem.blogs.Blogs;
import com.uee.solarpanelsystem.inquiry.Inquiry;
import com.uee.solarpanelsystem.packages.Packages;
import com.uee.solarpanelsystem.tasks.Tasks;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //bottomNavigationView.setSelectedItemId(R.id.pack);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.blogs:
                    startActivity(new Intent(getApplicationContext()
                            , Blogs.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.inquiry:
                    startActivity(new Intent(getApplicationContext()
                            , Inquiry.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.task:
                    startActivity(new Intent(getApplicationContext()
                            , Tasks.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.pack:
                    startActivity(new Intent(getApplicationContext()
                            , Packages.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.bill:
                    startActivity(new Intent(getApplicationContext()
                            , Bill.class));
                    overridePendingTransition(0, 0);
                    return true;
            }

            return false;
        });
    }
}