package com.development.mycolive.views.fragment.favouriteBooking;


import android.content.Intent;
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
import com.development.mycolive.adapter.FavouriteAdapter;
import com.development.mycolive.clickListener.RecyclerTouchListener;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentFavouritePropertyBinding;
import com.development.mycolive.adapter.SearchScreenAdapter;
import com.development.mycolive.model.SearchResultModel;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.model.favourite.FavouriteApiResponse;
import com.development.mycolive.model.favourite.FavouriteBookingModel;
import com.development.mycolive.model.favourite.FavouritePropertyModel;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.MyFavourite;
import com.development.mycolive.views.activity.favouriteRoomate.FavouriteRoomateDetail;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.development.mycolive.views.activity.searchDetailPage.RoomDetail;
import com.development.mycolive.views.fragment.profile.ProfileViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Headers;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteProperty extends Fragment implements View.OnClickListener {
    FragmentFavouritePropertyBinding propertyBinding;
    private FavouriteAdapter favouriteAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    FavouriteViewModel viewModel;
    SessionManager session;
    public FavouriteProperty() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        propertyBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_favourite_property, container, false);
       // setClickListener();
        getSession();
        return propertyBinding.getRoot();
    }

    private void setClickListener(){
        //propertyBinding.contentSearch
        propertyBinding.fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }


    private void setReyclerView(List<FavouritePropertyModel> bookingModelList){
        favouriteAdapter = new FavouriteAdapter(getActivity(), bookingModelList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        propertyBinding.contentSearch.recyclerView.setLayoutManager(mLayoutManager);
        propertyBinding.contentSearch.recyclerView.setItemAnimator(new DefaultItemAnimator());
        propertyBinding.contentSearch.recyclerView.setAdapter(favouriteAdapter);

        propertyBinding.contentSearch.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                propertyBinding.contentSearch.recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                FavouritePropertyModel resultModel = bookingModelList.get(position);
                Intent intent = new Intent(getActivity(),PropertyDetail.class);
                intent.putExtra("Property_Id",resultModel.getId());
                startActivity(intent);

                /*startActivity(new Intent(getActivity(), RoomDetail.class));*/
                // Toast.makeText(getApplicationContext(), resultModel.getAddress() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

   private void getFavuoriteList(String token){
       ((MyFavourite) getActivity()).showProgressDialog(getResources().getString(R.string.loading));


       Map<String,String> headers = new HashMap<>();
       headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
       headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
       headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
       headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
       headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
       headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);


        String type = "PROPERTY";
        String offset="0";
        String per_page="10";
        viewModel = ViewModelProviders.of(getActivity()).get(FavouriteViewModel.class);

        viewModel.getFavourite(getActivity(),headers, type,offset,per_page).observe(getActivity(), new Observer<FavouriteApiResponse>() {
           @Override
           public void onChanged(FavouriteApiResponse apiResponse) {
               ((MyFavourite) getActivity()).hideProgressDialog();

               if (apiResponse.response != null) {
                   if(apiResponse.getResponse().getStatus() ==1){
                       List<FavouritePropertyModel> bookingModelList = apiResponse.getResponse().getData();
                       if(bookingModelList.size()>0){
                           setReyclerView(bookingModelList);
                       }else{
                           Toast.makeText(getActivity(), "No Favourite Property", Toast.LENGTH_SHORT).show();
                       }

                      // Toast.makeText(getActivity(), apiResponse.response.getMessage(), Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(getActivity(), apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                   }
               } else{
                   Toast.makeText(getActivity(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();

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
        String token = user.get(SessionManager.KEY_TOKEN);

        getFavuoriteList(token);

    }
}
