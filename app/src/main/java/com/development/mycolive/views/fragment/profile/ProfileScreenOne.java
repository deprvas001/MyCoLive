package com.development.mycolive.views.fragment.profile;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentProfileScreenOneBinding;
import com.development.mycolive.model.editProfile.Data;
import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.DistrictModel;
import com.development.mycolive.model.searchScreen.Period;
import com.development.mycolive.model.searchScreen.UniversityModel;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfileData;
import com.development.mycolive.views.activity.changePassword.ChangePassword;
import com.development.mycolive.views.fragment.filterSearch.SearchViewModel;
import com.google.android.gms.common.api.Api;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Headers;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileScreenOne extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    FragmentProfileScreenOneBinding oneBinding;
    ProfileViewModel profileViewModel;
    SessionManager session;
    private boolean isDistrcitAvailable = false;
    private int REQUEST_CODE = 100;
    String university_id="",duration_period="";
    String city_spinner_id="";
    String district_spiner="",category_type="";
    private String fdate = "";
    SearchViewModel searchViewModel;
    List<DistrictModel> districtModelList =new ArrayList<>();
    List<UniversityModel> universityModelList = new ArrayList<>();
    List<UniversityModel> university = new ArrayList<>();
    private String image_string = "";
    private DatePickerDialog mDatePickerDialog;
    private String token="",name="",email="",user_type="",type="";
    public ProfileScreenOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        oneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_screen_one, container, false);
        initializeView();
        getSession();
        return oneBinding.getRoot();
    }

    private void initializeView() {
        setDateTimeField();
        ((ShowHomeScreen) getActivity()).screenBinding.appBar.titleTxt.setText("My Profile");
        oneBinding.profileImage.setOnClickListener(this);
        oneBinding.fieldLayout.btnSave.setOnClickListener(this);

        oneBinding.fieldLayout.citySpinner.setOnItemSelectedListener(this);
        oneBinding.fieldLayout.districtSpinner.setOnItemSelectedListener(this);
        oneBinding.fieldLayout.universitySpinner.setOnItemSelectedListener(this);


        oneBinding.fieldLayout.inputDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePickerDialog.show();
            }
        });

    }

    private void getBooking(String token) {
        ((ShowHomeScreen) getActivity()).showProgressDialog(getResources().getString(R.string.loading));
        String type = ApiConstant.PROFILE;



        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        profileViewModel = ViewModelProviders.of(getActivity()).get(ProfileViewModel.class);

        profileViewModel.getProfile(getActivity(),headers, type).observe(getActivity(), new Observer<ProfileApiResponse>() {
            @Override
            public void onChanged(ProfileApiResponse apiResponse) {
                  ((ShowHomeScreen) getActivity()).hideProgressDialog();
               /* if (apiResponse.response != null) {
                    setView(apiResponse);
                } else if (apiResponse.getStatus() == 401) {
                    ((ShowHomeScreen) getActivity()).hideProgressDialog();
                    Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                } else {
                    ((ShowHomeScreen) getActivity()).hideProgressDialog();
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }*/

                ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if (apiResponse.response != null) {

                    if(apiResponse.getResponse().getStatus() == 1){
                        setView(apiResponse);
                   //     Toast.makeText(getActivity(), apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getActivity(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setView(ProfileApiResponse apiResponse) {
        ProfileData profileData = apiResponse.getResponse().getData();
       // List<ProfileData> profileData = data.getData();

        city_spinner_id = profileData.getCity_id();
        district_spiner = profileData.getDistrict_id();
        university_id = profileData.getUniversity_id();

        oneBinding.fieldLayout.inputName.setText(profileData.getName());
        oneBinding.fieldLayout.inputEmail.setText(profileData.getEmail());
        oneBinding.fieldLayout.inputPhone.setText(profileData.getMobile());
        oneBinding.fieldLayout.inputDob.setText(profileData.getDob());
        oneBinding.fieldLayout.country.setText(profileData.getCountry());
        oneBinding.fieldLayout.city.setText(profileData.getCity());
        oneBinding.fieldLayout.district.setText(profileData.getDistrict());
        oneBinding.fieldLayout.university.setText(profileData.getUniversity());
        oneBinding.fieldLayout.postCode.setText(profileData.getPost_code());

        Picasso.get()
                .load(profileData.getProfile_image())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(oneBinding.profileImage);

        if (profileData.getGender() != null) {
            if (profileData.getGender().equals("F")) {
                oneBinding.fieldLayout.femaleRadiobtn.setChecked(true);
                oneBinding.fieldLayout.maleRadionbtn.setChecked(false);
            } else {
                oneBinding.fieldLayout.femaleRadiobtn.setChecked(false);
                oneBinding.fieldLayout.maleRadionbtn.setChecked(true);
            }
        }

        getDefaultData();
    }

    private void profileUpdate() {
        ((ShowHomeScreen) getActivity()).showProgressDialog(getResources().getString(R.string.loading));

        PostProfileModel postProfileModel = new PostProfileModel();
        postProfileModel.setName(oneBinding.fieldLayout.inputName.getText().toString());
        postProfileModel.setEmail(oneBinding.fieldLayout.inputEmail.getText().toString());
        postProfileModel.setMobile(oneBinding.fieldLayout.inputPhone.getText().toString());
        postProfileModel.setDob(oneBinding.fieldLayout.inputDob.getText().toString());
        postProfileModel.setCountry(oneBinding.fieldLayout.country.getText().toString());
        postProfileModel.setCity(city_spinner_id);
        postProfileModel.setDistrict(district_spiner);
        postProfileModel.setUniversity(university_id);
        postProfileModel.setPostCode(oneBinding.fieldLayout.postCode.getText().toString());
        postProfileModel.setImage(image_string);

        if (oneBinding.fieldLayout.femaleRadiobtn.isChecked()) {
            postProfileModel.setGender("F");
        } else {
            postProfileModel.setGender("M");
        }

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);


        profileViewModel = ViewModelProviders.of(getActivity()).get(ProfileViewModel.class);

        profileViewModel.updateProfile(getActivity(),headers, postProfileModel).observe(getActivity(), new Observer<ProfileApiResponse>() {
            @Override
            public void onChanged(ProfileApiResponse apiResponse) {
                 ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if (apiResponse.response != null) {
                    Toast.makeText(getActivity(), apiResponse.response.getMessage(), Toast.LENGTH_SHORT).show();

                    String userID =  apiResponse.getResponse().getData().getUser_id();
                    String image = apiResponse.getResponse().getData().getProfile_image();
                    String user_name = apiResponse.getResponse().getData().getName();
                    String user_email = apiResponse.getResponse().getData().getEmail();

                    session.createLoginSession(name,
                            email,userID,user_type,token,image,type);


                    ((ShowHomeScreen) getActivity()).screenBinding.headerLayout.name.setText(user_name);
                    ((ShowHomeScreen) getActivity()).screenBinding.headerLayout.email.setText(user_email);

                    Picasso.get()
                            .load(image)
                            /*  .placeholder(R.drawable.image1)
                              .error(R.drawable.err)*/
                            .into(((ShowHomeScreen) getActivity()).screenBinding.headerLayout.profileImage);

                } else if (apiResponse.getStatus() == 401) {
                    Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void convetBitmapString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        image_string = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private void pickImage(int REQUEST_CODE) {
        ImagePicker.with(this)                         //  Initialize ImagePicker with activity or fragment context
                .setToolbarColor("#212121")         //  Toolbar color
                .setStatusBarColor("#000000")       //  StatusBar color (works with SDK >= 21  )
                .setToolbarTextColor("#FFFFFF")     //  Toolbar text color (Title and Done button)
                .setToolbarIconColor("#FFFFFF")     //  Toolbar icon color (Back and Camera button)
                .setProgressBarColor("#4CAF50")     //  ProgressBar color
                .setBackgroundColor("#212121")      //  Background color
                .setCameraOnly(false)               //  Camera mode
                .setMultipleMode(false)              //  Select multiple images or single image
                .setFolderMode(true)                //  Folder mode
                .setShowCamera(true)                //  Show camera button
                .setFolderTitle("Albums")           //  Folder title (works with FolderMode = true)
                .setImageTitle("Galleries")         //  Image title (works with FolderMode = false)
                .setDoneTitle("Done")               //  Done button title
                .setLimitMessage("You have reached selection limit")    // Selection limit message
                .setMaxSize(5)                     //  Max images can be selected
                .setSavePath("ImagePicker")         //  Image capture folder name
                //.setSelectedImages(images)          //  Selected images
                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
                .setRequestCode(REQUEST_CODE)                //  Set request code, default Config.RC_PICK_IMAGES
                .setKeepScreenOn(true)              //  Keep screen on when selecting images
                .start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                pickImage(REQUEST_CODE);
                break;

            case R.id.btn_save:
                if(oneBinding.fieldLayout.inputName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Name field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.inputEmail.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Email field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.inputPhone.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Phone field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.inputDob.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Date of Birth field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.country.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Country field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(city_spinner_id.isEmpty()){
                    Toast.makeText(getActivity(), "City field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(district_spiner.isEmpty()){
                    Toast.makeText(getActivity(), "District field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(university_id.isEmpty()){
                    Toast.makeText(getActivity(), "University field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.postCode.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "PostCode field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    profileUpdate();
                }
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            // do your logic here...
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 2;

            String path = images.get(0).getPath();
            Toast.makeText(getActivity(), path, Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            oneBinding.profileImage.setImageBitmap(bitmap);

            convetBitmapString(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here
        // so ImagePicker can work with fragment mode
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
              //  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat sd1 = new SimpleDateFormat("dd-MM-yyyy");
                //  sd.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Date startDate = newDate.getTime();
               // fdate = sd.format(startDate);
                String final_date = sd1.format(startDate);
                oneBinding.fieldLayout.inputDob.setText(final_date);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
       // mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        /*  mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());*/

    }


    private void getSession(){
        session = new SessionManager(getActivity());
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);
        user_type = user.get(SessionManager.KEY_USERTYPE);
        type = user.get(SessionManager.KEY_LOGIN_TYPE);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
         token = user.get(SessionManager.KEY_TOKEN);


        getBooking(token);
    }


    private void getDefaultData(){
        String type = "ALL";

        searchViewModel = ViewModelProviders.of(getActivity()).get(SearchViewModel.class);

        searchViewModel.getDefaultData(getActivity(),type).observe(getActivity(), new Observer<FilterApiResponse>() {
            @Override
            public void onChanged(FilterApiResponse filterApiResponse) {
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

    private void setSpinner(List<CityModel> cityModelList,List<Period>  periodList) {

        CityModel cityModel = new CityModel();
        cityModel.setCity_name(getResources().getString(R.string.city_name));

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

        ArrayAdapter<CityModel> cityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, cityModelList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oneBinding.fieldLayout.citySpinner.setAdapter(cityAdapter);


        if(!city_spinner_id.equalsIgnoreCase("N/A") && !city_spinner_id.equalsIgnoreCase("") ){
            for(int i=0;i<cityModelList.size();i++){
                if (city_spinner_id.equals(cityModelList.get(i).getId())){

                    oneBinding.fieldLayout.citySpinner.setSelection(i);
                }
            }
        }

        ArrayAdapter<DistrictModel> districtAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, districtModelList);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oneBinding.fieldLayout.districtSpinner.setAdapter(districtAdapter);
        oneBinding.fieldLayout.districtSpinner.setSelection(Integer.parseInt(district_spiner),false);

        if(!district_spiner.equalsIgnoreCase("N/A") && !district_spiner.equalsIgnoreCase("") ){
            for(int i=0;i<districtModelList.size();i++){
                if (district_spiner.equals(districtModelList.get(i).getId())){

                    oneBinding.fieldLayout.districtSpinner.setSelection(i);
                }
            }
        }


        ArrayAdapter<UniversityModel> universityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,universityModelList);
        universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oneBinding.fieldLayout.universitySpinner.setAdapter(universityAdapter);

        if(!university_id.equalsIgnoreCase("N/A") && !university_id.equalsIgnoreCase("") ){
            for(int i=0;i<universityModelList.size();i++){
                if (university_id.equals(universityModelList.get(i).getId())){

                    oneBinding.fieldLayout.universitySpinner.setSelection(i);
                }
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()){


            case R.id.city_spinner:
                if(position>0){
                    CityModel cityModel = (CityModel)adapterView.getSelectedItem();
                    city_spinner_id = cityModel.getId();
                    Toast.makeText(getActivity(), cityModel.getCity_name(), Toast.LENGTH_SHORT).show();
                }else{
                    city_spinner_id="";
                }
                break;

            case R.id.district_spinner:
                if(position>0){
                    university.clear();
                    UniversityModel universityMode = new UniversityModel();
                    universityMode.setUniversity_name(getString(R.string.university));
                    university.add(0,universityMode);
                    for(DistrictModel districtModel:districtModelList){
                        for(UniversityModel universityModel:universityModelList) {
                            if(universityModel.getDistrict_id().equals(districtModel.getId())){
                                university.add(universityModel);
                                DistrictModel district = (DistrictModel)adapterView.getSelectedItem();
                                Toast.makeText(getActivity(), district.getDistrict_name(), Toast.LENGTH_SHORT).show();
                                district_spiner = district.getId();
                                ArrayAdapter<UniversityModel> universityAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, university);
                                universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                oneBinding.fieldLayout.universitySpinner.setAdapter(universityAdapter);

                            }
                        }
                    }
                }else{
                    district_spiner = "";
                }

                break;

            case R.id.university_spinner:
                if(position>0){
                    UniversityModel universityModel = (UniversityModel)adapterView.getSelectedItem();
                    university_id = universityModel.getId();
                    Toast.makeText(getActivity(), universityModel.getUniversity_name(), Toast.LENGTH_SHORT).show();
                }else{
                    university_id="";
                }

                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
