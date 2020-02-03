package com.development.mycolive.views.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentCurrentBookingBinding;
import com.development.mycolive.views.adapter.CurrentBookingAdapter;
import com.development.mycolive.views.adapter.FindAdapter;
import com.development.mycolive.views.adapter.PropertiesAdapter;
import com.development.mycolive.views.model.CurrentBookingModel;
import com.development.mycolive.views.model.Find;
import com.development.mycolive.views.model.PropertiesFeatures;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentBooking extends Fragment {
FragmentCurrentBookingBinding bookingBinding ;
    private CurrentBookingAdapter bookingAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<CurrentBookingModel> bookingList = new ArrayList<>();
    public CurrentBooking() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bookingBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_current_booking,container,false);
        setRecycleView();
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

    private void getBooking(){
         bookingList.clear();
        for (int i=0;i<4;i++){
            CurrentBookingModel booking = new CurrentBookingModel("",
                    "","","","");
            bookingList.add(booking);
        }
        bookingAdapter.notifyDataSetChanged();

    }
}
