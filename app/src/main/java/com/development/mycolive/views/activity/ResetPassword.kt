@file:Suppress("DEPRECATION")

package com.development.mycolive.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.development.mycolive.R
import com.development.mycolive.databinding.ActivityResetPasswordBinding

class ResetPassword : AppCompatActivity() {
   lateinit var resetPassword: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetPassword = DataBindingUtil.setContentView(this, R.layout.activity_reset_password)
        initializeView()
    }

    private fun initializeView(){
        resetPassword.toolbar.setTitle("")
        setSupportActionBar(resetPassword.toolbar)
        resetPassword.toolbar.navigationIcon = resources.getDrawable(R.drawable.back_arrow);
       // supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
