package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.CommentListModel;

import java.util.List;

public class CurrentListAdapter extends RecyclerView.Adapter<CurrentListAdapter.MyViewHolder>  {

    private List<CommentListModel> commentList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            /*title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);*/
        }
    }

    public CurrentListAdapter(Context context,List<CommentListModel> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @Override
    public CurrentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item_row, parent, false);

        return new CurrentListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CurrentListAdapter.MyViewHolder holder, int position) {
        CommentListModel CommentListModel = commentList.get(position);

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
