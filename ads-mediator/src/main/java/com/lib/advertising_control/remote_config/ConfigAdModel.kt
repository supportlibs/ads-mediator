package com.lib.advertising_control.remote_config

import com.google.gson.annotations.SerializedName

data class ConfigAdModel(
    @SerializedName("ads_provider")
    val adsProvider: AdType,
    @SerializedName("isTestAd")
    val isTest: Boolean,
    @SerializedName("id")
    val id: String
)

enum class AdType {
    @SerializedName("admob")
    ADMOB,
    @SerializedName("none")
    NONE,
    @SerializedName("facebook")
    FACEBOOK
}