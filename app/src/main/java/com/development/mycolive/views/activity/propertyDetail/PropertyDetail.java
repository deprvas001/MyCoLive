package com.development.mycolive.views.activity.propertyDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaCas;
import android.os.Bundle;
import android.util.Property;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.adapter.FacilityAdapter;
import com.development.mycolive.adapter.HomeSlideAdapter;
import com.development.mycolive.adapter.MonthDataAdapter;
import com.development.mycolive.adapter.PropertyDetailAdapter;
import com.development.mycolive.clickListener.RecyclerTouchListener;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityPropertyDetailBinding;
import com.development.mycolive.model.RoomCategoryDetail;
import com.development.mycolive.model.bookingHistory.MonthHistory;
import com.development.mycolive.model.home.CountData;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailApiResponse;
import com.development.mycolive.model.propertyDetailModel.PropertyDetailData;
import com.development.mycolive.model.propertyDetailModel.PropertyImageSlider;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.BankAccount;
import com.development.mycolive.model.searchFilterModel.FilterApiResponse;
import com.development.mycolive.model.searchScreen.CityModel;
import com.development.mycolive.model.searchScreen.UniversityModel;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.bookingDetail.BookingDetails;
import com.development.mycolive.views.fragment.filterSearch.SearchViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyDetail extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
   public ActivityPropertyDetailBinding propertyDetailBinding;
    private PropertyDetailAdapter roomAdapter;
    private FacilityAdapter facilityAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    PropertyDetailViewModel detailViewModel;
    SessionManager session;
    int edit_position = 0,early_check=0;
    private DatePickerDialog mDatePickerDialog;
    float total_cost;
    String id="";
    String period,from,to;
    BankAccount bankAccount;
    List<PropertyRoomData> roomList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        propertyDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_property_detail);
        if(getIntent()!=null){
            id= getIntent().getExtras().getString("Property_Id");
        }
        setClickListener();
        getSession();

    }

    private void setSliderAndView(List<HomeSlider> sliderList) {
        final HomeSlideAdapter adapter = new HomeSlideAdapter(this, sliderList);
        adapter.setCount(sliderList.size());

        propertyDetailBinding.imageSlider.setSliderAdapter(adapter);

        propertyDetailBinding.imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        propertyDetailBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        propertyDetailBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        propertyDetailBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        propertyDetailBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        propertyDetailBinding.imageSlider.startAutoCycle();

        propertyDetailBinding.imageSlider.setOnIndicatorClickListener(position ->
                propertyDetailBinding.imageSlider.setCurrentPagePosition(position));

    }


    private void setRecycleView(List<PropertyRoomData> detailList, List<FacilityData> facilityData,String apartment_price){
        roomAdapter = new PropertyDetailAdapter(this, detailList,apartment_price);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true);
        propertyDetailBinding.recyclerView.setLayoutManager(mLayoutManager);
        propertyDetailBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        propertyDetailBinding.recyclerView.setAdapter(roomAdapter);

        facilityAdapter = new  FacilityAdapter(this, facilityData);
        mLayoutManager =new  GridLayoutManager(this, 2);
        propertyDetailBinding.recyclerViewFacility.setLayoutManager(mLayoutManager);
        propertyDetailBinding.recyclerViewFacility.setItemAnimator(new DefaultItemAnimator());
        propertyDetailBinding.recyclerViewFacility.setAdapter(facilityAdapter);
    }


    private void getDefaultData(String token) {
       /* String id = "6";*/
        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        detailViewModel = ViewModelProviders.of(this).get(PropertyDetailViewModel.class);
        detailViewModel.getPropertyDetail(this, headers,id).observe(this, new Observer<PropertyDetailApiResponse>() {
            @Override
            public void onChanged(PropertyDetailApiResponse apiResponse) {
                if (apiResponse.response != null) {
                 initializeView(apiResponse);
                List<PropertyDetailData> propertyDetails =   apiResponse.getResponse().getData();
                bankAccount =  propertyDetails.get(0).getBank_account();
                 String apartment_price = apiResponse.getResponse().getData().get(0).getTotal_price();
                 setRecycleView(apiResponse.getResponse().getData().get(0).getRoom(),
                         apiResponse.getResponse().getData().get(0).getFacility(),apartment_price);
                 setSliderAndView(apiResponse.getResponse().getData().get(0).getImage_slider());
                }
            }
        });
    }


    private void initializeView(PropertyDetailApiResponse apiResponse){
        propertyDetailBinding.apartmentName.setText(apiResponse.getResponse().getData().get(0).getApartment_name());
        propertyDetailBinding.addressApartment.setText(apiResponse.getResponse().getData().get(0).getAddress());
        propertyDetailBinding.description.setText(apiResponse.getResponse().getData().get(0).getDescription());
        propertyDetailBinding.totalPrice.setText("$"+apiResponse.getResponse().getData().get(0).getTotal_price());

        total_cost = Float.parseFloat(apiResponse.getResponse().getData().get(0).getTotal_price());
     }

    private void getSession(){
        session = new SessionManager(getApplicationContext());
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        String token = user.get(SessionManager.KEY_TOKEN);

        propertyDetailBinding.toolbar.setTitle(getResources().getString(R.string.PropertyDetail));
        setSupportActionBar(propertyDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getDefaultData(token);
    }

    private void setClickListener(){
        setDateTimeField();
        propertyDetailBinding.type.setOnItemSelectedListener(this);
        propertyDetailBinding.fromEdit.setOnClickListener(this);
        propertyDetailBinding.toEdit.setOnClickListener(this);
        propertyDetailBinding.arrivalDate.setOnClickListener(this);
        propertyDetailBinding.earlyCheckIn.setOnClickListener(this);
        propertyDetailBinding.btnProceed.setOnClickListener(this);


        propertyDetailBinding.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),  propertyDetailBinding.recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (adapterView.getId()){
            case R.id.type:
                if(position>0){
                    // period = adapterView.getSelectedItem().toString();
                    period = String.valueOf(position);
                    if(position == 4){
                        propertyDetailBinding.durationLayout.setVisibility(View.VISIBLE);
                    }else{
                        propertyDetailBinding.durationLayout.setVisibility(View.GONE);
                        propertyDetailBinding.fromEdit.setText(null);
                        propertyDetailBinding.toEdit.setText(null);
                        from="";
                        to="";
                    }
                }else{
                    period="";
                    from="";
                    to="";
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_proceed:
                if(period.isEmpty()){
                    Toast.makeText(this, "Please Select Period.", Toast.LENGTH_SHORT).show();
                  return;
                }/*else if(propertyDetailBinding.arrivalDate.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please Select Arrival date.", Toast.LENGTH_SHORT).show();
                 return;
                }*/else if(period.equals("4")){
                    if(propertyDetailBinding.fromEdit.getText().toString().isEmpty()
                            || propertyDetailBinding.toEdit.getText().toString().isEmpty()){
                        Toast.makeText(this, "Please select from and to date.", Toast.LENGTH_SHORT).show();
                    }else{
                       from = propertyDetailBinding.fromEdit.getText().toString();
                       to = propertyDetailBinding.fromEdit.getText().toString();
                      //  Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                      bookingDetail();
                    }

                }else{
                      bookingDetail();
                }
                break;

            case R.id.from_edit:
                edit_position=1;
                mDatePickerDialog.show();
                break;

            case R.id.to_edit:
                edit_position=2;
                mDatePickerDialog.show();
                break;

            case R.id.arrival_date:
                edit_position=3;
                mDatePickerDialog.show();
                break;

            case R.id.early_check_in:

                if(early_check ==0){
                    propertyDetailBinding.earlyCheckDescrp.setVisibility(View.VISIBLE);
                    propertyDetailBinding.earlyCheckIn.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
                    early_check =1;
                }else{
                    propertyDetailBinding.earlyCheckDescrp.setVisibility(View.GONE);
                    propertyDetailBinding.earlyCheckIn.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));
                    early_check =0;
                }
                break;
        }
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                //  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat sd1 = new SimpleDateFormat("dd-MM-yyyy");
                //  sd.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Date startDate = newDate.getTime();
                // fdate = sd.format(startDate);
                String final_date = sd1.format(startDate);
               // oneBinding.fieldLayout.inputDob.setText(final_date);
                if(edit_position == 1){
                    propertyDetailBinding.fromEdit.setText(final_date);
                }else if (edit_position == 2){
                    propertyDetailBinding.toEdit.setText(final_date);
                }else if(edit_position == 3){
                    propertyDetailBinding.arrivalDate.setText(final_date);
                }

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
         mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        /*  mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bookingDetail(){
        if(PropertyDetailAdapter.roomDataList.size()>0){
            if(propertyDetailBinding.policyAccept.isChecked() ){
                PaymentRequestBody requestBody = new PaymentRequestBody();
                if(period.equals("4")){
                    requestBody.setDaterange(from+"-"+to);
                }else{
                    requestBody.setDaterange("");
                }
                requestBody.setReceipt("");
                requestBody.setEarly_check(propertyDetailBinding.arrivalDate.getText().toString());
                requestBody.setContract("1");
                requestBody.setPayment_method("Bank");
                requestBody.setDuration(period);


                Intent intent = new Intent(PropertyDetail.this,BookingDetails.class);
                intent.putExtra("total_price",total_cost);
                intent.putExtra("booking_info",requestBody);
                intent.putExtra("bank_account",bankAccount);
                intent.putParcelableArrayListExtra("data", PropertyDetailAdapter.roomDataList);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Please accept Policy.", Toast.LENGTH_SHORT).show();
            }     
        }else{
            Toast.makeText(this, "Please select room.", Toast.LENGTH_SHORT).show();
        }
       

    }
}
