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
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.views.activity.RoomateDetails;
import com.development.mycolive.model.FindRoomateModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FindRoomateAdapter extends RecyclerView.Adapter<FindRoomateAdapter.MyViewHolder> {

    private List<RoomateData> roomateModelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,age,resident,city,university,address;
        public ImageView roomate_image,fav_icon;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.cardView);
            name = (TextView)view.findViewById(R.id.name);
            age = (TextView)view.findViewById(R.id.age);
            resident = (TextView)view.findViewById(R.id.resident);
            city = (TextView)view.findViewById(R.id.host_city);
            university = (TextView)view.findViewById(R.id.university);
            roomate_image = (ImageView)view.findViewById(R.id.imageView);
            address = (TextView)view.findViewById(R.id.address);
            fav_icon = (ImageView)view.findViewById(R.id.favourite_icon);



          /*  title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);*/
        }
    }


    public FindRoomateAdapter(Context context,List<RoomateData> roomateModelList) {
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
        RoomateData roomateModel = roomateModelList.get(position);
        holder.name.setText(roomateModel.getName());
        holder.age.setText(roomateModel.getAge());
        holder.address.setText(roomateModel.getAddress());
        holder.city.setText(roomateModel.getCity_name());
        holder.university.setText(roomateModel.getUniversity_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RoomateDetails.class));
            }
        });

        Picasso.get()
                .load(roomateModel.getProfile_image())
                // .placeholder(R.drawable.image1)
                //   .error(R.drawable.err)
                .into(holder.roomate_image);

    }

    @Override
    public int getItemCount() {
        return roomateModelList.size();
    }
}
