package com.uee.solarpanelsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uee.solarpanelsystem.blogs.Blog;
import com.uee.solarpanelsystem.database.DBHandler;

public class editBlog extends AppCompatActivity {

    EditText title,blog;
    DBHandler dbHandler;
    Button submit;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_blog);

        title = findViewById(R.id.editTitle);
        blog = findViewById(R.id.editBlog);
        submit = findViewById(R.id.editBtn);

        dbHandler = new DBHandler(this);

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        blog.setText(intent.getStringExtra("blog"));
        id=Integer.parseInt(intent.getStringExtra("id"));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editData();
            }
        });

    }

    private void editData(){
        if(!(title.getText().toString().equals("")||title.getText().toString().equals(null))){
            if(!(blog.getText().toString().equals("")||blog.getText().toString().equals(null))){
                Blog blog_data = new Blog();
                blog_data.setTitle(title.getText().toString());
                blog_data.setBlog(blog.getText().toString());
                blog_data.setId(id);

                boolean res = dbHandler.editBlog(blog_data);
                if (res){
                    clearAll();
                    Toast.makeText(this,"Successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this,ListBlogs.class));
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