package com.uee.solarpanelsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uee.solarpanelsystem.bill.Bill;
import com.uee.solarpanelsystem.blogs.Blogs;
import com.uee.solarpanelsystem.inquiry.Inquiry;
import com.uee.solarpanelsystem.packages.Packages;
import com.uee.solarpanelsystem.tasks.Tasks;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MaterialToolbar mainToolbar;
    private FirebaseAuth mAuth;
    CardView Blogs, Packages, Task, Bill;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mainToolbar = findViewById(R.id.topAppBar2);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Solar Info");

        Blogs = findViewById(R.id.Blogs);
        Packages = findViewById(R.id.Packages);
        Task = findViewById(R.id.Task);
        Bill = findViewById(R.id.Bill);

        Blogs.setOnClickListener(this);
        Packages.setOnClickListener(this);
        Task.setOnClickListener(this);
        Bill.setOnClickListener(this);

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

    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null){
            sendToLogin();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting_btn:
                return true;
            case R.id.action_logout_btn:
                logout();
                return true;
            default:
                return false;
        }
    }

    private void logout() {
        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.Blogs:
                intent = new Intent(this,Blogs.class);
                startActivity(intent);
                break;
            case R.id.Packages:
                intent = new Intent(this,Packages.class);
                startActivity(intent);
                break;
            case R.id.Task:
                intent = new Intent(this,Tasks.class);
                startActivity(intent);
                break;
            case R.id.Bill:
                intent = new Intent(this,Bill.class);
                startActivity(intent);
                break;
        }
    }
}