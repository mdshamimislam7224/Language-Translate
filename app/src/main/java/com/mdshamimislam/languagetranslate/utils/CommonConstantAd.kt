package com.mdshamimislam.languagetranslate.utils

import android.app.Activity
import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mdshamimislam.languagetranslate.interfaces.AdsCallback
import com.mdshamimislam.languagetranslate.model_class.Constant

object CommonConstantAd {
    private final var TAG = "CommonConstantAd"
    private var mInterstitialAd: InterstitialAd? = null
    private var mCountDownTimer: CountDownTimer? = null
    private var mGameIsInProgress = false
    private var mAdIsLoading: Boolean = false
    private var mTimerMilliseconds = 0L

    private lateinit var adViewBottom : AdView
    private val adRequest: AdRequest
        get() = AdRequest.Builder().build()
    var interstitialAd: InterstitialAd? = null

    fun googlebeforloadAd(context: Context) {
        try {
            InterstitialAd.load(context,"ca-app-pub-3491584953793219/3157440673", adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    super.onAdFailedToLoad(adError)
                    interstitialAd = null
                    Log.d(TAG, adError.message)
                    Log.d(TAG, "Ad was failed="+adError)
                }
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    this@CommonConstantAd.interstitialAd = interstitialAd
                    Log.d(TAG, "Ad was loaded="+interstitialAd)
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun showInterstitialAdsGoogle(activity: Activity, adsCallback: AdsCallback) {
        try {
            if (interstitialAd != null) {
                interstitialAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        super.onAdFailedToShowFullScreenContent(adError)
                        interstitialAd = null

                       adsCallback.startNextScreen()
                    }

                    override fun onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent()
                        adsCallback.onLoaded()
                        Log.d(TAG, "Ad was onAdshow")

                    }

                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
                        interstitialAd = null
                        adsCallback.startNextScreen()
                    }



                }
                interstitialAd!!.show(activity)
            } else {
                adsCallback.startNextScreen()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun loadBannerGoogleAd(context: Context?, adView: AdView) {
         adViewBottom = AdView(context!!)
       adViewBottom.setAdSize(AdSize.FULL_BANNER)
        adViewBottom.adUnitId = Constant.GOOGLE_BANNER_ID

        adView.addView(adViewBottom)
        val adRequest = AdRequest.Builder().build()
        adViewBottom.loadAd(adRequest)
        adViewBottom.adListener = object :AdListener() {

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded()


            }
            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError)
                adViewBottom.loadAd(adRequest)
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened()
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                //can I visibilty gone
                //gone for  specific user or Block
                Toast.makeText(context, "Please Do not Touch this", Toast.LENGTH_SHORT).show()



            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                super.onAdClosed()
            }
        }
    }

    private fun loadAd(context: Context) {
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(context, "ca-app-pub-3940256099942544/1033173712", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    super.onAdFailedToLoad(adError)
                    Log.d(TAG, adError.message)
                    mInterstitialAd = null
                    mAdIsLoading = false
                    val error = "domain: ${adError.domain}, code: ${adError.code}, " +
                            "message: ${adError.message}"
                    Toast.makeText(
                        context,
                        "onAdFailedToLoad() with error $error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    super.onAdLoaded(interstitialAd)
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
                }
            }
        )
    }




    private fun createTimer(milliseconds: Long) {
        mCountDownTimer?.cancel()

        mCountDownTimer = object : CountDownTimer(milliseconds, 50) {
            override fun onTick(millisUntilFinished: Long) {
                mTimerMilliseconds = millisUntilFinished
            }

            override fun onFinish() {
                mGameIsInProgress = false

            }
        }
    }



    fun showInterstitial(context: Activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(TAG, "Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                    googlebeforloadAd(context)
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    Log.d(TAG, "Ad failed to show.")
                    super.onAdFailedToShowFullScreenContent(p0)
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(TAG, "Ad showed fullscreen content.")
                    // Called when ad is dismissed.
                }


                override fun onAdClicked() {
                    super.onAdClicked()
                }

            }
            mInterstitialAd?.show(context)
        }
        else {
            context.runOnUiThread(){
                kotlin.run {
                    Toast.makeText(context, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


}