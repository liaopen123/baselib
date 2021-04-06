package com.lph.wearable.lib.utils

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment

fun Activity.startAct(clazz: Class<Any>){
    startActivity(Intent(this,clazz))
}
fun Fragment.startAct(clazz: Class<Any>){
    startActivity(Intent(activity,clazz))
}
