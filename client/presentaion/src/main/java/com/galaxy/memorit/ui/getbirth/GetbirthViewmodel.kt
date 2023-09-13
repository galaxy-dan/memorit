package com.galaxy.memorit.ui.getbirth

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.galaxy.domain.model.getPhone.req.PhoneData
import com.orhanobut.logger.Logger
import com.galaxy.domain.model.login.req.JoinWithBirthReq
import com.galaxy.domain.usecase.login.GetidUsecase
import com.galaxy.domain.usecase.login.JoinWithBirthUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetbirthViewmodel @Inject constructor(private val joinWithBirthUsecase: JoinWithBirthUsecase,
    private val getidUsecase: GetidUsecase): ViewModel() {


    private val _phoneData = mutableStateListOf<PhoneData>()
    var phoneData: List<PhoneData> = _phoneData

    fun getPhoneData(idx: Int): PhoneData {
        return phoneData[idx]
    }

    fun setPhoneData(data: ArrayList<PhoneData>?) {
        if (data== null) return
        _phoneData.clear()
        for(i in data) {
            _phoneData.add(PhoneData(i.name, i.checked))
        }
    }

    fun setPhoneDataChecked(i: Int, state: Boolean){
        _phoneData[i] = _phoneData[i].copy(checked = state)
    }

    fun goNext(yy: Int, mm: Int, dd: Int, navToMain: () -> Unit) {
        val id = getidUsecase()

        var newmm = mm.toString()
        if (mm < 10) newmm = "0$newmm"
        val str = "$yy-$newmm-$dd"
        viewModelScope.launch {
            val data = joinWithBirthUsecase(JoinWithBirthReq(str, id))
            when (data.code) {
                200 -> navToMain()
                else -> {
                    Logger.d("오류")
                }
            }
        }
    }




}