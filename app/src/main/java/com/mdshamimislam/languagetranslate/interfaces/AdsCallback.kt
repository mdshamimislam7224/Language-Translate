package com.mdshamimislam.languagetranslate.interfaces

interface AdsCallback {
    fun adLoadingFailed()

    fun adClose()

    fun startNextScreen()

    fun onLoaded()
}