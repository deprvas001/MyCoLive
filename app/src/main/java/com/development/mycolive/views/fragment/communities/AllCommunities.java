package com.development.mycolive.views.fragment.communities;


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
import com.development.mycolive.databinding.FragmentAllCommunityBinding;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.adapter.AllCommunityAdapter;
import com.development.mycolive.model.AllCommunityModel;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.communityModel.CommunityApiResponse;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCommunities extends Fragment {
FragmentAllCommunityBinding communityBinding;
    private AllCommunityAdapter communityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<AllCommunityModel> communityList = new ArrayList<>();
    CommunitiesViewModel communityViewModel;
    public AllCommunities() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        communityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_community, container, false);
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.titleTxt.setText("Communities");
        getCommunity();
        return communityBinding.getRoot();
    }

    private void setRecyclerview(List<AllPost> allPostList){
        communityAdapter = new AllCommunityAdapter(getActivity(), allPostList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        communityBinding.recyclerView.setLayoutManager(mLayoutManager);
        communityBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        communityBinding.recyclerView.setAdapter(communityAdapter);
    }


    private void getCommunity(){
        String type = "General";

        communityViewModel = ViewModelProviders.of(getActivity()).get(CommunitiesViewModel.class);

        communityViewModel.getCommunityData(getActivity(),type).observe(getActivity(), new Observer<CommunityApiResponse>() {
            @Override
            public void onChanged(CommunityApiResponse communityApiResponse) {
                if(communityApiResponse.response !=null){
                 List<AllPost> allPostList  = communityApiResponse.getResponse().getData().getAllPost();
                 setRecyclerview(allPostList);
                }else if(communityApiResponse.getStatus()== 401){
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
             /*   bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }

}
