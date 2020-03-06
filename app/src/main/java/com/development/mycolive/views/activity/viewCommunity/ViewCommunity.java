package com.development.mycolive.views.activity.viewCommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.adapter.CommentAdapter;
import com.development.mycolive.adapter.HomeSlideAdapter;
import com.development.mycolive.adapter.MonthDataAdapter;
import com.development.mycolive.adapter.ViewCommunitySlider;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityViewCommunityBinding;
import com.development.mycolive.model.ViewCommentModel;
import com.development.mycolive.model.ViewSliderModel;
import com.development.mycolive.model.bookingHistory.MonthHistory;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.home.CountData;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.viewCommunityModel.CommentApiResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.model.viewCommunityModel.CommentReply;
import com.development.mycolive.model.viewCommunityModel.LikeUnlike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityModel;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.fragment.communities.CommunitiesViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Headers;

public class ViewCommunity extends BaseActivity implements View.OnClickListener {
    CommunityViewModel communityViewModel;
    ActivityViewCommunityBinding communityBinding;
    CommentAdapter commentAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    SessionManager session;
    String token="",id="";
    String comment_id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communityBinding = DataBindingUtil.setContentView(this,R.layout.activity_view_community);
        getSession();
        setClickListener();
    }

    private void getCommunity(String id,String token){

        showProgressDialog(getResources().getString(R.string.loading));
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        communityViewModel = ViewModelProviders.of(this).get(CommunityViewModel.class);

        communityViewModel.getCommunityData(this,headers,id).observe(this, new Observer<ViewCommunityApiResponse>() {
            @Override
            public void onChanged(ViewCommunityApiResponse communityApiResponse) {
                hideProgressDialog();
                if(communityApiResponse.response !=null){
                      setView(communityApiResponse.getResponse().getData());
                }
                else if(communityApiResponse.getStatus()== 401){
                }
                else{
                }
             /*  bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }

    private void setView(List<ViewCommunityModel> communityModelList){
        communityBinding.communityViewLayout.imageSlider.setVisibility(View.VISIBLE);
        communityBinding.communityViewLayout.knowMore.setVisibility(View.GONE);
        communityBinding.communityViewLayout.date.setText(communityModelList.get(0).getDate());
        communityBinding.communityViewLayout.name.setText(communityModelList.get(0).getUser_name());
        communityBinding.communityViewLayout.type.setText("Type: "+communityModelList.get(0).getPost_type_name());
        communityBinding.communityViewLayout.city.setText("City: "+communityModelList.get(0).getCity_name());
        communityBinding.communityViewLayout.university.setText("University: "+communityModelList.get(0).getUniversity_name());
        communityBinding.communityViewLayout.comment.setText(communityModelList.get(0).getComment());
        communityBinding.communityViewLayout.like.setText(communityModelList.get(0).getTotal_likes());
        communityBinding.communityViewLayout.commentCount.setText(communityModelList.get(0).getTotal_reply_comment());

        Picasso.get()
                .load(communityModelList.get(0).getProfile_image())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(communityBinding.communityViewLayout.imageView);

                setSliderAndView(communityModelList.get(0).getImage());

                setRecycleView(communityModelList.get(0).getComment_reply());

    }

    private void setSliderAndView(List<String> sliderList) {
        final ViewCommunitySlider adapter = new ViewCommunitySlider(this, sliderList);
        adapter.setCount(sliderList.size());

        communityBinding.communityViewLayout.imageSlider.setSliderAdapter(adapter);

        communityBinding.communityViewLayout.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        communityBinding.communityViewLayout.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        communityBinding.communityViewLayout.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        communityBinding.communityViewLayout.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        communityBinding.communityViewLayout.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        communityBinding.communityViewLayout.imageSlider.startAutoCycle();

            communityBinding.communityViewLayout.imageSlider.setOnIndicatorClickListener(position ->
                communityBinding.communityViewLayout.imageSlider.setCurrentPagePosition(position));

    }

    private void setRecycleView( List<CommentReply> commentList){
        commentAdapter = new CommentAdapter(this, commentList);
        mLayoutManager = new LinearLayoutManager(this);
        communityBinding.recyclerView.setLayoutManager(mLayoutManager);
        communityBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        communityBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        communityBinding.recyclerView.setAdapter(commentAdapter);
    }

    private void setClickListener(){
        communityBinding.postButton.setOnClickListener(this);
        communityBinding.communityViewLayout.like.setOnClickListener(this);
        communityBinding.commentEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.post_button:
                if(communityBinding.commentEdit.getText().toString().isEmpty()){
                    Toast.makeText(this, "Comment Field can not be empty.", Toast.LENGTH_SHORT).show();
                }else{
                    commentPost();
                }
                break;

            case R.id.like:
                likeUnlike();
                break;
        }
    }

    private void commentPost(){
        showProgressDialog(getResources().getString(R.string.loading));
        CommentPost commentPost = new CommentPost();
        commentPost.setComment(communityBinding.commentEdit.getText().toString());
        commentPost.setComment_id(id);

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        communityViewModel = ViewModelProviders.of(this).get(CommunityViewModel.class);

        communityViewModel.getCommentResponse(this,headers,commentPost).observe(this, new Observer<CommentApiResponse>() {
            @Override
            public void onChanged(CommentApiResponse apiResponse) {
                hideProgressDialog();
                if(apiResponse.response !=null){
                    communityBinding.commentEdit.setText(null);
                    getCommunity(id,token);
                }else if(apiResponse.getStatus()== 401){
                    Toast.makeText(ViewCommunity.this, "Try Later.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ViewCommunity.this, "Try Later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void likeUnlike(){
        LikeUnlike likeUnlike = new LikeUnlike();
        likeUnlike.setComment_id(id);


        showProgressDialog(getResources().getString(R.string.loading));
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        communityViewModel = ViewModelProviders.of(this).get(CommunityViewModel.class);

        communityViewModel.getLikeUnilike(this,headers,likeUnlike).observe(this, new Observer<CommentApiResponse>() {
            @Override
            public void onChanged(CommentApiResponse apiResponse) {
                hideProgressDialog();
                if(apiResponse.response !=null){
                  String likes =  apiResponse.getResponse().getData().getTotal_likes();
                  communityBinding.communityViewLayout.like.setText(likes);
                  int user_like = apiResponse.getResponse().getData().getLike_unlike();
                  if(user_like != 0){
                      communityBinding.communityViewLayout.like.setTextColor(getResources().getColor(R.color.like_color));
                      communityBinding.communityViewLayout.like.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.like_color)));
                  }else{
                      communityBinding.communityViewLayout.like.setTextColor(getResources().getColor(R.color.login_subheading));
                      communityBinding.communityViewLayout.like.setCompoundDrawableTintList(ColorStateList.valueOf(getResources().getColor(R.color.login_subheading)));
                  }
                }else if(apiResponse.getStatus()== 401)
                {

                }
                else{
                }
            }
        });
    }

    private void getSession(){
        if(getIntent()!=null){
            id = getIntent().getExtras().getString("Id");
        }

        session = new SessionManager(ViewCommunity.this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
     //    id = user.get(SessionManager.KEY_USERID);
        token = user.get(SessionManager.KEY_TOKEN);
        getCommunity(id,token);

      //  getCommunity("ALL");

        //   getBooking(token);
    }
}
