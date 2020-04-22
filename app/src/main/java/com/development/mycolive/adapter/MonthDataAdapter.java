package com.development.mycolive.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.bookingHistory.MonthHistory;
import com.development.mycolive.views.activity.paymentMode.PaymentMode;

import java.util.List;

public class MonthDataAdapter  extends RecyclerView.Adapter<MonthDataAdapter.MyViewHolder> implements View.OnClickListener {
    private List<MonthHistory> monthList;
    private Context context;
    private ViewGroup group;
    private AlertDialog alertDialog;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView month,status,payment_mode,receipt,approval;
        public ImageView imageView;
        public Button view_detail;

        public MyViewHolder(View view) {
            super(view);
            month = (TextView)view.findViewById(R.id.month_name);
            status = (TextView)view.findViewById(R.id.status);
            payment_mode = (TextView)view.findViewById(R.id.payment_mode);
            receipt = (TextView)view.findViewById(R.id.receipt);
            approval = (TextView)view.findViewById(R.id.approval);
            view_detail = (Button)view.findViewById(R.id.view_detail);

           // imageView = (ImageView)view.findViewById(R.id.alert);
        }
    }

    public MonthDataAdapter(Context context,List<MonthHistory> monthList) {
        this.context = context;
        this.monthList = monthList;
    }

    @Override
    public MonthDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_history_payment, parent, false);
        group = parent;

        return new MonthDataAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MonthDataAdapter.MyViewHolder holder, int position) {
        MonthHistory  monthHistory  = monthList.get(position);

        holder.month.setText(monthHistory.getMonthName());
        holder.status.setText(monthHistory.getStatus());
        holder.payment_mode.setText(monthHistory.getPayment_method());
        holder.receipt.setText(monthHistory.getReceipt());
        holder.approval.setText(monthHistory.getApproval());
        holder.view_detail.setOnClickListener(view ->
                showCustomDialog(context,monthHistory));
    }

    @Override
    public int getItemCount() {
        return monthList.size();
    }

    private void showCustomDialog(Context context,MonthHistory  monthHistory){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        //  ViewGroup viewGroup = context.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, group, false);

        TextView payment_id = (TextView)dialogView.findViewById(R.id.payment_id);
        payment_id.setText(": "+monthHistory.getPayment_id());

        TextView payment_status = (TextView)dialogView.findViewById(R.id.payment_status);
        payment_status.setText(": "+monthHistory.getStatus());

        TextView month = (TextView)dialogView.findViewById(R.id.month);
        month.setText(": "+monthHistory.getMonthName());

        ImageView close = (ImageView)dialogView.findViewById(R.id.close);

        TextView amount = (TextView)dialogView.findViewById(R.id.amount);
        amount.setText(": €"+monthHistory.getPayment());

        TextView approval = (TextView)dialogView.findViewById(R.id.approval);
        approval.setText(": "+monthHistory.getApproval());

        Button ok = (Button)dialogView.findViewById(R.id.buttonOk);

        if(monthHistory.getPayment().equals("1")){
            ok.setText("Pay Now");
        }else {
            ok.setText("Ok");
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        alertDialog = builder.create();
        alertDialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(monthHistory.getPayment().equals("1")){

                   Intent intent = new Intent(context, PaymentMode.class);
                   intent.putExtra("month_id",monthHistory.getDues_month_id());
                   intent.putExtra("due_amount",monthHistory.getDues_amount());
                   context.startActivity(intent);
               }
               else{
               }
            }
        });

       close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     alertDialog.dismiss();
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonOk:
                alertDialog.dismiss();
                break;
        }
    }
}
