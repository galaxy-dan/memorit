package com.galaxy.data.model.login.req

import kotlinx.serialization.Serializable

@Serializable
data class JoinReqDto(
    var userNo: Int,
    var userBirthday: String
)
