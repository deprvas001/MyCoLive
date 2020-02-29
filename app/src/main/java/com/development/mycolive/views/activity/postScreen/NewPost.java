package com.development.mycolive.views.activity.postScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityNewPostBinding;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.UniversityModel;
import com.development.mycolive.model.viewCommunityModel.CommentApiResponse;
import com.development.mycolive.model.viewCommunityModel.CommentPost;
import com.development.mycolive.views.activity.viewCommunity.CommunityViewModel;
import com.development.mycolive.views.fragment.filterSearch.SearchViewModel;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewPost extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ActivityNewPostBinding postBinding;
    List<String> post_type = new ArrayList<String>();
    List<String> images = new ArrayList<>();
    private int REQUEST_CODE = 0;
    SearchViewModel searchViewModel;
    PostViewModel postViewModel;
    private String type="";
    private String city="";
    private String university="";
    private HashMap<Integer,String> image_option =new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_post);
        setClickListener();
        getDefaultData();

    }

    private void setSpinner(List<CityModel> cityModelList, List<UniversityModel> universityModelList) {

        CityModel cityModel = new CityModel();
        cityModel.setCity_name(getString(R.string.city_name));

        UniversityModel universityModel = new UniversityModel();
        universityModel.setUniversity_name(getString(R.string.university));

        cityModelList.add(0, cityModel);
        universityModelList.add(0, universityModel);

        /*post_type.add("Post Type");
        // Creating adapter for spinner
        ArrayAdapter<String> postAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, post_type);

        // Drop down layout style - list view with radio button
        postAdapter.setDropDownViewResource(R.layout.spinner_item);

        // attaching data adapter to spinner
        postBinding.postType.setAdapter(postAdapter);*/


        ArrayAdapter<CityModel> cityAdapter = new ArrayAdapter<CityModel>(this, android.R.layout.simple_spinner_dropdown_item, cityModelList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postBinding.citySpinner.setAdapter(cityAdapter);

        ArrayAdapter<UniversityModel> universityAdapter = new ArrayAdapter<UniversityModel>(this, android.R.layout.simple_spinner_item, universityModelList);
        universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postBinding.university.setAdapter(universityAdapter);
    }

    private void setClickListener() {
        postBinding.back.setOnClickListener(this);
        postBinding.uploadImage.setOnClickListener(this);
        postBinding.uploadImage1.setOnClickListener(this);
        postBinding.uploadImage2.setOnClickListener(this);
        postBinding.uploadImage3.setOnClickListener(this);
        postBinding.uploadImage4.setOnClickListener(this);
        postBinding.btnPost.setOnClickListener(this);
        postBinding.university.setOnItemSelectedListener(this);
        postBinding.citySpinner.setOnItemSelectedListener(this);
        postBinding.postType.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.upload_image:
                REQUEST_CODE = 100;
                pickImage(REQUEST_CODE);
                break;

            case R.id.upload_image1:
                REQUEST_CODE = 101;
                pickImage(REQUEST_CODE);
                break;

            case R.id.upload_image2:
                REQUEST_CODE = 102;
                pickImage(REQUEST_CODE);
                break;

            case R.id.upload_image3:
                REQUEST_CODE = 103;
                pickImage(REQUEST_CODE);
                break;

            case R.id.upload_image4:
                REQUEST_CODE = 104;
                pickImage(REQUEST_CODE);
                break;

            case R.id.btn_post:
                if(type.isEmpty()){
                    Toast.makeText(this, "Please select post type", Toast.LENGTH_SHORT).show();
                    return;
                }else if(city.isEmpty()){
                    Toast.makeText(this, "Please select city", Toast.LENGTH_SHORT).show();
                    return;
                }else if(university.isEmpty()){
                    Toast.makeText(this, "Please select university", Toast.LENGTH_SHORT).show();
                    return;
                }else if(images.size()==0){
                    Toast.makeText(this, "Please select upload one image", Toast.LENGTH_SHORT).show();
                    return;
                }else if(postBinding.comment.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please add comment", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    setPost();
                }

                break;
        }
    }

    private void getDefaultData() {
        String type = "ALL";

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        searchViewModel.getDefaultData(this, type).observe(this, new Observer<FilterApiResponse>() {
            @Override
            public void onChanged(FilterApiResponse filterApiResponse) {
                if (filterApiResponse.filterResponse != null) {
                    List<CityModel> cityModelList = filterApiResponse.getFilterResponse().getData().getCityList();
                    List<UniversityModel> universityModelList = filterApiResponse.getFilterResponse().getData().getUniversityList();
                    setSpinner(cityModelList, universityModelList);
                }
            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            // do your logic here...
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 2;

            String path = images.get(0).getPath();
            Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);

            setImageView(REQUEST_CODE,bitmap);

          //  postBinding.uploadImage1.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here
        // so ImagePicker can work with fragment mode
    }

    private void setImageView(int code,Bitmap bitmap){

        switch (code){
            case 100:
                postBinding.uploadImage.setImageBitmap(bitmap);
                convetBitmapString(bitmap);
                break;

            case 101:
                postBinding.uploadImage1.setImageBitmap(bitmap);
                convetBitmapString(bitmap);
                break;

            case 102:
                postBinding.uploadImage2.setImageBitmap(bitmap);
                convetBitmapString(bitmap);
                break;

            case 103:
                postBinding.uploadImage3.setImageBitmap(bitmap);
                convetBitmapString(bitmap);
                break;

            case 104:
                postBinding.uploadImage4.setImageBitmap(bitmap);
                convetBitmapString(bitmap);
                break;
        }

    }

    private void convetBitmapString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        images.add(encoded);
    }


    private void setPost(){
        PostCommunity postCommunity = new PostCommunity();
        postCommunity.setCity("2");
        postCommunity.setComment("sdfsdf");
        postCommunity.setPost_type("2");
        postCommunity.setUniversity("6");
        postCommunity.setImage(images);


        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        postViewModel.getResponse(this,postCommunity).observe(this, new Observer<PostApiResponse>() {
            @Override
            public void onChanged(PostApiResponse apiResponse) {
                if(apiResponse.response !=null){

                }else if(apiResponse.getStatus()== 401){
                }
                else{
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()) {
            case R.id.post_type:
                if(position>0){
                  if(position ==1){
                      type = "1";
                  }else{
                      type = "2";
                  }
                }else{
                    type="";
                }
                break;

            case R.id.university:
                if(position>0){
                    UniversityModel universityModel = (UniversityModel)adapterView.getSelectedItem();
                    university = universityModel.getId();
                    Toast.makeText(this, university, Toast.LENGTH_SHORT).show();
                }else{
                    university = "";
                }
                break;

            case R.id.city_spinner:
                if(position>0){
                    CityModel cityModel = (CityModel)adapterView.getSelectedItem();
                    city = cityModel.getId();
                    Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
                }else{
                    city = "";
                }
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
