package com.development.mycolive.views.activity.forgotPassword

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.development.mycolive.R
import com.development.mycolive.databinding.ActivityForgotPasswordBinding
import com.development.mycolive.views.activity.BaseActivity
import com.development.mycolive.model.forgotModel.ForgotRequestModel
import com.development.mycolive.model.loginModel.LoginApiResponse

class ForgotPassword :BaseActivity() , View.OnClickListener {

    lateinit var passwordBinding: ActivityForgotPasswordBinding
    lateinit var toolbar:Toolbar
    private var forgotRequestModel = ForgotRequestModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passwordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        intitializeView()
        setClickListener()
    }

    private fun intitializeView(){
        passwordBinding.toolbar.setTitle("")
        setSupportActionBar(passwordBinding.toolbar)
     //   passwordBinding.toolbar.navigationIcon = resources.getDrawable(R.drawable.back_arrow)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if(item.itemId == android.R.id.home){
            finish()
         }
         return super.onOptionsItemSelected(item)
    }

    private fun setClickListener(){
        passwordBinding.btnReset.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_reset -> {
              /*  var startJobIntent = Intent(this, ResetPassword::class.java)
                // startJobIntent.putExtra(AppConstants.JOBS_UPCOMING, upcoming)
                startActivity(startJobIntent)*/
               // finish()
                forgotPassword()

            }
        }
    }

   private fun forgotPassword(){
       showProgressDialog(getString(R.string.loading))
       forgotRequestModel.email="deepak.shadsf@webfume.com"
       val forgotViewModel = ViewModelProviders.of(this).get(ForgotViewModel::class.java)


           forgotViewModel.forgotPassword(this, forgotRequestModel).observe(this,Observer{ loginApiResponse: LoginApiResponse? ->

               if(loginApiResponse!!.getResponse()!!.status   == 1){
                   hideProgressDialog()
                   Toast.makeText(this, loginApiResponse!!.getResponse().message, Toast.LENGTH_LONG).show()
               }else if(loginApiResponse!!.getResponse()!!.status   == 0){
                   hideProgressDialog()
                   Toast.makeText(this, loginApiResponse!!.getResponse().message, Toast.LENGTH_LONG).show()
               }

           })
   }

}
