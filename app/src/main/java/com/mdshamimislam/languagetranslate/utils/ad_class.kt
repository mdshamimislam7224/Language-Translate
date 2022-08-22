package com.mdshamimislam.languagetranslate.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import com.google.android.gms.ads.AdView
import com.mdshamimislam.languagetranslate.utils.CommonConstantAd.loadBannerGoogleAd

 class ad_class (context: Context)
{
    companion object {
        lateinit var sharedPreferences: SharedPreferences


        fun setValueFrom(value: Int) {
            val editor = sharedPreferences.edit()
            editor.putInt("SpinnerImage", value)
            editor.apply()
        }
        fun setValueTo(value: String) {
            val editor = sharedPreferences.edit()
            editor.putString("SpinnerName", value)
            editor.apply()
        }
        fun getValueFrom(): Int{
            return sharedPreferences.getInt("SpinnerImage",0 )
        }


        fun getPref(context: Context?, key: String, value: String): String? {

            return PreferenceManager.getDefaultSharedPreferences(context).getString(key, value)
        }
        fun getPref(context: Context?, key: String?, value: Int?): Int {
            return PreferenceManager.getDefaultSharedPreferences(context).getInt(key, value!!)
        }
        fun setPref(context: Context?, key: String?, value: Int?) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(key, value!!)
            editor.apply()
        }



        fun loadBannerAd(context: Context?, adView: AdView) {
                loadBannerGoogleAd(context, adView)

            }


    }
    init{
        sharedPreferences = context.getSharedPreferences("MyPref", 0)
    }


}