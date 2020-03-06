package com.development.mycolive.views.activity;

import android.os.Bundle;

import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityFavouriteRoomateDetailBinding;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewClient;

import com.development.mycolive.R;
import com.squareup.picasso.Picasso;

public class FavouriteRoomateDetail extends AppCompatActivity {
ActivityFavouriteRoomateDetailBinding roomateDetailBinding;
    RoomateData roomateModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomateDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_favourite_roomate_detail);
        roomateModel = (RoomateData) getIntent().getParcelableExtra(ApiConstant.ROOMMATAE_ID);
        initializeView();
    }

    private void initializeView(){
        roomateDetailBinding.toolbar.setTitle(getResources().getString(R.string.RoomateDetail));
        setSupportActionBar(roomateDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
}
