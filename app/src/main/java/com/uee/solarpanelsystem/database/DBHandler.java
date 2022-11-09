package com.sliit.solarpanelsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.uee.solarpanelsystem.blogs.Blog;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context) {
        super(context,"data.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table blog(ID INTEGER PRIMARY KEY AUTOINCREMENT,title text,blog text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addBlog(Blog blog){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("title",blog.getTitle());
        contentValues.put("blog",blog.getBlog());

        long res=db.insert("blog",null,contentValues);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    public boolean editBlog(Blog blog){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("title",blog.getTitle());
        contentValues.put("blog",blog.getBlog());

        long res=db.update("blog",contentValues,"ID="+blog.getId(),null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    public boolean deleteBlog(int ID){

        SQLiteDatabase db = this.getWritableDatabase();

        long res=db.delete("blog","ID="+ID,null);

        if(res==-1){
            return false;
        }else{
            return true;
        }

    }

    public ArrayList<Blog> readAllBlog(){

        ArrayList<Blog> blog= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery("select * from blog",null);
        results.moveToFirst();

        while (results.isAfterLast()==false){

            Blog model= new Blog();

            model.setId(results.getInt(0));
            model.setTitle(results.getString(1));
            model.setBlog(results.getString(2));
            blog.add(model);
            results.moveToNext();

        }

        return blog;
    }

}
