package com.uee.solarpanelsystem.packages;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uee.solarpanelsystem.database.DBHelper;
import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.bill.Bill;
import com.uee.solarpanelsystem.blogs.Blogs;
import com.uee.solarpanelsystem.inquiry.Inquiry;
import com.uee.solarpanelsystem.tasks.Tasks;


public class AddPackage extends AppCompatActivity {
    EditText package_name, description;
    boolean isfieldsvalidated=false;  //check all field validations
    String setStatusMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_package);

        Log.d("workflow","AddPackage onCreate method called");
        package_name=findViewById(R.id.inp_package_name);
        description=findViewById(R.id.inp_description);

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
                Log.d("workflow","AddPackage bottom navigation method called");
                return false;
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addPackage(View view) {
        Log.d("workflow","addPackage method called");
        isfieldsvalidated = CheckAllFields();

        if (isfieldsvalidated) {
            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.addPackage(package_name.getText().toString(),
                    description.getText().toString());

            if (val == -1) {
                setStatusMsg = getString(R.string.msg_package_add_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_package_add_succesfull);
            }

            Intent intent = new Intent(this, Packages.class).putExtra("status", setStatusMsg);
            startActivity(intent);
            Log.i("BTN Click", "Add package confirmation button clicked");
        }
    }

    // Checks whether the inputs meet the min and max length
    public boolean CheckAllFields() {

        int maxchar=100;

        Log.d("workflow","AddPackage CheckAllFields method called");
        if (package_name.length() == 0) {
            package_name.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (description.length() == 0) {
            description.setError(getString(R.string.error_msg_mandatory));
            return false;
        }
        if (package_name.length() > maxchar) {
            package_name.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        if (description.length() > maxchar) {
            description.setError(getString(R.string.error_msg_max_characters)+" "+maxchar);
            return false;
        }

        return true;

    }

}
