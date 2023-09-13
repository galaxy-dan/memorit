package com.galaxy.memorit.ui.getbirth

import android.content.Context
import android.provider.ContactsContract
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chargemap.compose.numberpicker.NumberPicker
import com.galaxy.memorit.R
import com.galaxy.memorit.ui.getbirth.stateholder.PickerStateHolder
import com.galaxy.memorit.ui.theme.maplestory
import com.galaxy.memorit.ui.theme.themecolor
import com.orhanobut.logger.Logger


@Composable
fun Getbirth(modifier: Modifier = Modifier, viewModel: GetbirthViewmodel = hiltViewModel(),
             navToMain: () -> Unit = {}) {
    val stateHolder = PickerStateHolder()
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        Logger.d(getContactsString(context).toString())
    }


    Box(modifier = Modifier.fillMaxSize()) {
        PhoneList(viewModel.phoneData, viewModel)
    }
//    Column(
//        modifier
//            .background(Color.White)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        Row(
//            modifier
//                .weight(2f)
//                .padding(bottom = 90.dp), verticalAlignment = Alignment.Bottom) {
//            Title()
//        }
//        Row(
//            modifier
//                .weight(1f)
//                .padding(horizontal = 80.dp)
//                .fillMaxWidth()) {
//            Picker(stateHolder = stateHolder)
//        }
//        Row(
//            modifier
//                .weight(2f)
//                .padding(bottom = 70.dp)
//                .padding(horizontal = 55.dp), verticalAlignment = Alignment.Bottom) {
//            Button(navToMain = navToMain, viewModel = viewModel, stateHolder = stateHolder)
//        }
//    }
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.getbirth_title),
            fontFamily = maplestory,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth())
        Text(text = stringResource(id = R.string.getbirth_subtitle),
            fontFamily = maplestory,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 20.dp))
    }
}

@Composable
fun Picker(modifier: Modifier = Modifier, stateHolder: PickerStateHolder) {
    Logger.d("pickercomp")
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        NumberPicker(value = stateHolder.year,
            onValueChange = { stateHolder.year = it },
            range = stateHolder.getMinYear()..stateHolder.getMaxYear(),
            dividersColor = Color.Black,
            textStyle = TextStyle(fontSize = 16.sp, fontFamily = maplestory),
            modifier = modifier)
        NumberPicker(value = stateHolder.month,
            onValueChange = {stateHolder.month = it},
            range = 1..12,
            dividersColor = Color.Black,
            textStyle = TextStyle(fontSize = 16.sp, fontFamily = maplestory),
            modifier = modifier)
        NumberPicker(value = stateHolder.day,
            onValueChange = {stateHolder.day = it},
            range = 1..stateHolder.getMaxday(),
            dividersColor = Color.Black,
            textStyle = TextStyle(fontSize = 16.sp, fontFamily = maplestory),
            modifier = modifier)
    }
}

@Composable
fun Button(modifier: Modifier = Modifier, navToMain: () -> Unit, viewModel: GetbirthViewmodel, stateHolder: PickerStateHolder) {
    Card( modifier = modifier
        .fillMaxWidth()
        .height(45.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        TextButton(onClick = { viewModel.goNext(stateHolder.year, stateHolder.month, stateHolder.day) { navToMain() } },
            modifier
                .fillMaxSize()
                .background(themecolor)) {
            Text(text = stringResource(id = R.string.getbirth_continue),
                color = Color.White,
                fontFamily = maplestory,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}



fun getContactsString(context: Context): ArrayList<PhoneData>? {
    val datas = ArrayList<PhoneData>()
    val resolver = context.contentResolver
    val phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    val projection =
        arrayOf( //        ContactsContract.CommonDataKinds.Phone.CONTACT_ID // 인덱스 값, 중복될 수 있음 -- 한 사람 번호가 여러개인 경우
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )
    val sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC"
    val cursor = resolver.query(phoneUri, projection, null, null, sortOrder)
    if (cursor != null) {
        while (cursor.moveToNext()) {
//        int idIndex = cursor.getColumnIndex(projection[0]); // 이름을 넣어주면 그 칼럼을 가져와준다.
            val nameIndex = cursor.getColumnIndex(projection[0])
            val numberIndex = cursor.getColumnIndex(projection[1])
            //        String id = cursor.getString(idIndex);
            val name = cursor.getString(nameIndex)
            val number = cursor.getString(numberIndex)

            datas.add(PhoneData(name, false))
        }
    }

    // 데이터 계열은 반드시 닫아줘야 한다.
    cursor!!.close()
    return datas
}

@Composable
@Preview
fun GetBirthPrev() {
    Getbirth()
}
