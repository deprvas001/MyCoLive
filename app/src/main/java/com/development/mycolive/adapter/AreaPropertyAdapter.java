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
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.development.mycolive.views.activity.searchDetailPage.RoomDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AreaPropertyAdapter extends RecyclerView.Adapter<AreaPropertyAdapter.MyViewHolder> {

    private List<HomePropertyArea> propertiesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,price,address,name,rating,created_date;
        public ImageView imageView;
        public LinearLayout viewLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.address);
            imageView = (ImageView)view.findViewById(R.id.image);
            price = (TextView)view.findViewById(R.id.price);
            address = (TextView)view.findViewById(R.id.address);
            name = (TextView)view.findViewById(R.id.type);
            rating = (TextView)view.findViewById(R.id.rating);
            created_date = (TextView)view.findViewById(R.id.created_date);
            viewLayout = (LinearLayout)view.findViewById(R.id.property_view);

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
        holder.address.setText(featureProperty.getCount()+" "+featureProperty.getCity());
        holder.price.setVisibility(View.GONE);
        holder.name.setVisibility(View.GONE);
        holder.rating.setVisibility(View.GONE);

        holder.viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PropertyDetail.class);
                intent.putExtra("Property_Id",featureProperty.getPost_code());
                context.startActivity(intent);
            }
        });
        Picasso.get()
                .load(featureProperty.getImage())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }
}
