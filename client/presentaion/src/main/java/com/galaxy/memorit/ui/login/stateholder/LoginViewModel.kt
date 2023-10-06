package com.galaxy.memorit.ui.login.stateholder

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.galaxy.domain.usecase.login.CheckTokenUsecase
import com.galaxy.domain.usecase.login.CheckKakaoTokenUsecase
import com.galaxy.domain.usecase.login.StoreIdUsecase
import com.galaxy.memorit.ui.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val application: Application,
    private val checkKakaoTokenUsecase: CheckKakaoTokenUsecase,
    private val storeIdUsecase: StoreIdUsecase,
    private val checkTokenUsecase: CheckTokenUsecase): AndroidViewModel(
        application
) {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState>
        get() = _uiState.asStateFlow()

    var loginCode by mutableStateOf(0)
        private set


    fun resetCode() {
        loginCode = 0
    }
    fun afterKakaoLogin(token: String) {
        Logger.d("토큰가지고 서버 호출")

        viewModelScope.launch {
            val data = checkKakaoTokenUsecase(token)
            loginCode = data.code
            if (loginCode == 201 || loginCode == 200) {
                storeIdUsecase(data.id)
            }
        }
    }
    fun autoLogin(navToMain: () -> Unit) {
        viewModelScope.launch {
            Logger.d("자동로그인 실행")
            checkTokenUsecase(navToMain) {
                Toast.makeText(
                    application.applicationContext,
                    "네트워크 에러",
                    Toast.LENGTH_SHORT
                )
            }
        }
        }
    }