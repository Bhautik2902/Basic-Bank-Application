package com.example.bsbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter_passbook extends RecyclerView.Adapter<CustomAdapter_passbook.MyViewHolder> {
    private Context context;
    private ArrayList sender_name, rcvr_name, sender_accno, rcvr_accno, date_time, amount;

    CustomAdapter_passbook(Context context, ArrayList sender_name, ArrayList rcvr_name, ArrayList sender_accno, ArrayList rcvr_accno, ArrayList date_time, ArrayList amount) {
        this.context = context;
        this.sender_name = sender_name;
        this.rcvr_name = rcvr_name;
        this.sender_accno = sender_accno;
        this.rcvr_accno = rcvr_accno;
        this.date_time = date_time;
        this.amount = amount;
    }

    @NonNull
    @Override
    public CustomAdapter_passbook.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.passbook_record_row, parent, false);

        return new CustomAdapter_passbook.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter_passbook.MyViewHolder holder, int position) {

        holder.sendername.setText(String.valueOf(sender_name.get(position)));
        holder.rcvrname.setText(String.valueOf(rcvr_name.get(position)));
        holder.senderaccno.setText(String.valueOf(sender_accno.get(position)));
        holder.rcvraccno.setText(String.valueOf(rcvr_accno.get(position)));
        holder.dt.setText(String.valueOf(date_time.get(position)));
        holder.amt.setText(String.valueOf(amount.get(position)));
    }

    @Override
    public int getItemCount() {
        return sender_name.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sendername, rcvrname, senderaccno, rcvraccno, dt, amt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sendername = itemView.findViewById(R.id.sender_name);
            rcvrname = itemView.findViewById(R.id.rcvr_name);
            senderaccno = itemView.findViewById(R.id.sender_acc_no);
            rcvraccno = itemView.findViewById(R.id.rcvr_acc_no);
            dt = itemView.findViewById(R.id.date_time);
            amt = itemView.findViewById(R.id.transfered_amt);
        }
    }
}
