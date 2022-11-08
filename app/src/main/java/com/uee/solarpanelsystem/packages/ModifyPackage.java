package com.uee.solarpanelsystem.packages;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.bill.Bill;
import com.uee.solarpanelsystem.blogs.Blogs;
import com.uee.solarpanelsystem.database.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.uee.solarpanelsystem.inquiry.Inquiry;
import com.uee.solarpanelsystem.tasks.Tasks;

public class ModifyPackage extends AppCompatActivity {
    TextInputEditText txt_package_id, txt_package_name, txt_description, txt_price, txt_sp_qty, txt_rating, txt_batteries, txt_backup, txt_connection, txt_ch_current, txt_ch_time;

    String pid, package_name, description, price, sp_qty, rating, batteries, backup, connection, ch_current, ch_time;
    String setStatusMsg;

    boolean isfieldsvalidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_package);
        Log.d("workflow","ModifyPackage onCreate method  Called");
        txt_package_id=findViewById(R.id.inp_pid);
        txt_package_name=findViewById(R.id.inp_pack_name);
        txt_description=findViewById(R.id.inp_pack_desc);
        txt_price=findViewById(R.id.inp_pack_price);
        txt_sp_qty=findViewById(R.id.inp_sp_qty);
        txt_rating=findViewById(R.id.inp_rating);
        txt_batteries=findViewById(R.id.inp_batteries);
        txt_backup=findViewById(R.id.inp_bacup);
        txt_connection=findViewById(R.id.inp_connection);
        txt_ch_current=findViewById(R.id.inp_ch_current);
        txt_ch_time=findViewById(R.id.inp_ch_time);

        getAndSetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.pack);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                Log.d("workflow","ModifyPackage Bottom Nav method  Called");
                return false;
            }
        });

    }




    void getAndSetIntentData() {

        Intent intent=new Intent();

        Log.d("workflow","ModifyPackage getAndSetIntentData  method  Called");

        if(getIntent().hasExtra("pid") &&
                getIntent().hasExtra("package_name") &&
                getIntent().hasExtra("description") &&
                getIntent().hasExtra("price") &&
                getIntent().hasExtra("sp_qty") &&
                getIntent().hasExtra("rating") &&
                getIntent().hasExtra("batteries") &&
                getIntent().hasExtra("backup") &&
                getIntent().hasExtra("connection") &&
                getIntent().hasExtra("ch_current") &&
                getIntent().hasExtra("ch_time"))
        {


            pid = getIntent().getStringExtra("pid");
            package_name = getIntent().getStringExtra("package_name");
            description = getIntent().getStringExtra("description");
            price = getIntent().getStringExtra("price");
            sp_qty = getIntent().getStringExtra("sp_qty");
            rating = getIntent().getStringExtra("rating");
            batteries = getIntent().getStringExtra("batteries");
            backup = getIntent().getStringExtra("backup");
            connection = getIntent().getStringExtra("connection");
            ch_current = getIntent().getStringExtra("ch_current");
            ch_time = getIntent().getStringExtra("ch_time");

            //  Log.d("mvalies",rid);
            txt_package_id.setText(pid);
            txt_package_name.setText(package_name);
            txt_description.setText(description);
            txt_price.setText(price);
            txt_sp_qty.setText(sp_qty);
            txt_rating.setText(rating);
            txt_batteries.setText(batteries);
            txt_backup.setText(backup);
            txt_connection.setText(connection);
            txt_ch_current.setText(ch_current);
            txt_ch_time.setText(ch_time);
        }
        else{
            Toast.makeText(this, "No data Available", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updatePackage(View view) {


        isfieldsvalidated = CheckAllFields();
        Log.d("workflow","updatePackage method called");
        if (isfieldsvalidated) {

            DBHelper dbHelper = new DBHelper(this);

            int val = dbHelper.updatePackage(txt_package_id.getText().toString(),
                    txt_package_name.getText().toString(),
                    txt_description.getText().toString(),
                    txt_price.getText().toString(),
                    txt_sp_qty.getText().toString(),
                    txt_rating.getText().toString(),
                    txt_batteries.getText().toString(),
                    txt_backup.getText().toString(),
                    txt_connection.getText().toString(),
                    txt_ch_current.getText().toString(),
                    txt_ch_time.getText().toString());



            if (val == -1) {
                setStatusMsg = getString(R.string.msg_package_update_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_package_update_succesfull);
            }

            Intent intent = new Intent(this, Packages.class).putExtra("status", setStatusMsg);
            startActivity(intent);

            Log.i("BTN Click", "UpdatePackage Confirmation button clicked");
        }
    }

    private boolean CheckAllFields() {
        Log.d("workflow","ModifyPackage CheckAllFields  method  Called");

        int maxchar=100;

        Log.d("workflow","AddPackage CheckAllFields method called");
        if (txt_package_name.length() == 0) {
            txt_package_name.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_description.length() == 0) {
            txt_description.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_price.length() == 0) {
            txt_price.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_sp_qty.length() == 0) {
            txt_sp_qty.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_rating.length() == 0) {
            txt_rating.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_batteries.length() == 0) {
            txt_batteries.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_backup.length() == 0) {
            txt_backup.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_connection.length() == 0) {
            txt_connection.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_ch_current.length() == 0) {
            txt_ch_current.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_ch_time.length() == 0) {
            txt_ch_time.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_package_name.length() > maxchar) {
            txt_package_name.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_description.length() > maxchar) {
            txt_description.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_price.length() > maxchar) {
            txt_price.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_sp_qty.length() > maxchar) {
            txt_sp_qty.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_rating.length() > maxchar) {
            txt_rating.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_batteries.length() > maxchar) {
            txt_batteries.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_backup.length() > maxchar) {
            txt_backup.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_connection.length() > maxchar) {
            txt_connection.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_ch_current.length() > maxchar) {
            txt_ch_current.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (txt_ch_time.length() > maxchar) {
            txt_ch_time.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        return true;

    }

    public void deletePackage(View view){
        confirmDialog();
        Log.d("workflow", "deletePackage method called");
    }

    private void errorDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.msg_oops));
        builder.setMessage((getString(R.string.label_package_id))
                +" "+
                pid
                +" "+
                (getString(R.string.msg_unable_delete))
                +"."+
                (getString(R.string.msg_retry_delete)));
        builder.setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    void confirmDialog() {

        Log.d("workflow","Modify Package confirmDialog method called");
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.msg_are_u_sure));
        builder.setMessage((getString(R.string.msg_confirm_delete))
                +" "+
                (getString(R.string.label_package_id)
                +" "+
                pid
                +" ? "+
                (getString(R.string.msg_confirm_delete_canot_be_undone))));
        builder.setPositiveButton(R.string.btn_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbHelper=new DBHelper(ModifyPackage.this);
                        int val= dbHelper.deletePackage(txt_package_id.getText().toString());
                        if (val == 1) {
                            setStatusMsg = getString(R.string.msg_package_delete_succesfull);
                        }
                        else {
                            setStatusMsg = getString(R.string.msg_package_delete_unsuccesfull);

                        }
                        Intent intent = new Intent(ModifyPackage.this, Packages.class).putExtra("status", setStatusMsg);
                        startActivity(intent);
                        Log.i("BTN Click", "Add package confirmation button clicked");

                    }

                }

        );


        builder.setNegativeButton(R.string.btn_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}