package com.example.instagram

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InstaSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_splash)

        val sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "empty")
        when(token){
            "empty" -> {
                // 로그인이 되어 있지 않은 경우
                startActivity(Intent(this, InstaLoginActivity::class.java))
            }
            else -> {
                // 로그인이 되어있는 경우
                startActivity(Intent(this, InstaMainActivity::class.java))

            }
        }

    }
}