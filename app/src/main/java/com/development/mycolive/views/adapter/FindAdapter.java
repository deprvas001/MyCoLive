package com.development.mycolive.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.views.activity.FindRoomate;
import com.development.mycolive.views.activity.SearchResult;
import com.development.mycolive.views.model.Find;

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.MyViewHolder> {
    private List<Find> findList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);
        }
    }


    public FindAdapter(Context context,List<Find> findList) {
        this.context = context;
        this.findList = findList;
    }

    @Override
    public FindAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.find_row, parent, false);

        return new FindAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FindAdapter.MyViewHolder holder, int position) {
        Find find_room = findList.get(position);
        holder.title.setText(find_room.getType());

        if(position == 1){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.images));
        }else if(position == 2){
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.roomate));
        }

        if(position == 2){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, FindRoomate.class));
                }
            });
        }else{
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, SearchResult.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return findList.size();
    }
}
