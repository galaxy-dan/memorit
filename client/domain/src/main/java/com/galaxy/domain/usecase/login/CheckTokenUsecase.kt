package com.galaxy.domain.usecase.login

import com.galaxy.domain.repository.LoginRepository
import javax.inject.Inject

class CheckTokenUsecase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(navToMain: () -> Unit, fail: () -> Unit) {
        loginRepository.checkAccessToken().collect { accessCode ->
            if (accessCode == 1) navToMain()
            else if (accessCode == -1) fail()
            else loginRepository.checkRefreshToken().collect { refreshCode ->
                if (refreshCode == 1) {
                    loginRepository.checkAccessToken().collect {
                        if (it == 1) navToMain()
                        else fail()
                    }
                }
                else fail()
            }
        }
    }
}