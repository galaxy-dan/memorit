package com.galaxy.domain.usecase.login

import com.galaxy.domain.model.login.resp.CheckBirth
import com.galaxy.domain.repository.LoginRepository
import javax.inject.Inject

class CheckKakaoTokenUsecase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(token: String): CheckBirth {
       return repository.checkKakaoToken(token)
    }
}