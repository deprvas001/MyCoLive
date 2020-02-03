package com.development.mycolive.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.views.activity.RoomDetail;
import com.development.mycolive.views.activity.RoomateDetails;
import com.development.mycolive.views.model.PropertiesFeatures;

import java.util.List;

public class PropertiesAdapter extends RecyclerView.Adapter<PropertiesAdapter.MyViewHolder>  {
    private List<PropertiesFeatures> propertiesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.address);
            imageView = (ImageView)view.findViewById(R.id.image);
        }
    }


    public PropertiesAdapter(Context context,List<PropertiesFeatures> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public PropertiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.properties_layout, parent, false);

        return new PropertiesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropertiesAdapter.MyViewHolder holder, int position) {
        PropertiesFeatures find_room = propertiesList.get(position);

        if(position ==1){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.image1));
        }else if(position ==2){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.image2));
        }else if(position ==3){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.image3));
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, RoomDetail.class));
            }
        });
       /* holder.title.setText(find_room.getType());*/
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }
}
