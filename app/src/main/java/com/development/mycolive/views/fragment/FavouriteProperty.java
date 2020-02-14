package com.development.mycolive.views.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentFavouritePropertyBinding;
import com.development.mycolive.adapter.SearchScreenAdapter;
import com.development.mycolive.model.SearchResultModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteProperty extends Fragment implements View.OnClickListener {
    FragmentFavouritePropertyBinding propertyBinding;
    List<SearchResultModel> searchList = new ArrayList<>();
    private SearchScreenAdapter searchAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    public FavouriteProperty() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        propertyBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite_property, container, false);
        setClickListener();
        setReyclerView();
        setSearchListItem();
        return propertyBinding.getRoot();
    }

    private void setClickListener(){
        propertyBinding.fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;

        }
    }


    private void setReyclerView(){
/*        searchAdapter = new SearchScreenAdapter(getActivity(), searchList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        propertyBinding.contentSearch.recyclerView.setLayoutManager(mLayoutManager);
        propertyBinding.contentSearch.recyclerView.setItemAnimator(new DefaultItemAnimator());
        propertyBinding.contentSearch.recyclerView.setAdapter(searchAdapter);

        propertyBinding.contentSearch.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                propertyBinding.contentSearch.recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                SearchResultModel resultModel = searchList.get(position);
                startActivity(new Intent(getActivity(), RoomDetail.class));
                // Toast.makeText(getApplicationContext(), resultModel.getAddress() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
    }

    private void setSearchListItem(){
      /*  searchList.clear();

        for(int i=0;i<5;i++){
            SearchResultModel resultModel = new SearchResultModel("","JMD Megapolish","");
            searchList.add(resultModel);
        }

        searchAdapter.notifyDataSetChanged();*/
    }

}
