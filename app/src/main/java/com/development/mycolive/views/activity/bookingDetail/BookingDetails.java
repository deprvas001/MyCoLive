package com.development.mycolive.views.activity.bookingDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.adapter.BookingDetailAdapter;
import com.development.mycolive.databinding.ActivityBookingDetailsBinding;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.BankAccount;
import com.development.mycolive.model.termscondition.ContractResponse;
import com.development.mycolive.views.activity.paymentScreen.SelectPayment;

import java.util.ArrayList;
import java.util.List;

public class BookingDetails extends AppCompatActivity implements View.OnClickListener {
    ActivityBookingDetailsBinding bookingDetailsBinding;
    ArrayList<PropertyRoomData> roomDataList=new ArrayList<>();
    float total_price =0,early_check=0,final_price;
    BookingDetailAdapter bookingDetailAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    PaymentRequestBody requestBody;
    ContractResponse contractResponse;
    ArrayList<String> email_id = new ArrayList<>();
    List<String> id_list =new ArrayList<>();
    BankAccount bankAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookingDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking_details);
       /* bookingDetailsBinding.toolbar.setTitle(getString(R.string.booking_detail));
        setSupportActionBar(bookingDetailsBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        if (getIntent() != null) {

        roomDataList = getIntent().getParcelableArrayListExtra("data");
        total_price = getIntent().getExtras().getFloat("total_price");
        requestBody = getIntent().getParcelableExtra("booking_info");
        bankAccount = getIntent().getParcelableExtra("bank_account") ;
        contractResponse = getIntent().getParcelableExtra("contract_response");

         early_check  = contractResponse.getEarly_checkin_rent();
         requestBody.setEarly_check_price(String.valueOf(early_check));

        for(int i=0;i<roomDataList.size();i++){
            id_list.add(roomDataList.get(i).getId());
        }

        requestBody.setRoom_id(id_list);
        initializeView(roomDataList);
        }

        setClickListener();
        //  setContentView(R.layout.booking_detail_item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected (item);
    }

    private void initializeView(ArrayList<PropertyRoomData> roomDataList){
        for(int i=0;i<roomDataList.size();i++){
            total_price = total_price+Float.parseFloat(roomDataList.get(i).getTotal_price());
        }

          final_price = total_price+early_check;
          bookingDetailsBinding.totalPrice.setText("€ "+String.valueOf(total_price)+" + "+early_check+" (Early_Check_In_Price)");
          bookingDetailsBinding.finalPrice.setText("€ "+final_price);
        setRecyclerView(roomDataList,contractResponse);
    }

    private void setRecyclerView(ArrayList<PropertyRoomData> roomDataList, ContractResponse contractResponse){
        bookingDetailAdapter = new BookingDetailAdapter(this, roomDataList,contractResponse);
        mLayoutManager = new LinearLayoutManager(this);
        bookingDetailsBinding.recyclerView.setLayoutManager(mLayoutManager);
        bookingDetailsBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        bookingDetailsBinding.recyclerView.setAdapter(bookingDetailAdapter);
    }

    private void setClickListener(){
        bookingDetailsBinding.btnProceed.setOnClickListener(this);
        bookingDetailsBinding.policyLink.setOnClickListener(this);
        bookingDetailsBinding.back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_proceed:

                if(bookingDetailsBinding.referFriend.isChecked()){
                   String email = bookingDetailsBinding.referEdit.getText().toString();

                   if(email.isEmpty()){
                       Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                   }else{
                       if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                           if(bookingDetailsBinding.policAccept.isChecked()){
                               email_id.add(email);
                               gotoNext(email);
                           }else{
                               Toast.makeText(this, "Please accept policy.", Toast.LENGTH_SHORT).show();
                           }

                       }else{
                           bookingDetailsBinding.referEdit.setError("Enter valid email.");
                       }

                   }
                }else{
                    if(bookingDetailsBinding.policAccept.isChecked()){
                        email_id.add("");
                        gotoNext("");
                    }else{
                        Toast.makeText(this, "Please accept policy.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.policy_link:
                String url = "https://webfume.in/mani-budapest/landing/privacyPolicy";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.back:
                finish();
                break;
        }
    }

    private void gotoNext(String email){

        Intent i = new Intent(this,SelectPayment.class);
        i.putExtra("total_price",final_price);
        i.putExtra("booking_info",requestBody);
        i.putExtra("bank_account",bankAccount);
        i.putStringArrayListExtra("email",email_id);
        startActivity(i);
    }


}
