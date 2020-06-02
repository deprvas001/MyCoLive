package com.development.mycolive.views.activity.roomate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.adapter.FindRoomateAdapter;
import com.development.mycolive.databinding.ActivityRoommateListBinding;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.views.activity.BaseActivity;

import java.util.List;

public class RoommateList extends BaseActivity implements View.OnClickListener {
ActivityRoommateListBinding listBinding ;
FindRoomateAdapter roomateAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommate_list);
        listBinding = DataBindingUtil.setContentView(this,R.layout.activity_roommate_list);
        initializeView();
    }

    private void initializeView(){
      /*  listBinding.toolbar.setTitle(getString(R.string.roommate_list));

        setSupportActionBar(listBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
          listBinding.back.setOnClickListener(this);

        if(getIntent()!=null){
            List<RoomateData> roomateDataList = getIntent().getParcelableArrayListExtra("roommateList");
            setRecyclerview(roomateDataList);
            listBinding.resultFound.setText(String.valueOf(roomateDataList.size())+" Result Found");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected (item);
    }


    private void setRecyclerview(List<RoomateData> roomateDataList){
        roomateAdapter = new FindRoomateAdapter(this, roomateDataList);
        mLayoutManager = new LinearLayoutManager(this);
        listBinding.recyclerView.setLayoutManager(mLayoutManager);
        listBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        listBinding.recyclerView.setAdapter(roomateAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
