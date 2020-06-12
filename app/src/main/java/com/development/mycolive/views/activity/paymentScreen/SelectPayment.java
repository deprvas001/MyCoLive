package com.development.mycolive.views.activity.paymentScreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivitySelectPaymentBinding;
import com.development.mycolive.model.paymentModel.PaymentApiResponse;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.postCommunity.PostApiResponse;
import com.development.mycolive.model.postCommunity.PostCommunity;
import com.development.mycolive.model.searchDetailPage.BankAccount;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.postScreen.NewPost;
import com.development.mycolive.views.activity.postScreen.PostViewModel;
import com.development.mycolive.views.activity.stripeScreen.PaymentActivity;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectPayment extends BaseActivity implements RadioGroup.OnCheckedChangeListener , View.OnClickListener {
ActivitySelectPaymentBinding paymentBinding;
    private String image_string = "";
    private int REQUEST_CODE = 100;
    SessionManager session;
    String token;
   ArrayList<String> email;
    PaymentViewModel viewModel;
    float total_price;
    PaymentRequestBody requestBody;
    BankAccount bankAccount;
    String upload_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentBinding = DataBindingUtil.setContentView(this,R.layout.activity_select_payment);

        if (getIntent() != null) {
            total_price = getIntent().getExtras().getFloat("total_price");
            requestBody = getIntent().getParcelableExtra("booking_info");

            bankAccount = getIntent().getParcelableExtra("bank_account");
            email = getIntent().getStringArrayListExtra("email");

            if(getIntent().getExtras().containsKey("upload_image")){
                upload_id = getIntent().getExtras().getString("upload_image");
            }

            requestBody.setEmail_id(email);

            requestBody.setId_proof(upload_id);

        }
        setClickListener();
    }

    private void setClickListener(){
        paymentBinding.btnProceed.setText("€"+String.valueOf(total_price)+" / "+"Pay Now");
        paymentBinding.accountName.setText(bankAccount.getName());
        paymentBinding.accountNumber.setText(bankAccount.getAccountId());
        paymentBinding.ibanNo.setText(bankAccount.getIban());
        paymentBinding.shortName.setText(bankAccount.getSortName());
        paymentBinding.description.setText(bankAccount.getDesc());
        paymentBinding.back.setOnClickListener(this);


        paymentBinding.radioGroup.setOnCheckedChangeListener(this);
        paymentBinding.btnProceed.setOnClickListener(this);
        paymentBinding.uploadReceipt.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected (item);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        if(id == R.id.bank_transfer_radio){
            paymentBinding.bankTransferLayout.setVisibility(View.VISIBLE);
        }else if(id== R.id. radio_stripe){
            image_string="";
            paymentBinding.bankTransferLayout.setVisibility(View.GONE);
        }
    }


    private void convetBitmapString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        image_string = Base64.encodeToString(byteArray, Base64.DEFAULT);
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
            //    .setSavePath("ImagePicker")         //  Image capture folder name
                //.setSelectedImages(images)          //  Selected images
                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
                .setRequestCode(REQUEST_CODE)                //  Set request code, default Config.RC_PICK_IMAGES
             //   .setKeepScreenOn(true)              //  Keep screen on when selecting images
                .start();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_proceed){
            if(paymentBinding.bankTransferRadio.isChecked()){
                if(!image_string.isEmpty()){
                    getSession();
                }else{
                    Toast.makeText(this, "Please upload your id proof.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Intent intent = new Intent(SelectPayment.this,PaymentActivity.class);
                intent.putExtra("total_price",total_price);
                intent.putExtra("booking_info",requestBody);
                intent.putExtra("bank_account",bankAccount);
                /*intent.put("refer_email",email);*/
               startActivity(intent);
            }

        }else if(view.getId() == R.id.upload_receipt){
            pickImage(REQUEST_CODE );
        }else if(view.getId() == R.id.back){
            finish();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            paymentBinding.uploadReceipt.setImageBitmap(bitmap);

            convetBitmapString(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);  // You MUST have this line to be here
        // so ImagePicker can work with fragment mode
    }

    private void postBooking(){
            showProgressDialog(getResources().getString(R.string.loading));

            Map<String,String> headers = new HashMap<>();
            headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
            headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
            headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
            headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
            headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
            headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

            requestBody.setReceipt(image_string);


          viewModel = ViewModelProviders.of(this).get(PaymentViewModel.class);

        viewModel.bookingPost(this,headers,requestBody).observe(this, new Observer<PaymentApiResponse>() {
                @Override
                public void onChanged(PaymentApiResponse apiResponse) {
                    hideProgressDialog();
                    if(apiResponse.response !=null){
                      String message = apiResponse.getResponse().getMessage();
                      if(apiResponse.getResponse().getStatus() == 1){
                          showCustomDialog("Booking Confirmation",message);
                      }else if(apiResponse.getResponse().getStatus() == 0){
                          showCustomDialog("MCoLive",message);
                      }else {
                          showCustomDialog("MCoLive","Something went try Later.");
                      }

                    }else {
                        Toast.makeText(SelectPayment.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

      //  showCustomDialog();
    }

    private void getSession(){
        session = new SessionManager(SelectPayment.this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);

      //  getDefaultData();
        postBooking();
    }

    private void showCustomDialog(String title,String message){
        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.booking_success_dialog,null);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        TextView title_txt = dialogView.findViewById(R.id.title);
        title_txt.setText(title);


        TextView confirmation_txt = dialogView.findViewById(R.id.confirmation);
        confirmation_txt.setText(message);

        Button ok =(Button)dialogView.findViewById(R.id.buttonOk);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectPayment.this, ShowHomeScreen.class);
                // Closing all the Activities
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                startActivity(i);
            }
        });

        //finally creating the alert dialog and displaying it
        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }
}


