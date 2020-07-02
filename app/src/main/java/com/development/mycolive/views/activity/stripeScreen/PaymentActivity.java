package com.development.mycolive.views.activity.stripeScreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.ActivityPaymentBinding;
import com.development.mycolive.model.paymentModel.PaymentApiResponse;
import com.development.mycolive.model.paymentModel.PaymentModeRequest;
import com.development.mycolive.model.paymentModel.PaymentRequestBody;
import com.development.mycolive.model.searchDetailPage.BankAccount;
import com.development.mycolive.model.stripe.StripeApiResponse;
import com.development.mycolive.model.stripe.StripeRequestBody;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.BaseActivity;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.SuccessScreen;
import com.development.mycolive.views.activity.paymentMode.PaymentMode;
import com.development.mycolive.views.activity.paymentMode.PaymentModeViewModel;
import com.development.mycolive.views.activity.paymentScreen.PaymentViewModel;
import com.development.mycolive.views.activity.paymentScreen.SelectPayment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PaymentActivity extends BaseActivity {
    /**
     *
     * This example collects card payments, implementing the guide here: https://stripe.com/docs/payments/accept-a-payment#android
     *
     * To run this app, follow the steps here: https://github.com/stripe-samples/accept-a-card-payment#how-to-run-locally
     */
    // 10.0.2.2 is the Android emulator's alias to localhost
    private static final String BACKEND_URL = "https://webfume.in/mani-budapest/api/";
    SessionManager session;
    String token,month_id;
    boolean isCurrent = false;
    private StripPaymentViewModel viewModel;
    private OkHttpClient httpClient = new OkHttpClient();
    private String paymentIntentClientSecret;
    String stripePublishableKey;
    private Stripe stripe;
    float total_price;
    PaymentRequestBody requestBody;
    BankAccount bankAccount;
    String email;
    ActivityPaymentBinding paymentBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paymentBinding = DataBindingUtil.setContentView(this,R.layout.activity_payment);
        /*paymentBinding.toolbar.setTitle(getString(R.string.stripe));
        setSupportActionBar(paymentBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        paymentBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(getIntent()!=null){
             total_price = getIntent().getExtras().getFloat("total_price");
            requestBody = getIntent().getParcelableExtra("booking_info");
            bankAccount = getIntent().getParcelableExtra("bank_account");
         //   email = getIntent().getExtras().getString("refer_email");
            int total_price_int = (int)total_price;
            paymentBinding.payButton.setText("€"+String.valueOf(total_price_int)+" / Pay Now");

            Bundle bundle = getIntent().getExtras();
            if(bundle !=null){
                if(bundle.containsKey("month_id")){
                    month_id = getIntent().getExtras().getString("month_id");
                    isCurrent = getIntent().getExtras().getBoolean("current_booking");
                }
            }
        }

        getSession();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected (item);
    }

    private void startCheckout() {
     /*   // Create a PaymentIntent by calling the sample server's /create-payment-intent endpoint.
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        String json = "{"
                + "\"currency\":\"usd\","
                + "\"items\":["
                + "{\"id\":\"photo_subscription\"}"
                + "]"
                + "}";
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(BACKEND_URL + "sandbox_payment")
                .post(body)
                .build();
        httpClient.newCall(request)
                .enqueue(new PayCallback(this));*/

        // Hook up the pay button to the card widget and stripe instance
        Button payButton = findViewById(R.id.payButton);
        payButton.setOnClickListener((View view) -> {
            showProgressDialog(getString(R.string.loading));
            CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
            PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();

           /* stripePublishableKey = "pk_test_jiW1nHcBnV6Ijo1RQgQ6jSbs00HhqWsgoD";
            paymentIntentClientSecret = "pi_1GO4QvIWzZYVbxDQc0W70Rsz_secret_vNpWYRd7Xm5aGlVy8tc6LP3Ow";*/
            stripe = new Stripe(
                    getApplicationContext(),
                    Objects.requireNonNull(stripePublishableKey));

            if (params != null) {
                ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                        .createWithPaymentMethodCreateParams( params, paymentIntentClientSecret);
                stripe.confirmPayment(this, confirmParams);
            }
        });
    }

    private void displayAlert(@NonNull String title,
                              @Nullable String message,
                              boolean restartDemo) {
        hideProgressDialog();

        //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.booking_success_dialog,null);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        Button ok =(Button)dialogView.findViewById(R.id.buttonOk);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                cardInputWidget.clear();
                //  startCheckout();

                Intent i = new Intent(PaymentActivity.this, ShowHomeScreen.class);
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


        /*AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);
        if (restartDemo) {
            builder.setPositiveButton("Ok",
                    (DialogInterface dialog, int index) -> {
                        CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                        cardInputWidget.clear();
                      //  startCheckout();

                        Intent i = new Intent(PaymentActivity.this, ShowHomeScreen.class);
                        // Closing all the Activities
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);

                        // Staring Login Activity
                        startActivity(i);
                    });
        } else {
            builder.setPositiveButton("Ok", null);
        }
        builder.create().show();*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the result of stripe.confirmPayment
        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));
    }

    private void onPaymentSuccess(@NonNull final Response response) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> responseMap = gson.fromJson(
                Objects.requireNonNull(response.body()).string(),
                type
        );

        // The response from the server includes the Stripe publishable key and
        // PaymentIntent details.
        // For added security, our sample app gets the publishable key from the server

        String stripePublishableKey = "pk_test_jiW1nHcBnV6Ijo1RQgQ6jSbs00HhqWsgoD";
        paymentIntentClientSecret = "pi_1GO4QvIWzZYVbxDQc0W70Rsz_secret_vNpWYRd7Xm5aGlVy8tc6LP3Ow";
        //    String stripePublishableKey = responseMap.get("pk_test_jiW1nHcBnV6Ijo1RQgQ6jSbs00HhqWsgoD");
   //     paymentIntentClientSecret = responseMap.get("pi_1GO4QvIWzZYVbxDQc0W70Rsz_secret_vNpWYRd7Xm5aGlVy8tc6LP3Ow");

        // Configure the SDK with your Stripe publishable key so that it can make requests to the Stripe API
        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull(stripePublishableKey)
        );
    }

    private static final class PayCallback implements Callback {
        @NonNull private final WeakReference<PaymentActivity> activityRef;

        PayCallback(@NonNull PaymentActivity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onFailure(@NonNull Call call, @NonNull IOException e) {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            activity.runOnUiThread(() ->
                    Toast.makeText(
                            activity, "Error: " + e.toString(), Toast.LENGTH_LONG
                    ).show()
            );
        }

        @Override
        public void onResponse(@NonNull Call call, @NonNull final Response response)
                throws IOException {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            if (!response.isSuccessful()) {
                activity.runOnUiThread(() ->
                        Toast.makeText(
                                activity, "Error: " + response.toString(), Toast.LENGTH_LONG
                        ).show()
                );
            } else {
                activity.onPaymentSuccess(response);
            }
        }
    }

    private static final class PaymentResultCallback
            implements ApiResultCallback<PaymentIntentResult> {
        @NonNull private final WeakReference<PaymentActivity> activityRef;

        PaymentResultCallback(@NonNull PaymentActivity activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                /*Gson gson = new GsonBuilder().setPrettyPrinting().create();
                activity.displayAlert(
                        "Payment completed",
                        gson.toJson(paymentIntent),
                        true
                );*/
                if(!activity.isCurrent){
                    activity.postRequestServer();
                }else{
                    activity.payAmount();
                }

            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed – allow retrying using a different payment method
                activity.displayAlert(
                        "Payment failed",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage(),
                        false
                );
            }
        }

        @Override
        public void onError(@NonNull Exception e) {
            final PaymentActivity activity = activityRef.get();
            if (activity == null) {
                return;
            }

            // Payment request failed – allow retrying using the same payment method
            activity.displayAlert("Error", e.toString(), false);
        }
    }


    private void getStripeKeys(String email){

        showProgressDialog(getResources().getString(R.string.loading));

        StripeRequestBody requestBody = new StripeRequestBody();
        requestBody.setEmail(email);
        requestBody.setAmount(String.valueOf(total_price));
        requestBody.setMonth_id(month_id);


            Map<String,String> headers = new HashMap<>();
            headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
            headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
            headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
            headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
            headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
            headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

            viewModel = ViewModelProviders.of(this).get(StripPaymentViewModel.class);


            viewModel.getStripeKey(this,headers,requestBody).observe(this, new Observer<StripeApiResponse>() {
                @Override
                public void onChanged(StripeApiResponse apiResponse) {
                    hideProgressDialog();
                    if(apiResponse.response !=null){
                        stripePublishableKey = apiResponse.getResponse().getData().getPublishableKey();
                        paymentIntentClientSecret = apiResponse.getResponse().getData().getClient_secret();
                      //  String message = apiResponse.getResponse().
                //        Toast.makeText(PaymentActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        startCheckout();
                    }else if(apiResponse.getStatus()== 401){
                         Toast.makeText(PaymentActivity.this, "Authentication", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(PaymentActivity.this, "Try Later", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }


    private void getSession(){
        session = new SessionManager(PaymentActivity.this);
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManager.KEY_NAME);

        // email
        String email = user.get(SessionManager.KEY_EMAIL);
        String image = user.get(SessionManager.KEY_IMAGE);
        token = user.get(SessionManager.KEY_TOKEN);

        getStripeKeys(email);
    }


    public void postRequestServer(){
        showProgressDialog(getResources().getString(R.string.loading));

        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);

          /* PaymentRequestBody requestBody = new PaymentRequestBody();
           requestBody.setRoom_id(roomIdList);
           requestBody.setDuration("4");
           requestBody.setPayment_method("BANK");
           requestBody.setContract("1");
           requestBody.setEarly_check("23-03-2020");
           requestBody.setReceipt(image_string);
           requestBody.setDaterange("Apr 2020 - Jun 2020");*/
        requestBody.setPayment_method(ApiConstant.BANK_STRIPE);
        requestBody.setReceipt("");


        PaymentViewModel paymentViewModel = ViewModelProviders.of(this).get(PaymentViewModel.class);

        paymentViewModel.bookingPost(this,headers,requestBody).observe(this, new Observer<PaymentApiResponse>() {
            @Override
            public void onChanged(PaymentApiResponse apiResponse) {
                hideProgressDialog();
                if(apiResponse.response !=null){
                    String message = apiResponse.getResponse().getMessage();
                    if(apiResponse.getResponse().getStatus()==1){

                        showCustomDialog();

                       // displayAlert("Payment completed",message,true);
                    }else{
                        displayAlert("Payment failed",message,false);
                    }

                }else {
                    Toast.makeText(PaymentActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void payAmount(){
        showProgressDialog(getResources().getString(R.string.loading));

        PaymentModeRequest modeRequest =new PaymentModeRequest();
        modeRequest.setMonth_id(month_id);
        modeRequest.setPayment_method("STRIPE");
        modeRequest.setReceipt("");


        Map<String,String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE,ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES,ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE,ApiConstant. USER_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TYPE,ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN,ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN,token);


      PaymentModeViewModel  viewModel1 = ViewModelProviders.of(this).get(PaymentModeViewModel.class);

        viewModel1.payAmount(this,headers,modeRequest).observe(this, new Observer<PaymentApiResponse>() {
            @Override
            public void onChanged(PaymentApiResponse apiResponse) {
                hideProgressDialog();
                if(apiResponse.response !=null){
                    if(apiResponse.getResponse().getStatus() ==1){
                        showCustomDialog();
                    }
                }
                else{
                    Toast.makeText(PaymentActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showCustomDialog(){

        Intent i = new Intent(PaymentActivity.this, SuccessScreen.class);
        // Closing all the Activities
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        startActivity(i);


       /* //then we will inflate the custom alert dialog xml that we created
        View dialogView = LayoutInflater.from(this).inflate(R.layout.booking_success_dialog,null);

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        Button ok =(Button)dialogView.findViewById(R.id.buttonOk);
        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PaymentActivity.this, ShowHomeScreen.class);
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
        alertDialog.show();*/
    }


    @Override
    public void onBackPressed() {
     //   super.onBackPressed();
    }
}
