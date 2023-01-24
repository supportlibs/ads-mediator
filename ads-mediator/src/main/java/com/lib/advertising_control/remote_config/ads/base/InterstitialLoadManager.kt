package com.lib.advertising_control.remote_config.ads.base

import com.google.android.gms.ads.interstitial.InterstitialAd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

abstract class InterstitialLoadManager {

    var interstitialAd: InterstitialAd? = null
    open var isLoadingInterstitial = false
    open var isInterstitialLoadingDone: Boolean? = false
    open var isInterstitialShowed = false
    open var isInterstitialDismissed = false
    val flow = MutableSharedFlow<InterstitialAd?>()

    abstract fun loadInterstitial(id: String)

    fun interstitialIsShowed() {
        isInterstitialShowed = true
        interstitialAd = null
    }

    fun dismissInterstitial() {
        isInterstitialDismissed = true
    }

}