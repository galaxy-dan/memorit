package com.galaxy.data.network.Interceptor

import com.orhanobut.logger.Logger
import com.galaxy.data.datasource.local.SharedPreference
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val sharedPreference: SharedPreference) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()

        val req = if (origin.url.encodedPath.equals("/partylog/user/mobile/login", true)||
            origin.url.encodedPath.equals("/partylog/user/join", true)) {
            //jwt 필요없는 요청들
            Logger.d("jwi없음")
            origin.newBuilder().build()
        }
        else {
            Logger.d("jwt추가")
            origin.newBuilder().apply {
                addHeader(
                    "Authorization",
                    "Bearer " + sharedPreference.getAccessToken()
                )
            }.build()
        }

      return chain.proceed(req)
    }
}