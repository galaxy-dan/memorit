package com.galaxy.domain.usecase.login

import com.galaxy.domain.repository.LoginRepository
import javax.inject.Inject

class GetidUsecase @Inject constructor(private val loginRepository: LoginRepository) {
    operator fun invoke(): Int {
        return loginRepository.getId()
    }
}