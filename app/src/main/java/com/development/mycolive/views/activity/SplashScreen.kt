package com.development.mycolive.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.development.mycolive.R
import com.development.mycolive.session.SessionManager
import com.development.mycolive.views.activity.login.LoginActivity
import com.development.mycolive.views.activity.testimonial.Testimonials

class SplashScreen : AppCompatActivity() {
    private lateinit var session: SessionManager
    private val splashTime = 3000L //3 seconds
    private lateinit var  myHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        session = SessionManager(applicationContext)
        myHandler = Handler()
        myHandler.postDelayed({
            goToMainActivity()
        },splashTime)
    }

    private fun goToMainActivity(){
      //  session.checkLogin()
        val mainActivityIntent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }
}
