package com.uee.solarpanelsystem.packages;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uee.solarpanelsystem.adapters.PackageAdapter;
import com.uee.solarpanelsystem.bill.Bill;
import com.uee.solarpanelsystem.blogs.Blogs;
import com.uee.solarpanelsystem.database.DBHelper;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.inquiry.Inquiry;
import com.uee.solarpanelsystem.tasks.Tasks;

import java.util.ArrayList;

public class Packages extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    ImageView empty_imageview;
    TextView no_data;
    Bundle bundle;

    DBHelper db;
    ArrayList<String> package_id,package_name, description, price, sp_qty, rating, batteries, backup, connection, ch_current, ch_time;
    PackageAdapter packageAdapter;
    MaterialToolbar materialToolbar;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        try {
            bundle = getIntent().getExtras();
            String getstatus = bundle.getString("status");
            Snackbar snackbar = Snackbar.make(findViewById(R.id.packagesLayout), getstatus, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.btn_ok, v -> snackbar.dismiss());
            snackbar.show();
        } catch (Exception ignore) { }

        Log.d("workflow","Packages onCreate method called");

        recyclerView=findViewById(R.id.recyclerView);
        empty_imageview=findViewById(R.id.empty_image);
        no_data = findViewById(R.id.no_data);

        db =new DBHelper(this);
        package_id = new ArrayList<>();
        package_name = new ArrayList<>();
        description=new ArrayList<>();
        price=new ArrayList<>();
        sp_qty=new ArrayList<>();
        rating=new ArrayList<>();
        batteries=new ArrayList<>();
        backup=new ArrayList<>();
        connection=new ArrayList<>();
        ch_current=new ArrayList<>();
        ch_time=new ArrayList<>();

        storeDataInArrays();
        Log.d("workflow","Packages storeDataInArrays method called");

        packageAdapter = new PackageAdapter(this,this, package_id,
                package_name, description, price, sp_qty, rating, batteries, backup, connection, ch_current, ch_time);

        recyclerView.setAdapter(packageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        materialToolbar = findViewById(R.id.topAppBar);
        //setSupportActionBar(materialToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pack);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                        return true;

                    case R.id.bill:
                        startActivity(new Intent(getApplicationContext()
                                , Bill.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                Log.d("workflow","Packages bottom navigation method called");
                return false;
            }
        });


        //Add new package button
        fab = findViewById(R.id.btn_add_package);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openAddNewPackage();
                Log.d("workflow","Packages float button clicked");
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
            Log.d("workflow","Packages onActivityResult method called");
        }
    }

    private void storeDataInArrays() {
        Log.d("workflow","Packages storeDataInArrays method called");
        Cursor cursor=db.readAllPackages();
        if(cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            Resources resources=getResources();
            while(cursor.moveToNext()){
                package_id.add(cursor.getString(0));
                package_name.add(cursor.getString(1).substring(0, 1).toUpperCase() + cursor.getString(1).substring(1));
                description.add(cursor.getString(2).substring(0, 1).toUpperCase() + cursor.getString(2).substring(1));
                price.add(cursor.getString(3));
                sp_qty.add(cursor.getString(4));
                rating.add(cursor.getString(5));
                batteries.add(cursor.getString(6));
                backup.add(cursor.getString(7));
                connection.add(cursor.getString(8));
                ch_current.add(cursor.getString(9));
                ch_time.add(cursor.getString(10));
                Log.d("workflow",cursor.getString(10));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }


    //Function to load the add package page
    public void openAddNewPackage() {
        Log.d("workflow","openAddNewPackage method called");
        Intent intent = new Intent(this, AddPackage.class);
        startActivity(intent);
        Log.i("Lifecycle", "Add package button clicked");
    }


    //Code for view package button
    public void openViewPackage()
    {
        Log.d("workflow","Packages openViewPackage method called");
        Intent intent = new Intent(this,ModifyPackage.class);
        startActivity(intent);
        Log.i("workflow","Add package button clicked");
    }

}