package com.example.bsbank;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList first_name, last_name, contact_no, amount;

    CustomAdapter(Context context, ArrayList first_name, ArrayList last_name, ArrayList contact_no,  ArrayList amount) {
        this.context = context;
        this.first_name =first_name;
        this.last_name = last_name;
        this.contact_no = contact_no;
        this.amount = amount;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.record_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String fname = String.valueOf(first_name.get(position));
        String lname = String.valueOf(last_name.get(position));

        holder.user_name.setText(fname + " " + lname);
        holder.contact_no.setText(String.valueOf(contact_no.get(position)));
        holder.balance.setText(String.valueOf(amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return contact_no.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_name, contact_no, balance;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            contact_no = itemView.findViewById(R.id.mobile_no);
            balance = itemView.findViewById(R.id.amount);
        }
    }
}
