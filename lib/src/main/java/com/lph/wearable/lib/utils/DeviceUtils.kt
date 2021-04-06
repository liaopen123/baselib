package com.lph.wearable.lib.utils

import android.Manifest.permission.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.lph.wearable.lib.base.Utils
import java.io.File
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*


fun isDeviceRooted(): Boolean {
    val su = "su"
    val locations = arrayOf(
        "/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
        "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/",
        "/system/sbin/", "/usr/bin/", "/vendor/bin/"
    )
    for (location in locations) {
        if (File(location + su).exists()) {
            return true
        }
    }
    return false
}

/**
 * Return whether ADB is enabled.
 *
 * @return `true`: yes<br></br>`false`: no
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun isAdbEnabled(): Boolean {
    return Settings.Secure.getInt(
        Utils.mContext.contentResolver,
        Settings.Global.ADB_ENABLED, 0
    ) > 0
}

fun getSDKVersionName(): String? {
    return Build.VERSION.RELEASE
}

/**
 * @return version code of device's system
 */
fun getSDKVersionCode(): Int {
    return Build.VERSION.SDK_INT
}

/**
 * Return the android id of device.
 *
 * @return the android id of device
 */
@SuppressLint("HardwareIds")
fun getAndroidID(): String {
    val id = Settings.Secure.getString(
        Utils.mContext.contentResolver,
        Settings.Secure.ANDROID_ID
    )
    return if ("9774d56d682e549c" == id) "" else id ?: ""
}



private fun getWifiEnabled(): Boolean {
    @SuppressLint("WifiManagerLeak") val manager =
        Utils.mContext.getSystemService(WIFI_SERVICE) as WifiManager
            ?: return false
    return manager.isWifiEnabled
}

/**
 * wifi是否可用
 * Must hold `<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />`
 * @param enabled True to enabled, false otherwise.
 */
@RequiresPermission(CHANGE_WIFI_STATE)
private fun setWifiEnabled(enabled: Boolean) {
    @SuppressLint("WifiManagerLeak") val manager =
        Utils.mContext.getSystemService(WIFI_SERVICE) as WifiManager
            ?: return
    if (enabled == manager.isWifiEnabled) return
    manager.isWifiEnabled = enabled
}




@RequiresPermission(ACCESS_WIFI_STATE)
private fun getMacAddressByWifiInfo(): String {
    try {
        val wifi = Utils.mContext
            .getApplicationContext().getSystemService(WIFI_SERVICE) as WifiManager
        if (wifi != null) {
            val info = wifi.connectionInfo
            if (info != null) {
                @SuppressLint("HardwareIds") val macAddress = info.macAddress
                if (!TextUtils.isEmpty(macAddress)) {
                    return macAddress
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return "02:00:00:00:00:00"
}





private fun getInetAddress(): InetAddress? {
    try {
        val nis: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
        while (nis.hasMoreElements()) {
            val ni: NetworkInterface = nis.nextElement()
            // To prevent phone of xiaomi return "10.0.2.15"
            if (!ni.isUp) continue
            val addresses: Enumeration<InetAddress> = ni.inetAddresses
            while (addresses.hasMoreElements()) {
                val inetAddress: InetAddress = addresses.nextElement()
                if (!inetAddress.isLoopbackAddress()) {
                    val hostAddress: String = inetAddress.hostAddress
                    if (hostAddress.indexOf(':') < 0) return inetAddress
                }
            }
        }
    } catch (e: SocketException) {
        e.printStackTrace()
    }
    return null
}



/**
 *
 * 返回手机生产商 e.g. Xiaomi
 *
 * @return the manufacturer of the product/hardware
 */
fun getManufacturer(): String? {
    return Build.MANUFACTURER
}

/**
 *返回手机型号 e.g. MI2SC
 * @return the model of device
 */
fun getModel(): String? {
    var model = Build.MODEL
    model = model?.trim { it <= ' ' }?.replace("\\s*".toRegex(), "") ?: ""
    return model
}


/**
 *是否是平板电脑
 * @return `true`: yes<br></br>`false`: no
 */
fun isTablet(): Boolean {
    return ((Resources.getSystem().configuration.screenLayout
            and Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE)
}

/**
 *是不是模拟器
 * @return `true`: yes<br></br>`false`: no
 */
fun isEmulator(): Boolean {
    val checkProperty = (Build.FINGERPRINT.startsWith("generic")
            || Build.FINGERPRINT.toLowerCase().contains("vbox")
            || Build.FINGERPRINT.toLowerCase().contains("test-keys")
            || Build.MODEL.contains("google_sdk")
            || Build.MODEL.contains("Emulator")
            || Build.MODEL.contains("Android SDK built for x86")
            || Build.MANUFACTURER.contains("Genymotion")
            || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
            || "google_sdk" == Build.PRODUCT)
    if (checkProperty) return true
    var operatorName = ""
    val tm = Utils.mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    if (tm != null) {
        val name = tm.networkOperatorName
        if (name != null) {
            operatorName = name
        }
    }
    val checkOperatorName = operatorName.toLowerCase() == "android"
    if (checkOperatorName) return true
    val url = "tel:" + "123456"
    val intent = Intent()
    intent.data = Uri.parse(url)
    intent.action = Intent.ACTION_DIAL
    val checkDial = intent.resolveActivity(Utils.mContext.packageManager) == null
    return checkDial
}

/**
 * 开发者模式是否可用
 * @return whether user has enabled development settings.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun isDevelopmentSettingsEnabled(): Boolean {
    return Settings.Global.getInt(
        Utils.mContext.getContentResolver(),
        Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0
    ) > 0
}


