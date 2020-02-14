package com.development.mycolive.views.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityFindRoomateBinding;
import com.development.mycolive.databinding.FragmentFavouriteRoomateBinding;
import com.development.mycolive.adapter.FindRoomateAdapter;
import com.development.mycolive.model.FindRoomateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteRoomate extends Fragment {
    private FindRoomateAdapter roomateAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<FindRoomateModel> roomateList = new ArrayList<>();
    ActivityFindRoomateBinding roomateBinding;
    FragmentFavouriteRoomateBinding favouriteRoomateBinding;

    public FavouriteRoomate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        favouriteRoomateBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite_roomate, container, false);
        setRecyclerview();
        typeRoomate();
        return favouriteRoomateBinding.getRoot();
    }

    private void setRecyclerview(){
        roomateAdapter = new FindRoomateAdapter(getActivity(), roomateList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        favouriteRoomateBinding.recyclerView.setLayoutManager(mLayoutManager);
        favouriteRoomateBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        favouriteRoomateBinding.recyclerView.setAdapter(roomateAdapter);
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



}
