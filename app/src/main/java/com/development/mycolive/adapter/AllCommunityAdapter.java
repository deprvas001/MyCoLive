package com.development.mycolive.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.model.alert.AlertRequest;
import com.development.mycolive.model.communityModel.AllPost;
import com.development.mycolive.model.myCommunityModel.MyCommunityApiResponse;
import com.development.mycolive.model.myCommunityModel.PostDeleteRequest;
import com.development.mycolive.model.searchFilterModel.AlertReason;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.viewCommunityModel.ViewCommunityApiResponse;
import com.development.mycolive.util.Util;
import com.development.mycolive.views.activity.myCommunity.MyCommunityViewModel;
import com.development.mycolive.views.activity.viewCommunity.CommunityViewModel;
import com.development.mycolive.views.activity.viewCommunity.ViewCommunity;
import com.development.mycolive.views.fragment.filterSearch.SearchViewModel;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCommunityAdapter  extends RecyclerView.Adapter<AllCommunityAdapter.MyViewHolder> implements View.OnClickListener {
    private ViewGroup group;
    private List<AllPost> allPostList;
    private Context context;
    ProgressDialog progressDialog;
    String token;
    CommunityViewModel communityViewModel;
    int reason_check = 0;
    AlertReason alertReason;

    @Override
    public void onClick(View view) {
       /* alertReason = (AlertReason) ((RadioButton)view).getTag();
        reason_check = Integer.parseInt(alertReason.getId());*/
      /*  switch (view.getId()){
            case view.getId():
                //  ((RadioButton)v).getText() +" Id is "+v.getId());
                //  ((RadioButton)view).getText() +" Id is "+view.getId()

                //  Toast.makeText(this, ((RadioButton)view).getText(), Toast.LENGTH_SHORT).show();
                break;
        }*/
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView viewDetail,name,type,city,university,comment,date,like,comment_count;
        public ImageView imageView,user_image,post_image;
        public LinearLayout postLayout;

        public MyViewHolder(View view) {
            super(view);

            imageView = (ImageView)view.findViewById(R.id.alert);
            user_image= (ImageView)view.findViewById(R.id.imageView);
            name = (TextView)view.findViewById(R.id.name);
            type = (TextView)view.findViewById(R.id.type);
            city = (TextView)view.findViewById(R.id.city);
            university = (TextView)view.findViewById(R.id.university);
            comment = (TextView)view.findViewById(R.id.comment);
            date = (TextView)view.findViewById(R.id.date);
            like = (TextView)view.findViewById(R.id.like);
            comment_count = (TextView)view.findViewById(R.id.comment_count);
            post_image = (ImageView)view.findViewById(R.id.post_image);
            postLayout = (LinearLayout)view.findViewById(R.id.post_layout);
        }
    }

    public AllCommunityAdapter(Context context,List<AllPost> allPostList,String token) {
        this.context = context;
        this.allPostList = allPostList;
        this.token = token;
    }


    @Override
    public AllCommunityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.community_item_row, parent, false);
        group = parent;

        return new AllCommunityAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllCommunityAdapter.MyViewHolder holder, int position) {
        AllPost post = allPostList.get(position);
        holder.name.setText(post.getUser_name());
        holder.type.setText("Type: "+post.getPost_type_name());
        holder.city.setText("City: "+post.getCity_name());
        holder.university.setText("University: "+post.getUniversity_name());
        holder.comment.setText(post.getComment());
        holder.date.setText(post.getDate());
        holder.like.setText(post.getTotal_likes());
        holder.comment_count.setText(post.getTotal_reply_comment());

        holder.postLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ViewCommunity.class);
                intent.putExtra("Id",post.getId());
              context.startActivity(intent);
            }
        });

        Util.loadImage(holder.user_image,post.getProfile_image() ,
                Util.getCircularDrawable(context));


        if(post.getImage().size()>0){
            Util.loadImage(holder.post_image,post.getImage().get(0) ,
                    Util.getCircularDrawable(context));
                  }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   showCustomDialog(context);
                getDefaultData(post.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPostList.size();
    }

    private void showCustomDialog(List<AlertReason> reasonList, String id){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
     //   ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_request_dialog, group, false);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        EditText name = (EditText)dialogView.findViewById(R.id.name) ;
        EditText email = (EditText)dialogView.findViewById(R.id.email);

        Button ok = (Button)dialogView.findViewById(R.id.buttonOk);
        ImageView close_dialog = (ImageView)dialogView.findViewById(R.id.close);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        RadioGroup radioGroup = (RadioGroup)dialogView.findViewById(R.id.reason_group);

        try {
            radioGroup .setOrientation(LinearLayout.VERTICAL);

            for (int i = 0; i <= reasonList.size(); i++) {
                RadioButton rdbtn = new RadioButton(context);
                rdbtn.setId(0+1);
                rdbtn.setText(reasonList.get(i).getReason());
                rdbtn.setTag(reasonList.get(i));
                rdbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // rdbtn.setChecked(true);
                        alertReason = (AlertReason) ((RadioButton)view).getTag();
                        reason_check = Integer.parseInt(alertReason.getId());
                     //   alertDialog.dismiss();
                    }
                });
                radioGroup.addView(rdbtn);
            }
        }catch (Exception e){
            e.printStackTrace();
        }




        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(context, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }else if(email.getText().toString().isEmpty()){
                    Toast.makeText(context, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }else{

                    if(reason_check != 0){
                        EditText description_edit = (EditText)dialogView.findViewById(R.id.description);
                        String description = description_edit.getText().toString();
                        sendRequest(name,email,description,String.valueOf(reason_check),id);
                        alertDialog.dismiss();
                    }else{
                        Toast.makeText(context, "Select Reason", Toast.LENGTH_SHORT).show();
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



    public void showProgressDialog(String message) {
        if(progressDialog == null)
            progressDialog = new ProgressDialog(context);

        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progressDialog !=null && progressDialog.isShowing())
            progressDialog.dismiss();
    }


    private void getDefaultData(String id){
        showProgressDialog(context.getResources().getString(R.string.loading));
        String type = "ALL";

        SearchViewModel searchViewModel = ViewModelProviders.of((FragmentActivity) context).get(SearchViewModel.class);

        searchViewModel.getDefaultData(context,type).observe((LifecycleOwner)context, new Observer<FilterApiResponse>() {
            @Override
            public void onChanged(FilterApiResponse filterApiResponse) {
                hideProgressDialog();
                if(filterApiResponse.filterResponse !=null){
                    List<AlertReason> reasonList = filterApiResponse.getFilterResponse().getData().getReasons();

                   /* List<CityModel>  cityModelList =    filterApiResponse.getFilterResponse().getData().getCityList();
                    List<Period>  periodList = filterApiResponse.getFilterResponse().getData().getPeriodList();*/
                    showCustomDialog(reasonList,id);
                }else {
                    Toast.makeText(context, "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void sendRequest(EditText name, EditText email, String description, String reason , String id){
        showProgressDialog(context .getResources().getString(R.string.loading));
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

        communityViewModel = ViewModelProviders.of((FragmentActivity) context).get(CommunityViewModel.class);

        communityViewModel.sendComplain(context,headers,alertRequest).observe((LifecycleOwner) context, new Observer<ViewCommunityApiResponse>() {
            @Override
            public void onChanged(ViewCommunityApiResponse communityApiResponse) {
                hideProgressDialog();
                if(communityApiResponse.response !=null){
                    if(communityApiResponse.getResponse().getStatus() == 1){
                        String message = communityApiResponse.getResponse().getMessage();
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                    //  setView(communityApiResponse.getResponse().getData());
                }else{
                    Toast.makeText(context, communityApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
             /*  bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);*/
            }
        });
    }


}
