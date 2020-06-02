package com.development.mycolive.views.activity.bookingDetail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.development.mycolive.R;
import com.development.mycolive.adapter.BookingDetailAdapter;
import com.development.mycolive.adapter.PropertyDetailAdapter;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityBookingDetailsBinding;
import com.development.mycolive.model.bookingDetail.BookingDetailApiResponse;
import com.development.mycolive.model.bookingDetail.UploadIdRequest;
import com.development.mycolive.model.notificationModel.NotificationApiResponse;
import com.development.mycolive.model.notificationModel.NotificationBodyRequest;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.propertyDetailModel.PropertyRoomData;
import com.development.mycolive.model.searchDetailPage.BankAccount;
import com.development.mycolive.model.termscondition.ContractResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.ImagePickerScreen;
import com.development.mycolive.views.activity.notification.Notification;
import com.development.mycolive.views.activity.notification.NotificationViewModel;
import com.development.mycolive.views.activity.paymentScreen.SelectPayment;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDetails extends BaseActivity implements View.OnClickListener {
    ActivityBookingDetailsBinding bookingDetailsBinding;
    ArrayList<PropertyRoomData> roomDataList=new ArrayList<>();
    float total_price =0,early_check=0,final_price;
    BookingDetailAdapter bookingDetailAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    PaymentRequestBody requestBody;
    SessionManager session;
    ContractResponse contractResponse;
    private int REQUEST_CODE = 100;
    private String token="",user_id="";
    private String image_string = "";
    ArrayList<String> email_id = new ArrayList<>();
    List<String> id_list =new ArrayList<>();
    BankAccount bankAccount;
    BookingDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookingDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_booking_details);

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
        bookingDetailsBinding.uploadReceipt.setOnClickListener(this);
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
                                    //   gotoNext(email);
                                    getSession(email);

                                   /*if(!image_string.isEmpty()) {
                                       email_id.add(email);
                                       //   gotoNext(email);
                                       getSession(email);
                                   }else{
                                       Toast.makeText(this, "Please Upload Id Proof.", Toast.LENGTH_SHORT).show();
                                   }
*/
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
                            //    gotoNext("");
                            getSession("");

                          /* if(!image_string.isEmpty()){
                               email_id.add("");
                               //    gotoNext("");
                               getSession("");
                           }else{
                               Toast.makeText(this, "Please Upload Id Proof.", Toast.LENGTH_SHORT).show();
                           }*/

                        }else{
                            Toast.makeText(this, "Please accept policy.", Toast.LENGTH_SHORT).show();
                        }
                    }


                break;

            case R.id.policy_link:
            /*    String url = "https://webfume.in/mani-budapest/landing/privacyPolicy";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/
               showCustomDialog();
                break;

            case R.id.back:
                finish();
                break;

            case R.id.upload_receipt:
             //  pickImage(REQUEST_CODE);
                Intent intent = new Intent(BookingDetails.this, ImagePickerScreen.class);
                startActivityForResult(intent, 102);//
                break;
        }
    }

    private void gotoNext(String email ,String upload_image){

        Intent i = new Intent(this,SelectPayment.class);
        i.putExtra("total_price",final_price);
        i.putExtra("booking_info",requestBody);
        i.putExtra("bank_account",bankAccount);
        i.putExtra("upload_image",upload_image);
        i.putStringArrayListExtra("email",email_id);
        startActivity(i);
    }

    public void showCustomDialog(){
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

      //  close.setVisibility(View.GONE);

        CheckBox check = (CheckBox)dialogView.findViewById(R.id.terms_condition);

        Button button_ok = (Button)dialogView.findViewById(R.id.buttonOk);

      //  button_ok.setVisibility(View.GONE);

   //     check.setVisibility(View.GONE);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
       /* button_ok.setOnClickListener(new View.OnClickListener() {
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
        });*/

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

    private void convetBitmapString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        image_string = Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

   /* private void pickImage(int REQUEST_CODE) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/ImagePicker");

        ImagePicker.with(this)                       //  Initialize ImagePicker with activity or fragment context
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
    }*/
  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            // do your logic here...
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 2;

            String path = images.get(0).getPath();
            Toast.makeText(BookingDetails.this, path, Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            bookingDetailsBinding.uploadReceipt.setImageBitmap(bitmap);

            convetBitmapString(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
        // You MUST have this line to be here
        // so ImagePicker can work with fragment mode
    }*/

    private void uploadIdProof(String friend_email){
        showProgressDialog(getString(R.string.loading));

        UploadIdRequest request =new UploadIdRequest();
        request.setId_proof(image_string);

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

        viewModel = ViewModelProviders.of(this).get(BookingDetailsViewModel.class);
        viewModel.uploadIdProof(this,headers,request).observe(this, new Observer<BookingDetailApiResponse>() {
            @Override
            public void onChanged(BookingDetailApiResponse apiResponse) {
                hideProgressDialog();

                if (apiResponse.response != null) {
                    if (apiResponse.getResponse().getStatus() == 0) {

                        Toast.makeText(BookingDetails.this, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        String upload_image = apiResponse.getResponse().getUrl();
                       // Toast.makeText(BookingDetails.this, "Success", Toast.LENGTH_SHORT).show();
                        gotoNext(friend_email,upload_image);
                    }

                } else {
                    Toast.makeText(BookingDetails.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }


        });
    }

    private void getSession(String friend_email){
        session = new SessionManager(this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);
        user_id = user.get(SessionManager.KEY_USERID);

        uploadIdProof(friend_email);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                ArrayList<Image> images = data.getParcelableArrayListExtra(ApiConstant.IMAGE_PICK);

                if (images.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(images.get(0).getId()));

                        // do your logic here...
                        BitmapFactory.Options options = new BitmapFactory.Options();

                        // downsizing image as it throws OutOfMemory Exception for larger
                        // images
                        //  options.inSampleSize = calculteInSampleSize(option,500,500)
                        options.inSampleSize = 2;

                        final InputStream imageStream;
                        try {
                            imageStream = getContentResolver().openInputStream(uri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            bookingDetailsBinding.uploadReceipt.setImageBitmap(selectedImage);

                            convetBitmapString(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


/*
                        String path = uri.getPath();
                        //   Toast.makeText(BookingDetails.this, path, Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                        bookingDetailsBind(ing.uploadReceipt.setImageBitmap(bitmap);

                        convetBitmapString(bitmap);*/

                    } else {

                        // do your logic here...
                        BitmapFactory.Options options = new BitmapFactory.Options();

                        // downsizing image as it throws OutOfMemory Exception for larger
                        // images
                        //  options.inSampleSize = calculteInSampleSize(option,500,500)
                        options.inSampleSize = 2;

                        String path = images.get(0).getPath();
                        //   Toast.makeText(BookingDetails.this, path, Toast.LENGTH_SHORT).show();
                        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                        bookingDetailsBinding.uploadReceipt.setImageBitmap(bitmap);

                        convetBitmapString(bitmap);

                    }
                }
            }

        }
    }
}
