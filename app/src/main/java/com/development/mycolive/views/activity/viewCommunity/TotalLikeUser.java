package com.development.mycolive.views.activity.viewCommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.adapter.CommentAdapter;
import com.development.mycolive.adapter.UserLikeAdpater;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityTotalLikeUserBinding;
import com.development.mycolive.model.viewCommunityModel.CommentReply;
import com.development.mycolive.model.viewCommunityModel.UserLike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotalLikeUser extends BaseActivity implements View.OnClickListener {
    ActivityTotalLikeUserBinding userBinding;
    SessionManager session;
    String token = "", id = "";
    RecyclerView.LayoutManager mLayoutManager;
    CommunityViewModel communityViewModel;
    UserLikeAdpater userLikeAdpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBinding = DataBindingUtil.setContentView(this, R.layout.activity_total_like_user);

        setClickListener();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    private void setClickListener() {
        userBinding.back.setOnClickListener(this);
        getSession();
    }

    private void getSession() {
        if (getIntent() != null) {
            id = getIntent().getExtras().getString("Id");
        }

        session = new SessionManager(TotalLikeUser.this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        // image = user.get(SessionManager.KEY_IMAGE);
        //    id = user.get(SessionManager.KEY_USERID);
        token = user.get(SessionManager.KEY_TOKEN);
        getCommunity(id, token);

        //  getCommunity("ALL");

        //   getBooking(token);
    }

    private void getCommunity(String id, String token) {

        showProgressDialog(getResources().getString(R.string.loading));
        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD, ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN, token);

        communityViewModel = ViewModelProviders.of(this).get(CommunityViewModel.class);

        communityViewModel.getCommunityData(this, headers, id).observe(this, new Observer<ViewCommunityApiResponse>() {
            @Override
            public void onChanged(ViewCommunityApiResponse communityApiResponse) {
                hideProgressDialog();
                if (communityApiResponse.response != null) {
                    if (communityApiResponse.getResponse().getData().size() > 0) {
                        setRecycleView(communityApiResponse.getResponse().getData().get(0).getTotal_liked_user());
                    } else {
                        Toast.makeText(TotalLikeUser.this, "No Result Found.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else if (communityApiResponse.getStatus() == 401) {
                } else {
                }
             /*  bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }

    private void setRecycleView(List<UserLike> userList) {
        userLikeAdpater = new UserLikeAdpater(this, userList);
        mLayoutManager = new LinearLayoutManager(this);
        userBinding.recyclerView.setLayoutManager(mLayoutManager);
        userBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        userBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        userBinding.recyclerView.setAdapter(userLikeAdpater);
    }
}
