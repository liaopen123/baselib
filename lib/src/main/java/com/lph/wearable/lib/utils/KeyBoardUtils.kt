package com.lph.wearable.lib.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.lph.wearable.lib.base.Utils


fun showSoftInput() {
    val imm: InputMethodManager =
        Utils.mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            ?: return
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun hideSoftInput(view: View) {
    val imm = Utils.mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        ?: return
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

