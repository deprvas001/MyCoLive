package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.adapter.SearchScreenAdapter;
import com.development.mycolive.databinding.ActivityFilterResultPropertyBinding;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.views.activity.searchResult.SearchResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FilterResultProperty extends AppCompatActivity implements View.OnClickListener {
ActivityFilterResultPropertyBinding  propertyBinding;
    private SearchScreenAdapter searchAdapter;
    String token = "";
    SessionManager session;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<HomeFeatureProperty> propertyList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        propertyBinding = DataBindingUtil.setContentView(this,R.layout.activity_filter_result_property);
        setClickListener();

        if(getIntent()!=null){

            propertyList = getIntent().getParcelableArrayListExtra("property");
            propertyBinding.contentSearch.count.setText(String.valueOf(propertyList.size()) +" "+getString(R.string.search_result));
            setReyclerView(propertyList);
        }
    }

    private void setClickListener(){
        propertyBinding.fab.setOnClickListener(this);
        propertyBinding.back.setOnClickListener(this);
        propertyBinding.notification.setOnClickListener(this);

        getSession();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Intent intent = new Intent(FilterResultProperty.this, PropertyMap.class);
                intent.putParcelableArrayListExtra("search_list",propertyList);
                startActivity(intent);
                break;

            case R.id.back:
                finish();
                break;

            case R.id.notification:
                startActivity(new Intent(this, Notification.class));
                break;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setReyclerView(List<HomeFeatureProperty> featurePropertyList){
        propertyBinding.contentSearch.shimmerViewContainer.stopShimmer();
        propertyBinding.contentSearch.shimmerViewContainer.setVisibility(View.GONE);

        searchAdapter = new SearchScreenAdapter(FilterResultProperty.this, featurePropertyList,token);
        mLayoutManager = new LinearLayoutManager(this);
        propertyBinding.contentSearch.recyclerView.setLayoutManager(mLayoutManager);
        propertyBinding.contentSearch.recyclerView.setItemAnimator(new DefaultItemAnimator());
        propertyBinding.contentSearch.recyclerView.setAdapter(searchAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        propertyBinding.contentSearch.shimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        propertyBinding.contentSearch.shimmerViewContainer.stopShimmer();
        super.onPause();
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
    }
}
