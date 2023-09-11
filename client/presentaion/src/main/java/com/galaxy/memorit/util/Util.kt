package com.galaxy.memorit.util

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.kakao.sdk.user.UserApiClient
import com.orhanobut.logger.Logger
import com.galaxy.memorit.ui.login.stateholder.LoginViewModel

class Util(private var viewModel: ViewModel) {
    fun kakaoLogin(context: Context) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context = context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e("로그인 실패", error.toString())
                } else if (token != null) {
                    Logger.d("카카오 로그인 성공"+"${token.accessToken}")
                    (viewModel as LoginViewModel).afterKakaoLogin(token.accessToken)
                }
            }
        }
        else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    Log.e("로그인 실패", error.toString())
                }
                else if (token != null) {
                    Logger.d("카카오 로그인 성공"+"${token.accessToken}")
                    (viewModel as LoginViewModel).afterKakaoLogin(token.accessToken)
                }
            }
        }
    }
}