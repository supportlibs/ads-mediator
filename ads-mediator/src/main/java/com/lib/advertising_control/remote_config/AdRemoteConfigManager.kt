package com.lib.advertising_control.remote_config

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import com.lib.advertising_control.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class AdRemoteConfigManager {

    val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance().apply {
        val configSettings = FirebaseRemoteConfigSettings.Builder().setFetchTimeoutInSeconds(0).build()
        setConfigSettingsAsync(configSettings)
        setDefaultsAsync(R.xml.remote_config_defaults)
    }

    private val gson = Gson()

    fun getAdRequest(key: String): RequestState<ConfigAdModel?> {

        try {
            Log.e("remote config", "getAdRequest $key: ${gson.fromJson(remoteConfig.getString(key), ConfigAdModel::class.java)}" )
            return RequestState.Success(gson.fromJson(remoteConfig.getString(key), ConfigAdModel::class.java))

        } catch (e: SocketTimeoutException) {
            Log.e("AD_CONFIG", "${e.message}")
            return RequestState.Error(e)

        } catch (e: UnknownHostException) {
            Log.e("AD_CONFIG", "${e.message}")
            return RequestState.Error(e)

        } catch (e: FirebaseRemoteConfigClientException) {
            Log.e("AD_CONFIG", "${e.message}")
            return RequestState.Error(e)

        } catch (e: Exception) {
            Log.e("AD_CONFIG", "${e.message}")
            FirebaseCrashlytics.getInstance().recordException(Throwable(e.message))
            return RequestState.Error(e)
        }
    }

}