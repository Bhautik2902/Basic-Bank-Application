package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Transaction_record extends AppCompatActivity {

    RecyclerView recycleView;
    DBHelper dbHelper;
    ArrayList<String> sender_name, rcvr_name, date_time, sender_accno, rcvr_accno;
    ArrayList<Integer> amount;
    CustomAdapter_passbook customAdapter_passbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);

        recycleView = findViewById(R.id.transaction_recycleView);
        dbHelper = new DBHelper(this);
        sender_name = new ArrayList<>();
        rcvr_name = new ArrayList<>();
        sender_accno = new ArrayList<>();
        rcvr_accno = new ArrayList<>();
        date_time = new ArrayList<>();
        amount = new ArrayList<>();

        storeDataInArray();
        customAdapter_passbook = new CustomAdapter_passbook(this, sender_name, rcvr_name, sender_accno, rcvr_accno, date_time, amount);
        recycleView.setAdapter(customAdapter_passbook);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void storeDataInArray() {
        Cursor cursor = dbHelper.getAllData("Passbook");
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                sender_name.add(cursor.getString(0));
                rcvr_name.add(cursor.getString(1));
                sender_accno.add(cursor.getString(2));
                rcvr_accno.add(cursor.getString(3));
                date_time.add(cursor.getString(4));
                amount.add(cursor.getInt(5));

            }
        }
    }
}