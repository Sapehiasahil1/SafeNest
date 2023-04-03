package com.example.safenest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUserLoggedIn  = SharedPref.getBoolean(PrefConstants.IS_USER_LOGGED_IN)


        if(isUserLoggedIn){

            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }
        startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
        finish()
    }
}