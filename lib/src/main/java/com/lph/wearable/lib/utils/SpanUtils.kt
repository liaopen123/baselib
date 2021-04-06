package com.lph.wearable.lib.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.*
import androidx.annotation.ColorInt


/**
     *  //设置字体的颜色
     */
     fun String.getColorSpan(@ColorInt color: Int, startIndex: Int, endIndex: Int):SpannableString {
        val spanString = SpannableString(this)
        val span = ForegroundColorSpan(color)
        spanString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
      return spanString
    }
    /**
     *  //设置字体的大小
     */
     fun String.addFontSpan(textSizeDP: Int, startIndex: Int, endIndex: Int) :SpannableString{
        val spanString = SpannableString(this)
        val span = AbsoluteSizeSpan(textSizeDP, true)
        spanString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
       return  spanString
    }
/**
 *  //设置加粗
 */
 fun  String.addStyleSpan(startIndex: Int, endIndex: Int)  :SpannableString{
    val spanString = SpannableString(this)
    val span = StyleSpan(Typeface.BOLD_ITALIC) //加粗
    spanString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
     return  spanString
}

/**
 * 设置删除线
 */
private fun String.addStrikeSpan(startIndex: Int, endIndex: Int):SpannableString {
    val spanString = SpannableString(this)
    val span = StrikethroughSpan()
    spanString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return  spanString
}
/**
 * 添加下划线
 */
private fun String.addUnderLineSpan(startIndex: Int, endIndex: Int) :SpannableString{
    val spanString = SpannableString(this)
    val span = UnderlineSpan()
    spanString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return  spanString
}

/**
 * 超链接
 */
private fun String.addUrlSpan(url:String,startIndex: Int, endIndex: Int) :SpannableString{
    val spanString = SpannableString(this)
    val span = URLSpan(url)
    spanString.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return  spanString
}

