package com.development.mycolive.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.favourite.FavouritePropertyModel;
import com.development.mycolive.model.favourite.PropertyImage;
import com.development.mycolive.model.home.HomePropertyArea;
import com.development.mycolive.views.activity.searchDetailPage.RoomDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {

    private List<FavouritePropertyModel> propertiesList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, price, address, name, rating, created_date, no_of_bathroom, no_of_room;
        public ImageView imageView, favIcon;
        public LinearLayout viewLayout;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            price = (TextView) view.findViewById(R.id.price);
            address = (TextView) view.findViewById(R.id.address);
            name = (TextView) view.findViewById(R.id.type);
            favIcon = (ImageView) view.findViewById(R.id.fav_icon);
            no_of_bathroom = (TextView) view.findViewById(R.id.no_of_bathroom);
            no_of_room = (TextView) view.findViewById(R.id.no_of_room);
        }
    }


    public FavouriteAdapter(Context context, List<FavouritePropertyModel> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favourite_property, parent, false);

        return new FavouriteAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavouriteAdapter.MyViewHolder holder, int position) {
        FavouritePropertyModel bookingModel = propertiesList.get(position);

        holder.name.setText(bookingModel.getName());
        holder.price.setText("€ " +bookingModel.getPrice() +"/Month");
        holder.address.setText(bookingModel.getAddress());
        holder.no_of_bathroom.setText(bookingModel.getNo_of_bathroom());
        holder.no_of_room.setText(bookingModel.getNumber_of_room());

        if (bookingModel.getFavourites().equals("1")) {
            holder.favIcon.setColorFilter(ContextCompat.getColor(context,
                    R.color.colorPrimaryDark));
        }

        List<PropertyImage> imageList = bookingModel.getImage();

        if (bookingModel.getImage().size() > 0) {
            Picasso.get()
                    .load(imageList.get(0).getImage())
                    .placeholder(R.drawable.no_image_found)
                    // .placeholder(R.drawable.image1)
                    //   .error(R.drawable.err)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }
}
