package com.development.mycolive.views.fragment.homeFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.development.mycolive.views.adapter.AreaPropertyAdapter;
import com.development.mycolive.views.adapter.FindAdapter;
import com.development.mycolive.views.adapter.HotPropertyAdapter;
import com.development.mycolive.views.adapter.PropertiesAdapter;
import com.development.mycolive.views.fragment.booking.BookingViewModel;
import com.development.mycolive.views.model.Find;
import com.development.mycolive.views.model.PropertiesFeatures;
import com.development.mycolive.views.model.booking.BookingApiResponse;
import com.development.mycolive.views.model.booking.BookingData;
import com.development.mycolive.views.model.home.HomeApiResponse;
import com.development.mycolive.views.model.home.HomeFeatureProperty;
import com.development.mycolive.views.model.home.HomeHotProperty;
import com.development.mycolive.views.model.home.HomePropertyArea;

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
    List<BookingData> bookingList = new ArrayList<>();
    HomeViewModel homeViewModel;
    private PropertiesAdapter propertiesAdapter;
    private HotPropertyAdapter hotPropertyAdapter;
    private AreaPropertyAdapter areaPropertyAdapter;
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
       // setReyclerView();
        getData();
       // typeRoom();homeApiResponse
        return homeBinding.getRoot();
    }

    private void setReyclerView(List<HomeFeatureProperty> featurePropertyList,
                                List<HomeHotProperty> hotPropertyList,List<HomePropertyArea> homePropertyAreaList) {

    /*    mAdapter = new FindAdapter(getActivity(), finds);
        mLayoutManager = new GridLayoutManager(getActivity(),3);
        homeBinding.recyclerView.setLayoutManager(mLayoutManager);
        homeBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerView.setAdapter(mAdapter);*/

        propertiesAdapter = new PropertiesAdapter(getActivity(), featurePropertyList);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        homeBinding.recyclerViewFind.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewFind.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewFind.setAdapter(propertiesAdapter);

        hotPropertyAdapter = new HotPropertyAdapter(getActivity(), hotPropertyList);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        homeBinding.recyclerViewHot.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewHot.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewHot.setAdapter(hotPropertyAdapter);

        areaPropertyAdapter = new AreaPropertyAdapter(getActivity(), homePropertyAreaList);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        homeBinding.recyclerViewArea.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewArea.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewArea.setAdapter(propertiesAdapter);


      /*
        propertiesAdapter = new PropertiesAdapter(getActivity(), featuresList);
        mLayoutManager = new GridLayoutManager(getContext(),2);
        homeBinding.recyclerViewArea.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewArea.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewArea.setAdapter(propertiesAdapter);*/
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

    private void getData() {
        String type = "";

        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        homeViewModel.getData(getActivity(),type).observe(getActivity(), new Observer<HomeApiResponse>() {
            @Override
            public void onChanged(HomeApiResponse homeApiResponse) {
                if (homeApiResponse.response != null) {

                    List<HomeFeatureProperty> featurePropertyList =   homeApiResponse.getResponse().getData().getFeaturedPropertyList();
                    List<HomeHotProperty> hotPropertyList = homeApiResponse.getResponse().getData().getHotPropertyList();
                    List<HomePropertyArea> homePropertyAreaList = homeApiResponse.getResponse().getData().getPropertyAreaList();
                    setReyclerView(featurePropertyList,hotPropertyList,homePropertyAreaList);
               /*  bookingList.clear();
                    bookingList = bookingApiResponse.response.getBookingDataList();
                    setRecycleView();*/

                }
                homeBinding.shimmerViewContainer.stopShimmer();
                homeBinding.shimmerViewContainer.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        homeBinding.shimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        homeBinding.shimmerViewContainer.stopShimmer();
        super.onPause();

    }
}
