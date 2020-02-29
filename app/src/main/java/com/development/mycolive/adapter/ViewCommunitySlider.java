package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.development.mycolive.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class ViewCommunitySlider extends
        SliderViewAdapter< ViewCommunitySlider.ViewCommunitySliderVH> {

    private Context context;
    private int mCount;
    private List<String> sliderList;

    public ViewCommunitySlider(Context context, List<String> sliderList) {
        this.context = context;
        this.sliderList = sliderList;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public ViewCommunitySlider.ViewCommunitySliderVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout, null);
        return new ViewCommunitySlider.ViewCommunitySliderVH(inflate);
    }

    @Override
    public void onBindViewHolder(ViewCommunitySlider.ViewCommunitySliderVH viewHolder, final int position) {


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });


        Glide.with(viewHolder.itemView)
                .load(sliderList.get(0))
                //.load(R.drawable.image1)
                .fitCenter()
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class ViewCommunitySliderVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public ViewCommunitySliderVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
