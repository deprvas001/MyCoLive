package com.development.mycolive.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.development.mycolive.R
import com.development.mycolive.views.activity.forgotPassword.ForgotPassword

class LoginScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        toForgotPassword()
    }

  private fun toForgotPassword(){
      val forgotPassword = Intent(applicationContext, ForgotPassword::class.java)
      startActivity(forgotPassword)
  }
}
