package com.development.mycolive.views.activity.searchResult;

import android.content.Intent;
import android.os.Bundle;

import com.development.mycolive.adapter.AreaPropertyAdapter;
import com.development.mycolive.adapter.AreaPropertyListAdapter;
import com.development.mycolive.databinding.ActivitySearchResultBinding;
import com.development.mycolive.model.home.HomePropertyArea;
import com.development.mycolive.model.homeProperty.FeatureApiResponse;
import com.development.mycolive.views.activity.PropertyMap;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.adapter.SearchScreenAdapter;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.views.fragment.filterSearch.Search;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
ActivitySearchResultBinding resultBinding;
    ArrayList<HomeFeatureProperty> searchList = new ArrayList<>();
    private SearchScreenAdapter searchAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private AreaPropertyListAdapter areaPropertyAdapter;
    SearchViewModel searchViewModel;
    String type="";
    String post_code="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultBinding = DataBindingUtil.setContentView(this,R.layout.activity_search_result);
       if(getIntent()!=null){
           Bundle bundle = getIntent().getExtras();
          if(bundle.containsKey("type")){
              type = bundle.getString("type");
           }
          if(bundle.containsKey("post_code")){
             post_code = bundle.getString("post_code");
          } }
        setClickListener();
        getPropertyList();
    }


    private void setClickListener(){
        resultBinding.fab.setOnClickListener(this);
        resultBinding.back.setOnClickListener(this);
        resultBinding.notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                 Intent intent = new Intent(SearchResult.this, PropertyMap.class);
                 intent.putParcelableArrayListExtra("search_list",searchList);
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
        searchAdapter = new SearchScreenAdapter(SearchResult.this, featurePropertyList);
        mLayoutManager = new LinearLayoutManager(this);
        resultBinding.contentSearch.recyclerView.setLayoutManager(mLayoutManager);
        resultBinding.contentSearch.recyclerView.setItemAnimator(new DefaultItemAnimator());
        resultBinding.contentSearch.recyclerView.setAdapter(searchAdapter);
    }

   /* private void setSearchListItem(){
        searchList.clear();

        for(int i=0;i<5;i++){
            SearchResultModel resultModel = new SearchResultModel("","JMD Megapolish","");
            searchList.add(resultModel);
        }

        searchAdapter.notifyDataSetChanged();
    }*/

   private void getPropertyList(){
       String offset = "0";
       String per_page= "10";

       Map<String,String> params = new HashMap<>();
       params.put("type",type);
       if(!post_code.isEmpty()){
           params.put("post_code",post_code);
       }

       searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

       searchViewModel.getData(this,params,offset,per_page).observe(this, new Observer<FeatureApiResponse>() {
           @Override
           public void onChanged(FeatureApiResponse homeApiResponse) {
               if (homeApiResponse.response != null) {

                   ArrayList<HomeFeatureProperty> homeFeaturePropertyList = homeApiResponse.getResponse().getData();
                   searchList.clear();
                   homeApiResponse.getResponse().getData();
                   searchList = homeFeaturePropertyList;

                 /*  List<HomeFeatureProperty> featurePropertyList =   homeApiResponse.getResponse().getData().getFeaturedPropertyList();
                   searchList = featurePropertyList;*/

                 if(type.equals("PROPERTYBYAREA") && post_code.isEmpty()){
                     setRecyclereAdapter(homeFeaturePropertyList);
                 }else{
                     setReyclerView(homeFeaturePropertyList);
                 }

               }
               resultBinding.contentSearch.shimmerViewContainer.stopShimmer();
               resultBinding.contentSearch.shimmerViewContainer.setVisibility(View.GONE);
           }
       });
   }

    @Override
    public void onResume() {
        super.onResume();
        resultBinding.contentSearch.shimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        resultBinding.contentSearch.shimmerViewContainer.stopShimmer();
        super.onPause();
    }

    private void setRecyclereAdapter(List<HomeFeatureProperty> featurePropertyList){
        resultBinding.fab.setVisibility(View.GONE);
        List<HomePropertyArea> homePropertyAreaList =new ArrayList<>();
        HomePropertyArea propertyArea;
        for(int i=0;i<featurePropertyList.size();i++){
            propertyArea = new HomePropertyArea();
            propertyArea.setCity(featurePropertyList.get(i).getCity());
            propertyArea.setPost_code(featurePropertyList.get(i).getPost_code());
            propertyArea.setCount(featurePropertyList.get(i).getCount());
            propertyArea.setImage(featurePropertyList.get(i).getImage());

            homePropertyAreaList.add(propertyArea);
        }

        if(homePropertyAreaList.size()>0){
            areaPropertyAdapter = new AreaPropertyListAdapter(SearchResult.this, homePropertyAreaList);
            mLayoutManager = new LinearLayoutManager(this);
            resultBinding.contentSearch.recyclerView.setLayoutManager(mLayoutManager);
            resultBinding.contentSearch.recyclerView.setItemAnimator(new DefaultItemAnimator());
            resultBinding.contentSearch.recyclerView.setAdapter(areaPropertyAdapter);
        }


    }
}
