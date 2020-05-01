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
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.PriceLevel;
import com.development.mycolive.model.termscondition.ContractResponse;
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
    ContractResponse response;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price, name, month_rent, security, sub_total,other_bill,
                room_name,period,room_name_text;
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
            other_bill = (TextView)view.findViewById(R.id.other_price);
            room_name = (TextView)view.findViewById(R.id.room_name);
            period = (TextView)view.findViewById(R.id.period);
            room_name_text = (TextView)view.findViewById(R.id.room_name_text);

        }
    }

    public BookingDetailAdapter(Context context, ArrayList<PropertyRoomData> roomDataList,ContractResponse response) {

        this.context = context;
        this.roomDataList = roomDataList;
        this.response =response;
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
        holder.security.setText("€ "+priceLevels.get(1).getPrice()+"/Month");
        holder.month_rent.setText("€ "+priceLevels.get(0).getPrice()+"/Month");
        holder.room_name.setText(roomData.getApartment_name());
        holder.room_name_text.setText(roomData.getApartment_name());
        if(!response.getDuration().isEmpty() && response.getDuration()!=null) {
            holder.period.setText(response.getDuration());
        }


        total = Float.parseFloat(priceLevels.get(0).getPrice())+Float.parseFloat(priceLevels.get(1).getPrice());
        if(priceLevels.size()>1){
            holder.other_bill.setText("€ "+priceLevels.get(2).getPrice()+"/Month");
            total = total + Float.parseFloat(priceLevels.get(2).getPrice());
        }

        holder.sub_total.setText("€ "+String.valueOf(total)+"/Month");
        Picasso.get()
                .load(sliderList.get(0).getImage())
                // *//*  .placeholder(R.drawable.image1)
                //  .error(R.drawable.err)*//*
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return roomDataList.size();
    }

}
