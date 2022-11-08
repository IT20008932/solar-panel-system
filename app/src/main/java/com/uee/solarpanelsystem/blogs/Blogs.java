package com.uee.solarpanelsystem.blogs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.adapters.BlogAdapter;
import com.uee.solarpanelsystem.adapters.PackageAdapter;
import com.uee.solarpanelsystem.database.DBHelper;
import com.uee.solarpanelsystem.packages.AddPackage;

import java.util.ArrayList;

public class Blogs extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    ImageView empty_imageview;
    TextView no_data;
    Bundle bundle;

    DBHelper db;
    ArrayList<String> blog_id,blog_name, description;
    BlogAdapter blogAdapter;
    MaterialToolbar materialToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);

        try {
            bundle = getIntent().getExtras();
            String getstatus = bundle.getString("status");
            Snackbar snackbar = Snackbar.make(findViewById(R.id.blogsLayout), getstatus, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.btn_ok, v -> snackbar.dismiss());
            snackbar.show();
        } catch (Exception ignore) { }

        Log.d("workflow","Packages onCreate method called");

        recyclerView=findViewById(R.id.recyclerView);
        empty_imageview=findViewById(R.id.empty_image);
        no_data = findViewById(R.id.no_data);

        db =new DBHelper(this);
        blog_id = new ArrayList<>();
        blog_name = new ArrayList<>();
        description=new ArrayList<>();

        storeDataInArrays();
        Log.d("workflow","Blogs storeDataInArrays method called");

        blogAdapter = new BlogAdapter(this,this, blog_id,
                blog_name,
                description);

        recyclerView.setAdapter(blogAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        materialToolbar = findViewById(R.id.topAppBar);
        //setSupportActionBar(materialToolbar);

        //Add new package button
        fab = findViewById(R.id.btn_add_package);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openAddNewBlog();
                Log.d("workflow","Blogs float button clicked");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1){
            recreate();    //refresh if data not fetched
            Log.d("workflow","Blogs onActivityResult method called");
        }
    }

    private void storeDataInArrays() {
        Log.d("workflow","Blogs storeDataInArrays method called");
        Cursor cursor=db.readAllBlogs();
        if(cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            Resources resources=getResources();
            while(cursor.moveToNext()){
                blog_id.add(cursor.getString(0));
                blog_name.add(cursor.getString(1).substring(0, 1).toUpperCase() + cursor.getString(1).substring(1));
                description.add(cursor.getString(2).substring(0, 1).toUpperCase() + cursor.getString(2).substring(1));
                Log.d("workflow",cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    private void openAddNewBlog() {
        Log.d("workflow","openAddNewPackage method called");
        Intent intent = new Intent(this, AddBlog.class);
        startActivity(intent);
        Log.i("Lifecycle", "Add package button clicked");
    }
}