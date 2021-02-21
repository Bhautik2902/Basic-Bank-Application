package com.example.bsbank;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private ArrayList first_name, last_name, contact_no, amount;
    private RecyclerViewClickListener listener;

    CustomAdapter(Context context, ArrayList first_name, ArrayList last_name, ArrayList contact_no,  ArrayList amount, RecyclerViewClickListener listener) {
        this.context = context;
        this.first_name =first_name;
        this.last_name = last_name;
        this.contact_no = contact_no;
        this.amount = amount;
        this.listener = listener;
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView user_name, contact_no, balance;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_name);
            contact_no = itemView.findViewById(R.id.mobile_no);
            balance = itemView.findViewById(R.id.amount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try {
                listener.onClick(view, getAdapterPosition());
            }
            catch (Exception e){
                Log.d(TAG, "onClick: listener is empty!" + e);
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
