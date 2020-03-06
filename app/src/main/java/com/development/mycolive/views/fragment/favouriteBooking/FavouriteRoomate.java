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
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityFindRoomateBinding;
import com.development.mycolive.databinding.FragmentFavouriteRoomateBinding;
import com.development.mycolive.adapter.FindRoomateAdapter;
import com.development.mycolive.model.FindRoomateModel;
import com.development.mycolive.model.favourite.FavouriteApiResponse;
import com.development.mycolive.model.favourite.FavouritePropertyModel;
import com.development.mycolive.model.favourite.FavouriteRoomateApiResponse;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    SessionManager session;;
    public FavouriteRoomate() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        favouriteRoomateBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite_roomate, container, false);
        getSession();
        return favouriteRoomateBinding.getRoot();
    }

    private void setRecyclerview(List<RoomateData> roomateDataList){
        roomateAdapter = new FindRoomateAdapter(getActivity(), roomateDataList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        favouriteRoomateBinding.recyclerView.setLayoutManager(mLayoutManager);
        favouriteRoomateBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        favouriteRoomateBinding.recyclerView.setAdapter(roomateAdapter);
    }


    private void getFavuoriteList(String token){
        String type = "ROOMMATE";

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(getActivity()).get(FavouriteViewModel.class);

        viewModel.getFavouriteRoomate(getActivity(), headers,type).observe(getActivity(), new Observer<FavouriteRoomateApiResponse>() {
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

    private void getSession() {
        session = new SessionManager(getActivity());
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        String token = user.get(SessionManager.KEY_TOKEN);

        getFavuoriteList(token);
    }
    }
