package com.lib.advertising_control.remote_config.ads.admob

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.lib.advertising_control.remote_config.ads.base.InterstitialShowManager

class AdmobShowManager(
    private val activity: Activity,
    private val ad: InterstitialAd?,
    private val fullScreenContentCallback: FullScreenContentCallback
) : InterstitialShowManager {

    override fun showInterstitial() {
        Log.d(this::class.java.simpleName, "showInterstitial() has been invoked.")

        ad?.show(activity)
        ad?.fullScreenContentCallback = fullScreenContentCallback
    }

}