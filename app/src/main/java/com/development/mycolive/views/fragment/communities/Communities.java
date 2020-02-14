package com.development.mycolive.views.fragment.communities;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.mycolive.R;
import com.development.mycolive.databinding.FragmentCommunityBinding;
import com.development.mycolive.views.activity.NewPost;

/**
 * A simple {@link Fragment} subclass.
 */
public class Communities extends Fragment implements View.OnClickListener {
FragmentCommunityBinding fragmentCommunityBinding;


    public Communities() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCommunityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_community, container, false);
         setClickListener();
         loadFragment(new AllCommunities());
        return fragmentCommunityBinding.getRoot();
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getActivity().getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    private void setClickListener(){
        fragmentCommunityBinding.fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                startActivity(new Intent(getActivity(), NewPost.class));
                break;
        }
    }
}
