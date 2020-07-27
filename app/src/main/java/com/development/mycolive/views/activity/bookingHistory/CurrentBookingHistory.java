package com.development.mycolive.views.activity.bookingHistory;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.clickListener.RecyclerTouchListener;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityCurrentBookingHistoryBinding;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.adapter.MonthDataAdapter;
import com.development.mycolive.model.bookingHistory.BookingHistoryApiResponse;
import com.development.mycolive.model.bookingHistory.BookingHistoryData;
import com.development.mycolive.model.bookingHistory.MonthHistory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentBookingHistory extends BaseActivity implements View.OnClickListener {
ActivityCurrentBookingHistoryBinding historyBinding;
BookingHistoryViewModel viewModel;
    private MonthDataAdapter bookingAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<MonthHistory> bookingList = new ArrayList<>();
    SessionManager session;
    String token="";
    Dialog dialog;
    String proof_image="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        historyBinding = DataBindingUtil.setContentView(this,R.layout.activity_current_booking_history);
       if(getIntent()!=null)
       {
           String orderId =  getIntent().getExtras().getString(ApiConstant.ORDER_ID);
           String bookingType = getIntent().getExtras().getString(ApiConstant.BOOKING_TYPE);
         //  Toast.makeText(this, bookingType, Toast.LENGTH_SHORT).show();
          getSession(bookingType,orderId);

       }
        historyBinding.toolbar.setTitle(getString(R.string.current_booking_history));
        setSupportActionBar(historyBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        historyBinding.proof.setOnClickListener(this);




     //   historyBinding.bookingHistory.viewDetail.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_detail:
                 showCustomDialog();
                break;

            case R.id.proof:
                 if(proof_image!=null){
                     showDialog(proof_image);
                 }

                break;
        }
    }

    private void showCustomDialog(){
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, viewGroup, false);


        TextView view = (TextView)dialogView.findViewById(R.id.payment_id) ;
        view.setText("");
        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);



        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getHistory(String type,String orderId) {
         showProgressDialog(getString(R.string.loading));

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.METHOD,ApiConstant.METHOD_GET);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(this).get(BookingHistoryViewModel.class);
        viewModel.bookingHistory(this,headers,type,orderId).observe(this, new Observer<BookingHistoryApiResponse>() {
            @Override
            public void onChanged(BookingHistoryApiResponse historyApiResponse) {
                hideProgressDialog();
                if (historyApiResponse.getError() == null && historyApiResponse.getStatus() == 400) {
                    // handle error here
                } else if (historyApiResponse.getError() == null ) {
                       if(historyApiResponse.getResponse().getStatus() ==1 &&
                               historyApiResponse.getResponse().getMessage().equals(getString(R.string.success)))
                       {
                           setView(historyApiResponse);
                       }

                } else {
                    // call failed.
                    Throwable e = historyApiResponse.getError();
                    Toast.makeText(CurrentBookingHistory.this, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    // Log.e(TAG, "Error is " + e.getLocalizedMessage());
                }
            }
        });
    }

    private void setView(BookingHistoryApiResponse historyApiResponse){
     List<BookingHistoryData> historyData = historyApiResponse.getResponse().getData();
        historyBinding.srValue.setText(String.valueOf(historyData.get(0).getSr_no()));
        historyBinding.orderValue.setText(historyData.get(0).getId());
        historyBinding.bookingDateValue.setText(historyData.get(0).getBooking_date());
        historyBinding.bookingForValue.setText(historyData.get(0).getBooking_for());
        historyBinding.amountValue.setText(historyData.get(0).getAmount());

        historyBinding.appartmentValue.setText(historyData.get(0).getApartment_name());
        historyBinding.roomNameValue.setText(historyData.get(0).getRoom_name());
        historyBinding.postCode.setText(historyData.get(0).getPost_code());
        historyBinding.advancePayment.setText(" € "+historyData.get(0).getAdvance_payment_amount());
        historyBinding.address.setText(historyData.get(0).getAddress());
        historyBinding.nearBy.setText(historyData.get(0).getNear_by_area());
        historyBinding.proof.setText(historyData.get(0).getId_proof());
        proof_image = historyData.get(0).getId_proof();

        List<MonthHistory> monthHistoryList = historyData.get(0).getMonth();
        if(monthHistoryList.size()>0){
            setRecycleView(monthHistoryList);
        }
    }

    private void setRecycleView( List<MonthHistory> monthHistoryList){
        bookingAdapter = new MonthDataAdapter(this, monthHistoryList);
        mLayoutManager = new LinearLayoutManager(this);
        historyBinding.recyclerView.setLayoutManager(mLayoutManager);
        historyBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        historyBinding.recyclerView.setAdapter(bookingAdapter);
    }

    private void getSession(String bookingType,String orderId){
        session = new SessionManager(this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);

        getHistory(bookingType,orderId);
    }

    private void showDialog(String image) {
        // custom dialog
        dialog = new Dialog(CurrentBookingHistory.this);
        dialog.setContentView(R.layout.custom_dialog_image);

        // set the custom dialog components - text, image and button
        ImageButton close = (ImageButton) dialog.findViewById(R.id.btnClose);
        ImageView imageView = (ImageView)dialog.findViewById(R.id.receipt_image);

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.no_image_found)
                .error(R.drawable.no_image_found)
                .into(imageView);

        // Close Button
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //TODO Close button action
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }
}
