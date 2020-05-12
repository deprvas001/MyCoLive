package com.development.mycolive.views.activity.viewCommunity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.development.mycolive.model.alert.AlertRequest;
import com.development.mycolive.model.bookingHistory.MonthHistory;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.communityModel.CommunityApiResponse;
import com.development.mycolive.model.home.CountData;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.searchFilterModel.AlertReason;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.Period;
import com.development.mycolive.model.viewCommunityModel.CommentApiResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.model.viewCommunityModel.CommentReply;
import com.development.mycolive.model.viewCommunityModel.LikeUnlike;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityModel;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.fragment.communities.CommunitiesViewModel;
import com.development.mycolive.views.fragment.filterSearch.SearchViewModel;
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
    int reason_check = 0;
    String share_url=  "";
    AlertReason alertReason;
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
                       if(communityApiResponse.getResponse().getData().size()>0){
                           setView(communityApiResponse.getResponse().getData());
                       }else{
                           Toast.makeText(ViewCommunity.this, "No Result Found.", Toast.LENGTH_SHORT).show();
                           finish();
                       }
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
        communityBinding.communityViewLayout.postImage.setVisibility(View.GONE);
        communityBinding.communityViewLayout.knowMore.setVisibility(View.GONE);
        communityBinding.communityViewLayout.date.setText(communityModelList.get(0).getDate());
        communityBinding.communityViewLayout.name.setText(communityModelList.get(0).getUser_name());
        communityBinding.communityViewLayout.type.setText("Type: "+communityModelList.get(0).getPost_type_name());
        communityBinding.communityViewLayout.city.setText("City: "+communityModelList.get(0).getCity_name());
        communityBinding.communityViewLayout.university.setText("University: "+communityModelList.get(0).getUniversity_name());
        communityBinding.communityViewLayout.comment.setText(communityModelList.get(0).getComment());
        communityBinding.communityViewLayout.like.setText(communityModelList.get(0).getTotal_likes());
        communityBinding.communityViewLayout.commentCount.setText(communityModelList.get(0).getTotal_reply_comment());
        share_url = communityModelList.get(0).getUrl_for_share();

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
       /* communityBinding.toolbar.setTitle(getResources().getString(R.string.view_community));
        setSupportActionBar(communityBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        communityBinding.postButton.setOnClickListener(this);
        communityBinding.communityViewLayout.like.setOnClickListener(this);
        communityBinding.commentEdit.setOnClickListener(this);
        communityBinding.back.setOnClickListener(this);
        communityBinding.communityViewLayout.alert.setOnClickListener(this);
        communityBinding.communityViewLayout.share.setOnClickListener(this);
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

            case R.id.share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MY CoLive");
                shareIntent.putExtra(Intent.EXTRA_TEXT, share_url);
                startActivity(Intent.createChooser(shareIntent, "Choose One"));
                break;

            case R.id.back:
                finish();
                break;

            case R.id.alert:
                getDefaultData();
                break;


            case 01:
             //  ((RadioButton)v).getText() +" Id is "+v.getId());
              //  ((RadioButton)view).getText() +" Id is "+view.getId()
                alertReason = (AlertReason) ((RadioButton)view).getTag();
                reason_check = Integer.parseInt(alertReason.getId());
             //  Toast.makeText(this, ((RadioButton)view).getText(), Toast.LENGTH_SHORT).show();
                break;

            case 11:
                //  ((RadioButton)v).getText() +" Id is "+v.getId());
                //  ((RadioButton)view).getText() +" Id is "+view.getId()
                 alertReason = (AlertReason) ((RadioButton)view).getTag();
                reason_check = Integer.parseInt(alertReason.getId());
                //  Toast.makeText(this, ((RadioButton)view).getText(), Toast.LENGTH_SHORT).show();
                break;

            case 21:
                //  ((RadioButton)v).getText() +" Id is "+v.getId());
                //  ((RadioButton)view).getText() +" Id is "+view.getId()
                alertReason = (AlertReason) ((RadioButton)view).getTag();
                reason_check = Integer.parseInt(alertReason.getId());
                //  Toast.makeText(this, ((RadioButton)view).getText(), Toast.LENGTH_SHORT).show();
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
                }else{
                    Toast.makeText(ViewCommunity.this, "Try Later", Toast.LENGTH_SHORT).show();
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

    private void getDefaultData(){
        showProgressDialog(getResources().getString(R.string.loading));
        String type = "ALL";

       SearchViewModel searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        searchViewModel.getDefaultData(this,type).observe(this, new Observer<FilterApiResponse>() {
            @Override
            public void onChanged(FilterApiResponse filterApiResponse) {
                  hideProgressDialog();
                 if(filterApiResponse.filterResponse !=null){
                   List<AlertReason> reasonList = filterApiResponse.getFilterResponse().getData().getReasons();

                   /* List<CityModel>  cityModelList =    filterApiResponse.getFilterResponse().getData().getCityList();
                    List<Period>  periodList = filterApiResponse.getFilterResponse().getData().getPeriodList();*/
                     showCustomDialog(reasonList);
                }else {
                    Toast.makeText(ViewCommunity.this, "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showCustomDialog(List<AlertReason> reasonList){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
          ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_request_dialog, viewGroup, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        EditText name = (EditText)dialogView.findViewById(R.id.name) ;
        EditText email = (EditText)dialogView.findViewById(R.id.email);
        Button ok = (Button)dialogView.findViewById(R.id.buttonOk);
        ImageView close_dialog = (ImageView)dialogView.findViewById(R.id.close);

        RadioGroup radioGroup = (RadioGroup)dialogView.findViewById(R.id.reason_group);

        try {
            radioGroup .setOrientation(LinearLayout.VERTICAL);
            for (int i = 0; i <= reasonList.size(); i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId(0+1);
                rdbtn.setText(reasonList.get(i).getReason());
                rdbtn.setTag(reasonList.get(i));
                rdbtn.setOnClickListener(this);
                radioGroup.addView(rdbtn);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(ViewCommunity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }else if(email.getText().toString().isEmpty()){
                    Toast.makeText(ViewCommunity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                  if(reason_check != 0){
                      EditText description_edit = (EditText)dialogView.findViewById(R.id.description);
                      String description = description_edit.getText().toString();
                      sendRequest(name,email,description,String.valueOf(reason_check),id);
                      alertDialog.dismiss();
                  }else{
                      Toast.makeText(ViewCommunity.this, "Select Reason", Toast.LENGTH_SHORT).show();
                  }
                }
            }
        });


        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               alertDialog.dismiss();
            }
        });

    }

    private void sendRequest(EditText name, EditText email, String description, String reason , String id){
        showProgressDialog(getResources().getString(R.string.loading));
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        AlertRequest alertRequest = new AlertRequest();
        alertRequest.setName(name.getText().toString());
        alertRequest.setEmail(email.getText().toString());
        alertRequest.setReason(reason);
        alertRequest.setComment_id(id);
        alertRequest.setDescription(description);

        communityViewModel = ViewModelProviders.of(this).get(CommunityViewModel.class);

        communityViewModel.sendComplain(this,headers,alertRequest).observe(this, new Observer<ViewCommunityApiResponse>() {
            @Override
            public void onChanged(ViewCommunityApiResponse communityApiResponse) {
                hideProgressDialog();
                if(communityApiResponse.response !=null){
                    if(communityApiResponse.getResponse().getStatus() == 1){
                        String message = communityApiResponse.getResponse().getMessage();
                        Toast.makeText(ViewCommunity.this, message, Toast.LENGTH_SHORT).show();
                    }
                  //  setView(communityApiResponse.getResponse().getData());
                }else{
                    Toast.makeText(ViewCommunity.this, communityApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
             /*  bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
