package com.mdshamimislam.languagetranslate.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.mdshamimislam.languagetranslate.R
import com.mdshamimislam.languagetranslate.interfaces.AdsCallback
import com.mdshamimislam.languagetranslate.interfaces.CallbackListener
import com.mdshamimislam.languagetranslate.utils.CommonConstantAd
import com.mdshamimislam.languagetranslate.utils.NetworkCheck
import com.mdshamimislam.languagetranslate.utils.ad_class
import kotlinx.android.synthetic.main.activity_selection_option.*

class Selection_Option : AppCompatActivity() ,View.OnClickListener,AdsCallback,CallbackListener {
    private var TAG:String="Selection_Option"
    private var card3: CardView? = null
    private  var card2:CardView? = null
    private  var card1:CardView? = null
    private  var card4:CardView? = null
    private var isLoaded = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_option)
        card3 = findViewById<View>(R.id.card1234) as CardView
        card2 = findViewById<View>(R.id.card12345) as CardView
        card4 = findViewById<View>(R.id.card123456) as CardView
        card1 = findViewById<View>(R.id.card1234567) as CardView

        card3!!.setOnClickListener(this)
        card2!!.setOnClickListener(this)
        card4!!.setOnClickListener(this)
        card1!!.setOnClickListener(this)

        adNetworkCheck()

    }
    private fun adNetworkCheck()
    {
      if (NetworkCheck.isNetworkConnected(this))
      {
          ad_class.loadBannerAd(this,googleBannerAds)
          var ad=CommonConstantAd.googlebeforloadAd(this)
          CommonConstantAd.showInterstitialAdsGoogle(this,this)
      }
        else
      {
          NetworkCheck.openInternetDialog(this,true,this)
      }
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.card1234 -> {
                var intent = Intent(this, Image_To_Text::class.java)
                startActivity(intent)
                finish()

            }

            R.id.card12345 ->
            {
                if (NetworkCheck.isNetworkConnected(this))
                {
                    var intent = Intent(this, VoiceToText::class.java)
                    startActivity(intent)
                    CommonConstantAd.showInterstitialAdsGoogle(this,this)
                }
                else
                {
                    NetworkCheck.openInternetDialog(this,true,this)
                }
            }

            R.id.card123456 ->
            {
                var intent = Intent(this, Setting_Activity::class.java)
                startActivity(intent)

            }
            R.id.card1234567 ->
            {
                if (NetworkCheck.isNetworkConnected(this))
                {
                    try {
                        val sendIntentGmail = Intent(Intent.ACTION_SEND)
                        sendIntentGmail.type = "plain/text"
                        sendIntentGmail.setPackage("com.google.android.gm")
                        //sendIntentGmail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                        sendIntentGmail.putExtra(Intent.EXTRA_EMAIL, arrayOf("mdshamimislam487@gmail.com"))
                        sendIntentGmail.putExtra(Intent.EXTRA_SUBJECT, "${resources.getString(R.string.app_name)} - Android")
                        //if (body != null) sendIntentGmail.putExtra(Intent.EXTRA_TEXT, body)
                        startActivity(sendIntentGmail)
                    } catch (e: Exception) {
                        //When Gmail App is not installed or disable
                        val sendIntentIfGmailFail = Intent(Intent.ACTION_SEND)
                        sendIntentIfGmailFail.type = "*/*"
                        sendIntentIfGmailFail.putExtra(Intent.EXTRA_EMAIL, arrayOf("mdshamimislam487@gmail.com"))
                        sendIntentIfGmailFail.putExtra(Intent.EXTRA_SUBJECT, "${resources.getString(R.string.app_name)} - Android")
                        //if (body != null) sendIntentIfGmailFail.putExtra(Intent.EXTRA_TEXT, body)
                        if (sendIntentIfGmailFail.resolveActivity(packageManager) != null) {
                            startActivity(sendIntentIfGmailFail)
                        }
                    }
                    CommonConstantAd.showInterstitialAdsGoogle(this,this)
                }
                else
                {
                    NetworkCheck.openInternetDialog(this,true,this)
                }
            }


        }
    }

    override fun adLoadingFailed() {
        TODO("Not yet implemented")
    }

    override fun adClose() {
        TODO("Not yet implemented")
    }

    override fun startNextScreen() {
        startNextActivity(0)
    }
    override fun onLoaded() {
        isLoaded = true
    }

    private val handler = Handler(Looper.getMainLooper())
    private val myRunnable : Runnable = Runnable {
        if (NetworkCheck.isNetworkConnected(this)) {
            if (!this.isLoaded) {
                startNextActivity(0)
            }
        }
    }
    fun startNextActivity(time: Long) {
        handler.postDelayed({

        }, time)
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onCancel() {
        TODO("Not yet implemented")
    }

    override fun onRetry() {
        adNetworkCheck()
    }
}