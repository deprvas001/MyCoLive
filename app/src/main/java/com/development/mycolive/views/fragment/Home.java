package com.development.mycolive.views.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentHomeBinding;
import com.development.mycolive.views.activity.HomeScreen;
import com.development.mycolive.views.activity.SearchResult;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.adapter.FindAdapter;
import com.development.mycolive.views.adapter.PropertiesAdapter;
import com.development.mycolive.views.model.Find;
import com.development.mycolive.views.model.PropertiesFeatures;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    FragmentHomeBinding homeBinding;
    List<Find> finds = new ArrayList<>();
    List<PropertiesFeatures> featuresList = new ArrayList<>();
    private FindAdapter mAdapter;
    private PropertiesAdapter propertiesAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    View view;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        initializeView();
        setReyclerView();
        typeRoom();
        return homeBinding.getRoot();
    }

    private void setReyclerView() {

        mAdapter = new FindAdapter(getActivity(), finds);
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        homeBinding.recyclerView.setLayoutManager(mLayoutManager);
        homeBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerView.setAdapter(mAdapter);

        propertiesAdapter = new PropertiesAdapter(getActivity(), featuresList);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        homeBinding.recyclerViewFind.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewFind.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewFind.setAdapter(propertiesAdapter);

        propertiesAdapter = new PropertiesAdapter(getActivity(), featuresList);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        homeBinding.recyclerViewHot.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewHot.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewHot.setAdapter(propertiesAdapter);

        propertiesAdapter = new PropertiesAdapter(getActivity(), featuresList);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        homeBinding.recyclerViewArea.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewArea.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewArea.setAdapter(propertiesAdapter);
    }

    private void typeRoom(){
        finds.clear();
        featuresList.clear();

        Find find_room = new Find("Room");
        finds.add(find_room);

        find_room = new Find("Apartment");
        finds.add(find_room);

        find_room = new Find("Roommate");
        finds.add(find_room);

        for (int i=0;i<4;i++){
            PropertiesFeatures propertiesFeatures = new PropertiesFeatures("4.2",
                    "South Yara","1 Day Ago","$1200","9");
            featuresList.add(propertiesFeatures);
        }
        mAdapter.notifyDataSetChanged();
        propertiesAdapter.notifyDataSetChanged();
    }

    private void initializeView(){
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.titleTxt.setText("Home Screen");
        homeBinding.btnFeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), SearchResult.class));
            }
        });
    }
}
