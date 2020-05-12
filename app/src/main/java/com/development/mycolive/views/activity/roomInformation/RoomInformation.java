package com.development.mycolive.views.activity.roomInformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.adapter.FacilityAdapter;
import com.development.mycolive.adapter.HomeSlideAdapter;
import com.development.mycolive.adapter.PriceLevelAdapter;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityRoomInformationBinding;
import com.development.mycolive.model.favouriteRoomate.FavouriteRequest;
import com.development.mycolive.model.favouriteRoomate.RoomateFavApiResponse;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyImageSlider;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.PriceLevel;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.favouriteRoomate.FavRoomateViewModel;
import com.development.mycolive.views.activity.favouriteRoomate.FavouriteRoomateDetail;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomInformation extends BaseActivity implements View.OnClickListener {
    ActivityRoomInformationBinding informationBinding;
    List<HomeSlider> homeSliderList = new ArrayList<>();
    SessionManager session;
    String token = "";
    PriceLevelAdapter priceLevelAdapter;
    float subtotal = 0;
    PropertyRoomData roomData;
    private FacilityAdapter facilityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        informationBinding = DataBindingUtil.setContentView(this, R.layout.activity_room_information);
       /* informationBinding.toolbar.setTitle(getResources().getString(R.string.RoomDetail));
        setSupportActionBar(informationBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        roomData = (PropertyRoomData) getIntent().getParcelableExtra("room");
        setSliderAndView(roomData.getImage_slider());
        informationBinding.back.setOnClickListener(this);
        getSession();

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

    private void initializeView(List<FacilityData> facilityData) {
        id = roomData.getId();
        List<PriceLevel> priceLevel = roomData.getPrice_levels();

        for (int i = 0; i < priceLevel.size(); i++) {
            subtotal = subtotal + Float.parseFloat(priceLevel.get(i).getPrice());
        }

        informationBinding.addressApartment.setText(roomData.getAddress());
      /*  if(priceLevel.size()>0){
          //  informationBinding.monthRentPrice.setText("€ "+priceLevel.get(0).getPrice()+"/Month");
           if(priceLevel.size()>1)
           // informationBinding.securityPrice.setText("€ "+priceLevel.get(1).getPrice()+"/Month");
           if(priceLevel.size()>2){
               subtotal = subtotal +Float.parseFloat(priceLevel.get(2).getPrice());
               informationBinding.otherPrice.setText("€ " + priceLevel.get(2).getPrice()+"/Month");
               informationBinding.otherLayout.setVisibility(View.VISIBLE);
           }

        }*/


        priceLevelAdapter = new PriceLevelAdapter(this, priceLevel);
        mLayoutManager = new LinearLayoutManager(this);
        informationBinding.recyclerView.setLayoutManager(mLayoutManager);
        informationBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        informationBinding.recyclerView.setAdapter(priceLevelAdapter);


        informationBinding.subTotal.setText("€ " + String.valueOf(subtotal) + "/Month");
        informationBinding.descriptionTxt.setText(roomData.getDescription());

        if (roomData.getFavourites().equals("0")) {
            informationBinding.favIcon.setColorFilter(ContextCompat.getColor(RoomInformation.this,
                    R.color.text_color_hint), android.graphics.PorterDuff.Mode.MULTIPLY);
        } else {
            informationBinding.favIcon.setColorFilter(ContextCompat.getColor(RoomInformation.this,
                    R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
        }


        facilityAdapter = new FacilityAdapter(this, facilityData);
        mLayoutManager = new GridLayoutManager(this, 2);
        informationBinding.recyclerViewFacility.setLayoutManager(mLayoutManager);
        informationBinding.recyclerViewFacility.setItemAnimator(new DefaultItemAnimator());
        informationBinding.recyclerViewFacility.setAdapter(facilityAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void addFav() {
        showProgressDialog(getString(R.string.loading));
        String type = "PROPERTY";

        FavouriteRequest request = new FavouriteRequest();
        request.setType(type);
        request.setId(id);

        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN, token);

        FavRoomateViewModel viewModel = ViewModelProviders.of(this).get(FavRoomateViewModel.class);
        viewModel.setFav(this, headers, request).observe(this, new Observer<RoomateFavApiResponse>() {
            @Override
            public void onChanged(RoomateFavApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {

                    if (apiResponse.getResponse().getStatus() == 1) {
                        Toast.makeText(RoomInformation.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                        if (apiResponse.getResponse().getIs_fav() == 1) {
                            informationBinding.favIcon.setColorFilter(ContextCompat.getColor(RoomInformation.this,
                                    R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
                        } else {
                            informationBinding.favIcon.setColorFilter(ContextCompat.getColor(RoomInformation.this,
                                    R.color.text_color_hint), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }

                    } else {
                        Toast.makeText(RoomInformation.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RoomInformation.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getSession() {
        session = new SessionManager(getApplicationContext());
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);

        setClickListener();

    }

    private void setClickListener() {
        informationBinding.favIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fav_icon:
                addFav();
                break;

            case R.id.back:
                finish();
                break;
        }
    }
}
