package com.lph.wearable.lib.utils

/**
 * 将手机号格式化成为 xxx xxxx xxxx
 * @param this
 * @return
 */
fun String.formatPhone(): String {
    return try {
        this.substring(0, 3) + " " + this.substring(3, 7) + " " + this.substring(7, 11)
    } catch (e: Exception) {
        this
    }
}
