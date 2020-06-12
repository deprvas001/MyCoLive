package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.development.mycolive.R;
import com.development.mycolive.model.home.HomeSlider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class ImageShowScreen extends AppCompatActivity  implements StoriesProgressView.StoriesListener {

    private static final int PROGRESS_COUNT = 6;

    private StoriesProgressView storiesProgressView;
    private ImageView image;

    private int counter = 0;
    private final int[] resources = new int[]{
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image1,
            R.drawable.image2,
    };

    private final long[] durations = new long[]{
            500L, 1000L, 1500L, 4000L, 5000L, 1000,
    };

    long pressTime = 0L;
    long limit = 500L;
    ArrayList<HomeSlider> sliderArrayList;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    pressTime = System.currentTimeMillis();
                    storiesProgressView.pause();
                    return false;
                case MotionEvent.ACTION_UP:
                    long now = System.currentTimeMillis();
                    storiesProgressView.resume();
                    return limit < now - pressTime;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show_screen);

        if(getIntent().getExtras()!=null){
            sliderArrayList = getIntent().getExtras().getParcelableArrayList("image_slider");
        }
        storiesProgressView = (StoriesProgressView) findViewById(R.id.stories);
        storiesProgressView.setStoriesCount( sliderArrayList.size());
        storiesProgressView.setStoryDuration(3000L);
        // or
        // storiesProgressView.setStoriesCountWithDurations(durations);
        storiesProgressView.setStoriesListener(this);
//        storiesProgressView.startStories();
        counter = 0;
        storiesProgressView.startStories(counter);

        image = (ImageView) findViewById(R.id.image);
       /* image.setImageResource(resources[counter]);*/

        Picasso.get()
                .load(sliderArrayList.get(counter).getImage())
              //  .placeholder(R.drawable.no_image_found)
                .fit()
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(image);

        // bind reverse view
        View reverse = findViewById(R.id.reverse);
        reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storiesProgressView.reverse();
            }
        });
        reverse.setOnTouchListener(onTouchListener);

        // bind skip view
        View skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storiesProgressView.skip();
            }
        });
        skip.setOnTouchListener(onTouchListener);
    }

    @Override
    public void onNext() {
    //    image.setImageResource(resources[++counter]);

        Picasso.get()
                .load(sliderArrayList.get(++counter).getImage())
              //  .placeholder(R.drawable.no_image_found)
                .fit()
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(image);
    }

    @Override
    public void onPrev() {
        if ((counter - 1) < 0) return;

       // image.setImageResource(resources[--counter]);
        Picasso.get()
                .load(sliderArrayList.get(--counter).getImage())
              //  .placeholder(R.drawable.no_image_found)
                .fit()
                /*  .placeholder(R.drawable.image1)
                  .error(R.drawable.err)*/
                .into(image);
    }

    @Override
    public void onComplete() {
        storiesProgressView.destroy();
       finish();
    }

    @Override
    protected void onDestroy() {
        // Very important !
        storiesProgressView.destroy();
        super.onDestroy();
    }
}
