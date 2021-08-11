package com.titusnangi.chucknorrisproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.titusnangi.chucknorrisproject.adapter.JokeRecyclerViewActivity

class JokeSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_splash)

        val backGrounding: ImageView = findViewById(R.id.splash_logo)
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        backGrounding.startAnimation(sideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, JokeRecyclerViewActivity::class.java))
            finish()

        }, 3000)
    }
}