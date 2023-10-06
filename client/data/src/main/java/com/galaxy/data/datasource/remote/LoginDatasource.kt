package com.galaxy.data.datasource.remote

import com.skydoves.sandwich.ApiResponse
import com.galaxy.data.model.login.req.JoinReqDto
import com.galaxy.data.model.login.resp.CommRespDto
import com.galaxy.data.service.login.LoginService
import javax.inject.Inject

class LoginDatasource @Inject constructor(private val service: LoginService) {
    suspend fun checkKakaoToken(token: String): ApiResponse<CommRespDto> = service.kakaoLogin(token)
    suspend fun getJoin(data: JoinReqDto): ApiResponse<CommRespDto> = service.joinWithbirth(data)
    suspend fun checkAccessToken(): ApiResponse<CommRespDto> = service.checkAccessToken()
    suspend fun checkRefreshToken(): ApiResponse<CommRespDto> = service.checkRefreshToken()
}