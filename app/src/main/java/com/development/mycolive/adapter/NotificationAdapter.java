package com.development.mycolive.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.model.notificationModel.BodyResponse;
import com.development.mycolive.model.notificationModel.NotificationData;
import com.development.mycolive.views.activity.NotificationDetails;
import com.development.mycolive.model.NotificationModel;
import com.development.mycolive.views.activity.bookingHistory.CurrentBookingHistory;
import com.development.mycolive.views.activity.favouriteRoomate.FavouriteRoomateDetail;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.development.mycolive.views.activity.viewCommunity.ViewCommunity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    List<NotificationData> notificationDataList ;
    private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title,short_description,date;
    public ImageView imageView;
    public CardView cardView;

    public MyViewHolder(View view) {
        super(view);
            title = (TextView) view.findViewById(R.id.title);
            short_description = (TextView)view.findViewById(R.id.shortDetails);
            imageView = (ImageView)view.findViewById(R.id.notification_image);
            date = (TextView)view.findViewById(R.id.date);
            cardView = (CardView)view.findViewById(R.id.cardView);
         //   imageView = (ImageView)view.findViewById(R.id.image);
//            cardView = (CardView)view.findViewById(R.id.cardView);
    }
}

    public NotificationAdapter(Context context, List<NotificationData> notificationDataList) {
        this.context = context;
        this.notificationDataList = notificationDataList;
    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item_row, parent, false);

        return new NotificationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.MyViewHolder holder, int position) {
        NotificationData notificationData = notificationDataList.get(position);
       BodyResponse notificationResponse = notificationData.getBody();
        holder.title.setText(notificationData.getBody().getTitle());
        holder.short_description.setText(notificationData.getBody().getShortDetail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(notificationResponse.getType().equalsIgnoreCase("community")){
                   Intent intent = new Intent(context, ViewCommunity.class);
                   intent.putExtra("Id",notificationResponse.getAccess_id());
                   context.startActivity(intent);
               }else if(notificationResponse.getType().equalsIgnoreCase("Share Room")){
                   Intent intent = new Intent(context, PropertyDetail.class);
                   intent.putExtra("Property_Id",notificationResponse.getAccess_id());
                   context.startActivity(intent);
               }else if(notificationResponse.getType().equalsIgnoreCase("Fav Roommate")){
                   Intent intent = new Intent(context, FavouriteRoomateDetail.class);
                   intent.putExtra(ApiConstant.ROOMMATAE_ID,notificationResponse.getAccess_id());
                   context.startActivity(intent);
               }else if(notificationResponse.getType().equalsIgnoreCase("Payment Reminder")){
                   Intent intent = new Intent(context, CurrentBookingHistory.class);
                   intent.putExtra(ApiConstant.BOOKING_TYPE,"CURRENTBOOKING");
                   intent.putExtra(ApiConstant.ORDER_ID,notificationResponse.getAccess_id());
                   context.startActivity(intent);
               }
            }
        });

        holder.date.setText(notificationData.getCreated_at());
        Picasso.get()
                .load(notificationData.getBody().getImage())
                // *//*  .placeholder(R.drawable.image1)
                //  .error(R.drawable.err)*//*
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }
}
