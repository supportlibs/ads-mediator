package com.lib.advertising_control.remote_config.ads.base

sealed class BaseAdObject{
    data class AdmobAdObject<T>(val ad: T) : BaseAdObject()
    data class FacebookAdObject<T>(val ad: T) : BaseAdObject()
    object NoneAdObject : BaseAdObject()
}


