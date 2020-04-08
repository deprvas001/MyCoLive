package com.development.mycolive.views.fragment.homeFragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentHomeBinding;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.model.home.RoomateApiResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.roomate.RoommateList;
import com.development.mycolive.views.activity.searchResult.SearchResult;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.adapter.AreaPropertyAdapter;
import com.development.mycolive.adapter.FindAdapter;
import com.development.mycolive.adapter.HomeSlideAdapter;
import com.development.mycolive.adapter.HotPropertyAdapter;
import com.development.mycolive.adapter.PropertiesAdapter;
import com.development.mycolive.model.home.CountData;
import com.development.mycolive.model.home.HomeApiResponse;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.home.HomeHotProperty;
import com.development.mycolive.model.home.HomePropertyArea;
import com.development.mycolive.model.home.HomeSlider;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding homeBinding;
    SessionManager session;
    String token;
    Intent intent;
    private HomeViewModel homeViewModel;
    private PropertiesAdapter propertiesAdapter;
    private HotPropertyAdapter hotPropertyAdapter;
    private AreaPropertyAdapter areaPropertyAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FindAdapter mAdapter;
    private double mSubTotal = 0;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initializeView();
        getData();
        // typeRoom();homeApiResponse
        return homeBinding.getRoot();
    }

    private void setReyclerView(List<HomeFeatureProperty> featurePropertyList,
                                List<HomeFeatureProperty> hotPropertyList,
                                List<HomePropertyArea> homePropertyAreaList,
                                List<HomeSlider> sliderList,CountData countData ) {

        propertiesAdapter = new PropertiesAdapter(getActivity(), featurePropertyList);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        homeBinding.recyclerViewFind.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewFind.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewFind.setAdapter(propertiesAdapter);

        hotPropertyAdapter = new HotPropertyAdapter(getActivity(), hotPropertyList);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        homeBinding.recyclerViewHot.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewHot.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewHot.setAdapter(hotPropertyAdapter);

        areaPropertyAdapter = new AreaPropertyAdapter(getActivity(), homePropertyAreaList);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        homeBinding.recyclerViewArea.setLayoutManager(mLayoutManager);
        homeBinding.recyclerViewArea.setItemAnimator(new DefaultItemAnimator());
        homeBinding.recyclerViewArea.setAdapter(areaPropertyAdapter);

        setSliderAndView(sliderList,countData);
    }


    private void initializeView() {
        ((ShowHomeScreen) getActivity()).screenBinding.appBar.titleTxt.setText("Home Screen");
        homeBinding.btnFeature.setOnClickListener(this);
        homeBinding.hotPropertyBtn.setOnClickListener(this);
        homeBinding.btnPropertyArea.setOnClickListener(this);

        homeBinding.roomApartment.roomLayout.setOnClickListener(this);
        homeBinding.roomApartment.roomateLayout.setOnClickListener(this);
        homeBinding.roomApartment.apartmentLayout.setOnClickListener(this);
    }

    private void getData() {
        String type = "";

        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        homeViewModel.getData(getActivity(), type).observe(getActivity(), new Observer<HomeApiResponse>() {
            @Override
            public void onChanged(HomeApiResponse homeApiResponse) {
                if (homeApiResponse.response != null) {

                    List<HomeFeatureProperty> featurePropertyList = homeApiResponse.getResponse().getData().getFeaturedPropertyList();
                    List<HomeFeatureProperty> hotPropertyList = homeApiResponse.getResponse().getData().getHotProperty();
                    List<HomePropertyArea> homePropertyAreaList = homeApiResponse.getResponse().getData().getPropertyAreaList();
                    List<HomeSlider> sliderList = homeApiResponse.getResponse().getData().getSliderList();
                    CountData countData = homeApiResponse.getResponse().getData().getCountData();
                    setReyclerView(featurePropertyList, hotPropertyList, homePropertyAreaList, sliderList,countData);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_feature:
                if( ((ShowHomeScreen) getActivity()).isNetworkAvailable(getActivity())){
                    intent = new Intent(getActivity(),SearchResult.class);
                    intent.putExtra("type","FEATUREDPROPERTY");
                    getActivity().startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.hot_property_btn:
                if( ((ShowHomeScreen) getActivity()).isNetworkAvailable(getActivity())){
                    intent = new Intent(getActivity(),SearchResult.class);
                    intent.putExtra("type","FEATUREDPROPERTY");
                    getActivity().startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_property_area:

                if( ((ShowHomeScreen) getActivity()).isNetworkAvailable(getActivity())){
                    intent = new Intent(getActivity(),SearchResult.class);
                    intent.putExtra("type","PROPERTYBYAREA");
                    getActivity().startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.room_layout:
                if( ((ShowHomeScreen) getActivity()).isNetworkAvailable(getActivity())){
                    intent = new Intent(getActivity(),SearchResult.class);
                    intent.putExtra("type","ROOM");
                    getActivity().startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }

                break;


            case R.id.apartment_layout:

                if( ((ShowHomeScreen) getActivity()).isNetworkAvailable(getActivity())){
                    intent = new Intent(getActivity(),SearchResult.class);
                    intent.putExtra("type","ALLPROPERTY");
                    getActivity().startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.roomate_layout:
                if( ((ShowHomeScreen) getActivity()).isNetworkAvailable(getActivity())){
                    getSession();
                }else{
                    Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void setSliderAndView(List<HomeSlider> sliderList,CountData countData) {
        final HomeSlideAdapter adapter = new HomeSlideAdapter(getActivity(), sliderList);
        adapter.setCount(sliderList.size());

        homeBinding.imageSlider.setSliderAdapter(adapter);

        homeBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        homeBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        homeBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        homeBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        homeBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        homeBinding.imageSlider.startAutoCycle();

        homeBinding.imageSlider.setOnIndicatorClickListener(position ->
                homeBinding.imageSlider.setCurrentPagePosition(position));

        homeBinding.roomApartment.room.setText(countData.getTotal_room()+" Room");
        homeBinding.roomApartment.roomMate.setText(countData.getTotal_roommate()+ " Roommate");
        homeBinding.roomApartment.apartment.setText(countData.getTotal_apart()+" Apartment");
    }


    private void getRoommateData() {
        ((ShowHomeScreen) getActivity()).showProgressDialog(getResources().getString(R.string.loading));

        String type = "ROOMMATE";

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        homeViewModel.getRoomateData(getActivity(),headers, type).observe(getActivity(), new Observer<RoomateApiResponse>() {
            @Override
            public void onChanged(RoomateApiResponse apiResponse) {
                ((ShowHomeScreen) getActivity()).hideProgressDialog();

                if (apiResponse.response != null) {
                    if(apiResponse.getResponse().getStatus() ==1){
                        ArrayList<RoomateData> roommateList = apiResponse.getResponse().getData().getData();
                        if(type.equalsIgnoreCase("Roommate")){
                            Intent intent = new Intent(getActivity(), RoommateList.class);
                            intent.putParcelableArrayListExtra("roommateList",roommateList);
                            startActivity(intent);

                        }
                    }

                }else{
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getSession(){
        session = new SessionManager(getActivity());
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);

        getRoommateData();
    }

}
