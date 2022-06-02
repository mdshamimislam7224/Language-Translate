package com.mdshamimislam.languagetranslate.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mdshamimislam.languagetranslate.R

class splash : AppCompatActivity() {
    lateinit var  iv: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        iv = findViewById(R.id.iv)
        val myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition)
        iv.startAnimation(myanim)
        val i = Intent(this, Selection_Option::class.java)
        val timer: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(4000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(i)
                    finish()
                }
            }
        }
        timer.start()
    }
}