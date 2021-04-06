package com.lph.wearable.lib.utils

import android.util.Log

fun Any.loge(message: Any){
    LogUtils.e(this.javaClass.simpleName, message)
}
fun Any.logv(message: Any){
    LogUtils.v(this.javaClass.simpleName, message)
}
fun Any.logi(message: Any){
    LogUtils.i(this.javaClass.simpleName, message)
}
fun Any.logd(message: Any){
    LogUtils.d(this.javaClass.simpleName, message)
}
fun Any.logw(message: Any){
    LogUtils.w(this.javaClass.simpleName, message)
}
fun Any.loga(message: Any){
    LogUtils.a(this.javaClass.simpleName, message)
}

