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
import android.widget.DatePicker;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentProfileScreenOneBinding;
import com.development.mycolive.model.editProfile.Data;
import com.development.mycolive.model.editProfile.PostProfileModel;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.model.editProfile.ProfileApiResponse;
import com.development.mycolive.model.editProfile.ProfileData;
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
public class ProfileScreenOne extends Fragment implements View.OnClickListener {
    FragmentProfileScreenOneBinding oneBinding;
    ProfileViewModel profileViewModel;
    SessionManager session;
    private int REQUEST_CODE = 100;
    private String fdate = "";
    private String image_string = "";
    private DatePickerDialog mDatePickerDialog;
    private String token="";
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
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        profileViewModel = ViewModelProviders.of(getActivity()).get(ProfileViewModel.class);

        profileViewModel.getProfile(getActivity(),headers, type).observe(getActivity(), new Observer<ProfileApiResponse>() {
            @Override
            public void onChanged(ProfileApiResponse apiResponse) {
                  ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if (apiResponse.response != null) {
                    setView(apiResponse);
                } else if (apiResponse.getStatus() == 401) {
                    ((ShowHomeScreen) getActivity()).hideProgressDialog();
                    Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                } else {
                    ((ShowHomeScreen) getActivity()).hideProgressDialog();
                    Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setView(ProfileApiResponse apiResponse) {
        Data data = apiResponse.getResponse().getData();
        List<ProfileData> profileData = data.getData();
        oneBinding.fieldLayout.inputName.setText(profileData.get(0).getName());
        oneBinding.fieldLayout.inputEmail.setText(profileData.get(0).getEmail());
        oneBinding.fieldLayout.inputPhone.setText(profileData.get(0).getMobile());
        oneBinding.fieldLayout.inputDob.setText(profileData.get(0).getDob());
        oneBinding.fieldLayout.country.setText(profileData.get(0).getCountry());
        oneBinding.fieldLayout.city.setText(profileData.get(0).getCity());
        oneBinding.fieldLayout.district.setText(profileData.get(0).getDistrict());
        oneBinding.fieldLayout.university.setText(profileData.get(0).getUniversity());
        oneBinding.fieldLayout.postCode.setText(profileData.get(0).getPost_code());

        Picasso.get()
                .load(profileData.get(0).getProfile_image())
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(oneBinding.profileImage);

        if (profileData.get(0).getGender() != null) {
            if (profileData.get(0).getGender().equals("Female")) {
                oneBinding.fieldLayout.femaleRadiobtn.setChecked(true);
                oneBinding.fieldLayout.maleRadionbtn.setChecked(false);
            } else {
                oneBinding.fieldLayout.femaleRadiobtn.setChecked(false);
                oneBinding.fieldLayout.maleRadionbtn.setChecked(true);
            }
        }
    }

    private void profileUpdate() {
        ((ShowHomeScreen) getActivity()).showProgressDialog(getResources().getString(R.string.loading));

        PostProfileModel postProfileModel = new PostProfileModel();
        postProfileModel.setName(oneBinding.fieldLayout.inputName.getText().toString());
        postProfileModel.setEmail(oneBinding.fieldLayout.inputEmail.getText().toString());
        postProfileModel.setMobile(oneBinding.fieldLayout.inputPhone.getText().toString());
        postProfileModel.setDob(oneBinding.fieldLayout.inputDob.getText().toString());
        postProfileModel.setCountry(oneBinding.fieldLayout.country.getText().toString());
        postProfileModel.setCity(oneBinding.fieldLayout.city.getText().toString());
        postProfileModel.setDistrict(oneBinding.fieldLayout.district.getText().toString());
        postProfileModel.setUniversity(oneBinding.fieldLayout.university.getText().toString());
        postProfileModel.setPostCode(oneBinding.fieldLayout.postCode.getText().toString());
        postProfileModel.setImage(image_string);

        if (oneBinding.fieldLayout.femaleRadiobtn.isChecked()) {
            postProfileModel.setGender("Female");
        } else {
            postProfileModel.setGender("Male");
        }

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);


        profileViewModel = ViewModelProviders.of(getActivity()).get(ProfileViewModel.class);

        profileViewModel.updateProfile(getActivity(),headers, postProfileModel).observe(getActivity(), new Observer<ProfilePostApiResponse>() {
            @Override
            public void onChanged(ProfilePostApiResponse apiResponse) {
                 ((ShowHomeScreen) getActivity()).hideProgressDialog();
                if (apiResponse.response != null) {
                    Toast.makeText(getActivity(), apiResponse.response.getMessage(), Toast.LENGTH_SHORT).show();
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
                }else if(oneBinding.fieldLayout.city.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "City field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.district.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "District field empty.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.university.getText().toString().isEmpty()){
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

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
         token = user.get(SessionManager.KEY_TOKEN);


        getBooking(token);
    }
}
