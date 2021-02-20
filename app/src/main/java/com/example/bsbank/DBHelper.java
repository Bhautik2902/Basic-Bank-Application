package com.example.bsbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    public DBHelper(Context context) {
        super(context, "Userdetails.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(First_Name TEXT, Last_Name TEXT, Mobile_No TEXT UNIQUE, Email TEXT UNIQUE, Account_No TEXT primary key UNIQUE, IFSC TEXT, Current_balance INTEGER)");
        db.execSQL("create Table Passbook(SenderName TEXT, ReceiverName TEXT, Sender_accno TEXT, Receiver_accno TEXT, Dt datetime DEFAULT CURRENT_TIMESTAMP, Amount INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists Userdetails");
        db.execSQL("drop table if exists Passbook");
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

    public boolean insertDataInPassbook(String sender_name, String rcvr_name, String sender_accno, String rcvr_accno, String date_time, int amount) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SenderName", sender_name);
        contentValues.put("ReceiverName", rcvr_name);
        contentValues.put("Sender_accno", sender_accno);
        contentValues.put("Receiver_accno", rcvr_accno);
        contentValues.put("Dt", date_time);
        contentValues.put("Amount",amount);

        long result = DB.insert("Passbook", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData(String table_name) {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor data=null;
        if (DB != null) {
            data = DB.rawQuery("Select * from " + table_name, null);
        }
        return data;
    }

    public Cursor getSpecificData(String acc_no, String ifsc) {
        SQLiteDatabase db = this.getWritableDatabase();

        String qry = "SELECT * FROM Userdetails WHERE Account_no=" + acc_no;
        Cursor crs = db.rawQuery(qry, null);
        return crs;
    }

    public boolean updateBalance(Context context, String sender_acc_no, String rcvr_acc_no, int tra_amt) {
        boolean flag = false;
        int current_bal=0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor crs = db.rawQuery("SELECT * FROM Userdetails WHERE Account_no=" + sender_acc_no, null);
        if (crs.getCount() == 0) {
            Toast.makeText(context, "Couldn't Update!",Toast.LENGTH_SHORT).show();
        }
        else {
            while(crs.moveToNext()) {
                current_bal = crs.getInt(6);
            }
        }

        if (current_bal >= tra_amt) {
            String sender_qry = "UPDATE Userdetails SET Current_balance = Current_balance - " + tra_amt + " WHERE account_no=" + sender_acc_no;
            db.execSQL(sender_qry);
            flag = true;
        }

        String rcvr_qry = "UPDATE Userdetails SET Current_balance = Current_balance + "+ tra_amt +" WHERE account_no=" + rcvr_acc_no;
        db.execSQL(rcvr_qry);
        return flag;
    }


}
