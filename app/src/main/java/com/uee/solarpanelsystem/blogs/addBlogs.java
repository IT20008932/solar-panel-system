package com.uee.solarpanelsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uee.solarpanelsystem.blogs.Blog;
import com.uee.solarpanelsystem.database.DBHandler;

public class addBlogs extends AppCompatActivity {

    EditText title,blog;
    DBHandler dbHandler;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blogs);

        title = findViewById(R.id.insertTitle);
        blog = findViewById(R.id.insertBlog);
        submit = findViewById(R.id.insertBtn);

        dbHandler = new DBHandler(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }

    private void insertData(){
        if(!(title.getText().toString().equals("")||title.getText().toString().equals(null))){
            if(!(blog.getText().toString().equals("")||blog.getText().toString().equals(null))){
                Blog blog_data = new Blog();
                blog_data.setTitle(title.getText().toString());
                blog_data.setBlog(blog.getText().toString());

                boolean res = dbHandler.addBlog(blog_data);
                if (res){
                    clearAll();
                    Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"Unsuccessful",Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "Blog Required!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Title Required!", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearAll(){
        title.setText("");
        blog.setText("");
    }
}