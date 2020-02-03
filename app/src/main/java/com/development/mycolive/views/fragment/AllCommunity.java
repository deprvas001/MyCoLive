package com.development.mycolive.views.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentAllCommunityBinding;
import com.development.mycolive.databinding.FragmentProfileScreenOneBinding;
import com.development.mycolive.views.activity.BookingDetailFill;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.adapter.AllCommunityAdapter;
import com.development.mycolive.views.model.AllCommunityModel;
import com.development.mycolive.views.model.Find;
import com.development.mycolive.views.model.PropertiesFeatures;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCommunity extends Fragment {
FragmentAllCommunityBinding communityBinding;
    private AllCommunityAdapter communityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<String> select_room = new ArrayList<String>();
    List<String> select_arrival = new ArrayList<>();
    List<AllCommunityModel> communityList = new ArrayList<>();
    public AllCommunity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        communityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_community, container, false);
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.titleTxt.setText("Community");
        setRecyclerview();
        typeCommuity();
        return communityBinding.getRoot();
    }

    private void setRecyclerview(){
        communityAdapter = new AllCommunityAdapter(getActivity(), communityList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        communityBinding.recyclerView.setLayoutManager(mLayoutManager);
        communityBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        communityBinding.recyclerView.setAdapter(communityAdapter);
    }

    private void typeCommuity() {
        communityList.clear();

        for (int i = 0; i < 4; i++) {
            AllCommunityModel propertiesFeatures = new AllCommunityModel("",
                    "", "");
            communityList.add(propertiesFeatures);
        }
        communityAdapter.notifyDataSetChanged();
    }



}
