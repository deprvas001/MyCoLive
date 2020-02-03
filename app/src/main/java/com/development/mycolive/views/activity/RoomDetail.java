package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityRoomDetailBinding;
import com.development.mycolive.views.adapter.FeaturedFacilitesAdapter;
import com.development.mycolive.views.adapter.SearchScreenAdapter;
import com.development.mycolive.views.adapter.SliderAdapter;
import com.development.mycolive.views.model.FeaturedProperty;
import com.development.mycolive.views.model.SearchResultModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class RoomDetail extends AppCompatActivity implements View.OnClickListener {
    SliderView sliderView;
    ActivityRoomDetailBinding detailBinding;
    FeaturedFacilitesAdapter facilitesAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<FeaturedProperty> featuredProperties=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        detailBinding = DataBindingUtil.setContentView(this,R.layout.activity_room_detail);
        sliderView = findViewById(R.id.imageSlider);
        initializeView();

        final SliderAdapter adapter = new SliderAdapter(this);
        adapter.setCount(5);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });


        setRecylerView();
        setItem();
    }

    private void setRecylerView(){
        facilitesAdapter = new FeaturedFacilitesAdapter(RoomDetail.this, featuredProperties);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        detailBinding.recyclerView.setLayoutManager(mLayoutManager);
        detailBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        detailBinding.recyclerView.setAdapter(facilitesAdapter);
    }


    private void setItem(){
        featuredProperties.clear();

        for(int i=0;i<5;i++){
            FeaturedProperty property = new FeaturedProperty("Bath Room");
            featuredProperties.add(property);
        }

        facilitesAdapter.notifyDataSetChanged();
    }

    private void initializeView(){
         detailBinding.back.setOnClickListener(this);
         detailBinding.notification.setOnClickListener(this);
         detailBinding.viewMap.setOnClickListener(this);
         detailBinding.btnBook.setOnClickListener(this);
     /*   detailBinding.toolbar.setTitle(getString(R.string.search_result));
        setSupportActionBar(detailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.notification:
                 startActivity(new Intent(this,Notification.class));
                break;

            case R.id.view_map:
                startActivity(new Intent(this,MapScreen.class));
                break;

            case R.id.btn_book:
                startActivity(new Intent(this,BookingDetailFill.class));
                break;
        }
    }
}
