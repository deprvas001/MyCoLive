package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.development.mycolive.R;
import com.development.mycolive.model.communityModel.AllPost;

import java.util.List;

public class AllCommunityAdapter  extends RecyclerView.Adapter<AllCommunityAdapter.MyViewHolder> {

    private List<AllPost> allPostList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView viewDetail;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.alert);
        }
    }

    public AllCommunityAdapter(Context context,List<AllPost> allPostList) {
        this.context = context;
        this.allPostList = allPostList;
    }

    @Override
    public AllCommunityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.community_item_row, parent, false);
        group = parent;

        return new AllCommunityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllCommunityAdapter.MyViewHolder holder, int position) {
        AllPost post = allPostList.get(position);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPostList.size();
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
