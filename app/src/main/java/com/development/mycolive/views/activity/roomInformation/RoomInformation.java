package com.development.mycolive.views.activity.roomInformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.adapter.FacilityAdapter;
import com.development.mycolive.adapter.HomeSlideAdapter;
import com.development.mycolive.databinding.ActivityRoomInformationBinding;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyImageSlider;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.PriceLevel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class RoomInformation extends AppCompatActivity {
ActivityRoomInformationBinding informationBinding;
List<HomeSlider> homeSliderList=new ArrayList<>();
    PropertyRoomData roomData;
    private FacilityAdapter facilityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        informationBinding = DataBindingUtil.setContentView(this,R.layout.activity_room_information);

         roomData = (PropertyRoomData) getIntent().getParcelableExtra("room");
         setSliderAndView(roomData.getImage_slider());

    }

    private void setSliderAndView(List<HomeSlider> sliderList) {
        final HomeSlideAdapter adapter = new HomeSlideAdapter(this, sliderList);
        adapter.setCount(sliderList.size());

        informationBinding.imageSlider.setSliderAdapter(adapter);

        informationBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        informationBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        informationBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        informationBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        informationBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        informationBinding.imageSlider.startAutoCycle();

        informationBinding.imageSlider.setOnIndicatorClickListener(position ->
                informationBinding.imageSlider.setCurrentPagePosition(position));

        initializeView(roomData.getFacility());

    }

    private void initializeView(List<FacilityData> facilityData){
        List<PriceLevel> priceLevel = roomData.getPrice_levels();
        int subtotal = Integer.parseInt(priceLevel.get(0).getPrice())+Integer.parseInt(priceLevel.get(1).getPrice());
        informationBinding.addressApartment.setText(roomData.getAddress());
        informationBinding.monthRentPrice.setText("$ "+priceLevel.get(0).getPrice()+"/Month");
        informationBinding.securityPrice.setText("$ "+priceLevel.get(1).getPrice()+"/Month");
        informationBinding.subTotal.setText("$ "+String.valueOf(subtotal)+"/Month");
        informationBinding.descriptionTxt.setText(roomData.getDescription());


        facilityAdapter = new FacilityAdapter(this, facilityData);
        mLayoutManager =new GridLayoutManager(this, 2);
        informationBinding.recyclerViewFacility.setLayoutManager(mLayoutManager);
        informationBinding.recyclerViewFacility.setItemAnimator(new DefaultItemAnimator());
        informationBinding.recyclerViewFacility.setAdapter(facilityAdapter);
    }
}
