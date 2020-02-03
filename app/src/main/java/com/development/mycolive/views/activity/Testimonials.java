package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityTestimonialsBinding;
import com.development.mycolive.views.adapter.TestimonialAdapter;
import com.development.mycolive.views.model.TestimonialModel;

import java.util.ArrayList;
import java.util.List;

public class Testimonials extends AppCompatActivity implements View.OnClickListener {
    private TestimonialAdapter testimonialAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<TestimonialModel> testimonialList = new ArrayList<>();
    ActivityTestimonialsBinding testimonialsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testimonialsBinding = DataBindingUtil.setContentView(this,R.layout.activity_testimonials);

        /*testimonialsBinding.toolbar.setTitle(getString(R.string.testimonial));
        setSupportActionBar(testimonialsBinding.toolbar);
        setSupportActionBar(testimonialsBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        setRecyclerView();
        settestimonialList();
        setClickListener();
    }

    private void setRecyclerView() {
        testimonialAdapter = new TestimonialAdapter(Testimonials.this, testimonialList);
        mLayoutManager = new LinearLayoutManager(Testimonials.this);
        testimonialsBinding.recyclerView.setLayoutManager(mLayoutManager);
        testimonialsBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        testimonialsBinding.recyclerView.setAdapter(testimonialAdapter);

    }

    private void settestimonialList(){
        testimonialList.clear();
        for (int i=0;i<4;i++){
            TestimonialModel testimonial = new TestimonialModel("",
                    "","","");
            testimonialList.add(testimonial);
        }
        testimonialAdapter.notifyDataSetChanged();
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }*/

  private void setClickListener(){
      testimonialsBinding.back.setOnClickListener(this);
      testimonialsBinding.notification.setOnClickListener(this);
  }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;

            case R.id.notification:
                startActivity(new Intent(this,Notification.class));
                break;
        }
    }
}
