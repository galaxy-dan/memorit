package com.galaxy.domain.usecase.login

import com.galaxy.domain.repository.LoginRepository
import javax.inject.Inject

class StoreIdUsecase @Inject constructor(private val loginRepository: LoginRepository) {
    operator fun invoke(id: Int) {
        loginRepository.storeId(id)
    }
}