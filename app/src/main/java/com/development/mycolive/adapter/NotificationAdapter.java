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
import com.development.mycolive.views.activity.NotificationDetails;
import com.development.mycolive.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

private List<NotificationModel> notificationList;
private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView imageView;
    public CardView cardView;

    public MyViewHolder(View view) {
        super(view);
            /*title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);*/
            cardView = (CardView)view.findViewById(R.id.cardView);
    }
}

    public NotificationAdapter(Context context, List<NotificationModel> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item_row, parent, false);

        return new NotificationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.MyViewHolder holder, int position) {
        NotificationModel NotificationModel = notificationList.get(position);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NotificationDetails.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
