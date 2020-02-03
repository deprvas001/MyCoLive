package com.development.mycolive.views.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentSearchBinding;
import com.development.mycolive.views.activity.SearchResult;
import com.development.mycolive.views.activity.ShowHomeScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment implements View.OnClickListener {
FragmentSearchBinding searchBinding;
    List<String> type_category = new ArrayList<String>();
    List<String> city = new ArrayList<>();
    List<String> district = new ArrayList<>();
    List<String> university = new ArrayList<>();
    List<String> month = new ArrayList<>();

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        searchBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false);
        initializeView();
        setClickListener();
        setSpinner();
        return searchBinding.getRoot();
    }

    private void setSpinner() {
        type_category.add("Select Category");
        city.add("Select Sub Category");
        district.add("Select Sub Category");
        university.add("Choose Miles");
        month.add("Duration Month");
        // Creating adapter for spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, type_category);

        // Drop down layout style - list view with radio button
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        searchBinding.type.setAdapter(typeAdapter);


        ArrayAdapter<String>  cityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, city);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBinding.city.setAdapter(cityAdapter);

        ArrayAdapter<String> districtAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, district);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBinding.district.setAdapter(districtAdapter);

        ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, university);
        universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBinding.university.setAdapter(universityAdapter);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, month);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBinding.duration.setAdapter(monthAdapter);
    }

    private void initializeView(){
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.titleTxt.setText("Search");
    }

    private void setClickListener(){
        searchBinding.btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                startActivity(new Intent(getActivity(), SearchResult.class));
                break;
        }
    }

}
