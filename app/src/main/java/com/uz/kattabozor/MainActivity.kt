package com.uz.kattabozor

import android.graphics.Color
import android.os.Bundle
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.uz.kattabozor.home.HomeScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = HomeScreen()

        window.statusBarColor = Color.WHITE
        WindowCompat.getInsetsController(window,window.decorView).isAppearanceLightStatusBars = true
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, fragment)
            commit()
        }
    }
}