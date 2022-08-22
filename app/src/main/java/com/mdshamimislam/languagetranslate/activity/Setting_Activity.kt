package com.mdshamimislam.languagetranslate.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.mdshamimislam.languagetranslate.R
import com.mdshamimislam.languagetranslate.interfaces.AdsCallback
import com.mdshamimislam.languagetranslate.interfaces.CallbackListener
import com.mdshamimislam.languagetranslate.utils.CommonConstantAd
import com.mdshamimislam.languagetranslate.utils.NetworkCheck
import com.mdshamimislam.languagetranslate.utils.ad_class
import kotlinx.android.synthetic.main.activity_setting.*

class Setting_Activity : AppCompatActivity() ,CallbackListener,View.OnClickListener, AdsCallback {
    private var rate: TextView? = null
    private var share: TextView? = null
    private var contactMe: TextView? = null
    private var privacyPolicy: TextView? = null
    private var moreApps: TextView? = null
    private var isLoaded = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        rate=findViewById(R.id.rateUs) as TextView
        share=findViewById(R.id.shareApp) as TextView
        contactMe=findViewById(R.id.contact_Us) as TextView
        privacyPolicy=findViewById(R.id.privacy_Policy) as TextView
        moreApps=findViewById(R.id.moreApps) as TextView

        rate!!.setOnClickListener(this)
        share!!.setOnClickListener(this)
        contactMe!!.setOnClickListener(this)
        privacyPolicy!!.setOnClickListener(this)
        moreApps!!.setOnClickListener(this)

        initDefine()
    }
    private fun initDefine()
    {
        if (NetworkCheck.isNetworkConnected(this))
        {
          ad_class.loadBannerAd(this,googleBannerAdsSettings)
            CommonConstantAd.showInterstitialAdsGoogle(this,this)

        }
        else
        {
        NetworkCheck.openInternetDialog(this,true,this)
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
        initDefine()
    }

    override fun onClick(p0: View?) {
        if (p0 !=null)
        {
            when(p0.id)
            {
              R.id.rateUs ->
              {
                  if (NetworkCheck.isNetworkConnected(this))
                  {
                      try {
                          startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                      } catch (anfe: android.content.ActivityNotFoundException) {
                          startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+packageName)))
                      }
                  }
                  else
                  {
                    NetworkCheck.openInternetDialog(this,true,this)
                  }
              }
                R.id.shareApp ->
                {
                    if (NetworkCheck.isNetworkConnected(this))
                    {
                        val shareIntent = Intent()
                        shareIntent.action = Intent.ACTION_SEND
                        val link = "https://play.google.com/store/apps/details?id=$packageName"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, link)
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.app_name))
                        shareIntent.type = "text/plain"
                        startActivity(Intent.createChooser(shareIntent, resources.getString(R.string.app_name)))
                    }
                    else
                    {
                        NetworkCheck.openInternetDialog(this,true,this)
                    }

                }

                R.id.contact_Us ->
                {
                    if (NetworkCheck.isNetworkConnected(this))
                    {
                        try {
                            val sendIntentGmail = Intent(Intent.ACTION_SEND)
                            sendIntentGmail.type = "plain/text"
                            sendIntentGmail.setPackage("com.google.android.gm")
                            //sendIntentGmail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                            sendIntentGmail.putExtra(Intent.EXTRA_EMAIL, arrayOf("mdshamimislam487@gmail.com"))
                            sendIntentGmail.putExtra(Intent.EXTRA_SUBJECT, "${resources.getString(R.string.app_name)}-Android")
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
                    }
                    else
                    {
                        NetworkCheck.openInternetDialog(this,true,this)
                    }

                }

                R.id.privacy_Policy ->
                {
                    Toast.makeText(applicationContext, "Future Add this content", Toast.LENGTH_SHORT).show()
                }
                R.id.moreApps ->
                {
                    if (NetworkCheck.isNetworkConnected(this))
                    {
                        val uri = Uri.parse("https://play.google.com/console/u/0/developers/7326979754422021388/app-list?pli=1")
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }
                    else
                    {
                        NetworkCheck.openInternetDialog(this,true,this)
                    }

                }
            }
        }
    }

}