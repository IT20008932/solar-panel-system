package com.uee.solarpanelsystem.packages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.bill.Bill;
import com.uee.solarpanelsystem.blogs.Blogs;
import com.uee.solarpanelsystem.database.DBHelper;
import com.uee.solarpanelsystem.inquiry.Inquiry;
import com.uee.solarpanelsystem.tasks.Tasks;

public class AddContactDetails extends AppCompatActivity {

    Bundle bundle;
    EditText txt_name, txt_address, txt_contact, txt_email;
    String package_id, setStatusMsg;
    boolean isfieldsvalidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_details);

        try {
            bundle = getIntent().getExtras();
            package_id = bundle.getString("package_id");
            txt_name=findViewById(R.id.inp_full_name);
            txt_address=findViewById(R.id.inp_address);
            txt_contact=findViewById(R.id.inp_contact_no);
            txt_email=findViewById(R.id.inp_email);
            Log.d("workflow", "Package id passed from ViewPackage activity " + package_id);
            Log.i("TAG", "Thread ID " + Thread.currentThread().getId());
        } catch (Exception e) {
            finish();
        }

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveContactDetails(View view) {
        Log.d("workflow","addPackage method called");
        isfieldsvalidated = CheckAllFields();

        if (isfieldsvalidated) {
            DBHelper dbHelper = new DBHelper(this);

            long val = dbHelper.addCustomer(package_id,
                    txt_name.getText().toString(),
                    txt_address.getText().toString(),
                    txt_contact.getText().toString(),
                    txt_email.getText().toString());

            if (val == -1) {
                setStatusMsg = getString(R.string.msg_contact_details_unsuccesfull);
            }
            else {
                setStatusMsg = getString(R.string.msg_contact_details_succesfull);
            }

            Intent intent = new Intent(AddContactDetails.this, Packages.class).putExtra("status", setStatusMsg);
            startActivity(intent);
            Log.i("BTN Click", "Proceed to payment button clicked");
        }
    }

    // Checks whether the inputs meet the min and max length
    public boolean CheckAllFields() {

        int maxchar=50;

        Log.d("workflow","AddPackage CheckAllFields method called");

        if (txt_name.length() == 0) {
            txt_name.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_address.length() == 0) {
            txt_address.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        if (txt_contact.length() == 0) {
            txt_contact.setError(getString(R.string.error_msg_mandatory));
            return false;
        }

        return true;

    }
}