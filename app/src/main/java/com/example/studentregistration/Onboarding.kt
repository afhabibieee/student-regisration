package com.example.studentregistration

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.RequiresApi

class Onboarding : AppCompatActivity(), View.OnClickListener {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // transparent status bar
        requestWindowFeature(1)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.statusBarColor = Color.TRANSPARENT

        setContentView(R.layout.activity_onboarding)

        val btnGetStarted: Button = findViewById(R.id.btn_get_started)
        btnGetStarted.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_get_started -> {
                val intent = Intent(this@Onboarding, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}