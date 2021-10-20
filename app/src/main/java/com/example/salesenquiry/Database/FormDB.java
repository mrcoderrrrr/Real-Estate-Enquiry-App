package com.example.salesenquiry.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FormDB extends SQLiteOpenHelper {
    public static final String DB_NAME="Customer.db";

    public FormDB(@Nullable Context context) {
        super(context,DB_NAME,null,7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="Create table CustData(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FNAME TEXT,LNAME,LOCALITY TEXT,CITY TEXT,PINCODE INTEGER,TIME_TO_CALL TEXT,PHONE TEXT,ALTPHONE TEXT,EMAIL TEXT,"+
                "GENDER TEXT,STATUS TEXT,OCCUPATION TEXT,COMPANY_NAME TEXT,DESIGNATION TEXT,WORK_NATURE TEXT,BUSINESS_LOCATION TEXT,"+
                "CONFIG_ONE TEXT,CONFIG_TWO TEXT,CONFIG_THREE TEXT,CONFIG_OTHER TEXT,SPECIFY TEXT,BUDGET TEXT,LOAN TEXT,BANK_NAME TEXT,PURCHASE TEXT,RESIDENTAL TEXT," +
                "NEWSPAPER_ADV TEXT,NEWSPAPER_INSERT TEXT,HORDING TEXT,DIGITAL TEXT,TELECALLING TEXT,BROKER_FNAME TEXT,BROKER_LNAME TEXT,SOURCE_ADV TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP Table if exists CustData");
    }

    public Boolean InsertFormData(String Fname,String Lname, String Locality, String City, int Pincode, String Time_to_call, String Phone, String Altphone, String Email,
                                  String Gender,String Status, String Occupation, String Company_name, String Designation, String Work_nature, String Business_location,
                                  String Config_One,String Config_Two,String Config_Three,String Config_Other, String Specify, String Budget, String Loan, String Bankname, String Purchase, String Residental,
                                  String Newspaper_adv , String Newspaper_insert, String Hording, String Digital, String Telecalling, String Broker_Fname, String Broker_Lname,String Source_adv){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //persona details
        values.put("FNAME",Fname);
        values.put("LNAME",Lname);
        values.put("LOCALITY",Locality);
        values.put("CITY",City);
        values.put("PINCODE",Pincode);
        values.put("TIME_TO_CALL",Time_to_call);
        values.put("PHONE",Phone);
        values.put("ALTPHONE",Altphone);
        values.put("EMAIL",Email);
        //Personal Details 2
        values.put("GENDER",Gender);
        values.put("STATUS",Status);
        values.put("OCCUPATION",Occupation);
        values.put("COMPANY_NAME",Company_name);
        values.put("DESIGNATION",Designation);
        values.put("WORK_NATURE",Work_nature);
        values.put("BUSINESS_LOCATION",Business_location);
        //Need and Requirement
        values.put("CONFIG_ONE",Config_One);
        values.put("CONFIG_TWO",Config_Two);
        values.put("CONFIG_THREE",Config_Three);
        values.put("CONFIG_OTHER",Config_Other);
        values.put("SPECIFY",Specify);
        values.put("BUDGET",Budget);
        values.put("LOAN",Loan);
        values.put("BANK_NAME",Bankname);
        values.put("PURCHASE",Purchase);
        values.put("RESIDENTAL",Residental);
        //About project
        values.put("SOURCE_ADV",Source_adv);
        values.put("NEWSPAPER_ADV",Newspaper_adv);
        values.put("NEWSPAPER_INSERT",Newspaper_insert);
        values.put("HORDING",Hording);
        values.put("DIGITAL",Digital);
        values.put("TELECALLING",Telecalling);
        values.put("BROKER_FNAME",Broker_Fname);
        values.put("BROKER_FNAME",Broker_Lname);
        long result=db.insert("CustData",null,values);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor FetchCustData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="Select * from CustData";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public Boolean UpdateFormData(int id,String Fname,String Lname, String Locality, String City, int Pincode, String Time_to_call, String Phone, String Altphone, String Email,
                                  String Gender,String Status, String Occupation, String Company_name, String Designation, String Work_nature, String Business_location,
                                  String Config_One,String Config_Two,String Config_Three,String Config_Other, String Specify, String Budget, String Loan, String Bankname, String Purchase, String Residental,
                                  String Newspaper_adv , String Newspaper_insert, String Hording, String Digital, String Telecalling,String Broker_Fname, String Broker_Lname,String Source_adv){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //persona details
        values.put("FNAME",Fname);
        values.put("LNAME",Lname);
        values.put("LOCALITY",Locality);
        values.put("CITY",City);
        values.put("PINCODE",Pincode);
        values.put("TIME_TO_CALL",Time_to_call);
        values.put("PHONE",Phone);
        values.put("ALTPHONE",Altphone);
        values.put("EMAIL",Email);
        //Personal Details 2
        values.put("GENDER",Gender);
        values.put("STATUS",Status);
        values.put("OCCUPATION",Occupation);
        values.put("COMPANY_NAME",Company_name);
        values.put("DESIGNATION",Designation);
        values.put("WORK_NATURE",Work_nature);
        values.put("BUSINESS_LOCATION",Business_location);
        //Need and Requirement
        values.put("CONFIG_ONE",Config_One);
        values.put("CONFIG_TWO",Config_Two);
        values.put("CONFIG_THREE",Config_Three);
        values.put("CONFIG_OTHER",Config_Other);
        values.put("SPECIFY",Specify);
        values.put("BUDGET",Budget);
        values.put("LOAN",Loan);
        values.put("BANK_NAME",Bankname);
        values.put("PURCHASE",Purchase);
        values.put("RESIDENTAL",Residental);
        //About project
        values.put("SOURCE_ADV",Source_adv);
        values.put("NEWSPAPER_ADV",Newspaper_adv);
        values.put("NEWSPAPER_INSERT",Newspaper_insert);
        values.put("HORDING",Hording);
        values.put("DIGITAL",Digital);
        values.put("TELECALLING",Telecalling);
        values.put("BROKER_FNAME",Broker_Fname);
        values.put("BROKER_FNAME",Broker_Lname);
        //long result=db.update("CustData",values,"id=?",null);
        long result = db.update("CustData", values, "id = " + id  , null);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkId(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select  * from CustData where id=?",new String[]{String.valueOf(id)});
        if (cursor.getCount() >0)
            return true;
        else
            return false;
    }
}
