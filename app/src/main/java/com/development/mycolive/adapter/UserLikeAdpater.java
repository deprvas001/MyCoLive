package com.development.mycolive.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.model.viewCommunityModel.UserLike;
import com.development.mycolive.util.Util;
import com.development.mycolive.views.activity.favouriteRoomate.FavouriteRoomateDetail;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class UserLikeAdpater extends RecyclerView.Adapter<UserLikeAdpater.MyViewHolder> {
    private List<UserLike> userLikeList;
    private Context context;
    Dialog dialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,chat,date;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            imageView = (ImageView)view.findViewById(R.id.imageView);
            chat = (TextView)view.findViewById(R.id.chat);
            date = (TextView)view.findViewById(R.id.date);
        }
    }

    public UserLikeAdpater(Context context,List<UserLike> userLikeList) {
        this.context = context;
        this.userLikeList = userLikeList;
    }

    @Override
    public UserLikeAdpater.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_like_row, parent, false);

        return new UserLikeAdpater.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserLikeAdpater.MyViewHolder holder, int position) {
        UserLike userLike = userLikeList.get(position);

        holder.name.setText(userLike.getName());

      /*  Picasso.get()
                .load(UserLike.getProfile_image())
                .placeholder(R.drawable.no_image_found)
                *//*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*//*
                .into(holder.imageView);*/

        Util.loadImage(holder.imageView,userLike.getUser_image() ,
                Util.getCircularDrawable(context));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDialog(userLike.getUser_image());
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FavouriteRoomateDetail.class);
                intent.putExtra(ApiConstant.ROOMMATAE_ID,userLike.getUser_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userLikeList.size();
    }

    public  void showImageDialog(String image) {
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.property_view);

        // set the custom dialog components - text, image and button
        ImageButton close = (ImageButton) dialog.findViewById(R.id.btnClose);
        PhotoView imageView = dialog.findViewById(R.id.photo_view);

       /* Picasso.get()
                .load(image)
                .placeholder(R.drawable.no_image_available)
                .error(R.drawable.no_image_available)
                .into(imageView);
*/
        Util.loadImage(imageView,image ,
                Util.getCircularDrawable(context));

        // Close Button
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //TODO Close button action
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }

}
