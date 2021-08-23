package com.example.studentregistration

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.annotation.RequiresApi

class splash_screen : AppCompatActivity() {
    private lateinit var handler: Handler

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // transparent status bar
        requestWindowFeature(1)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT

        setContentView(R.layout.activity_splash_screen)

        // delay goto another activity page
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, Onboarding::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}