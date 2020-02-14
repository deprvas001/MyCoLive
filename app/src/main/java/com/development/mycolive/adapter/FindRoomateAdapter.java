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
import com.development.mycolive.views.activity.RoomateDetails;
import com.development.mycolive.model.FindRoomateModel;

import java.util.List;

public class FindRoomateAdapter extends RecyclerView.Adapter<FindRoomateAdapter.MyViewHolder> {

    private List<FindRoomateModel> roomateModelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.cardView);
          /*  title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);*/
        }
    }


    public FindRoomateAdapter(Context context,List<FindRoomateModel> roomateModelList) {
        this.context = context;
        this.roomateModelList = roomateModelList;
    }

    @Override
    public FindRoomateAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.find_roomate_item, parent, false);

        return new FindRoomateAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindRoomateAdapter.MyViewHolder holder, int position) {
        FindRoomateModel roomateModel = roomateModelList.get(position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RoomateDetails.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return roomateModelList.size();
    }
}
