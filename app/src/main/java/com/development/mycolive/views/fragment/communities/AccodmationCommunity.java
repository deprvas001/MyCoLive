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
import com.development.mycolive.adapter.AllCommunityAdapter;
import com.development.mycolive.databinding.FragmentAccodmationCommunityBinding;
import com.development.mycolive.model.AllCommunityModel;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.communityModel.CommunityApiResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccodmationCommunity extends Fragment {
    FragmentAccodmationCommunityBinding accodmationBinding;
    private AllCommunityAdapter communityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    CommunitiesViewModel communityViewModel;

    public AccodmationCommunity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        accodmationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_accodmation_community, container, false);
        getCommunity();
        return accodmationBinding.getRoot();

    }

    private void getCommunity() {
        String type = "ACCOMODATION";

        communityViewModel = ViewModelProviders.of(getActivity()).get(CommunitiesViewModel.class);

        communityViewModel.getCommunityData(getActivity(), type).observe(getActivity(), new Observer<CommunityApiResponse>() {
            @Override
            public void onChanged(CommunityApiResponse communityApiResponse) {
                if (communityApiResponse.response != null) {
                    List<AllPost> allPostList = communityApiResponse.getResponse().getData();
                    setRecyclerview(allPostList);
                } else if (communityApiResponse.getStatus() == 401) {
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
             /*   bookingBinding.shimmerViewContainer.stopShimmer();
                  bookingBinding.shimmerViewContainer.setVisibility(View.GONE); */
            }
        });
    }

    private void setRecyclerview(List<AllPost> allPostList) {
        communityAdapter = new AllCommunityAdapter(getActivity(), allPostList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        accodmationBinding.recyclerView.setLayoutManager(mLayoutManager);
        accodmationBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        accodmationBinding.recyclerView.setAdapter(communityAdapter);
    }
}