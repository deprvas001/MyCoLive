package com.development.mycolive.views.activity.propertyDetail;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
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
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.development.mycolive.model.termscondition.ContractResponse;
import com.development.mycolive.model.termscondition.TermCondApiResponse;
import com.development.mycolive.model.termscondition.TermRequest;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.bookingDetail.BookingDetails;
import com.development.mycolive.views.fragment.filterSearch.SearchViewModel;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static androidx.core.util.Preconditions.checkNotNull;

public class PropertyDetail extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
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
    String token="";
    public static float apartment_price =0;
    boolean isTextViewClicked = false;
    String period,from,to,lat,lng,title;
    BankAccount bankAccount;
    String fbLink="",chatLink="",video_link="";
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


    private void setRecycleView(List<PropertyRoomData> detailList, List<FacilityData> facilityData,String apartment_total){
        apartment_price = Float.parseFloat(apartment_total);
        roomAdapter = new PropertyDetailAdapter(this, detailList,apartment_total);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
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
      showProgressDialog(getString(R.string.loading));
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
                hideProgressDialog();
                if (apiResponse.response != null) {

                    if(apiResponse.getResponse().getData().size()>0 &&
                       apiResponse.getResponse().getStatus() == 1){
                        initializeView(apiResponse);
                        List<PropertyDetailData> propertyDetails =   apiResponse.getResponse().getData();
                        bankAccount =  propertyDetails.get(0).getBank_account();
                        String apartment_price = apiResponse.getResponse().getData().get(0).getTotal_price();
                        setRecycleView(apiResponse.getResponse().getData().get(0).getRoom(),
                                apiResponse.getResponse().getData().get(0).getFacility(),apartment_price);
                        setSliderAndView(apiResponse.getResponse().getData().get(0).getImage_slider());
                    }else{
                        Toast.makeText(PropertyDetail.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                     }
                }
            }
        });
    }


    private void initializeView(PropertyDetailApiResponse apiResponse){
        lat = apiResponse.getResponse().getData().get(0).getLatitude();
        lng = apiResponse.getResponse().getData().get(0).getLongitude();
        title = apiResponse.getResponse().getData().get(0).getApartment_name();

        chatLink =apiResponse.getResponse().getData().get(0).getFb_chating_link();
        fbLink = apiResponse.getResponse().getData().get(0).getFb_page_link();
         video_link = apiResponse.getResponse().getData().get(0).getVideo_link();
       if(chatLink!=null){
           if(chatLink.isEmpty()){
               propertyDetailBinding.messageIcon.setVisibility(View.GONE);
           }
           if(fbLink.isEmpty()){
               propertyDetailBinding.facebookIcon.setVisibility(View.GONE);
           }
       }



        propertyDetailBinding.apartmentName.setText(apiResponse.getResponse().getData().get(0).getApartment_name());
        propertyDetailBinding.addressApartment.setText(apiResponse.getResponse().getData().get(0).getAddress());
        propertyDetailBinding.description.setText(apiResponse.getResponse().getData().get(0).getDescription());
        propertyDetailBinding.totalPrice.setText("€ "+apiResponse.getResponse().getData().get(0).getTotal_price());

       if(video_link !=null ){
           if(video_link.isEmpty()){
                propertyDetailBinding.youtubeIcon.setVisibility(View.GONE);
           }
       }

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
         token = user.get(SessionManager.KEY_TOKEN);

        /*propertyDetailBinding.toolbar.setTitle(getResources().getString(R.string.PropertyDetail));
        setSupportActionBar(propertyDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        if(isNetworkAvailable(PropertyDetail.this)){
            getDefaultData(token);
        }else{
            Toast.makeText(this, getString(R.string.check_network), Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void setClickListener(){
        setDateTime();
        propertyDetailBinding.type.setOnItemSelectedListener(this);
        propertyDetailBinding.fromEdit.setOnClickListener(this);
        propertyDetailBinding.toEdit.setOnClickListener(this);
        propertyDetailBinding.arrivalDate.setOnClickListener(this);
        propertyDetailBinding.earlyCheckIn.setOnClickListener(this);
        propertyDetailBinding.btnProceed.setOnClickListener(this);
        propertyDetailBinding.readMore.setOnClickListener(this);
        propertyDetailBinding.policyLink.setOnClickListener(this);
        propertyDetailBinding.locationImage.setOnClickListener(this);
        propertyDetailBinding.facebookIcon.setOnClickListener(this);
        propertyDetailBinding.messageIcon.setOnClickListener(this);
        propertyDetailBinding.youtubeIcon.setOnClickListener(this);
        propertyDetailBinding.back.setOnClickListener(this);
        propertyDetailBinding.clear.setOnClickListener(this);


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
            case R.id.clear:
                propertyDetailBinding.arrivalDate.setText("");
                break;

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
                      to = propertyDetailBinding.toEdit.getText().toString();
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

            case R.id.policy_link:
                String url = "https://webfume.in/mani-budapest/landing/privacyPolicy";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

            case R.id.location_image:
                String geoUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + title + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(geoUri));
                startActivity(intent);
                break;

            case R.id.read_more:


                if(isTextViewClicked){
                    //This will shrink textview to 2 lines if it is expanded.
                    propertyDetailBinding.description.setMaxLines(2);
                    propertyDetailBinding.readMore.setText(getResources().getString(R.string.read_more));
                    isTextViewClicked = false;
                } else {
                    propertyDetailBinding.readMore.setText(getResources().getString(R.string.read_less));
                    //This will expand the textview if it is of 2 lines

                    propertyDetailBinding.description.setMaxLines(Integer.MAX_VALUE);
                    isTextViewClicked = true;
                }
                break;

            case R.id.to_edit:
                edit_position=2;
                mDatePickerDialog.show();
                break;

            case R.id.arrival_date:
                edit_position=3;
                mDatePickerDialog.show();
                break;

            case R.id.back:
                finish();
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


            case R.id.facebook_icon:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(fbLink));
                startActivity(intent);
                break;

            case R.id.message_icon:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(chatLink));
                startActivity(intent);
                break;

            case R.id.youtube_icon:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(video_link));
                startActivity(intent);
                break;
        }
    }

    private void setDateTime() {
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
                    monthYearFormat(final_date);
                  //  propertyDetailBinding.fromEdit.setText(final_date);
                }else if (edit_position == 2){
                    monthYearFormat(final_date);
                   // propertyDetailBinding.toEdit.setText(final_date);
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

                List<String> id_list = new ArrayList<>();


            for(int i=0;i<PropertyDetailAdapter.roomDataList.size();i++){
                id_list.add(PropertyDetailAdapter.roomDataList.get(i).getId());
            }

              requestBody.setRoom_id(id_list);

              getContract(token,total_cost,requestBody,bankAccount);


        }else{
            Toast.makeText(this, "Please select room.", Toast.LENGTH_SHORT).show();
        }
    }

  public   void showCustomDialog(ContractResponse contractResponse ,PaymentRequestBody requestBody){
      //before inflating the custom alert dialog layout, we will get the current activity viewgroup
      ViewGroup viewGroup = findViewById(android.R.id.content);

      //then we will inflate the custom alert dialog xml that we created
      View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_terms_condition, viewGroup, false);


      //Now we need an AlertDialog.Builder object
      AlertDialog.Builder builder = new AlertDialog.Builder(this);

      //setting the view of the builder to our custom view that we already inflated
      builder.setView(dialogView);
      //finally creating the alert dialog and displaying it
      AlertDialog alertDialog = builder.create();
      alertDialog.show();

  //    Spanned htmlAsSpanned = Html.fromHtml(contractResponse.getContractDetails());

      TextView contract = (TextView)dialogView.findViewById(R.id.contract);

      SpannableStringBuilder spanned = (SpannableStringBuilder) Html.fromHtml(contractResponse.getContractDetails());
      spanned = trimSpannable(spanned);
      contract.setText(spanned,TextView.BufferType.SPANNABLE);

      ImageView close = (ImageView)dialogView.findViewById(R.id.close);

      CheckBox check = (CheckBox)dialogView.findViewById(R.id.terms_condition);

      Button button_ok = (Button)dialogView.findViewById(R.id.buttonOk);


      close.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              alertDialog.dismiss();
          }
      });
      button_ok.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(check.isChecked()){
                  if(contractResponse.getIs_available() == 1 ){
                      Intent intent = new Intent(PropertyDetail.this,BookingDetails.class);
                      intent.putExtra("contract_response",contractResponse);
                      intent.putExtra("bank_info",bankAccount);
                      intent.putExtra("total_price",total_cost);
                      intent.putExtra("booking_info",requestBody);
                      intent.putExtra("bank_account",bankAccount);
                      intent.putParcelableArrayListExtra("data", PropertyDetailAdapter.roomDataList);
                      startActivity(intent);
                  }else{
                      Toast.makeText(PropertyDetail.this, contractResponse.getMsg(), Toast.LENGTH_SHORT).show();
                  }

              }else{
                  Toast.makeText(PropertyDetail.this, "Please accept terms And condition", Toast.LENGTH_SHORT).show();
              }
          }
      });

    }


    private void getContract(String token, float total_cost, PaymentRequestBody requestBody, BankAccount bankAccount) {
        /* String id = "6";*/
       showProgressDialog(getString(R.string.loading));
        TermRequest request = new TermRequest();
        request.setDuration(period);
        request.setEarly_check(requestBody.getEarly_check());
        request.setDaterange(requestBody.getDaterange());
        request.setRoom_id(requestBody.getRoom_id());

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        detailViewModel = ViewModelProviders.of(this).get(PropertyDetailViewModel.class);
        detailViewModel.getTermsCondition(this, headers,request).observe(this, new Observer<TermCondApiResponse>() {
            @Override
            public void onChanged(TermCondApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {
                    if(apiResponse.getResponse().getStatus() ==1){
                        if(apiResponse.getResponse().getData().getIs_available() == 1){
                    //       showCustomDialog(apiResponse.response.getData(),requestBody);
                         nextScreen(apiResponse.response.getData(),requestBody);

                        }else{
                            Toast.makeText(PropertyDetail.this, apiResponse.getResponse().getData().getMsg()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(PropertyDetail.this, apiResponse.getResponse().getMessage()
                                , Toast.LENGTH_SHORT).show();
                    }

                    }else{
                        Toast.makeText(PropertyDetail.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }


    private SpannableStringBuilder trimSpannable(SpannableStringBuilder spannable) {
     //   checkNotNull(spannable);
        int trimStart = 0;
        int trimEnd = 0;

        String text = spannable.toString();

        while (text.length() > 0 && text.startsWith("\n")) {
            text = text.substring(1);
            trimStart += 1;
        }

        while (text.length() > 0 && text.endsWith("\n")) {
            text = text.substring(0, text.length() - 1);
            trimEnd += 1;
        }

        return spannable.delete(0, trimStart).delete(spannable.length() - trimEnd, spannable.length());
    }

    private void nextScreen(ContractResponse contractResponse ,PaymentRequestBody requestBody){
        if(contractResponse.getIs_available() == 1 ){
            Intent intent = new Intent(PropertyDetail.this,BookingDetails.class);
            intent.putExtra("contract_response",contractResponse);
            intent.putExtra("bank_info",bankAccount);
            intent.putExtra("total_price",total_cost);
            intent.putExtra("booking_info",requestBody);
            intent.putExtra("bank_account",bankAccount);
            intent.putParcelableArrayListExtra("data", PropertyDetailAdapter.roomDataList);
            startActivity(intent);
        }else{
            Toast.makeText(PropertyDetail.this, contractResponse.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    private void monthYearFormat(String date_value){
        SimpleDateFormat month_date = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date date = null;
        try {
            date = sdf.parse(date_value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String month_name = month_date.format(date);

        if(edit_position == 1){
            propertyDetailBinding.fromEdit.setText(month_name);
        }else if (edit_position == 2){
          //  monthYearFormat(final_date,edit_position);
            propertyDetailBinding.toEdit.setText(month_name);
        }

//        System.out.println("Month :" + month_name);  //Mar 20
    }
}
