package com.development.mycolive.views.activity.myCommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.adapter.AllCommunityAdapter;
import com.development.mycolive.adapter.MyCommunityAdapter;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityMyCommunityBinding;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.MyCommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.MyPostComment;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.postScreen.NewPost;
import com.development.mycolive.views.fragment.communities.CommunitiesViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCommunity extends BaseActivity implements View.OnClickListener {
ActivityMyCommunityBinding communityBinding;
    SessionManager session;
    MyCommunityViewModel viewModel;
    MyCommunityAdapter communityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<MyPostComment> commentList =new ArrayList<>();
    List<MyPostComment>  likeList =new ArrayList<>();
    String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communityBinding = DataBindingUtil.setContentView(this,R.layout.activity_my_community);
        setClickListner();
        getSession();
    }


    private void getSession(){
        session = new SessionManager(MyCommunity.this);
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


    private void getCommunity(String type){
       showProgressDialog(getResources().getString(R.string.loading));
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(this).get(MyCommunityViewModel.class);

        viewModel.getCommunityData(this,headers,type).observe(this, new Observer<MyCommunityApiResponse>() {
            @Override
            public void onChanged(MyCommunityApiResponse communityApiResponse) {
               hideProgressDialog();
                if(communityApiResponse.response !=null){
                    commentList.clear();
                    likeList.clear();
                    likeList = communityApiResponse.getResponse().getData().getMy_liked();
                    commentList = communityApiResponse.getResponse().getData().getMy_comments();
                  List<MyPostComment> postCommentList  =communityApiResponse.getResponse().getData().getMy_post();
                 //   List<AllPost> allPostList  = communityApiResponse.getResponse().getData();
                   setRecyclerview(postCommentList);
                }else if(communityApiResponse.getStatus()== 401){
                   // Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }else{
                  //  Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
             /*   bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }

    private void setViewBackground(View view) {
        switch (view.getId()) {
            case R.id.general:
                communityBinding.general.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                communityBinding.general.setTextColor(getResources().getColor(R.color.white));
                communityBinding.allCommunity.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.allCommunity.setBackground(null);
                communityBinding.accodmation.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.accodmation.setBackground(null);
                break;

            case R.id.all_community:
                communityBinding.allCommunity.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                communityBinding.allCommunity.setTextColor(getResources().getColor(R.color.white));
                communityBinding.general.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.general.setBackground(null);
                communityBinding.accodmation.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.accodmation.setBackground(null);
                break;

            case R.id.accodmation:
                communityBinding.accodmation.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                communityBinding.accodmation.setTextColor(getResources().getColor(R.color.white));
                communityBinding.allCommunity.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.allCommunity.setBackground(null);
                communityBinding.general.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.general.setBackground(null);
                break;

            default:
                communityBinding.allCommunity.setBackground(getResources().getDrawable(R.drawable.booking_background_selected));
                communityBinding.allCommunity.setTextColor(getResources().getColor(R.color.white));
                communityBinding.general.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.general.setBackground(null);
                communityBinding.accodmation.setTextColor(getResources().getColor(R.color.login_subheading));
                communityBinding.accodmation.setBackground(null);

                break;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.all_community:
                setViewBackground(view);
                getCommunity("ALL");
                //loadFragment(new AllCommunities());
                break;

            case R.id.general:
                setViewBackground(view);
                setRecyclerview(commentList);
                //    loadFragment(new GeneralCommunity());
                break;

            case R.id.accodmation:
                setViewBackground(view);
                setRecyclerview(likeList);
               // getCommunity("ACCOMODATION");
                //  loadFragment(new AccodmationCommunity());
                break;
        }
    }

    private void setClickListner(){
        communityBinding.allCommunity.setOnClickListener(this);
        communityBinding.general.setOnClickListener(this);
        communityBinding.accodmation.setOnClickListener(this);

        communityBinding.toolbar.setTitle(getString(R.string.mycommunity));
        setSupportActionBar(communityBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setRecyclerview(List<MyPostComment> postCommentList){

        communityAdapter = new MyCommunityAdapter(this, postCommentList);
        mLayoutManager = new LinearLayoutManager(this);
        communityBinding.recyclerView.setLayoutManager(mLayoutManager);
        communityBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        communityBinding.recyclerView.setAdapter(communityAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
