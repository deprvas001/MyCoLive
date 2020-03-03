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
import com.development.mycolive.model.RoomCategoryDetail;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.propertyDetailModel.PropertyImageSlider;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.views.activity.viewCommunity.ViewCommunity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class PropertyDetailAdapter  extends RecyclerView.Adapter<PropertyDetailAdapter.MyViewHolder> {

    private List<PropertyRoomData> detailList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price,name,type,city,university,comment,date,like,comment_count;
        public ImageView imageView,user_image,post_image;
        public LinearLayout postLayout;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.image);
            name = (TextView)view.findViewById(R.id.name);
            price = (TextView)view.findViewById(R.id.price);

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
        PropertyImageSlider imageSlider =  roomData.getImage_slider().get(0);
        holder.name.setText(roomData.getApartment_name());
        holder.price.setText(roomData.getTotal_price());
        Picasso.get()
                .load(imageSlider.getImage())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    private void showCustomDialog(Context context){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        //  ViewGroup viewGroup = context.findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_request_dialog, group, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);



        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
