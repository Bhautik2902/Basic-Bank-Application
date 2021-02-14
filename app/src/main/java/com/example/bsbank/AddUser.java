package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {

    EditText firstname, lastname, mobile_no, email, account_no, ifs_code, balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_layout);

        firstname = (EditText) findViewById(R.id.first_name);
        lastname = (EditText) findViewById(R.id.last_name);
        mobile_no = (EditText) findViewById(R.id.contact_no);
        email = (EditText) findViewById(R.id.email);
        account_no = (EditText) findViewById(R.id.Account_no);
        ifs_code = (EditText) findViewById(R.id.IFS_code);
        balance = (EditText) findViewById(R.id.current_amt);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("fact");
    }
}