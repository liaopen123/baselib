package com.lph.wearable.lib.base

import android.app.Application
import android.content.Context

class Utils {
    companion object{
        lateinit var mContext : Context
        fun init(context:Context){
            mContext = context
        }

    }
}