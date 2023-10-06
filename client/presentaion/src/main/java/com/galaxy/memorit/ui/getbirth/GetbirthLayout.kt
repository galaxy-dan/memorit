package com.galaxy.memorit.ui.getbirth

import android.content.Context
import android.provider.ContactsContract
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.galaxy.domain.model.getPhone.req.PhoneData
import com.galaxy.memorit.R
import com.galaxy.memorit.ui.getbirth.stateholder.PickerStateHolder
import com.galaxy.memorit.ui.theme.checkedColor
import com.galaxy.memorit.ui.theme.fabColor
import com.galaxy.memorit.ui.theme.maplestory
import com.galaxy.memorit.ui.theme.uncheckedColor
import com.orhanobut.logger.Logger


@Composable
fun Getbirth(modifier: Modifier = Modifier.fillMaxSize(), viewModel: GetbirthViewmodel = hiltViewModel(),
             navToMain: () -> Unit = {}) {
    val stateHolder = PickerStateHolder()
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        val data = getContactsString(context)
        Logger.d(data)
        viewModel.setPhoneData(data)
    }


    Box(modifier = Modifier.fillMaxWidth()) {
        PhoneList(viewModel.phoneData, viewModel)
        ExtendedFloatingActionButton(onClick = navToMain,
            shape = FloatingActionButtonDefaults.extendedFabShape,
            elevation = FloatingActionButtonDefaults.elevation(),
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(bottom = 35.dp, end = 7.dp),
            containerColor = fabColor) {
            Icon(painter = painterResource(id = R.drawable.ic_getcontact_next), contentDescription = null)
            Text(text = "선택완료")
        }
    }
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
fun PhoneList(data: List<PhoneData>, viewModel: GetbirthViewmodel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
//            .horizontalScroll(rememberScrollState())
            .padding(start = 30.dp, end = 30.dp, top = 10.dp)
    ) {
        itemsIndexed(data) { idx, item ->
            RowItemCard(item, idx, viewModel)
        }
    }
}

@Composable
fun RowItemCard(data: PhoneData, idx: Int, viewModel: GetbirthViewmodel) {




        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    viewModel.setPhoneDataChecked(idx, data.checked.not())
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = data.name)
            Checkbox(checked = viewModel.getPhoneData(idx).checked, onCheckedChange = {
                Logger.d(it)
                Logger.d(data)
                viewModel.setPhoneDataChecked(idx, it)
            },
                colors = CheckboxDefaults.colors(
                    checkedColor = checkedColor,
                    uncheckedColor = uncheckedColor,
                    checkmarkColor = Color.White
                )
            )
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