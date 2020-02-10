package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityBookingDetailFillBinding;
import com.development.mycolive.views.adapter.PropertiesAdapter;
import com.development.mycolive.views.model.Find;
import com.development.mycolive.views.model.PropertiesFeatures;

import java.util.ArrayList;
import java.util.List;

public class BookingDetailFill extends AppCompatActivity implements View.OnClickListener {
    ActivityBookingDetailFillBinding fillBinding;
    private PropertiesAdapter propertiesAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<String> select_room = new ArrayList<String>();
    List<String> select_arrival = new ArrayList<>();
    List<PropertiesFeatures> featuresList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillBinding = DataBindingUtil.setContentView(this,R.layout.activity_booking_detail_fill);
      /*  fillBinding.toolbar.setTitle(getString(R.string.booking_detail));
        setSupportActionBar(fillBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        setClickListener();
        setRecyclerview();
        typeRoom();
        setSpinner();
    }

    private void setRecyclerview(){
      /*propertiesAdapter = new PropertiesAdapter(BookingDetailFill.this, featuresList);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        fillBinding.recyclerViewFind.setLayoutManager(mLayoutManager);
        fillBinding.recyclerViewFind.setItemAnimator(new DefaultItemAnimator());
        fillBinding.recyclerViewFind.setAdapter(propertiesAdapter);*/
    }

    private void typeRoom(){
        featuresList.clear();
        for (int i=0;i<4;i++){
            PropertiesFeatures propertiesFeatures = new PropertiesFeatures("4.2",
                    "South Yara","1 Day Ago","$1200","9");
            featuresList.add(propertiesFeatures);
        }
        propertiesAdapter.notifyDataSetChanged();

    }

    private void setSpinner(){
        select_room.add("Select Room");
        select_arrival.add("Select Arrival Date(Early Check In)");
        // Creating adapter for spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, select_room);

        // Drop down layout style - list view with radio button
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        fillBinding.type.setAdapter(typeAdapter);


        ArrayAdapter<String>  cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,select_arrival);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fillBinding.arrival.setAdapter(cityAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setClickListener(){
        fillBinding.back.setOnClickListener(this);
        fillBinding.btnRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.btn_request:
                startActivity(new Intent(this,BookingDetailPayment.class));
                break;
        }
    }
}
