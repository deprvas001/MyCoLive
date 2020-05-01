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
import com.development.mycolive.session.SessionManager

class ForgotPassword :BaseActivity() , View.OnClickListener {

    lateinit var passwordBinding: ActivityForgotPasswordBinding
    lateinit var toolbar:Toolbar
    lateinit var session:SessionManager
    private var forgotRequestModel = ForgotRequestModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passwordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        intitializeView()
        setClickListener()
    }

    private fun intitializeView(){
      //  passwordBinding.toolbar.title = getString(R.string.forgot_password_title)
       /* setSupportActionBar(passwordBinding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)*/
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
        passwordBinding.back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_reset -> {
              /*  var startJobIntent = Intent(this, ResetPassword::class.java)
                // startJobIntent.putExtra(AppConstants.JOBS_UPCOMING, upcoming)
                startActivity(startJobIntent)*/
               // finish()
                val email: String = passwordBinding.inputEmail.text.toString()
               if(email.isEmpty()){
                  Toast.makeText(this, getString(R.string.email_empty), Toast.LENGTH_LONG).show()
               }else{
                   forgotPassword(email)
               }

            }

            R.id.back -> {
                finish()
            }

        }
    }

   private fun forgotPassword(email_user: String?) {
       showProgressDialog(getString(R.string.loading))
       forgotRequestModel.email=email_user
       val forgotViewModel = ViewModelProviders.of(this).get(ForgotViewModel::class.java)


           forgotViewModel.forgotPassword(this, forgotRequestModel).observe(this,Observer{ loginApiResponse: LoginApiResponse? ->
               hideProgressDialog()
               if (loginApiResponse!!.response != null) {
                   if(loginApiResponse!!.getResponse()!!.status   == 1){

                       Toast.makeText(this, loginApiResponse!!.getResponse().message, Toast.LENGTH_LONG).show()
                   }else if(loginApiResponse!!.getResponse()!!.status   == 0){
                       Toast.makeText(this, loginApiResponse!!.getResponse().message, Toast.LENGTH_LONG).show()
                   }
               }else{
                   Toast.makeText(this, loginApiResponse!!.getMessage(), Toast.LENGTH_SHORT).show()

               }




           })
   }

}
