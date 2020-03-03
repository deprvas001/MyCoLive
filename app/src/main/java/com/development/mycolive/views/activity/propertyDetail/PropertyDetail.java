package com.development.mycolive.views.activity.propertyDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.development.mycolive.R;
import com.development.mycolive.adapter.FacilityAdapter;
import com.development.mycolive.adapter.HomeSlideAdapter;
import com.development.mycolive.adapter.MonthDataAdapter;
import com.development.mycolive.adapter.PropertyDetailAdapter;
import com.development.mycolive.databinding.ActivityPropertyDetailBinding;
import com.development.mycolive.model.RoomCategoryDetail;
import com.development.mycolive.model.bookingHistory.MonthHistory;
import com.development.mycolive.model.home.CountData;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailApiResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyImageSlider;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.UniversityModel;
import com.development.mycolive.views.fragment.filterSearch.SearchViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class PropertyDetail extends AppCompatActivity {
    ActivityPropertyDetailBinding propertyDetailBinding;
    private PropertyDetailAdapter roomAdapter;
    private FacilityAdapter facilityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    PropertyDetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        propertyDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_property_detail);
        getDefaultData();
    }

    private void setSliderAndView(List<HomeSlider> sliderList) {
        final HomeSlideAdapter adapter = new HomeSlideAdapter(this, sliderList);
        adapter.setCount(sliderList.size());

        propertyDetailBinding.imageSlider.setSliderAdapter(adapter);

        propertyDetailBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        propertyDetailBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        propertyDetailBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        propertyDetailBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        propertyDetailBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        propertyDetailBinding.imageSlider.startAutoCycle();

        propertyDetailBinding.imageSlider.setOnIndicatorClickListener(position ->
                propertyDetailBinding.imageSlider.setCurrentPagePosition(position));

    }


    private void setRecycleView(List<PropertyRoomData> detailList, List<FacilityData> facilityData){
        roomAdapter = new PropertyDetailAdapter(this, detailList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true);
        propertyDetailBinding.recyclerView.setLayoutManager(mLayoutManager);
        propertyDetailBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        propertyDetailBinding.recyclerView.setAdapter(roomAdapter);

        facilityAdapter = new  FacilityAdapter(this, facilityData);
        mLayoutManager =new  GridLayoutManager(this, 2);
        propertyDetailBinding.recyclerViewFacility.setLayoutManager(mLayoutManager);
        propertyDetailBinding.recyclerViewFacility.setItemAnimator(new DefaultItemAnimator());
        propertyDetailBinding.recyclerViewFacility.setAdapter(facilityAdapter);
    }


    private void getDefaultData() {
        String id = "6";

        detailViewModel = ViewModelProviders.of(this).get(PropertyDetailViewModel.class);

        detailViewModel.getPropertyDetail(this, id).observe(this, new Observer<PropertyDetailApiResponse>() {
            @Override
            public void onChanged(PropertyDetailApiResponse apiResponse) {
                if (apiResponse.response != null) {
                 initializeView(apiResponse);
                 setRecycleView(apiResponse.getResponse().getData().get(0).getRoom(),
                         apiResponse.getResponse().getData().get(0).getFacility());
                 setSliderAndView(apiResponse.getResponse().getData().get(0).getImage_slider());
                }
            }
        });
    }


    private void initializeView(PropertyDetailApiResponse apiResponse){
        propertyDetailBinding.apartmentName.setText(apiResponse.getResponse().getData().get(0).getApartment_name());
        propertyDetailBinding.addressApartment.setText(apiResponse.getResponse().getData().get(0).getAddress());
        propertyDetailBinding.description.setText(apiResponse.getResponse().getData().get(0).getDescription());

    }
}
