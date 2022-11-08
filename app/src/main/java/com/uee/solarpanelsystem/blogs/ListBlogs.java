package com.sliit.blog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sliit.blog.Classes.Blog;
import com.sliit.blog.DB.DBHandler;

import java.util.ArrayList;

public class ListBlogs extends AppCompatActivity {

    private RecyclerView recview;
    private BlogAdapter blog_adapter;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_blogs);

        dbHandler = new DBHandler(this);

        ArrayList<Blog> allBlog = dbHandler.readAllBlog();

        recview=(RecyclerView) findViewById(R.id.recycler_view);
        recview.setLayoutManager(new LinearLayoutManager(this));

        blog_adapter = new BlogAdapter(allBlog);

        blog_adapter.notifyDataSetChanged();
        recview.setAdapter(blog_adapter);
        
    }
}