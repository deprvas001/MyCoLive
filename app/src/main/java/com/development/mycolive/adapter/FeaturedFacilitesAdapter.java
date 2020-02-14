package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.FeaturedProperty;

import java.util.List;

public class FeaturedFacilitesAdapter extends RecyclerView.Adapter<FeaturedFacilitesAdapter.MyViewHolder> {

    private List<FeaturedProperty> propertiesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);
        }
    }


    public FeaturedFacilitesAdapter(Context context,List<FeaturedProperty> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public FeaturedFacilitesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.featured_facilities_row, parent, false);

        return new FeaturedFacilitesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FeaturedFacilitesAdapter.MyViewHolder holder, int position) {
        FeaturedProperty featuredProperty = propertiesList.get(position);
        holder.title.setText(featuredProperty.getName());

        if(position == 0){
            holder.title.setText("Bath Room");
            //holder.imageView.setImageDrawable(context.getDrawable(R.drawable.images));
        }else if(position == 1){
            holder.title.setText("Living Room");
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.bed_icon));
        }else if(position == 2){
            holder.title.setText("Beddings");
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.bed_icon));
        }else if(position == 4){
            holder.title.setText("Wifi");
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.bed_icon));
        }
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }
}
