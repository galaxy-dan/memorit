package com.galaxy.data.repository

import com.orhanobut.logger.Logger
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.galaxy.data.datasource.local.SharedPreference
import com.galaxy.data.datasource.remote.LoginDatasource
import com.galaxy.data.mapper.LoginMapper
import com.galaxy.data.model.login.req.JoinReqDto
import com.galaxy.domain.model.login.resp.CheckBirth
import com.galaxy.domain.model.login.req.JoinWithBirthReq
import com.galaxy.domain.model.login.resp.JoinWithBirthResp
import com.galaxy.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginDatasource: LoginDatasource, private val sharedPreference: SharedPreference
): LoginRepository {
    override suspend fun checkKakaoToken(token: String): CheckBirth {

        Logger.d("datasource 실행$token")
        val data = loginDatasource.checkKakaoToken(token)
        var result = CheckBirth(0, 0)
        data.onSuccess {
            result = LoginMapper.KakaoCheckRespDtoToCheckBirth(this.data)
            Logger.d("카카오토큰 체크 성공")
            sharedPreference.setAccessToken(this.headers.get("authorization")!!.split(" ")[1])
            sharedPreference.setRefreshToken(this.headers.get("refresh-token")!!.split(" ")[1])
//            LoginMapper.checkKakaoRespToResult(this.data)
        }.onError {
            result = LoginMapper.KakaoCheckRespDtoToCheckBirth(this.response.body()!!)
            Logger.d("카카오토큰 체크 실패 err\n$this")
        }.onException {
            Logger.d("카카오토큰 체크 실패 exception\n$this")
        }
        return result
    }

    override suspend fun joinWithBirth(data: JoinWithBirthReq): JoinWithBirthResp {
        val resp = loginDatasource.getJoin(JoinReqDto(userBirthday = data.birth, userNo= data.id))
        var result = JoinWithBirthResp(code = -1, id = -1)

        resp.onSuccess {
            result = LoginMapper.joinWithBirthToJoin(this.data)
//            sharedPreference.setAccessToken(this.headers)
            Logger.d(this.headers)
        }
            .onError {
                result = LoginMapper.joinWithBirthToJoin(this.response.body()!!)
            }.onException {
            }

        return result
    }

    override fun storeId(id: Int) {
        sharedPreference.setMyid(id)
    }

    override fun getId(): Int {
        return sharedPreference.getMyid()
    }

    override suspend fun checkAccessToken(): Flow<Int>  = flow {
        Logger.d("access called")
        val resp = loginDatasource.checkAccessToken()
        resp.suspendOnSuccess {
            Logger.d("access success")
            sharedPreference.setMyid(this.data.data)
//            Logger.d(this.headers)
//            sharedPreference.setAccessToken(this.headers.get("authorization")!!.split(" ")[1])
//            sharedPreference.setRefreshToken(this.headers.get("refresh-token")!!.split(" ")[1])
            emit(1)
        }.suspendOnError {
            Logger.d("access err")
            emit(2)

            Logger.d("access err${this.message()}")
        }.suspendOnException {
            Logger.d("access exception${this.message}")
            emit(-1)
        }
    }

    override suspend fun checkRefreshToken(): Flow<Int> = flow {
        Logger.d("refresh called")
        val resp = loginDatasource.checkRefreshToken()
        resp.suspendOnSuccess {
            Logger.d("refresh success")
            sharedPreference.setAccessToken(this.headers.get("authorization")!!.split(" ")[1])
            emit(1)
        }.suspendOnError {
            Logger.d("refresh err${this.message()}")
            emit(2)
        }.suspendOnException {
            Logger.d("refresh exception")
            emit(-1)
        }
    }
}