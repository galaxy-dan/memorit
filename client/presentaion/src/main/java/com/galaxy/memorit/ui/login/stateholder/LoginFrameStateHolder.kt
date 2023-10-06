package com.galaxy.memorit.ui.login.stateholder

import android.content.Context
import com.galaxy.memorit.util.Util

class LoginFrameStateHolder(private val viewModel: LoginViewModel) {
    fun onKakaoSelected(context: Context) {
        Util(viewModel).kakaoLogin(context)
    }
}