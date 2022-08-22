package com.mdshamimislam.languagetranslate.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.mdshamimislam.languagetranslate.interfaces.CallbackListener


class NetworkCheck
{
    companion object
    {

        fun openInternetDialog(callbackListener: CallbackListener, network: Boolean?, context: Context) {
            if (!isNetworkConnected(context)) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("No internet Connection")
                builder.setCancelable(false)
                builder.setMessage("Please turn on internet connection to continue")
                builder.setNegativeButton("Retry") { dialog, which ->
                    if (!network!!) {
                        openInternetDialog(callbackListener, false, context)
                    }
                    dialog.dismiss()
                    callbackListener.onRetry()
                }
                builder.setPositiveButton("Close") { dialog, which ->
                    dialog.dismiss()
                    val homeIntent = Intent(Intent.ACTION_MAIN)
                    homeIntent.addCategory(Intent.CATEGORY_HOME)
                    homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    context.startActivity(homeIntent)
                    (context as Activity).finishAffinity()
                }
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }


        fun isNetworkConnected(context: Context): Boolean {
            var networkConnected = false
            val connectivityMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val allNetworks = connectivityMgr.allNetworks // added in API 21 (Lollipop)


            for (network in allNetworks) {
                val networkCapabilities = connectivityMgr.getNetworkCapabilities(network)
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                    ) networkConnected = true
                }
            }
            return networkConnected
        }
    }
}