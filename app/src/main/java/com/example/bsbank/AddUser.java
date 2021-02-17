package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {

    EditText firstname, lastname, mobile_no, email, account_no, ifs_code, balance;
    Button submit_btn;
    DBHelper dbHelper;
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
        submit_btn = (Button) findViewById(R.id.submit_btn);

        dbHelper = new DBHelper(this);
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname_txt="", lastname_txt="", mobile_no_txt="", email_txt="", account_no_txt="", ifs_code_txt="";
                int balance_amt=-1;
                if (TextUtils.isEmpty(firstname.getText())) {
                    firstname.setError("This field is required!");
                }
                else {  firstname_txt = firstname.getText().toString(); }

                if (TextUtils.isEmpty(lastname.getText())) {
                    lastname.setError("This field is required!");
                }
                else { lastname_txt = lastname.getText().toString(); }

                if (TextUtils.isEmpty(mobile_no.getText())) {
                    mobile_no.setError("This field is required!");
                }
                else { mobile_no_txt = mobile_no.getText().toString(); }

                if (TextUtils.isEmpty(email.getText())) {
                    mobile_no.setError("This field is required!");
                }
                else { email_txt = email.getText().toString();   }

                if (TextUtils.isEmpty(account_no.getText())) {
                    account_no.setError("This field is required!");
                }
                else { account_no_txt = account_no.getText().toString(); }

                if (TextUtils.isEmpty(ifs_code.getText())) {
                    ifs_code.setError("This field is required!");
                }
                else { ifs_code_txt = ifs_code.getText().toString(); }

                if (TextUtils.isEmpty(balance.getText())) {
                    balance.setError("This field is required!");
                }
                else { balance_amt = Integer.parseInt(balance.getText().toString()); }

                if (firstname_txt != "" && lastname_txt != "" && mobile_no_txt != "" && email_txt != "" && account_no_txt != "" && ifs_code_txt != "" && balance_amt > 0 ) {
                    Boolean checkforinsert = dbHelper.insertData(firstname_txt, lastname_txt, mobile_no_txt, email_txt, account_no_txt, ifs_code_txt, balance_amt);
                    if (checkforinsert == true) {
                        Toast.makeText(AddUser.this, "New Contact added", Toast.LENGTH_LONG).show();

                        // to reset text field after successful submission.
                        firstname.setText("");
                        lastname.setText("");
                        mobile_no.setText("");
                        email.setText("");
                        account_no.setText("");
                        ifs_code.setText("");
                        balance.setText("");
                    } else {
                        Toast.makeText(AddUser.this, "Something went wrong. Try again", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(AddUser.this, "Fill all fields", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}