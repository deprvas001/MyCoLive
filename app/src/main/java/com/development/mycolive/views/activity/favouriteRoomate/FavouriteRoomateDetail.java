package com.development.mycolive.views.activity.favouriteRoomate;

import android.os.Bundle;

import com.development.mycolive.adapter.PropertiesAdapter;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityFavouriteRoomateDetailBinding;
import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.model.favouriteRoomate.FavouriteRequest;
import com.development.mycolive.model.favouriteRoomate.RoomateFavApiResponse;
import com.development.mycolive.model.home.CountData;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.home.HomePropertyArea;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.bookingHistory.BookingHistoryViewModel;
import com.development.mycolive.views.activity.bookingHistory.CurrentBookingHistory;
import com.development.mycolive.views.activity.paymentScreen.SelectPayment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.development.mycolive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavouriteRoomateDetail extends BaseActivity implements View.OnClickListener {
ActivityFavouriteRoomateDetailBinding roomateDetailBinding;
PropertiesAdapter propertiesAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    String id;
    SessionManager session;
    private GoogleMap mMap;
    String token="";
    FavRoomateViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomateDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_favourite_roomate_detail);
   //     roomateModel = (RoomateData) getIntent().getParcelableExtra(ApiConstant.ROOMMATAE_ID);

        if(getIntent()!=null){
            id = getIntent().getExtras().getString(ApiConstant.ROOMMATAE_ID);
        }


        initializeView();
    }

    private void initializeView(){
       /* roomateDetailBinding.toolbar.setTitle(getResources().getString(R.string.RoomateDetail));
        setSupportActionBar(roomateDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        roomateDetailBinding.favIcon.setOnClickListener(this);
        roomateDetailBinding.back.setOnClickListener(this);

        getSession();

    }

    private void setWebView(String lat,String longt){
        roomateDetailBinding.webview.setWebViewClient(new WebViewClient());
        roomateDetailBinding.webview.getSettings().setJavaScriptEnabled(true);
        roomateDetailBinding.webview.loadUrl("http://maps.google.com/maps?q="+lat+","+longt);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void getSession(){
        session = new SessionManager(this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);


        getRoomateDetail();
    }

    private void getRoomateDetail(){
        showProgressDialog(getString(R.string.loading));
        String type = "ROOMMATE";

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(this).get(FavRoomateViewModel.class);
        viewModel.getRoomateDetail(this,headers,type,id).observe(this, new Observer<RoomateFavApiResponse>() {
            @Override
            public void onChanged(RoomateFavApiResponse apiResponse) {
                hideProgressDialog();
                if(apiResponse.response !=null){

                    RoomateData roomateData = apiResponse.getResponse().getData().getData().get(0);
                    List<HomeFeatureProperty> homeFeaturePropertyList = apiResponse.getResponse().getData().getNearByArea();
                    setView(roomateData);
                    setReyclerView(homeFeaturePropertyList);

                    if(roomateData.getFavourites().equals("0")){
                            roomateDetailBinding.favIcon.setColorFilter(ContextCompat.getColor(FavouriteRoomateDetail.this,
                                    R.color.text_color_hint), android.graphics.PorterDuff.Mode.MULTIPLY);
                    }else{
                        roomateDetailBinding.favIcon.setColorFilter(ContextCompat.getColor(FavouriteRoomateDetail.this,
                                R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);

                        /*if(apiResponse.getResponse().getStatus() == 1){
                            roomateDetailBinding.favIcon.setColorFilter(ContextCompat.getColor(FavouriteRoomateDetail.this,
                                    R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }*/
                    }

                }
                else{
                    Toast.makeText(FavouriteRoomateDetail.this, "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setView(RoomateData roomateModel){
            Picasso.get()
                .load(roomateModel.getProfile_image())
                // .placeholder(R.drawable.image1)
                //   .error(R.drawable.err)
                .into(roomateDetailBinding.imageView);



        roomateDetailBinding.name.setText(roomateModel.getName());
        roomateDetailBinding.age.setText(roomateModel.getAge());
        roomateDetailBinding.email.setText(roomateModel.getEmail());
        roomateDetailBinding.phone.setText(roomateModel.getMobile());
        roomateDetailBinding.gender.setText(roomateModel.getGender());
        roomateDetailBinding.resident.setText(roomateModel.getCountry());
        roomateDetailBinding.hostCity.setText(roomateModel.getCity_name());
        roomateDetailBinding.university.setText(roomateModel.getUniversity_name());
        roomateDetailBinding.location.setText(roomateModel.getAddress());
        setWebView(roomateModel.getLate(),roomateModel.getLang());
    }

    private void setReyclerView(List<HomeFeatureProperty> featurePropertyList) {

        propertiesAdapter = new PropertiesAdapter(this, featurePropertyList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        roomateDetailBinding.recyclerView.setLayoutManager(mLayoutManager);
        roomateDetailBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        roomateDetailBinding.recyclerView.setAdapter(propertiesAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fav_icon:
               addFav();
                break;

            case R.id.back:
                finish();
                break;
        }
    }

    private void addFav(){
        showProgressDialog(getString(R.string.loading));
        String type = "ROOMMATE";

        FavouriteRequest request = new FavouriteRequest();
        request.setType(type);
        request.setId(id);

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(this).get(FavRoomateViewModel.class);
        viewModel.setFav(this,headers,request).observe(this, new Observer<RoomateFavApiResponse>() {
            @Override
            public void onChanged(RoomateFavApiResponse apiResponse) {
                hideProgressDialog();
                if(apiResponse.response !=null){

                    if(apiResponse.getResponse().getStatus() == 1){
                        if(apiResponse.getResponse().getIs_fav() == 1){
                            roomateDetailBinding.favIcon.setColorFilter(ContextCompat.getColor(FavouriteRoomateDetail.this,
                                    R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }else{
                            roomateDetailBinding.favIcon.setColorFilter(ContextCompat.getColor(FavouriteRoomateDetail.this,
                                    R.color.text_color_hint), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }

                    }else {
                        Toast.makeText(FavouriteRoomateDetail.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(FavouriteRoomateDetail.this, "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
