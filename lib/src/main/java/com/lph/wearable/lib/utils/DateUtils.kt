package com.lph.wearable.lib.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Long.dateFormat(pattern: String = "yyyy-MM-dd HH:mm:ss"):String{
    val sdf = SimpleDateFormat(pattern, Locale.CHINA)
    return sdf.format(this)
}

/**
 * 得到当前小时 24小时制
 * @return
 */
fun getHours(): String? {
    val lefttime = System.currentTimeMillis()
    val sdf = SimpleDateFormat("HH", Locale.CHINA)
    return sdf.format(lefttime)
}

/**得到当前星期几 */
fun getWeekday(): String? {
    val c = Calendar.getInstance()
    c.timeZone = TimeZone.getTimeZone("GMT+8:00")
    return java.lang.String.valueOf(c[Calendar.DAY_OF_WEEK])
}

/**
 * 字符串转为long
 * @param time 字符串时间,注意:格式要与template定义的一样
 * @param template 要格式化的格式:如time为09:21:12那么template为"HH:mm:ss"
 * @return 时间戳
 */
fun String.StringTime2TimeStamp(template: String):Long{
    val sdf = SimpleDateFormat(template, Locale.CHINA)
    return try {
        val d = sdf.parse(this)
        val c = Calendar.getInstance()
        c.time = d
        c.timeInMillis
    } catch (e: ParseException) {
        e.printStackTrace()
        0
    }
}

/**
 * 获取将来某一天 距离现在时间的天数
 * @param endTime 将来时间的毫秒值
 * @return   相差天数long值
 */
fun Long.getDAYS(): Long {
    val currentTimeMillis = System.currentTimeMillis()
    return (this - currentTimeMillis) / (1000 * 3600 * 24)
}