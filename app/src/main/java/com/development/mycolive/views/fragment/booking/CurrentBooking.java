package com.development.mycolive.views.fragment.booking;


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
import com.development.mycolive.databinding.FragmentCurrentBookingBinding;
import com.development.mycolive.adapter.CurrentBookingAdapter;
import com.development.mycolive.model.booking.BookingApiResponse;
import com.development.mycolive.model.booking.BookingData;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBooking extends Fragment {
FragmentCurrentBookingBinding bookingBinding ;
    private CurrentBookingAdapter bookingAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<BookingData> bookingList = new ArrayList<>();
    BookingViewModel bookingViewModel;
    public CurrentBooking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bookingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_current_booking,container,false);

        getBooking();
        return bookingBinding.getRoot();
    }

    private void setRecycleView(){
        bookingAdapter = new CurrentBookingAdapter(getActivity(), bookingList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        bookingBinding.recyclerView.setLayoutManager(mLayoutManager);
        bookingBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookingBinding.recyclerView.setAdapter(bookingAdapter);
    }



  /*  private void getBooking(){
         bookingList.clear();
        for (int i=0;i<4;i++){
            CurrentBookingModel booking = new CurrentBookingModel("",
                    "","","","");
            bookingList.add(booking);
        }
        bookingAdapter.notifyDataSetChanged();
    }*/


    private void getBooking() {
        String type = "CURRENTBOOKING";

        bookingViewModel = ViewModelProviders.of(getActivity()).get(BookingViewModel.class);

        bookingViewModel.getBooking(getActivity(),type).observe(getActivity(), new Observer<BookingApiResponse>() {
            @Override
            public void onChanged(BookingApiResponse bookingApiResponse) {
                if(bookingApiResponse.response !=null){

                     bookingList.clear();
                     bookingList = bookingApiResponse.response.getBookingDataList();
                     setRecycleView();

                }else if(bookingApiResponse.response ==null){
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
                bookingBinding.shimmerViewContainer.stopShimmer();
                bookingBinding.shimmerViewContainer.setVisibility(View.GONE);
            }
        });
       /* loginViewModel.getLoginUser(this,requestModel).observe(this, loginApiResponse -> {
            if (loginApiResponse.getError()== null && loginApiResponse.getStatus()==400) {
                // handle error here
                hideProgressDialog();
                showAlertDialog(this, getString(R.string.invalid_credentails));
                //   Toast.makeText(this, getString(R.string.invalid_credentails), Toast.LENGTH_SHORT).show();
            }else if (loginApiResponse.getError() == null) {
                hideProgressDialog();
                if(loginApiResponse.getResponse().getStatus() ==1 ){
                    showAlertDialog(this, getString(R.string.success));
                }
            } else {
                // call failed.
                hideProgressDialog();
                Throwable e = loginApiResponse.getError();
                Toast.makeText(LoginActivity.this, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                // Log.e(TAG, "Error is " + e.getLocalizedMessage());
            }
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();
          bookingBinding.shimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        bookingBinding.shimmerViewContainer.stopShimmer();
        super.onPause();

    }
}
