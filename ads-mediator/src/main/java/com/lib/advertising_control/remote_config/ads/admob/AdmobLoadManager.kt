package com.lib.advertising_control.remote_config.ads.admob

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.lib.advertising_control.remote_config.ads.base.InterstitialLoadManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.coroutines.CoroutineContext

class AdmobLoadManager(
    private val context: Context,
    private val adRequest: AdRequest,
    private val isSubscribed: Boolean,
    private val scope: CoroutineScope
) : InterstitialLoadManager() {

    inner class InterstitialListener : InterstitialAdLoadCallback() {
        override fun onAdFailedToLoad(adError: LoadAdError) {
            super.onAdFailedToLoad(adError)
            Log.e(this::class.java.simpleName, "Interstitial AD failed to load: ${adError.message}")

            isLoadingInterstitial = false
            isInterstitialLoadingDone = true
            isInterstitialDismissed = true
        }

        override fun onAdLoaded(ad: InterstitialAd) {
            super.onAdLoaded(ad)
            Log.d(this::class.java.simpleName, "Interstitial AD has been loaded successfully")

            isLoadingInterstitial = false
            isInterstitialLoadingDone = true
            interstitialAd = ad
            if (scope.isActive) scope.launch(Dispatchers.IO) {
                flow.emit(ad)
            }
        }
    }

    override fun loadInterstitial(id: String) {
        if (!isSubscribed) {
            isLoadingInterstitial = true
            AdManagerInterstitialAd.load(
                context,
                id,
                adRequest,
                InterstitialListener()
            )
        } else {
            isInterstitialShowed = true
            isLoadingInterstitial = false
            isInterstitialDismissed = true
        }
    }

}