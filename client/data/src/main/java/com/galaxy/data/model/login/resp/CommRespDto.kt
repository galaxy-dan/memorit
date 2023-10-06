package com.galaxy.data.model.login.resp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CommRespDto(
    @SerialName("data")
    val data: Int,
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: String
)