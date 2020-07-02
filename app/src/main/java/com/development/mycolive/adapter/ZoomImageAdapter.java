package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.util.Util;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ZoomImageAdapter extends RecyclerView.Adapter<ZoomImageAdapter.MyViewHolder> {

    private List<HomeSlider> detailList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price,name,type,city,university,comment,date,like,comment_count;
        public ImageView imageView,user_image,post_image;
        public PhotoView zoomView;
        public LinearLayout postLayout;

        public MyViewHolder(View view) {
            super(view);

            zoomView = (PhotoView)view.findViewById(R.id.photo_view);

        }
    }

    public ZoomImageAdapter(Context context,List<HomeSlider> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public ZoomImageAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.zoom_image_row, parent, false);
        group = parent;

        return new ZoomImageAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ZoomImageAdapter.MyViewHolder holder, int position) {
        HomeSlider slider = detailList.get(position);

        Util.loadImage(holder.zoomView,slider.getImage(),
                Util.getCircularDrawable(context));

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
}
