package com.development.mycolive.views.fragment.favouriteBooking;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityFindRoomateBinding;
import com.development.mycolive.databinding.FragmentFavouriteRoomateBinding;
import com.development.mycolive.adapter.FindRoomateAdapter;
import com.development.mycolive.model.FindRoomateModel;
import com.development.mycolive.model.favourite.FavouriteApiResponse;
import com.development.mycolive.model.favourite.FavouritePropertyModel;
import com.development.mycolive.model.favourite.FavouriteRoomateApiResponse;
import com.development.mycolive.model.favourite.RoomateData;

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
    FavouriteViewModel viewModel;
    public FavouriteRoomate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        favouriteRoomateBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite_roomate, container, false);
        getFavuoriteList();
        return favouriteRoomateBinding.getRoot();
    }

    private void setRecyclerview(List<RoomateData> roomateDataList){
        roomateAdapter = new FindRoomateAdapter(getActivity(), roomateDataList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        favouriteRoomateBinding.recyclerView.setLayoutManager(mLayoutManager);
        favouriteRoomateBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        favouriteRoomateBinding.recyclerView.setAdapter(roomateAdapter);
    }


    private void getFavuoriteList(){
        String type = "ROOMMATE";
        viewModel = ViewModelProviders.of(getActivity()).get(FavouriteViewModel.class);

        viewModel.getFavouriteRoomate(getActivity(), type).observe(getActivity(), new Observer<FavouriteRoomateApiResponse>() {
            @Override
            public void onChanged(FavouriteRoomateApiResponse apiResponse) {

                //  ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if (apiResponse.response != null) {
                    List<RoomateData> roomateDataList = apiResponse.getResponse().getData();
                    setRecyclerview(roomateDataList);

                } else if (apiResponse.getStatus() == 401) {
                    Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
