package com.galaxy.memorit.ui.getbirth.stateholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.galaxy.memorit.ui.getbirth.stateholder.PickerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar

class PickerStateHolder {
    private val _uiState = MutableStateFlow(PickerState())
    var uiState: StateFlow<PickerState> = _uiState.asStateFlow()

    var year by mutableStateOf(setDefaultYear())
    var month by mutableStateOf(6)
    var day by mutableStateOf(15)

    private val days = arrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)


    fun setDefaultYear(): Int = Calendar.getInstance().get(Calendar.YEAR) - 20
    fun getMaxYear(): Int = Calendar.getInstance().get(Calendar.YEAR)
    fun getMinYear(): Int = Calendar.getInstance().get(Calendar.YEAR) - 150
    fun getMaxday(yy:Int = year, mm: Int = month): Int {
        return if (mm != 2) days[mm - 1]
        else {
            if (yy%4 == 0 && yy%100 == 0 && yy%400 == 0) 28
            else if (yy%4 == 0 && yy%100 == 0) 29
            else if (yy%4 == 0) 28
            else 29
        }
    }




}