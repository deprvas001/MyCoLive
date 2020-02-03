package com.development.mycolive.views.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.views.model.PropertiesFeatures;
import com.development.mycolive.views.model.SearchResultModel;

import java.util.List;

public class SearchScreenAdapter extends RecyclerView.Adapter<SearchScreenAdapter.MyViewHolder> {
private List<SearchResultModel> searchList;
private Context context;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView imageView;

    public MyViewHolder(View view) {
        super(view);
        /*title = (TextView) view.findViewById(R.id.address);
        imageView = (ImageView)view.findViewById(R.id.image);*/
    }
}
    public SearchScreenAdapter(Context context, List<SearchResultModel> searchList) {
        this.context = context;
        this.searchList = searchList;
    }

    @Override
    public SearchScreenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_layout, parent, false);

        return new SearchScreenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchScreenAdapter.MyViewHolder holder, int position) {
        SearchResultModel search_room = searchList.get(position);
        /* holder.title.setText(find_room.getType());*/
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
