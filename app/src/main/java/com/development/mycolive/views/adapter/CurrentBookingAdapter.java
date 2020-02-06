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
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.views.activity.bookingHistory.CurrentBookingHistory;
import com.development.mycolive.views.model.booking.BookingData;

import java.util.List;

public class CurrentBookingAdapter extends RecyclerView.Adapter<CurrentBookingAdapter.MyViewHolder> {

    private List<BookingData> bookingList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView viewDetail,srNo,bookingFor,amount,bookingDate;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            viewDetail = (TextView) view.findViewById(R.id.view_details);
            srNo = (TextView)view.findViewById(R.id.sr_value);
            bookingFor = (TextView)view.findViewById(R.id.booking_for_value);
            amount = (TextView)view.findViewById(R.id.amount_value);
            bookingDate = (TextView)view.findViewById(R.id.booking_value);

            /*title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);*/
        }
    }

    public CurrentBookingAdapter(Context context,List<BookingData> bookingList) {
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
        BookingData bookingData = bookingList.get(position);
        holder.srNo.setText(String.valueOf(bookingData.getSrNo()));
        holder.bookingDate.setText(bookingData.getBookingDate());
        holder.amount.setText(bookingData.getAmount());
        holder.bookingFor.setText(bookingData.getBookingFor());

        holder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CurrentBookingHistory.class);
                intent.putExtra(ApiConstant.BOOKING_TYPE,"CURRENTBOOKING");
                intent.putExtra(ApiConstant.ORDER_ID,bookingData.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }
}
