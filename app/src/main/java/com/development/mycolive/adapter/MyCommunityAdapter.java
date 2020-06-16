package com.development.mycolive.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.myCommunityModel.MyCommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.MyPostComment;
import com.development.mycolive.model.myCommunityModel.PostDeleteRequest;
import com.development.mycolive.util.Util;
import com.development.mycolive.views.activity.favouriteRoomate.FavouriteRoomateDetail;
import com.development.mycolive.views.activity.myCommunity.MyCommunity;
import com.development.mycolive.views.activity.myCommunity.MyCommunityViewModel;
import com.development.mycolive.views.activity.viewCommunity.ViewCommunity;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCommunityAdapter extends RecyclerView.Adapter<MyCommunityAdapter.MyViewHolder>  {

    List<MyPostComment> postCommentList;
    private Context context;
    private ViewGroup group;
    public MyCommunityViewModel viewModel;
    boolean myCommunity ;
    public String token;
    Dialog dialog;
    ProgressDialog progressDialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView viewDetail,name,type,city,university,comment,date,like,comment_count,know_more;
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
            know_more = (TextView)view.findViewById(R.id.know_more);
        }
    }

    public MyCommunityAdapter(Context context,List<MyPostComment> postCommentList,boolean myCommunity ,String token) {
        this.context = context;
        this.postCommentList = postCommentList;
        this.myCommunity = myCommunity;
        this.token = token;
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

        if(myCommunity){
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_delete_black_24dp));
        }else{
            holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_alert));

        }

         holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FavouriteRoomateDetail.class);
                intent.putExtra(ApiConstant.ROOMMATAE_ID,post.getCreated_by());
                context.startActivity(intent);
            }
        });

        holder.know_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewCommunity.class);
                intent.putExtra("Id",post.getId());
                context.startActivity(intent);
            }
        });


        Picasso.get()
                .load(post.getProfile_image())
                  .placeholder(R.drawable.no_image_available)
                  .error(R.drawable.no_image_available)
                .into(holder.user_image);

        if(post.getImage().size()>0){
            Picasso.get()
                    .load(imageList.get(0))
                    .placeholder(R.drawable.no_image_available)
                    .error(R.drawable.no_image_available)
                    .into(holder.post_image);
        }

        holder.post_image.setOnClickListener(view -> {
            showDialog(imageList.get(0));
        });

        holder.imageView.setOnClickListener(view -> {
            deleteItem(post.getId(),position);
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


    private void deleteItem(String id,int position){
     showProgressDialog(context.getResources().getString(R.string.loading));
        PostDeleteRequest request = new PostDeleteRequest();
        request.setComment_id(id);

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of((FragmentActivity) context).get(MyCommunityViewModel.class);

        viewModel.deletePost(context,headers,request).observe((LifecycleOwner) context, new Observer<MyCommunityApiResponse>() {
            @Override
            public void onChanged(MyCommunityApiResponse communityApiResponse) {
                hideProgressDialog();
                if(communityApiResponse.response !=null){

                    if(communityApiResponse.getResponse().getStatus() == 1){
                        postCommentList.remove(position);
                        notifyDataSetChanged();


                    }else{
                        Toast.makeText(context, communityApiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(context, communityApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showProgressDialog(String message) {
        if(progressDialog == null)
            progressDialog = new ProgressDialog(context);

        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progressDialog !=null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void showDialog(String image) {
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.property_view);

        // set the custom dialog components - text, image and button
        ImageButton close = (ImageButton) dialog.findViewById(R.id.btnClose);
        PhotoView imageView = dialog.findViewById(R.id.photo_view);

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
