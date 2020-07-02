package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.development.mycolive.R;
import com.development.mycolive.adapter.ZoomImageAdapter;
import com.development.mycolive.databinding.ActivityZoomImageScreenBinding;
import com.development.mycolive.model.home.HomeSlider;

import java.util.ArrayList;


public class ZoomImageScreen extends Activity {
    ActivityZoomImageScreenBinding screenBinding;
    ArrayList<HomeSlider> sliderList;
    private ZoomImageAdapter communityAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this, R.layout.activity_zoom_image_screen);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (getIntent().getExtras() != null) {
            sliderList = getIntent().getParcelableArrayListExtra("image_list");
            setRecyclerView(sliderList);

        }
    }

    public void setRecyclerView(ArrayList<HomeSlider> sliderList) {
        communityAdapter = new ZoomImageAdapter(this, sliderList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        screenBinding.recyclerView.setLayoutManager(mLayoutManager);
        screenBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        screenBinding.recyclerView.setAdapter(communityAdapter);
    }

}
