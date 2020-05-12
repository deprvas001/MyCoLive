package com.development.mycolive.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomePropertyArea;
import com.development.mycolive.views.activity.searchResult.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AreaPropertyListAdapter extends  RecyclerView.Adapter<AreaPropertyListAdapter.MyViewHolder> {
    private List<HomePropertyArea> propertiesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price,address,name,rating,no_bathRoom,no_Room;
        public ImageView imageView;
        public LinearLayout viewLayout;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image);
            price = (TextView)view.findViewById(R.id.price);
            address = (TextView)view.findViewById(R.id.address);
            name = (TextView)view.findViewById(R.id.type);
            no_bathRoom = view.findViewById(R.id.no_of_bathroom);
            no_Room = view.findViewById(R.id.no_of_room);

        }
    }


    public AreaPropertyListAdapter(Context context,List<HomePropertyArea> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public AreaPropertyListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_layout, parent, false);

        return new AreaPropertyListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AreaPropertyListAdapter.MyViewHolder holder, int position) {
        HomePropertyArea  featureProperty = propertiesList.get(position);
        holder.address.setText(featureProperty.getCount()+" "+"Properties");
        holder.price.setText("View");
        holder.name.setText(featureProperty.getCity());
        holder.no_Room.setVisibility(View.INVISIBLE);
        holder.no_bathRoom.setVisibility(View.INVISIBLE);

        holder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, SearchResult.class);
                intent.putExtra("type","PROPERTYBYAREA");
                intent.putExtra("post_code",featureProperty.getPost_code());
                context.startActivity(intent);
            }
        });
        Picasso.get()
                .load(featureProperty.getImage())
                .placeholder(R.drawable.no_image_found)
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }
}
