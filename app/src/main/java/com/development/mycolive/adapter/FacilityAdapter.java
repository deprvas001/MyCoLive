package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyImageSlider;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FacilityAdapter  extends RecyclerView.Adapter<FacilityAdapter.MyViewHolder> {

    private List<FacilityData> detailList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price,name,type,city,university,comment,date,like,comment_count;
        public ImageView imageView,user_image,post_image;
        public LinearLayout postLayout;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.facility_icon);
            name = (TextView)view.findViewById(R.id.facility_name);

        }
    }

    public FacilityAdapter(Context context,List<FacilityData> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public FacilityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.facility_feature_layout, parent, false);
        group = parent;

        return new FacilityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FacilityAdapter.MyViewHolder holder, int position) {
        FacilityData facilityData = detailList.get(position);

        holder.name.setText(facilityData.getFacilityString());
        Picasso.get()
                .load(facilityData.getIcon_url())
                .placeholder(R.drawable.no_image_found)
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
    
}
