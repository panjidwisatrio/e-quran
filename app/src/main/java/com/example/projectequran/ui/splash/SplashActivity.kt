package com.example.projectequran.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.projectequran.databinding.ActivitySplashBinding
import com.example.projectequran.ui.main.MainActivity
import com.example.projectequran.util.Constanta.SPLASH_SCREEN
import com.example.projectequran.util.Constanta.TIME_SPLASH

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        object : Thread() {
            override fun run() {
                try {
                    sleep(TIME_SPLASH)
                    moveToMain()
                    finish()
                } catch (e: Exception) {
                    Log.d(SPLASH_SCREEN, e.message.toString())
                }
            }
        }.start()
    }

    private fun moveToMain() {
        startActivity(
            Intent(this, MainActivity::class.java)
        )
    }
}