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
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.home.HomeHotProperty;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.development.mycolive.views.activity.searchDetailPage.RoomDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HotPropertyAdapter extends RecyclerView.Adapter<HotPropertyAdapter.MyViewHolder>  {

    private List<HomeFeatureProperty> propertiesList;
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


    public HotPropertyAdapter(Context context,List<HomeFeatureProperty> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public HotPropertyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.properties_layout, parent, false);

        return new HotPropertyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HotPropertyAdapter.MyViewHolder holder, int position) {
        HomeFeatureProperty  featureProperty = propertiesList.get(position);
        List<HomeSlider> homeSliderList = featureProperty.getImage_slider();
        holder.price.setText("€ "+featureProperty.getPrice());
        holder.address.setText(featureProperty.getAddress());
        holder.name.setText(featureProperty.getName());
        holder.rating.setVisibility(View.GONE);

        holder.viewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PropertyDetail.class);
                intent.putExtra("Property_Id",featureProperty.getId());
                context.startActivity(intent);
                //  context.startActivity(new Intent(context, RoomDetail.class));
            }
        });

        Picasso.get()
                .load(homeSliderList.get(0).getImage())
                .placeholder(R.drawable.no_image_available)
                .fit()
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }
}
