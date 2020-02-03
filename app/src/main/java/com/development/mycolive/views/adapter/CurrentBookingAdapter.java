package com.development.mycolive.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.views.activity.CurrentBookingHistory;
import com.development.mycolive.views.model.CurrentBookingModel;

import java.util.List;

public class CurrentBookingAdapter extends RecyclerView.Adapter<CurrentBookingAdapter.MyViewHolder> {

    private List<CurrentBookingModel> bookingList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView viewDetail;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            viewDetail = (TextView) view.findViewById(R.id.view_details);
            /*title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);*/
        }
    }

    public CurrentBookingAdapter(Context context,List<CurrentBookingModel> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @Override
    public CurrentBookingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_item_booking, parent, false);

        return new CurrentBookingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CurrentBookingAdapter.MyViewHolder holder, int position) {
        CurrentBookingModel CurrentBookingModel = bookingList.get(position);

        holder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, CurrentBookingHistory.class));
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
}
