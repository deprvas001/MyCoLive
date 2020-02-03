package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityNewPostBinding;

import java.util.ArrayList;
import java.util.List;

public class NewPost extends AppCompatActivity implements View.OnClickListener {
    ActivityNewPostBinding postBinding;
    List<String> post_type = new ArrayList<String>();
    List<String> city = new ArrayList<>();
    List<String> university = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postBinding = DataBindingUtil.setContentView(this,R.layout.activity_new_post);
        setClickListener();
        setSpinner();
    }

    private void setSpinner() {
        post_type.add("Post Type");
        city.add("City");
        university.add("University");
        // Creating adapter for spinner
        ArrayAdapter<String> postAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, post_type);

        // Drop down layout style - list view with radio button
        postAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        postBinding.postType.setAdapter(postAdapter);


        ArrayAdapter<String>  cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postBinding.citySpinner.setAdapter(cityAdapter);

        ArrayAdapter<String> universityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, university);
        universityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        postBinding.university.setAdapter(universityAdapter);
    }

    private void setClickListener(){
        postBinding.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
