package com.development.mycolive.views.fragment.profile;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentProfileScreenOneBinding;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfileData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileScreenOne extends Fragment {
    FragmentProfileScreenOneBinding oneBinding;
    ProfileViewModel profileViewModel;

    public ProfileScreenOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        oneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_screen_one, container, false);
        initializeView();
        getBooking();
        return oneBinding.getRoot();
    }

    private void initializeView() {
        ((ShowHomeScreen) getActivity()).screenBinding.appBar.titleTxt.setText("My Profile");
    }

    private void getBooking() {
       // ((ShowHomeScreen) getActivity()).showProgressDialog(getString(R.string.loading));
        String type = "PROFILE";

        profileViewModel = ViewModelProviders.of(getActivity()).get(ProfileViewModel.class);

        profileViewModel.getProfile(getActivity(), type).observe(getActivity(), new Observer<ProfileApiResponse>() {
            @Override
            public void onChanged(ProfileApiResponse apiResponse) {
              //  ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if (apiResponse.response != null) {
                    setView(apiResponse);
                }else if(apiResponse.getStatus() == 401){
                    Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setView(ProfileApiResponse apiResponse) {
        List<ProfileData> profileData = apiResponse.getResponse().getData();
        oneBinding.fieldLayout.inputName.setText(profileData.get(0).getName());
        oneBinding.fieldLayout.inputEmail.setText(profileData.get(0).getEmail());
        oneBinding.fieldLayout.inputPhone.setText(profileData.get(0).getMobile());
        oneBinding.fieldLayout.inputDob.setText(profileData.get(0).getDob());
    }

}
