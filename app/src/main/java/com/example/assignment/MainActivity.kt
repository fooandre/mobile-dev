package com.example.assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.splashView).animation = AnimationUtils.loadAnimation(this, R.anim.image_anim)
        findViewById<TextView>(R.id.titleView).animation = AnimationUtils.loadAnimation(this, R.anim.title_anim)

        Handler().postDelayed({
            val intent = Intent(this, MoviesList::class.java)
            startActivity(intent)
        }, 5000)
    }
}