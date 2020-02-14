package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomePropertyArea;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AreaPropertyAdapter extends RecyclerView.Adapter<AreaPropertyAdapter.MyViewHolder> {

    private List<HomePropertyArea> propertiesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price,address,name,rating,created_date;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.address);
            imageView = (ImageView)view.findViewById(R.id.image);
            price = (TextView)view.findViewById(R.id.price);
            address = (TextView)view.findViewById(R.id.address);
            name = (TextView)view.findViewById(R.id.type);
            rating = (TextView)view.findViewById(R.id.rating);
            created_date = (TextView)view.findViewById(R.id.created_date);

        }
    }


    public AreaPropertyAdapter(Context context,List<HomePropertyArea> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public AreaPropertyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.properties_layout, parent, false);

        return new AreaPropertyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AreaPropertyAdapter.MyViewHolder holder, int position) {
        HomePropertyArea  featureProperty = propertiesList.get(position);
      //  holder.price.setText("$"+featureProperty.getPrice());
        holder.address.setText(featureProperty.getAddress());
      //  holder.name.setText(featureProperty.getPropertyName());
        holder.rating.setVisibility(View.GONE);
    /*    holder.rating.setText(featureProperty.getRating());
        holder.created_date.setText(featureProperty.getCreatedDate());*/
        Picasso.get()
                .load(featureProperty.getImage())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);
/*
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RoomDetail.class));
            }
        });*/
        /* holder.title.setText(find_room.getType());*/
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }
}
