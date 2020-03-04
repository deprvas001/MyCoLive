package com.development.mycolive.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.views.activity.roomInformation.RoomInformation;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class PropertyDetailAdapter  extends RecyclerView.Adapter<PropertyDetailAdapter.MyViewHolder> {

    private List<PropertyRoomData> detailList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price,name,type,city,university,comment,date,facility_txt,more_facility,
                like,comment_count;
        public ImageView imageView,user_image,post_image,facility_image;
        public LinearLayout room_info;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.image);
            name = (TextView)view.findViewById(R.id.name);
            price = (TextView)view.findViewById(R.id.price);
            room_info = (LinearLayout)view.findViewById(R.id.room_info);
            facility_image = (ImageView)view.findViewById(R.id.facility_one);
            facility_txt = (TextView)view.findViewById(R.id.facility_txt);
            more_facility = (TextView) view.findViewById(R.id.more_facility);

        }
    }

    public PropertyDetailAdapter(Context context,List<PropertyRoomData> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public PropertyDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_category, parent, false);
        group = parent;

        return new PropertyDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropertyDetailAdapter.MyViewHolder holder, int position) {
        PropertyRoomData roomData = detailList.get(position);
        HomeSlider imageSlider =  roomData.getImage_slider().get(0);
        FacilityData facilityData = roomData.getFacility().get(0);
        holder.name.setText(roomData.getApartment_name());
        holder.price.setText("$"+roomData.getTotal_price()+"/Month");
        holder.facility_txt.setText(facilityData.getFacilityString());
        holder.more_facility.setText("+"+roomData.getImage_slider().size());
        Picasso.get()
                .load(imageSlider.getImage())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);

        Picasso.get()
                .load(facilityData.getIcon_url())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.facility_image);

                holder.room_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context,RoomInformation.class);
                        intent.putExtra("room",roomData);
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

}
