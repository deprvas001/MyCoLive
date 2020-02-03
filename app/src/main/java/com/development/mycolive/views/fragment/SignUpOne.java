package com.development.mycolive.views.fragment;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.ToolbarBindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentSignUpOneBinding;
import com.development.mycolive.views.activity.SignupScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpOne extends Fragment implements View.OnClickListener {
View view;
FragmentSignUpOneBinding oneBinding;

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
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit(); // save the changes
    }

    private void setOnClickListener(){
        oneBinding.fieldLayout.btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
               loadFragment(new NewAccount());
                break;
        }
    }
}
