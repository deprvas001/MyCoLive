package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchScreenAdapter extends RecyclerView.Adapter<SearchScreenAdapter.MyViewHolder> {
private List<HomeFeatureProperty> searchList;
private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title,adddress,noOfBathroom,noOfRoom,price;
    public ImageView imageView;

    public MyViewHolder(View view) {
        super(view);
        imageView = (ImageView)view.findViewById(R.id.image);
        title = (TextView)view.findViewById(R.id.type);
        adddress = (TextView)view.findViewById(R.id.address);
        noOfBathroom = (TextView)view.findViewById(R.id.no_of_bathroom);
        noOfRoom = (TextView)view.findViewById(R.id.no_of_room);
        price = (TextView)view.findViewById(R.id.price);
    }
}
    public SearchScreenAdapter(Context context, List<HomeFeatureProperty> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @Override
    public SearchScreenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_layout, parent, false);

        return new SearchScreenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchScreenAdapter.MyViewHolder holder, int position) {
        HomeFeatureProperty search_room = searchList.get(position);
        holder.title.setText(search_room.getPropertyName());
        holder.adddress.setText(search_room.getAddress());
        holder.noOfBathroom.setText(search_room.getNoOfBathroom());
        holder.noOfRoom.setText(search_room.getNumberOfRoom());
        holder.price.setText("$ "+search_room.getPrice());

        Picasso.get()
                .load(search_room.getImage())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);
        /* holder.title.setText(find_room.getType());*/
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
