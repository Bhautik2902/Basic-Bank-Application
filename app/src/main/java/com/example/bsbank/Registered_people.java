package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Registered_people extends AppCompatActivity {

    RecyclerView recycleView;
    DBHelper dbHelper;
    ArrayList<String> first_name, last_name, mobile_no, email, acc_no, ifs;
    ArrayList<Integer> balance;
    CustomAdapter customAdapter;
    private CustomAdapter.RecyclerViewClickListener listener;


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
        setOnClickListener();
        customAdapter = new CustomAdapter(this, first_name, last_name, mobile_no, balance, listener);
        recycleView.setAdapter(customAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setOnClickListener() {
        listener = (v, position) -> {
            try {
                Intent intent = new Intent(getApplicationContext(), CustomerProfile.class);
                // find account no.
                String account_no = acc_no.get(position);
                String ifsc = ifs.get(position);

                Cursor cursor = dbHelper.getSpecificData(account_no, ifsc);
                cursor.moveToNext();
                intent.putExtra("Firstname", cursor.getString(0));
                intent.putExtra("Lastname", cursor.getString(1));
                intent.putExtra("Email", cursor.getString(2));
                intent.putExtra("Contact No", cursor.getString(3));
                intent.putExtra("Account No", cursor.getString(4));
                intent.putExtra("IFSC", cursor.getString(5));
                intent.putExtra("Balance", cursor.getInt(6));

                startActivity(intent);
            }
            catch (Exception e) {
                Log.d(TAG, "onClick: problem in fetching" + e);
            }
        };
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