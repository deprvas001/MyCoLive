package com.development.mycolive.views.fragment.signUp;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.databinding.FragmentSignUpOneBinding;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.model.signup.SignPostRequest;
import com.development.mycolive.model.signup.SignUpApiResponse;
import com.development.mycolive.session.SessionManager;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.SignupScreen;
import com.development.mycolive.views.activity.login.LoginActivity;
import com.development.mycolive.views.fragment.NewAccount;
import com.development.mycolive.views.fragment.profile.ProfileViewModel;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpOne extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    View view;
    FragmentSignUpOneBinding oneBinding;
    SignUpViewModel viewModel;
    private String gender = "";
    private int REQUEST_CODE = 100;
    String login_type, socail_id;
    private String image_string = "";
    private DatePickerDialog mDatePickerDialog;
    SessionManager session;

    public SignUpOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        oneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_one, container, false);
        session = new SessionManager(getContext());
        view = oneBinding.getRoot();
        setOnClickListener();
        return view;
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        //   fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit(); // save the changes
    }


    private void setOnClickListener() {
        login_type = ((SignupScreen) getActivity()).type;
        socail_id = ((SignupScreen) getActivity()).social_id;

        if (!login_type.equalsIgnoreCase("NORMAL")) {
            oneBinding.fieldLayout.password.setVisibility(View.GONE);
        }

        setDateTimeField();
        oneBinding.fieldLayout.btnNext.setOnClickListener(this);
        oneBinding.fieldLayout.signIn.setOnClickListener(this);
        oneBinding.profileImage.setOnClickListener(this);
        oneBinding.fieldLayout.radioGroup.setOnCheckedChangeListener(this);
        oneBinding.fieldLayout.privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://webfume.in/mani-budapest/landing/privacyPolicy";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        oneBinding.fieldLayout.inputDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePickerDialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                // loadFragment(new NewAccount());
                if (oneBinding.fieldLayout.inputName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (oneBinding.fieldLayout.inputEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (oneBinding.fieldLayout.inputPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter phone", Toast.LENGTH_SHORT).show();
                    return;
                } else if (oneBinding.fieldLayout.inputDob.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter dob", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!oneBinding.fieldLayout.policyCheck.isChecked()) {
                    Toast.makeText(getActivity(), "Please accept the privacy policy.a", Toast.LENGTH_SHORT).show();
                    return;
                }else if(image_string.isEmpty()){
                    Toast.makeText(getActivity(), "Please Upload Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if (((SignupScreen) getActivity()).isNetworkAvailable(getActivity())) {
                        if (((SignupScreen) getActivity()).type.equalsIgnoreCase("NORMAL")) {
                            if (oneBinding.fieldLayout.password.getText().toString().isEmpty()) {
                                Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                setSignUp();
                            }
                        } else {
                            setSignUp();
                        }

                    } else {
                        Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.sign_in:
                getActivity().finish();
                break;

            case R.id.profile_image:
                pickImage(REQUEST_CODE);
                break;
        }
    }

    private void setSignUp() {

        ((SignupScreen) getActivity()).showProgressDialog(getString(R.string.loading));
        SignPostRequest signPostRequest = new SignPostRequest();
        signPostRequest.setName(oneBinding.fieldLayout.inputName.getText().toString());
        signPostRequest.setEmail(oneBinding.fieldLayout.inputEmail.getText().toString());
        signPostRequest.setDob(oneBinding.fieldLayout.inputDob.getText().toString());
        signPostRequest.setMobile(oneBinding.fieldLayout.inputPhone.getText().toString());
        signPostRequest.setPassword(oneBinding.fieldLayout.password.getText().toString());

        if (oneBinding.fieldLayout.maleRadionbtn.isChecked()) {
            gender = "M";
        } else {
            gender = "F";
        }

        if (login_type != null && !login_type.isEmpty()) {
            signPostRequest.setLogin_type(login_type);
        } else {
            signPostRequest.setLogin_type("NORMAL");
        }

        if (socail_id != null && !socail_id.isEmpty()) {
            signPostRequest.setSocial_id(socail_id);
        } else {
            signPostRequest.setSocial_id("abc1111");
        }
        signPostRequest.setGender(gender);

        signPostRequest.setTermCondition("1");
        signPostRequest.setImage(image_string);

        viewModel = ViewModelProviders.of(getActivity()).get(SignUpViewModel.class);

        viewModel.setSignUp(getActivity(), signPostRequest).observe(getActivity(), new Observer<SignUpApiResponse>() {
            @Override
            public void onChanged(SignUpApiResponse apiResponse) {
                ((SignupScreen) getActivity()).hideProgressDialog();
                if (apiResponse.response != null) {
                    if (apiResponse.getResponse().getStatus() == 0) {

                        Toast.makeText(getActivity(), apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {
                        // loadFragment(new NewAccount());
                        //  getActivity().finish();
                        Toast.makeText(getActivity(), apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                        String type = ApiConstant.NORMAL;
                        String token = apiResponse.getResponse().getData().getAuthenticateToken();
                        String userID = apiResponse.getResponse().getData().getUserId();
                        String userType = apiResponse.getResponse().getData().getUserType();
                        String name = apiResponse.getResponse().getData().getName();
                        String email = apiResponse.getResponse().getData().getEmail();
                        String image = apiResponse.getResponse().getData().getImage();


                        if (apiResponse.getResponse().getStatus() == 1) {
                            session.createLoginSession(name,
                                    email, userID, userType, token, image, type);
                            //  LoginActivity.this.showAlertDialog(LoginActivity.this, LoginActivity.this.getString(R.string.success));

                            Intent i = new Intent(getActivity(), ShowHomeScreen.class);
                            // Closing all the Activities
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            // Staring Login Activity
                            getActivity().startActivity(i);
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                //  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat sd1 = new SimpleDateFormat("dd-MM-yyyy");
                //  sd.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Date startDate = newDate.getTime();
                // fdate = sd.format(startDate);
                String final_date = sd1.format(startDate);
                oneBinding.fieldLayout.inputDob.setText(final_date);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        //   mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 568025136000L);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getId()) {
            case R.id.male_radionbtn:
                gender = "M";
                break;

            case R.id.female_radiobtn:
                gender = "F";
                break;
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
                .setSavePath("ImagePicker")         //  Image capture folder name
                //.setSelectedImages(images)          //  Selected images
                .setAlwaysShowDoneButton(true)      //  Set always show done button in multiple mode
                .setRequestCode(REQUEST_CODE)                //  Set request code, default Config.RC_PICK_IMAGES
                .setKeepScreenOn(true)              //  Keep screen on when selecting images
                .start();
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
            Toast.makeText(getActivity(), path, Toast.LENGTH_SHORT).show();
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            oneBinding.profileImage.setImageBitmap(bitmap);

            convetBitmapString(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
        // You MUST have this line to be here
        // so ImagePicker can work with fragment mode
    }
}
