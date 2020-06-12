package com.development.mycolive.views.fragment.filterSearch;


import android.app.Dialog;
import android.app.ProgressDialog;
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
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentSearchBinding;
import com.development.mycolive.model.editProfile.ProfileData;
import com.development.mycolive.model.favourite.RoomateData;
import com.development.mycolive.model.filterModel.FilterSearchApiResponse;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.searchScreen.Period;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.FilterResultProperty;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.views.activity.roomate.RoommateList;
import com.development.mycolive.views.activity.searchResult.SearchResult;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.DistrictModel;
import com.development.mycolive.model.searchScreen.UniversityModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment implements View.OnClickListener , AdapterView.OnItemSelectedListener{
FragmentSearchBinding searchBinding;
    List<String> type_category = new ArrayList<String>();
    List<CityModel> city = new ArrayList<>();
    List<DistrictModel> district = new ArrayList<>();
    SessionManager session;
    ProgressDialog progressDialog;
    Dialog dialog;
    String token="";
    List<DistrictModel> districtModelList =new ArrayList<>();
    List<UniversityModel> universityModelList = new ArrayList<>();
    List<UniversityModel> university = new ArrayList<>();
    List<String> month = new ArrayList<>();
    SearchViewModel searchViewModel;
    String city_spinner="",date_range="";
    String university_id="",duration_period="";
    String district_spiner="",category_type="";

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

    private void setSpinner(List<CityModel> cityModelList,List<Period>  periodList) {


        CityModel cityModel = new CityModel();
        cityModel.setCity_name(getString(R.string.city_name));

        DistrictModel districtModel = new DistrictModel();
        districtModel.setDistrict_name(getString(R.string.district));



        cityModelList.add(0,cityModel);
        districtModelList.add(0,districtModel);

        Period period = new Period();
        period.setName(getString(R.string.select_period));
        periodList.add(0,period);


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


        ArrayAdapter<Period> monthAdapter = new ArrayAdapter<Period>(getActivity(), android.R.layout.simple_spinner_item, periodList);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBinding.duration.setAdapter(monthAdapter);

    }

    private void initializeView(){
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.titleTxt.setText("Search");
        getSession();
    }

    private void setClickListener(){
        searchBinding.btnSearch.setOnClickListener(this);
        searchBinding.city.setOnItemSelectedListener(this);
        searchBinding.district.setOnItemSelectedListener(this);
        searchBinding.university.setOnItemSelectedListener(this);
        searchBinding.type.setOnItemSelectedListener(this);
        searchBinding.duration.setOnItemSelectedListener(this);
        ((ShowHomeScreen)getActivity()).screenBinding.appBar.notification.setOnClickListener(this);

        if( ((ShowHomeScreen) getActivity()).isNetworkAvailable(getActivity())){
            getDefaultData();
        }else{
            Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_search:
                if(category_type.isEmpty()){
                    Toast.makeText(getActivity(), "Please Select Category", Toast.LENGTH_SHORT).show();
                   return;
                }else if(city_spinner.isEmpty()){
                    Toast.makeText(getActivity(), "Please Select City", Toast.LENGTH_SHORT).show();
                    return;
                }/*else if(district_spiner.isEmpty()){
                    Toast.makeText(getActivity(), "Please Select District", Toast.LENGTH_SHORT).show();
                  return;
                }*/else if(university_id.isEmpty()){
                    Toast.makeText(getActivity(), "Please Select University", Toast.LENGTH_SHORT).show();
                    return;
                }/*else if(duration_period.isEmpty()){
                    Toast.makeText(getActivity(), "Please Select Duration", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                else{
                    getSerchData();
                }

               //  startActivity(new Intent(getActivity(), SearchResult.class));
                 break;

            case R.id.notification:
                startActivity(new Intent(getActivity(), Notification.class));
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()){

            case R.id.type:
                if(position>0){
                  category_type =  adapterView.getSelectedItem().toString();
                }else{
                    category_type = "";
                }
                break;

            case R.id.duration:
                if(position>0){
                    if(position == 4){
                        searchBinding.durationLayout.setVisibility(View.VISIBLE);
                    }else{
                        searchBinding.durationLayout.setVisibility(View.GONE);
                        searchBinding.fromEdit.setText(null);
                        searchBinding.toEdit.setText(null);
                    }
                     Period period = (Period)adapterView.getSelectedItem();
                      duration_period = period.getId();
                     //date_range = period.getName();
                }else{
                    duration_period="";
                }
                break;

            case R.id.city:
                if(position>0){
                    CityModel cityModel = (CityModel)adapterView.getSelectedItem();
                    city_spinner = cityModel.getCity_name();
                 //   Toast.makeText(getActivity(), cityModel.getCity_name(), Toast.LENGTH_SHORT).show();
                }else{
                    city_spinner="";
                }
                break;

            case R.id.district:
                if(position>0){
                    searchBinding.university.setVisibility(View.VISIBLE);
                    university.clear();
                    UniversityModel universityMode = new UniversityModel();
                    universityMode.setUniversity_name(getString(R.string.university));
                    university.add(0,universityMode);
                    for(DistrictModel districtModel:districtModelList){
                        for(UniversityModel universityModel:universityModelList) {
                            if(universityModel.getDistrict_id().equals(districtModel.getId())){
                                university.add(universityModel);
                                DistrictModel district = (DistrictModel)adapterView.getSelectedItem();
                          //      Toast.makeText(getActivity(), district.getDistrict_name(), Toast.LENGTH_SHORT).show();
                                 district_spiner = district.getId();
                                ArrayAdapter<UniversityModel> universityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, university);
                                universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                searchBinding.university.setAdapter(universityAdapter);

                            }
                        }

                    }
                }else{
                    district_spiner = "";
                }

                break;

            case R.id.university:
                if(position>0){
                    UniversityModel universityModel = (UniversityModel)adapterView.getSelectedItem();
                    university_id = universityModel.getId();
                  //  Toast.makeText(getActivity(), universityModel.getUniversity_name(), Toast.LENGTH_SHORT).show();
                }else{
                    university_id="";
                }

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getDefaultData(){
        showProgressDialog(getString(R.string.loading));
            String type = "ALL";

            searchViewModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);

            searchViewModel.getDefaultData(getActivity(),type).observe(getActivity(), new Observer<FilterApiResponse>() {
                @Override
                public void onChanged(FilterApiResponse filterApiResponse) {
                    hideProgressDialog();
                    if(filterApiResponse.filterResponse !=null){
                        districtModelList.clear();
                        university.clear();
                        List<CityModel>  cityModelList =    filterApiResponse.getFilterResponse().getData().getCityList();
                        List<Period>  periodList = filterApiResponse.getFilterResponse().getData().getPeriodList();
                        districtModelList = filterApiResponse.getFilterResponse().getData().getDistictList();
                        universityModelList = filterApiResponse.getFilterResponse().getData().getUniversityList();
                        setSpinner(cityModelList,periodList);


                    }else {
                        Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    private void getSerchData(){
       // https://webfume.in/mani-budapest/api/search?type=ROOM&city=Vaduz&district=2&university=6&duration=3&daterange=
       showProgressDialog(getResources().getString(R.string.loading));

        String type = category_type;
        String city = city_spinner;
        String district = district_spiner;
        String university = university_id;
        String duration = duration_period;
        String daterange ="";
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        searchViewModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);

        searchViewModel.getSearchData(getActivity(),headers,type
        ,city,district,university,duration,daterange).observe(getActivity(), new Observer<FilterSearchApiResponse>() {
            @Override
            public void onChanged(FilterSearchApiResponse filterApiResponse) {
               hideProgressDialog();

                if(filterApiResponse.response !=null){
                    if(filterApiResponse.getResponse().getStatus() ==1){
                        ArrayList<RoomateData> roommateList = filterApiResponse.getResponse().getData().getRoommate();
                        ArrayList<HomeFeatureProperty> apartmentList =filterApiResponse.getResponse().getData().getApartment();
                        ArrayList<HomeFeatureProperty> roomList =filterApiResponse.getResponse().getData().getRoom();

                        if(type.equalsIgnoreCase("Roommate")){
                            if(roommateList!=null){
                                if(roommateList.size()>0){
                                    Intent intent = new Intent(getActivity(), RoommateList.class);
                                    intent.putParcelableArrayListExtra("roommateList",roommateList);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getActivity(), "No Result Found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else if(type.equalsIgnoreCase("APARTMENT")){
                            if(apartmentList!=null){
                                if(apartmentList.size()>0){
                                    Intent intent = new Intent(getActivity(), FilterResultProperty.class);
                                    intent.putParcelableArrayListExtra("property",apartmentList);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getActivity(), "No Result Found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else if(type.equalsIgnoreCase("ROOM")){
                            if(roomList !=null){
                                if(roomList.size()>0){
                                    Intent intent = new Intent(getActivity(), FilterResultProperty.class);
                                    intent.putParcelableArrayListExtra("property",roomList);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getActivity(), "No Result Found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }else {
                    Toast.makeText(getActivity(), filterApiResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
        token = user.get(SessionManager.KEY_TOKEN);

        //   getBooking(token);
    }



    public void showProgressDialog(String message) {
        if(progressDialog == null)
            progressDialog = new ProgressDialog(getActivity());

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
}
