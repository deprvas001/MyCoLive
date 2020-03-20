package com.development.mycolive.views.fragment.signUp;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentSignUpOneBinding;
import com.development.mycolive.model.editProfile.ProfilePostApiResponse;
import com.development.mycolive.model.signup.SignPostRequest;
import com.development.mycolive.model.signup.SignUpApiResponse;
import com.development.mycolive.views.activity.ShowHomeScreen;
import com.development.mycolive.views.activity.SignupScreen;
import com.development.mycolive.views.fragment.NewAccount;
import com.development.mycolive.views.fragment.profile.ProfileViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpOne extends Fragment implements View.OnClickListener {
View view;
FragmentSignUpOneBinding oneBinding;
SignUpViewModel viewModel;
private DatePickerDialog mDatePickerDialog;

    public SignUpOne() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        oneBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_up_one,container,false);
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

    private void setOnClickListener(){
        setDateTimeField();
        oneBinding.fieldLayout.btnNext.setOnClickListener(this);
        oneBinding.fieldLayout.signIn.setOnClickListener(this);
        oneBinding.fieldLayout.inputDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatePickerDialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
              // loadFragment(new NewAccount());
                if(oneBinding.fieldLayout.inputName.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.inputEmail.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.inputPhone.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter phone", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.inputDob.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter dob", Toast.LENGTH_SHORT).show();
                    return;
                }else if(oneBinding.fieldLayout.password.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!oneBinding.fieldLayout.policyCheck.isChecked()){
                    Toast.makeText(getActivity(), "Please accept the privacy policy.a", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    setSignUp();
                }
                break;

            case R.id.sign_in:
                  getActivity().finish();
                 break;
        }
    }

   private void setSignUp(){

       ((SignupScreen) getActivity()).showProgressDialog(getString(R.string.loading));
       SignPostRequest signPostRequest = new SignPostRequest();
       signPostRequest.setName(oneBinding.fieldLayout.inputName.getText().toString());
       signPostRequest.setEmail(oneBinding.fieldLayout.inputEmail.getText().toString());
       signPostRequest.setDob(oneBinding.fieldLayout.inputDob.getText().toString());
       signPostRequest.setMobile(oneBinding.fieldLayout.inputPhone.getText().toString());
       signPostRequest.setPassword(oneBinding.fieldLayout.password.getText().toString());
       signPostRequest.setGender(oneBinding.fieldLayout.inputGender.getText().toString());
       signPostRequest.setLogin_type("NORMAL");
       signPostRequest.setTermCondition("1");

       viewModel = ViewModelProviders.of(getActivity()).get(SignUpViewModel.class);

       viewModel.setSignUp(getActivity(),  signPostRequest ).observe(getActivity(), new Observer<SignUpApiResponse>() {
           @Override
           public void onChanged(SignUpApiResponse apiResponse) {
               ((SignupScreen) getActivity()).hideProgressDialog();
               if (apiResponse.response != null) {
                   Toast.makeText(getActivity(), apiResponse.response.getMessage(), Toast.LENGTH_SHORT).show();
                 // loadFragment(new NewAccount());
               } else if (apiResponse.getStatus() == 401) {
                   Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_SHORT).show();
               } else if(apiResponse.getStatus() == 400){
                   Toast.makeText(getActivity(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(getActivity(), "Try Later", Toast.LENGTH_SHORT).show();
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
       // mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        /*  mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());*/
    }
}
