package com.example.salesenquiry.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDB extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public LoginDB(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT, Fullname TEXT,Username TEXT,Password TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP Table if exists users");
    }

    public boolean insertData(String Fullname,String Username,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("FULLNAME",Fullname);
        values.put("USERNAME",Username);
        values.put("PASSWORD",Password);
        long result=db.insert("users",null,values);
        if (result==-1) return false;
        else  return true;
    }
    public boolean checkUsername(String Username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select  * from users where Username=?",new String[]{Username});
        if (cursor.getCount() >0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassword(String Username,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where Username=? and Password=?",new String[]{Username,Password});
        if (cursor.getCount()>0)return true;
        else  return false;
        }

        public boolean changepassword(String Username,String Password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Password",Password);
        long result=db.update("users",values,"Username=?",new String[]{Username});
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
        }

    public Cursor FetchLoginData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String Query="Select * from users";
        Cursor cursor=db.rawQuery(Query,null);
        return cursor;
    }
    }
