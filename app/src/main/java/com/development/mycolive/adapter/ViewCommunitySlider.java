package com.development.mycolive.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.development.mycolive.R;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.util.Util;
import com.development.mycolive.views.activity.ZoomImageScreen;
import com.development.mycolive.views.activity.viewCommunity.ViewCommunity;
import com.github.chrisbanes.photoview.PhotoView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewCommunitySlider extends
        SliderViewAdapter< ViewCommunitySlider.ViewCommunitySliderVH> {

    private Context context;
    private int mCount;
    private List<String> sliderList;
    ArrayList<HomeSlider> homeSliders =new ArrayList<>();
    Dialog dialog;

    public ViewCommunitySlider(Context context, List<String> sliderList, ArrayList<HomeSlider> homeSliders) {
        this.context = context;
        this.sliderList = sliderList;
        this.homeSliders = homeSliders;

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
        String slider = sliderList.get(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //    showDialog(sliderList.get(0));


                if(homeSliders.size()>0){
                    Intent intent = new Intent(context , ZoomImageScreen.class);
                    intent.putParcelableArrayListExtra("image_list", homeSliders);
                    context.startActivity(intent);
                }

            }
        });


        Util.loadImage(viewHolder.imageViewBackground,slider,
                Util.getCircularDrawable(context));

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

    private void showDialog(String image) {
        // custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.property_view);

        // set the custom dialog components - text, image and button
        ImageButton close = (ImageButton) dialog.findViewById(R.id.btnClose);
        PhotoView imageView = dialog.findViewById(R.id.photo_view);

      /*  Picasso.get()
                .load(image)
                .placeholder(R.drawable.no_image_available)
                .error(R.drawable.no_image_available)
                .into(imageView);*/

        Util.loadImage(imageView,image,
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
