package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerProfile extends AppCompatActivity {
    TextView fullname, email, mobileno, accno, ifs_code, balance;
    String Fullname="", Email="", Mobileno="", Accno="", Ifs_code="";
    int curr_balance=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        fullname = findViewById(R.id.full_name);
        email = findViewById(R.id.show_email);
        mobileno = findViewById(R.id.show_mobileno);
        accno = findViewById(R.id.show_accno);
        ifs_code = findViewById(R.id.show_ifsc);
        balance = findViewById(R.id.show_Balance);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Fullname = extras.getString("Firstname") + " " + extras.getString("Lastname");
            Email = extras.getString("Email");
            Mobileno = extras.getString("Contact No");
            Accno = extras.getString("Account No");
            Ifs_code = extras.getString("IFSC");
            curr_balance = extras.getInt("Balance");

            fullname.setText(Fullname);
            email.setText(Email);
            mobileno.setText(Mobileno);
            accno.setText(Accno);
            ifs_code.setText(Ifs_code);
            balance.setText(String.valueOf(curr_balance) + " INR");

        }
        else {
            Toast.makeText(this, "Data couldn't fetched!", Toast.LENGTH_SHORT).show();
        }
    }
}