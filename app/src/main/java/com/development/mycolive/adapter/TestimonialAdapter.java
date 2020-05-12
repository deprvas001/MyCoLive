package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.testimonialmodel.TestimonialModel;
import com.squareup.picasso.Picasso;


import java.util.List;

public class TestimonialAdapter extends RecyclerView.Adapter<TestimonialAdapter.MyViewHolder> {

    private List<TestimonialModel> testimonialList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,testimonial_desc;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            /*title = (TextView) view.findViewById(R.id.type);
            imageView = (ImageView)view.findViewById(R.id.image);*/
            title = (TextView) view.findViewById(R.id.name);
            imageView = (ImageView)view.findViewById(R.id.image);
            testimonial_desc = (TextView)view.findViewById(R.id.description);
        }
    }

    public TestimonialAdapter(Context context,List<TestimonialModel> testimonialList) {
        this.context = context;
        this.testimonialList = testimonialList;
    }

    @Override
    public TestimonialAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testimonials_item_row, parent, false);

        return new TestimonialAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TestimonialAdapter.MyViewHolder holder, int position) {
         TestimonialModel testimonial = testimonialList.get(position);
         holder.title.setText(testimonial.getName());
         holder.testimonial_desc.setText(testimonial.getTestimonial());


            Picasso.get()
                    .load(testimonial.getImage())
                    .placeholder(R.drawable.no_image_found)
                    /*  .placeholder(R.drawable.image1)
                      .error(R.drawable.err)*/
                    .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return testimonialList.size();
    }
}
