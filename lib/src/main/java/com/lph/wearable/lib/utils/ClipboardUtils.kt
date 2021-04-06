package com.lph.wearable.lib.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ClipboardManager.OnPrimaryClipChangedListener
import android.content.Context
import com.lph.wearable.lib.base.Utils


fun String.copyText2Clipboard(){
    val cm: ClipboardManager =
        Utils.mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(ClipData.newPlainText(Utils.mContext.packageName, this))
}
fun clearClipboard(){
    val cm = Utils.mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.setPrimaryClip(ClipData.newPlainText(null, ""))
}
fun getText(): CharSequence {
    val cm = Utils.mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = cm.primaryClip
    if (clip != null && clip.itemCount > 0) {
        val text = clip.getItemAt(0).coerceToText(Utils.mContext)
        if (text != null) {
            return text
        }
    }
    return ""
}
/**
 * 添加剪贴板监听
 */
fun addClipboardChangedListener(listener: OnPrimaryClipChangedListener?) {
    val cm = Utils.mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.addPrimaryClipChangedListener(listener)
}

/**
 * 移除剪贴板监听
 */
fun removeClipboardChangedListener(listener: OnPrimaryClipChangedListener?) {
    val cm = Utils.mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    cm.removePrimaryClipChangedListener(listener)
}