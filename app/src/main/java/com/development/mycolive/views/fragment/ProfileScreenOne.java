package com.development.mycolive.views.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentProfileScreenOneBinding;
import com.development.mycolive.views.activity.ShowHomeScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileScreenOne extends Fragment {
FragmentProfileScreenOneBinding oneBinding;

    public ProfileScreenOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        oneBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile_screen_one, container, false);
        initializeView();
        return oneBinding.getRoot();
    }

    private void initializeView(){
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.titleTxt.setText("My Profile");
    }

}
