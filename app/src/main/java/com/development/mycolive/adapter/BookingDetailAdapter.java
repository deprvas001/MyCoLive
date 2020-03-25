package com.development.mycolive.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.PriceLevel;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.development.mycolive.views.activity.roomInformation.RoomInformation;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailAdapter extends RecyclerView.Adapter<BookingDetailAdapter.MyViewHolder> {

    String apartment_price;
    ArrayList<PropertyRoomData> roomDataList;
    private Context context;
    private ViewGroup group;
    private float total = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price, name, month_rent, security, sub_total;
        public CheckBox select;
        public ImageView imageView, user_image, post_image, facility_image;
        public LinearLayout room_info;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.imageView);
            name = (TextView) view.findViewById(R.id.name);
            month_rent = (TextView) view.findViewById(R.id.monthly_rent);
            security = (TextView) view.findViewById(R.id.security);
            sub_total = (TextView) view.findViewById(R.id.sub_total);

        }
    }

    public BookingDetailAdapter(Context context, ArrayList<PropertyRoomData> roomDataList) {

        this.context = context;
        this.roomDataList = roomDataList;
    }

    @Override
    public BookingDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_detail_item, parent, false);
        group = parent;

        return new BookingDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookingDetailAdapter.MyViewHolder holder, int position) {
        PropertyRoomData roomData = roomDataList.get(position);
        List<HomeSlider> sliderList = roomData.getImage_slider();
        List<PriceLevel> priceLevels = roomData.getPrice_levels();
        holder.name.setText(roomData.getApartment_name());
        holder.security.setText("€"+priceLevels.get(1).getPrice());
        holder.month_rent.setText("€"+priceLevels.get(0).getPrice());

       total = Float.parseFloat(priceLevels.get(0).getPrice())+Float.parseFloat(priceLevels.get(1).getPrice());
        holder.sub_total.setText("€"+String.valueOf(total));
        Picasso.get()
                .load(sliderList.get(0).getImage())
                // *//*  .placeholder(R.drawable.image1)
                //  .error(R.drawable.err)*//*
                .into(holder.imageView);

        /*HomeSlider imageSlider =  roomData.getImage_slider().get(0);
        FacilityData facilityData = roomData.getFacility().get(0);
        holder.name.setText(roomData.getApartment_name());
        holder.price.setText("$"+roomData.getTotal_price()+"/Month");
        holder.facility_txt.setText(facilityData.getFacilityString());
        holder.more_facility.setText("+"+roomData.getImage_slider().size());

        total = Float.parseFloat(apartment_price);
        holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.select.isChecked()){
                    total_list_price.add(roomData);
                    roomDataList.add(roomData);
                    getTotalPrice(roomData);
                }else{
                    if(roomDataList.contains(roomData)){
                        roomDataList.remove(roomData);
                    }
                    getTotalSubtractPrice(roomData);
                }
            }
        });*/

        /*if(holder.select.isChecked()){

          String total =  ((PropertyDetail)context).propertyDetailBinding.totalPrice.getText().toString();
          int price_total = Integer.parseInt(total);
          int room_price = Integer.parseInt(roomData.getTotal_price());
          int final_total = price_total+room_price;
            ((PropertyDetail)context).propertyDetailBinding.totalPrice.setText(String.valueOf(final_total));
        }*/

       /* Picasso.get()
                .load(imageSlider.getImage())
                *//*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*//*
                .into(holder.imageView);

        Picasso.get()
                .load(facilityData.getIcon_url())
                *//*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*//*
                .into(holder.facility_image);

        holder.room_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RoomInformation.class);
                intent.putExtra("room",roomData);
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return roomDataList.size();
    }

}
