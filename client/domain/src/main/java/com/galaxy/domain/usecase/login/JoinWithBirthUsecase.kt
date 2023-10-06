package com.galaxy.domain.usecase.login

import com.galaxy.domain.model.login.req.JoinWithBirthReq
import com.galaxy.domain.model.login.resp.JoinWithBirthResp
import com.galaxy.domain.repository.LoginRepository
import javax.inject.Inject

class JoinWithBirthUsecase @Inject constructor(private val repository: LoginRepository) {
    suspend operator fun invoke(dto: JoinWithBirthReq): JoinWithBirthResp {
        return repository.joinWithBirth(dto)
    }
}