package com.development.mycolive.views.fragment.filterSearch;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentSearchBinding;
import com.development.mycolive.views.activity.searchResult.SearchResult;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.DistrictModel;
import com.development.mycolive.model.searchScreen.UniversityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment implements View.OnClickListener , AdapterView.OnItemSelectedListener{
FragmentSearchBinding searchBinding;
    List<String> type_category = new ArrayList<String>();
    List<CityModel> city = new ArrayList<>();
    List<DistrictModel> district = new ArrayList<>();
    List<UniversityModel> university = new ArrayList<>();
    List<String> month = new ArrayList<>();
    SearchViewModel searchViewModel;

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

        return searchBinding.getRoot();
    }

    private void setSpinner(List<CityModel> cityModelList, List<DistrictModel> districtModelList, List<UniversityModel> universityModelList) {

            for(UniversityModel universityModel:universityModelList) {
                for(DistrictModel districtModel:districtModelList){
                    if(universityModel.getDistrict_id().equals(districtModel.getId())){

                    }
                }

            }
        CityModel cityModel = new CityModel();
        cityModel.setCity_name(getString(R.string.city_name));

        DistrictModel districtModel = new DistrictModel();
        districtModel.setDistrict_name(getString(R.string.district));

        UniversityModel universityModel = new UniversityModel();
        universityModel.setUniversity_name(getString(R.string.university));

        cityModelList.add(0,cityModel);
        districtModelList.add(0,districtModel);
        university.add(0,universityModel);
        month.add("Duration Month");

       /* // Creating adapter for spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, type_category);
        // Drop down layout style - list view with radio button
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        searchBinding.type.setAdapter(typeAdapter);*/

        ArrayAdapter<CityModel>  cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cityModelList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBinding.city.setAdapter(cityAdapter);

        ArrayAdapter<DistrictModel> districtAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, districtModelList);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBinding.district.setAdapter(districtAdapter);

        ArrayAdapter<UniversityModel> universityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, university);
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
        searchBinding.city.setOnItemSelectedListener(this);
        searchBinding.district.setOnItemSelectedListener(this);
        searchBinding.university.setOnItemSelectedListener(this);

        getDefaultData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                 startActivity(new Intent(getActivity(), SearchResult.class));
                 break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.city:
                CityModel cityModel = (CityModel)adapterView.getSelectedItem();
                Toast.makeText(getActivity(), cityModel.getCity_name(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.district:
                DistrictModel districtModel = (DistrictModel)adapterView.getSelectedItem();
                Toast.makeText(getActivity(), districtModel.getDistrict_name(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.university:
                UniversityModel universityModel = (UniversityModel)adapterView.getSelectedItem();
                Toast.makeText(getActivity(), universityModel.getUniversity_name(), Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getDefaultData(){
        String type = "ALL";

        searchViewModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);

        searchViewModel.getDefaultData(getActivity(),type).observe(getActivity(), new Observer<FilterApiResponse>() {
            @Override
            public void onChanged(FilterApiResponse filterApiResponse) {
                if(filterApiResponse.filterResponse !=null){
                List<CityModel>  cityModelList =    filterApiResponse.getFilterResponse().getData().getCityList();
                List<DistrictModel>  districtModelList = filterApiResponse.getFilterResponse().getData().getDistictList();
                List<UniversityModel> universityModelList = filterApiResponse.getFilterResponse().getData().getUniversityList();
                setSpinner(cityModelList,districtModelList,universityModelList);


                }else if(filterApiResponse.filterResponse ==null){
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
