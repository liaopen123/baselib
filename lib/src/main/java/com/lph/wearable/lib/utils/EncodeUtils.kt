package com.lph.wearable.lib.utils

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder


fun String.urlEncode(): String? {
    return urlEncode(this, "UTF-8")
}

fun urlEncode(input: String?, charsetName: String?): String? {
    return if (input == null || input.isEmpty()) "" else try {
        URLEncoder.encode(input, charsetName)
    } catch (e: UnsupportedEncodingException) {
        throw AssertionError(e)
    }
}

fun String.urlDecode(): String? {
    return urlDecode(this, "UTF-8")
}
fun urlDecode(input: String?, charsetName: String?): String? {
    return if (input == null || input.isEmpty()) "" else try {
        val safeInput =
            input.replace("%(?![0-9a-fA-F]{2})".toRegex(), "%25").replace("\\+".toRegex(), "%2B")
        URLDecoder.decode(safeInput, charsetName)
    } catch (e: UnsupportedEncodingException) {
        throw java.lang.AssertionError(e)
    }
}


fun String.base64Encode(): ByteArray? {
    return this.toByteArray().base64Encode()
}

fun ByteArray?.base64Encode(): ByteArray? {
    return if (this == null || this.isEmpty()) ByteArray(0) else Base64.encode(
        this,
        Base64.NO_WRAP
    )
}

fun ByteArray?.base64Encode2String(): String? {
    return if (this == null || this.isEmpty()) "" else Base64.encodeToString(
        this,
        Base64.NO_WRAP
    )
}

fun String?.base64Decode(): ByteArray? {
    return if (this == null || this.isEmpty()) ByteArray(0) else Base64.decode(
        this,
        Base64.NO_WRAP
    )
}
fun ByteArray?.base64Decode( ): ByteArray? {
    return if (this == null || this.isEmpty()) ByteArray(0) else Base64.decode(
        this,
        Base64.NO_WRAP
    )
}


