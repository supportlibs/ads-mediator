package com.lib.advertising_control.remote_config.ads.none

import com.lib.advertising_control.remote_config.ads.base.InterstitialLoadManager

class NoneLoadManager : InterstitialLoadManager() {

    override var isLoadingInterstitial = false
    override var isInterstitialLoadingDone: Boolean? = true
    override var isInterstitialShowed = true
    override var isInterstitialDismissed = true

    override fun loadInterstitial(id: String) {}

}