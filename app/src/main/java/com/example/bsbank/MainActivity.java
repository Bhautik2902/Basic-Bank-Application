package com.example.bsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton profile_btn, registered, transaction, add_user;
    Button makeTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_btn = (ImageButton) findViewById(R.id.profile_imgbtn);
        registered = (ImageButton) findViewById(R.id.registered_imgbtn);
        transaction = (ImageButton) findViewById(R.id.transaction_imgbtn);
        add_user = (ImageButton) findViewById(R.id.adduser_imgbtn);
        makeTransaction = (Button) findViewById(R.id.maketransaction_imgbtn);

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_profile = new Intent(MainActivity.this, Profile_page.class);
                startActivity(intent_profile);
            }
        });

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_registered = new Intent(MainActivity.this, Registered_people.class);
                startActivity(intent_registered);
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_transaction = new Intent(MainActivity.this, Transaction_record.class);
                startActivity(intent_transaction);
            }
        });

        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_adduser = new Intent(MainActivity.this, AddUser.class);
                startActivity(intent_adduser);
            }
        });

        makeTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_makeTransaction = new Intent(MainActivity.this, MakeTransaction.class);
                startActivity(intent_makeTransaction);
            }
        });

    }
}