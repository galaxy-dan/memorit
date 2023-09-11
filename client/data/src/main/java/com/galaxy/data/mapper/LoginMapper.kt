package com.galaxy.data.mapper

import com.galaxy.data.model.login.resp.CommRespDto
import com.galaxy.domain.model.login.resp.CheckBirth
import com.galaxy.domain.model.login.resp.JoinWithBirthResp

object LoginMapper {
    fun KakaoCheckRespDtoToCheckBirth(dto: CommRespDto): CheckBirth {
        return CheckBirth(code = Integer.parseInt(dto.code), id = dto.data)
    }

    fun joinWithBirthToJoin(dto: CommRespDto): JoinWithBirthResp {
        return JoinWithBirthResp(code = Integer.parseInt(dto.code), id = dto.data)
    }
}