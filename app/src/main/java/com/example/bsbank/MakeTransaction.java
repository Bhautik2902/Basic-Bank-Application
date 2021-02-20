package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeTransaction extends AppCompatActivity {

    EditText acc_no_sender, acc_no_rcvr, ifsc_sender, ifsc_rcvr, money;
    TextView sender_name, rcvr_name, available_amt;
    String sender_accNo=null, rcvr_accNo=null, sender_ifsc=null, rcvr_ifsc=null;
    String sender_fullname=null, rcvr_fullname=null;
    Button submit, pay;
    DBHelper dbHelper;
    Boolean isSubmitted=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_transaction_layout);

        acc_no_sender = findViewById(R.id.account_no_tra);
        ifsc_sender =  findViewById(R.id.ifsc_tra);
        acc_no_rcvr =  findViewById(R.id.account_no_tra_rcv);
        ifsc_rcvr =  findViewById(R.id.ifsc_tra_rcv);

        sender_name =  findViewById(R.id.show_username);
        rcvr_name =  findViewById(R.id.show_username_rcv);
        available_amt =  findViewById(R.id.show_amount);

        submit = findViewById(R.id.submit_btn);
        pay =  findViewById(R.id.pay_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbHelper = new DBHelper(MakeTransaction.this);

                    if (TextUtils.isEmpty(acc_no_sender.getText())) {
                        acc_no_sender.setError("This field is required!");
                    }
                    else { sender_accNo = acc_no_sender.getText().toString();   }

                    if (TextUtils.isEmpty(ifsc_sender.getText())) {
                        ifsc_sender.setError("This field is required!");
                    }
                    else { sender_ifsc = ifsc_sender.getText().toString();   }

                    if (TextUtils.isEmpty(acc_no_rcvr.getText())) {
                        acc_no_rcvr.setError("This field is required!");
                    }
                    else { rcvr_accNo = acc_no_rcvr.getText().toString();   }

                    if (TextUtils.isEmpty(ifsc_rcvr.getText())) {
                        ifsc_rcvr.setError("This field is required!");
                    }
                    else { rcvr_ifsc = ifsc_rcvr.getText().toString();   }


                    Cursor crsr1 = dbHelper.getSpecificData(sender_accNo, sender_ifsc);
//                    Cursor l_name = dbHelper.getSpecificData(sender_accNo, sender_ifsc, "Last_Name");
                    if (crsr1.getCount() == 0) {
                        Toast.makeText(MakeTransaction.this, "No record found for given sender details!",Toast.LENGTH_LONG).show();
                        sender_name.setText("");
                        available_amt.setText("");
                    }
                    else {
                        while(crsr1.moveToNext()) {
                            sender_fullname = crsr1.getString(0) + " " + crsr1.getString(1);
                            sender_name.setText(sender_fullname);
                            available_amt.setText(String.valueOf(crsr1.getInt(6)));
                        }
                    }

//                    Cursor amt = dbHelper.getSpecificData(sender_accNo, sender_ifsc);

                    Cursor crsr_rcvr = dbHelper.getSpecificData(rcvr_accNo, rcvr_ifsc);
                    if (crsr_rcvr.getCount() == 0) {
                        Toast.makeText(MakeTransaction.this, "No record found for given receiver details!",Toast.LENGTH_LONG).show();
                        rcvr_name.setText("");
                    }
                    else {
                        while(crsr_rcvr.moveToNext()) {
                            rcvr_fullname = crsr_rcvr.getString(0) + " " + crsr_rcvr.getString(1);
                            rcvr_name.setText(rcvr_fullname);
                        }
                    }
                    isSubmitted = true;
                }
                catch (Exception e) {
                    Toast.makeText(MakeTransaction.this, "The problem is:" + e, Toast.LENGTH_LONG).show();
                }
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSubmitted == false) {
                    Toast.makeText(MakeTransaction.this, "Please submit first!", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        money = findViewById(R.id.send_money);
                        DBHelper dbhelper = new DBHelper(MakeTransaction.this);
                        int update_money = 0;

                        if (TextUtils.isEmpty(money.getText())) {
                            money.setError("This field is required!");
                        } else {
                            update_money = Integer.parseInt(money.getText().toString());
                        }

                        boolean isPossible = dbhelper.updateBalance(MakeTransaction.this, sender_accNo, rcvr_accNo, update_money);
                        if (isPossible == true) {
                            Toast.makeText(MakeTransaction.this, "Transaction Successful", Toast.LENGTH_SHORT).show();

                            // add successful transaction in passbook
                            String timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                            boolean isTransactionInserted = dbhelper.insertDataInPassbook(sender_fullname, rcvr_fullname, sender_accNo, rcvr_accNo, timestamp, update_money);
                            if (isTransactionInserted == true) {
                                Toast.makeText(MakeTransaction.this, "Successfully added in passbook", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MakeTransaction.this, "Couldn't add in passbook", Toast.LENGTH_LONG).show();
                            }
                            acc_no_sender.setText("");    sender_name.setText("");
                            acc_no_rcvr.setText("");    rcvr_name.setText("");
                            ifsc_sender.setText("");    available_amt.setText("");
                            ifsc_rcvr.setText("");    money.setText("");

                        }
                        else {
                            Toast.makeText(MakeTransaction.this, "Insufficient balance!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(MakeTransaction.this, "problem" + e, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}