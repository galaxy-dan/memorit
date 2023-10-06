package com.galaxy.data.datasource.local

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreference @Inject constructor(sharedPreferences: SharedPreferences) {

//    lateinit var spref: SharedPreference
//    private val sprefName = "spref"
//    val spref = context.getSharedPreferences(sprefName, 0)
    companion object {
    private lateinit var spref: SharedPreferences
}
    init {
        spref = sharedPreferences
    }

    private val myid = "myid"
    private val accessToken = "accessToken"
    private val refreshToken = "refreshToken"


    fun setMyid(id: Int) = spref.edit().putInt(myid, id).apply()
    fun getMyid(): Int = spref.getInt(myid, -1)

    fun setAccessToken(token: String) = spref.edit().putString(accessToken, token).apply()
    fun getAccessToken(): String = spref.getString(accessToken, "")!!
    fun setRefreshToken(token: String) = spref.edit().putString(refreshToken, token).apply()
    fun getRefreshToken(): String = spref.getString(refreshToken, "")!!
}