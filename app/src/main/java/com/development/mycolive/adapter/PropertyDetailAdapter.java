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

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.RoomData;
import com.development.mycolive.util.Util;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.development.mycolive.views.activity.roomInformation.RoomInformation;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PropertyDetailAdapter  extends RecyclerView.Adapter<PropertyDetailAdapter.MyViewHolder> {

    private List<PropertyRoomData> detailList ;
    String apartment_price;
    private List<PropertyRoomData> total_list_price =new ArrayList<>();
    private Context context;
    private ViewGroup group;
    private float total=0;
    public static ArrayList<PropertyRoomData> roomDataList = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price,name,type,city,university,comment,date,facility_txt,more_facility,
                like,comment_count,label;
        public CheckBox select;
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
            select = (CheckBox)view.findViewById(R.id.checkbox_select);
            label = (TextView)view.findViewById(R.id.label);

        }
    }

    public PropertyDetailAdapter(Context context,List<PropertyRoomData> detailList,String apartment_price) {

        this.context = context;
        this.detailList = detailList;
        this.apartment_price = apartment_price;
    }

    @Override
    public PropertyDetailAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_category, parent, false);
        group = parent;
        roomDataList.clear();

        return new PropertyDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropertyDetailAdapter.MyViewHolder holder, int position) {
        PropertyRoomData roomData = detailList.get(position);
        if(roomData.getImage_slider().size()>0){
            HomeSlider imageSlider =  roomData.getImage_slider().get(0);
            Util.loadImage(holder.imageView,imageSlider.getImage() , Util.getCircularDrawable(context));
        }

        holder.label.setVisibility(View.GONE);
        if (roomData.getBooking_shared_btn() != null) {
            if (!roomData.getBooking_shared_btn().isEmpty()) {
                holder.label.setVisibility(View.VISIBLE);
                holder.label.setText(roomData.getBooking_shared_btn());
            }
        }

        if(roomData.getFacility().size()>0){
            FacilityData facilityData = roomData.getFacility().get(0);
            holder.facility_txt.setText(facilityData.getFacilityString());


            Util.loadImage(holder.facility_image,facilityData.getIcon_url() , Util.getCircularDrawable(context));

        }

        holder.name.setText(roomData.getApartment_name());
        holder.price.setText("€ "+roomData.getTotal_price()+"/Month");

        holder.more_facility.setText("+"+roomData.getImage_slider().size());

       // total = Float.parseFloat(apartment_price);
        holder.select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.select.isChecked()){
                     roomDataList.add(roomData);
                     getTotalPrice(roomData);
                }else{
                    roomDataList.remove(roomData);
                    getTotalSubtractPrice(roomData);

                }
            }
        });

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

 public float getTotalPrice(PropertyRoomData roomData){

         int room_price = Integer.parseInt(roomData.getTotal_price());
         PropertyDetail.apartment_price = PropertyDetail.apartment_price + room_price;


         ((PropertyDetail)context).propertyDetailBinding.totalPrice.setText("€"+String.valueOf(PropertyDetail.apartment_price));

     return total;
 }

    public float getTotalSubtractPrice(PropertyRoomData roomData){

        int room_price = Integer.parseInt(roomData.getTotal_price());
        PropertyDetail.apartment_price = PropertyDetail.apartment_price -room_price;

        ((PropertyDetail)context).propertyDetailBinding.totalPrice.setText("€"+String.valueOf(PropertyDetail.apartment_price ));

        return total;
    }
}
