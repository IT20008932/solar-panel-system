package com.uee.solarpanelsystem.packages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uee.solarpanelsystem.bill.Bill;
import com.uee.solarpanelsystem.blogs.Blogs;
import com.uee.solarpanelsystem.database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.inquiry.Inquiry;
import com.uee.solarpanelsystem.tasks.Tasks;

public class ViewPackage extends AppCompatActivity {

    Bundle bundle;
    DBHelper db;
    TextView txt_pid, txt_pname, txt_price, txt_sp_qty, txt_rating, txt_batteries, txt_backup, txt_connection, txt_ch_current, txt_ch_time;
    String package_id;
    Cursor cursor;
    Button btnGetPackage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_package);

        txt_pid = findViewById(R.id.txtpid);
        txt_pname = findViewById(R.id.txtpname);
        txt_price = findViewById(R.id.txtprice);
        txt_sp_qty = findViewById(R.id.txtspqty);
        txt_rating = findViewById(R.id.txtrating);
        txt_batteries = findViewById(R.id.txtbatteries);
        txt_backup = findViewById(R.id.txtbackup);
        txt_connection = findViewById(R.id.txtconnection);
        txt_ch_current = findViewById(R.id.txtchcurrent);
        txt_ch_time = findViewById(R.id.txtchtime);

        btnGetPackage = findViewById(R.id.btn_get_package);


        try {
            bundle = getIntent().getExtras();
            package_id = bundle.getString("package_id");
            Log.d("workflow", "Package id passed from Packages activity " + package_id);
            Log.i("TAG", "Thread ID " + Thread.currentThread().getId());
            loadPackage(package_id);
        } catch (Exception e) {
            finish();
        }

        btnGetPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("workflow","btnGetPackage button clicked");
                Intent intent= new Intent(ViewPackage.this, AddContactDetails.class);
                intent.putExtra("package_id",String.valueOf(txt_pid));
                startActivity(intent);

                Log.d("values",String.valueOf(txt_pid));

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pack);

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
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

            return false;
        });

    }

    private void loadPackage(String packageID) {
        new Thread(() -> {
            Log.i("TAG", "Thread ID " + Thread.currentThread().getId());
            Log.d("workflow", "loadPackage initiated");
            db = new DBHelper(getApplicationContext());
            cursor = db.readPackage(packageID);
            Log.d("workflow", "get row to cursor");
            if (cursor.getCount() == 0) {
                Log.d("workflow", "No Item");
            }
            else {
                if (cursor.moveToFirst()) {
                    txt_pid.setText((cursor.getString(0)));
                    txt_pname.setText(cursor.getString(1));
                    txt_price.setText(cursor.getString(3));
                    txt_sp_qty.setText(cursor.getString(4));
                    txt_rating.setText(cursor.getString(5));
                    txt_batteries.setText(cursor.getString(6));
                    txt_backup.setText(cursor.getString(7));
                    txt_connection.setText(cursor.getString(8));
                    txt_ch_current.setText(cursor.getString(9));
                    txt_ch_time.setText(cursor.getString(10));
                }
            }

        }).start();
    }
}