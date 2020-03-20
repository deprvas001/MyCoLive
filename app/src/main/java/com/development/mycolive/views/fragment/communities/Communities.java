package com.development.mycolive.views.fragment.communities;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentCommunityBinding;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.communityModel.SearchCommunityApiResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.postScreen.NewPost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Headers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Communities extends Fragment implements View.OnClickListener {
  public  FragmentCommunityBinding fragmentCommunityBinding;
    CommunitiesViewModel communityViewModel;
    private AllCommunityAdapter communityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    SessionManager session;

    String token="";
    public Communities() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCommunityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_community, container, false);
        setClickListener();
        getSession();
       // getCommunity("ALL");
      //  loadFragment(new AllCommunities());
        return fragmentCommunityBinding.getRoot();
    }
/*

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }
*/

    private void setClickListener() {
        fragmentCommunityBinding.fab.setOnClickListener(this);
        fragmentCommunityBinding.allCommunity.setOnClickListener(this);
        fragmentCommunityBinding.general.setOnClickListener(this);
        fragmentCommunityBinding.accodmation.setOnClickListener(this);
        fragmentCommunityBinding.searchEdit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startActivity(new Intent(getActivity(), NewPost.class));
                break;

            case R.id.all_community:
                setViewBackground(view);
                getCommunity("ALL");
                //loadFragment(new AllCommunities());
                break;

            case R.id.general:
                setViewBackground(view);
                getCommunity("GENERAL");
            //    loadFragment(new GeneralCommunity());
                break;

            case R.id.accodmation:
                setViewBackground(view);
                getCommunity("ACCOMODATION");
              //  loadFragment(new AccodmationCommunity());
                break;

            case R.id.search_edit:
                if(!fragmentCommunityBinding.searchEdit.getText().toString().isEmpty()){
                    setViewBackground(view);
                    getSearchCommunity("General");
                }
                
                break;
        }
    }

    private void setViewBackground(View view) {
        switch (view.getId()) {
            case R.id.general:
                fragmentCommunityBinding.general.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                fragmentCommunityBinding.general.setTextColor(getResources().getColor(R.color.white));
                fragmentCommunityBinding.allCommunity.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.allCommunity.setBackground(null);
                fragmentCommunityBinding.accodmation.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.accodmation.setBackground(null);
                break;

            case R.id.all_community:
                fragmentCommunityBinding.allCommunity.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                fragmentCommunityBinding.allCommunity.setTextColor(getResources().getColor(R.color.white));
                fragmentCommunityBinding.general.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.general.setBackground(null);
                fragmentCommunityBinding.accodmation.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.accodmation.setBackground(null);
                break;

            case R.id.accodmation:
                fragmentCommunityBinding.accodmation.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                fragmentCommunityBinding.accodmation.setTextColor(getResources().getColor(R.color.white));
                fragmentCommunityBinding.allCommunity.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.allCommunity.setBackground(null);
                fragmentCommunityBinding.general.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.general.setBackground(null);
                break;

            default:
                fragmentCommunityBinding.allCommunity.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                fragmentCommunityBinding.allCommunity.setTextColor(getResources().getColor(R.color.white));
                fragmentCommunityBinding.general.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.general.setBackground(null);
                fragmentCommunityBinding.accodmation.setTextColor(getResources().getColor(R.color.login_subheading));
                fragmentCommunityBinding.accodmation.setBackground(null);

                break;
        }
    }

    private void getSearchCommunity(String type){
        ((ShowHomeScreen) getActivity()).showProgressDialog(getResources().getString(R.string.loading));

        communityViewModel = ViewModelProviders.of(getActivity()).get(CommunitiesViewModel.class);

        communityViewModel.getSearchData(getActivity(),type).observe(getActivity(), new Observer<SearchCommunityApiResponse>() {
            @Override
            public void onChanged(SearchCommunityApiResponse apiResponse) {
                ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if(apiResponse.communityResponse !=null){
                    List<AllPost> allPostList  = apiResponse.communityResponse.getData().getAllpost();
                      setRecyclerview(allPostList);
                }else if(apiResponse.getStatus()== 401){
                      Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }else{
                     Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
             /*   bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }

    private void setRecyclerview(List<AllPost> allPostList){
        communityAdapter = new AllCommunityAdapter(getActivity(), allPostList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        fragmentCommunityBinding.recyclerView.setLayoutManager(mLayoutManager);
        fragmentCommunityBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentCommunityBinding.recyclerView.setAdapter(communityAdapter);
    }

    private void getCommunity(String type){
        ((ShowHomeScreen) getActivity()).   showProgressDialog(getResources().getString(R.string.loading));
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        communityViewModel = ViewModelProviders.of(getActivity()).get(CommunitiesViewModel.class);

        communityViewModel.getCommunityData(getActivity(),headers,type).observe(getActivity(), new Observer<CommunityApiResponse>() {
            @Override
            public void onChanged(CommunityApiResponse communityApiResponse) {
                ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if(communityApiResponse.response !=null){
                    List<AllPost> allPostList  = communityApiResponse.getResponse().getData();
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

        getCommunity("ALL");

     //   getBooking(token);
    }
}
