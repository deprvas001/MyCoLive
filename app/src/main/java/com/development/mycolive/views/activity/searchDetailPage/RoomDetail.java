package com.development.mycolive.views.activity.searchDetailPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.development.mycolive.views.activity.BookingDetailFill;
import com.development.mycolive.views.activity.MapScreen;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.adapter.FeaturedFacilitesAdapter;
import com.development.mycolive.adapter.SliderAdapter;
import com.development.mycolive.model.FeaturedProperty;
import com.development.mycolive.model.searchDetailPage.SearchDetailApiRsponse;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class RoomDetail extends AppCompatActivity implements View.OnClickListener {
    ActivityRoomDetailBinding detailBinding;
    FeaturedFacilitesAdapter facilitesAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RoomDetailViewModel roomDetailViewModel;
    List<FeaturedProperty> featuredProperties=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        detailBinding = DataBindingUtil.setContentView(this,R.layout.activity_room_detail);
        initializeView();
    }

    private void setRecylerView(){
        facilitesAdapter = new FeaturedFacilitesAdapter(RoomDetail.this, featuredProperties);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        detailBinding.recyclerView.setLayoutManager(mLayoutManager);
        detailBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        detailBinding.recyclerView.setAdapter(facilitesAdapter);


    }


    private void initializeView(){
         detailBinding.back.setOnClickListener(this);
         detailBinding.notification.setOnClickListener(this);
         detailBinding.viewMap.setOnClickListener(this);
         detailBinding.btnBook.setOnClickListener(this);

        final SliderAdapter adapter = new SliderAdapter(this);
        adapter.setCount(5);

        detailBinding.imageSlider.setSliderAdapter(adapter);

        detailBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        detailBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        detailBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        detailBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        detailBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        detailBinding.imageSlider.startAutoCycle();

        detailBinding.imageSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                detailBinding.imageSlider.setCurrentPagePosition(position);
            }
        });

         getPropertyData();
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
                 startActivity(new Intent(this, Notification.class));
                break;

            case R.id.view_map:
                startActivity(new Intent(this, MapScreen.class));
                break;

            case R.id.btn_book:
                startActivity(new Intent(this, BookingDetailFill.class));
                break;
        }
    }

    private void getPropertyData(){

        String id = "20";

        roomDetailViewModel = ViewModelProviders.of(this).get(RoomDetailViewModel.class);

        roomDetailViewModel.getData(this,id).observe(this, new Observer<SearchDetailApiRsponse>() {
            @Override
            public void onChanged(SearchDetailApiRsponse homeApiResponse) {
                if (homeApiResponse.response != null) {


                }
                detailBinding.shimmerViewContainer.stopShimmer();
                detailBinding.shimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        detailBinding.shimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        detailBinding.shimmerViewContainer.stopShimmer();
        super.onPause();
    }
}
