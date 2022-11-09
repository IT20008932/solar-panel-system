package com.uee.solarpanelsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ViewBlog extends AppCompatActivity {

    TextView title,blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blog);

        title = findViewById(R.id.viewTitle);
        blog = findViewById(R.id.viewBlog);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        blog.setText(intent.getStringExtra("blog"));
    }
}