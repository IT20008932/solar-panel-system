package com.uee.solarpanelsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SolarPanel.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }
    //change the DB version when upgrading the DB


    @Override
    public void onCreate(SQLiteDatabase db) {        //creating the table
        Log.d("workflow", "DB onCreate method called");
        String SQL_CREATE_PACKAGE_TABLE =
                "CREATE TABLE "
                        + PackageMaster.PackagesT.TABLE_NAME +
                        " ("
                        + PackageMaster.PackagesT.COLUMN_NAME_PACKAGE_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + PackageMaster.PackagesT.COLUMN_NAME_PACKAGE_NAME +
                        " TEXT, "
                        + PackageMaster.PackagesT.COLUMN_NAME_DESCRIPTION +
                        " TEXT" + ")";

        //defining the sql query
        db.execSQL(SQL_CREATE_PACKAGE_TABLE);//Execute the package table creation
        Log.d("workflow", "Package table created successfully");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("workflow", "DB Onupgrade method Called");
        db.execSQL("DROP TABLE IF EXISTS " + PackageMaster.PackagesT.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    // Package CRUD methods

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long addPackage(String name, String desc) //Parameters to be added to DB
    {
        Log.d("workflow", "DB addPackage method called");
        SQLiteDatabase db = getWritableDatabase();// get the data repository in writable mode

        ContentValues values = new ContentValues();  //create a new map of values, where column names the key
        values.put(PackageMaster.PackagesT.COLUMN_NAME_PACKAGE_NAME, name);
        values.put(PackageMaster.PackagesT.COLUMN_NAME_DESCRIPTION, desc);

        //Insert a new row and returning the primary key values of the new row
        long newRowID = db.insert(PackageMaster.PackagesT.TABLE_NAME, null, values);

        Log.d("workflow", "DB addPackage method call finished");

        return newRowID;
    }

    public List<String> getAllPackages() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + PackageMaster.PackagesT.TABLE_NAME
                + " ORDER BY " +
                PackageMaster.PackagesT.COLUMN_NAME_PACKAGE_ID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int updatePackage(String id, String name, String desc) { //define the attributes and parameters to be sent

        Log.d("workflow", "DB update package method called");

        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(PackageMaster.PackagesT.COLUMN_NAME_PACKAGE_NAME, name);
        values.put(PackageMaster.PackagesT.COLUMN_NAME_DESCRIPTION, desc);

        String selection = PackageMaster.PackagesT.COLUMN_NAME_PACKAGE_ID + " = ? ";
        String[] selectionArgs = {id};

        int count = db.update(PackageMaster.PackagesT.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return count;
    }


    public int deletePackage(String packageid) {
        Log.d("workflow", "DB delete package method called");

        SQLiteDatabase db = getReadableDatabase();
        String selection = PackageMaster.PackagesT.COLUMN_NAME_PACKAGE_ID + " = ? ";
        String[] selectionArgs = {packageid};

        int status = db.delete(PackageMaster.PackagesT.TABLE_NAME,   //table name
                selection,                         //where clause
                selectionArgs                      //selection clause
        );
        return status;
    }


    public Cursor readAllPackages() {
        Log.d("workflow", "DB readAllPackages method called");


        String query = "SELECT * From "
                + PackageMaster.PackagesT.TABLE_NAME;

        Log.d("workflow", query);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);

        }
        return cursor;
    }

}
