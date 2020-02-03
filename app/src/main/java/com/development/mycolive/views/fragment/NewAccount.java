package com.development.mycolive.views.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentNewAccountBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewAccount extends Fragment {
    FragmentNewAccountBinding accountBinding;
    List<String> country = new ArrayList<String>();
    List<String> destination = new ArrayList<>();
    List<String> district = new ArrayList<>();
    List<String> university = new ArrayList<>();

    public NewAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        accountBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_new_account, container, false);
        setSpinner();
        return accountBinding.getRoot();
    }

    private void setSpinner(){

        country.add("Country of Residence");
        destination.add("Select Destination/Host City");
        district.add("Select District/Zone/Area");
        university.add("Select University/Profession");
        // Creating adapter for spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, country);

        // Drop down layout style - list view with radio button
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        accountBinding.accountLayoout.country.setAdapter(typeAdapter);


        ArrayAdapter<String>  cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, destination);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountBinding.accountLayoout.destination.setAdapter(cityAdapter);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, district);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountBinding.accountLayoout.district.setAdapter(districtAdapter);

        ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, university);
        universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountBinding.accountLayoout.university.setAdapter(universityAdapter);

    }

}
