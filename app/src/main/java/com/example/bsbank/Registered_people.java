package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Registered_people extends AppCompatActivity {

    RecyclerView recycleView;
    DBHelper dbHelper;
    ArrayList<String> first_name, last_name, mobile_no, email, acc_no, ifs;
    ArrayList<Integer> balance;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registered_people_layout);

        recycleView = findViewById(R.id.recycleView);
        dbHelper = new DBHelper(this);
        first_name = new ArrayList<>();
        last_name = new ArrayList<>();
        mobile_no = new ArrayList<>();
        email = new ArrayList<>();
        acc_no = new ArrayList<>();
        ifs = new ArrayList<>();
        balance = new ArrayList<>();

        storeDataInArray();
        customAdapter = new CustomAdapter(this, first_name, last_name, mobile_no, balance);
        recycleView.setAdapter(customAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void storeDataInArray() {
        Cursor cursor = dbHelper.getAllData("Userdetails");
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                first_name.add(cursor.getString(0));
                last_name.add(cursor.getString(1));
                mobile_no.add(cursor.getString(2));
                email.add(cursor.getString(3));
                acc_no.add(cursor.getString(4));
                ifs.add(cursor.getString(5));
                balance.add(cursor.getInt(6));
            }
        }
    }
}