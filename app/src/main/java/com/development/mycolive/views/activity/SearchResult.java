package com.development.mycolive.views.activity;

import android.content.Intent;
import android.os.Bundle;

import com.development.mycolive.databinding.ActivitySearchResultBinding;
import com.development.mycolive.views.adapter.FindAdapter;
import com.development.mycolive.views.adapter.PropertiesAdapter;
import com.development.mycolive.views.adapter.SearchScreenAdapter;
import com.development.mycolive.views.clickListener.RecyclerTouchListener;
import com.development.mycolive.views.model.PropertiesFeatures;
import com.development.mycolive.views.model.SearchResultModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResult extends AppCompatActivity implements View.OnClickListener {
ActivitySearchResultBinding resultBinding;
    List<SearchResultModel> searchList = new ArrayList<>();
    private SearchScreenAdapter searchAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultBinding = DataBindingUtil.setContentView(this,R.layout.activity_search_result);
        initializeView();
        setClickListener();
        setReyclerView();
        setSearchListItem();
    }

    private void initializeView(){
     /*   resultBinding.toolbar.setTitle(getString(R.string.search_result));
        setSupportActionBar(resultBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/



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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

            case R.id.back:
                finish();
                break;

            case R.id.notification:
                startActivity(new Intent(this,Notification.class));
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

    private void setReyclerView(){
        searchAdapter = new SearchScreenAdapter(SearchResult.this, searchList);
        mLayoutManager = new LinearLayoutManager(this);
        resultBinding.contentSearch.recyclerView.setLayoutManager(mLayoutManager);
        resultBinding.contentSearch.recyclerView.setItemAnimator(new DefaultItemAnimator());
        resultBinding.contentSearch.recyclerView.setAdapter(searchAdapter);

        resultBinding.contentSearch.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                resultBinding.contentSearch.recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SearchResultModel resultModel = searchList.get(position);
                startActivity(new Intent(SearchResult.this,RoomDetail.class));
               // Toast.makeText(getApplicationContext(), resultModel.getAddress() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setSearchListItem(){
        searchList.clear();

        for(int i=0;i<5;i++){
            SearchResultModel resultModel = new SearchResultModel("","JMD Megapolish","");
            searchList.add(resultModel);
        }

        searchAdapter.notifyDataSetChanged();
    }
}
