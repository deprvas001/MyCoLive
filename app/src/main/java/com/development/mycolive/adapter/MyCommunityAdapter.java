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
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.myCommunityModel.MyPostComment;
import com.development.mycolive.views.activity.viewCommunity.ViewCommunity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCommunityAdapter extends RecyclerView.Adapter<MyCommunityAdapter.MyViewHolder>  {

    List<MyPostComment> postCommentList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView viewDetail,name,type,city,university,comment,date,like,comment_count;
        public ImageView imageView,user_image,post_image;
        public LinearLayout postLayout;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.alert);
            user_image= (ImageView)view.findViewById(R.id.imageView);
            name = (TextView)view.findViewById(R.id.name);
            type = (TextView)view.findViewById(R.id.type);
            city = (TextView)view.findViewById(R.id.city);
            university = (TextView)view.findViewById(R.id.university);
            comment = (TextView)view.findViewById(R.id.comment);
            date = (TextView)view.findViewById(R.id.date);
            like = (TextView)view.findViewById(R.id.like);
            comment_count = (TextView)view.findViewById(R.id.comment_count);
            post_image = (ImageView)view.findViewById(R.id.post_image);
            postLayout = (LinearLayout)view.findViewById(R.id.post_layout);
        }
    }

    public MyCommunityAdapter(Context context,List<MyPostComment> postCommentList) {
        this.context = context;
        this.postCommentList = postCommentList;
    }

    @Override
    public MyCommunityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.community_item_row, parent, false);
        group = parent;

        return new MyCommunityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyCommunityAdapter.MyViewHolder holder, int position) {
        MyPostComment post =postCommentList.get(position);
       List<String> imageList =  post.getImage();
        holder.name.setText(post.getUser_name());
        holder.type.setText("Type: "+post.getPost_type_name());
        holder.city.setText("City: "+post.getCity_name());
        holder.university.setText("University: "+post.getUniversity_name());
        holder.comment.setText(post.getComment());
        holder.date.setText(post.getDate());
        holder.like.setText(post.getTotal_likes());
        holder.comment_count.setText(String.valueOf(post.getTotal_reply_comment()));

        holder.postLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewCommunity.class);
                intent.putExtra("Id",post.getId());
                context.startActivity(intent);
            }
        });

        Picasso.get()
                .load(post.getProfile_image())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(holder.user_image);

        if(post.getImage().size()>0){
            Picasso.get()
                    .load(imageList.get(0))
                    /*  .placeholder(R.drawable.image1)
                      .error(R.drawable.err)*/
                    .into(holder.post_image);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postCommentList.size();
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
