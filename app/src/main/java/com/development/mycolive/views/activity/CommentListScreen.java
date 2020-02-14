package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityCommentListScreenBinding;
import com.development.mycolive.adapter.CurrentListAdapter;
import com.development.mycolive.model.CommentListModel;

import java.util.ArrayList;
import java.util.List;

public class CommentListScreen extends AppCompatActivity {
    ActivityCommentListScreenBinding screenBinding;
    private CurrentListAdapter commentAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    List<CommentListModel> commentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenBinding = DataBindingUtil.setContentView(this, R.layout.activity_comment_list_screen);

        screenBinding.toolbar.setTitle(getString(R.string.comment_list));
        setSupportActionBar(screenBinding.toolbar);
        setRecyclerView();
        setCommentList();
     }

    private void setRecyclerView() {
        commentAdapter = new CurrentListAdapter(CommentListScreen.this, commentList);
        mLayoutManager = new LinearLayoutManager(CommentListScreen.this);
        screenBinding.recyclerView.setLayoutManager(mLayoutManager);
        screenBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        screenBinding.recyclerView.setAdapter(commentAdapter);

    }

    private void setCommentList(){
        commentList.clear();
        for (int i=0;i<4;i++){
            CommentListModel comment = new CommentListModel("",
                    "","");
            commentList.add(comment);
        }
        commentAdapter.notifyDataSetChanged();
    }
}
