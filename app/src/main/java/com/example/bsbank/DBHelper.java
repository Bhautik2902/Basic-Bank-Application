package com.example.bsbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public DBHelper(Context context) {
        super(context, "Userdetails.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(First_Name TEXT, Last_Name TEXT, Mobile_No TEXT UNIQUE, Email TEXT UNIQUE, Account_No TEXT primary key, IFSC TEXT, Current_balance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Userdetails");
        onCreate(db);
    }

    public boolean insertData(String first_name, String last_name, String mobile_no, String email, String account_no, String ifsc, int balance) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("First_Name", first_name);
        contentValues.put("Last_Name", last_name);
        contentValues.put("Mobile_No", mobile_no);
        contentValues.put("Email", email);
        contentValues.put("Account_No", account_no);
        contentValues.put("IFSC", ifsc);
        contentValues.put("Current_balance", balance);

        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor data=null;
        if (DB != null) {
            data = DB.rawQuery("Select * from Userdetails", null);
        }
        return data;
    }
}
