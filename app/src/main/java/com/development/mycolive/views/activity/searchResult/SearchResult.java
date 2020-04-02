package com.development.mycolive.views.activity.searchResult;

import android.content.Intent;
import android.os.Bundle;

import com.development.mycolive.databinding.ActivitySearchResultBinding;
import com.development.mycolive.model.homeProperty.FeatureApiResponse;
import com.development.mycolive.views.activity.PropertyMap;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.adapter.SearchScreenAdapter;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
ActivitySearchResultBinding resultBinding;
    ArrayList<HomeFeatureProperty> searchList = new ArrayList<>();
    private SearchScreenAdapter searchAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    SearchViewModel searchViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultBinding = DataBindingUtil.setContentView(this,R.layout.activity_search_result);
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
       String type = "FEATUREDPROPERTY";
       String offset = "0";
       String per_page= "10";

       searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

       searchViewModel.getData(this,type,offset,per_page).observe(this, new Observer<FeatureApiResponse>() {
           @Override
           public void onChanged(FeatureApiResponse homeApiResponse) {
               if (homeApiResponse.response != null) {

                   ArrayList<HomeFeatureProperty> homeFeaturePropertyList = homeApiResponse.getResponse().getData();
                   searchList.clear();
                   homeApiResponse.getResponse().getData();
                   searchList = homeFeaturePropertyList;

                 /*  List<HomeFeatureProperty> featurePropertyList =   homeApiResponse.getResponse().getData().getFeaturedPropertyList();
                   searchList = featurePropertyList;*/
                   setReyclerView(homeFeaturePropertyList);

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
}
