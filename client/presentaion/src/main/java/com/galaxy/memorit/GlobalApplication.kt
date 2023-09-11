package com.galaxy.memorit

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.galaxy.data.datasource.local.SharedPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApplication: Application() {

    companion object {
        private lateinit var spref: SharedPreference
        private val sprefName = "spref"
    }
    override fun onCreate() {
        super.onCreate()
        init()
    }
    fun init() {
        KakaoSdk.init(this, appKey = BuildConfig.NATIVE_APP_KEY)

        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })

        spref = SharedPreference(applicationContext.getSharedPreferences(sprefName, 0))

    }
}