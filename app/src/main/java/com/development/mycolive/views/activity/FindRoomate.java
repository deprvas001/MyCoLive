package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityFindRoomateBinding;
import com.development.mycolive.adapter.FindRoomateAdapter;
import com.development.mycolive.model.FindRoomateModel;

import java.util.ArrayList;
import java.util.List;

public class FindRoomate extends AppCompatActivity {
    private FindRoomateAdapter roomateAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<FindRoomateModel> roomateList = new ArrayList<>();
    ActivityFindRoomateBinding roomateBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roomateBinding = DataBindingUtil.setContentView(this,R.layout.activity_find_roomate);
        roomateBinding.toolbar.setTitle(getString(R.string.booking_detail));
        setSupportActionBar(roomateBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setRecyclerview();
        typeRoomate();

    }

    private void setRecyclerview(){
       /* roomateAdapter = new FindRoomateAdapter(FindRoomate.this, roomateList);
        mLayoutManager = new LinearLayoutManager(FindRoomate.this);
        roomateBinding.recyclerView.setLayoutManager(mLayoutManager);
        roomateBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        roomateBinding.recyclerView.setAdapter(roomateAdapter);*/
    }

    private void typeRoomate(){
        roomateList.clear();
        for (int i=0;i<4;i++){
            FindRoomateModel roomate = new FindRoomateModel("",
                    "","","","","");
            roomateList.add(roomate);
        }
        roomateAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
