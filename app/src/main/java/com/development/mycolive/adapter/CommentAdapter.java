package com.development.mycolive.adapter;

import android.app.Dialog;
import android.content.Context;
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
import com.development.mycolive.model.viewCommunityModel.CommentReply;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.viewCommunity.ViewCommunity;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;


import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>  {

    private List<CommentReply> commentList;
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

    public CommentAdapter(Context context,List<CommentReply> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_row, parent, false);

        return new CommentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.MyViewHolder holder, int position) {
        CommentReply commentReply = commentList.get(position);

        holder.name.setText(commentReply.getUser_name());
        holder.chat.setText(commentReply.getComment());
        holder.date.setText(commentReply.getDate());

        Picasso.get()
                .load(commentReply.getProfile_image())
                .placeholder(R.drawable.no_image_found)
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.imageView);

         holder.imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 ((ViewCommunity)context).showImageDialog(commentReply.getProfile_image());
             }
         });

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

}
