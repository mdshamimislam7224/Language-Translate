package com.mdshamimislam.languagetranslate.activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import com.mdshamimislam.languagetranslate.R
import com.mdshamimislam.languagetranslate.interfaces.CallbackListener
import com.mdshamimislam.languagetranslate.utils.CommonConstantAd
import com.mdshamimislam.languagetranslate.utils.NetworkCheck


class splash : AppCompatActivity(),CallbackListener{
    private var TAG: String = "splash"
    lateinit var iv: LinearLayout

    private var isLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        iv = findViewById(R.id.iv)

           CallApi()
    }

    private fun CallApi() {

        if (NetworkCheck.isNetworkConnected(this))
        {
            MobileAds.initialize(this) {
            }

        CommonConstantAd.googlebeforloadAd(this)
        Log.d(TAG, "intertialsAd=" + CommonConstantAd.googlebeforloadAd(this))


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
    else
    {
        NetworkCheck.openInternetDialog(this, true,this)
    }
    }


    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onCancel() {
        TODO("Not yet implemented")
    }

    override fun onRetry() {
        CallApi()
    }

}

