package com.galaxy.memorit.ui.login

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.galaxy.memorit.R
import com.galaxy.memorit.ui.login.stateholder.LoginViewModel
import com.galaxy.memorit.ui.theme.PartylogTheme
import com.galaxy.memorit.ui.theme.maplestory
import com.orhanobut.logger.Logger

@Composable
fun Login(
    modifier: Modifier = Modifier, font: FontFamily = maplestory,
    viewModel: LoginViewModel = hiltViewModel(),
    navToMain: () -> Unit = {}, navToGetbirth: () -> Unit = {}
) {

    val loginCode = viewModel.loginCode

    val context = LocalContext.current

    val permissions = arrayOf(
        Manifest.permission.READ_CONTACTS
    )

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        /** 권한 요청시 동의 했을 경우 **/
        if (areGranted) {
            Logger.d("권한이 동의되었습니다.")
        }
        /** 권한 요청시 거부 했을 경우 **/
        else {
            Logger.d("권한이 거부되었습니다.")
        }
    }
    LaunchedEffect(key1 = true) {
        checkAndRequestPermissions(
            context = context, permissions = permissions, launcher = launcherMultiplePermissions
        )
    }

    AutoLogin {viewModel.autoLogin(navToMain)}

    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.orange_heart), contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    start = randLowNum().dp,
                    end = randHighNum().dp,
                    top = randHighNum().dp,
                    bottom = randLowNum().dp
                )
                .size(randCustomNum(getScreenWidth() / 4).dp),
            contentScale = ContentScale.Fit)
        Image(painter = painterResource(id = R.drawable.yellow_heart), contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = randHighNum().dp,
                    end = randLowNum().dp,
                    top = randLowNum().dp,
                    bottom = randHighNum().dp
                )
                .size(randCustomNum(getScreenWidth() / 4).dp))
        Image(painter = painterResource(id = R.drawable.red_heart), contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    start = randLowNum().dp,
                    end = randLowNum().dp,
                    top = randLowNum().dp,
                    bottom = randHighNum().dp
                )
                .size(randCustomNum(getScreenWidth() / 4).dp))
        Image(painter = painterResource(id = R.drawable.green_heart), contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(
                    start = randLowNum().dp,
                    end = randLowNum().dp,
                    top = randHighNum().dp,
                    bottom = randLowNum().dp
                )
                .size(randCustomNum(getScreenWidth() / 4).dp))
        Image(painter = painterResource(id = R.drawable.blue_heart), contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(
                    start = randLowNum().dp,
                    end = randLowNum().dp,
                    top = randHighNum().dp,
                    bottom = randLowNum().dp
                )
                .size(randCustomNum(getScreenWidth() / 4).dp))
        Image(painter = painterResource(id = R.drawable.navy_heart), contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = randHighNum().dp,
                    end = randLowNum().dp,
                    top = randLowNum().dp,
                    bottom = randHighNum().dp + randLowNum().dp
                )
                .size(randCustomNum(getScreenWidth() / 4).dp))
        Image(painter = painterResource(id = R.drawable.purple_heart), contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    start = randLowNum().dp,
                    end = randHighNum().dp,
                    top = randLowNum().dp,
                    bottom = randLowNum().dp + randLowNum().dp
                )
                .size(randCustomNum(getScreenWidth() / 4).dp))


        Column(modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(bottom = 300.dp, start = 40.dp, top = 15.dp)) {
            Title()
            SubTitle1()
            SubTitle2()
        }

        IconButton(onClick = navToGetbirth,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp)
                .fillMaxWidth()) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_login_kakaologin),
                contentDescription = null,
                tint = Color.Unspecified)
        }
    }
}

@Composable
fun Title() {
    Text(text = stringResource(id = R.string.app_name_kor),
        style = TextStyle(
            fontSize = 35.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(700),
            color = Color(0xFF000000),
            letterSpacing = 0.35.sp,
        )
    )
}

@Composable
fun SubTitle1() {
    Box() {
        Text(text = stringResource(id = R.string.first_greeting_line1),
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = Color.Black,
                letterSpacing = 0.18.sp))
        Text(text = stringResource(id = R.string.first_greeting_line1),
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = Color.Transparent,
                letterSpacing = 0.18.sp),
            modifier = Modifier
                .background(Color(0x3300B2FF))
                .height(9.dp)
                .align(Alignment.BottomCenter))
    }
}

@Composable
fun SubTitle2() {
    Box() {
        Text(
            text = stringResource(id = R.string.first_greeting_line2),
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = Color.Black,
                letterSpacing = 0.18.sp
            )
        )
        Text(
            text = stringResource(id = R.string.first_greeting_line2),
            style = TextStyle(
                fontSize = 18.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = Color.Transparent,
                letterSpacing = 0.18.sp
            ),
            modifier = Modifier
                .background(Color(0x3300B2FF))
                .height(9.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun AutoLogin(autoLogin: () -> Unit)  {
    LaunchedEffect(key1 = true) {
        autoLogin()
    }
}

fun randHighNum(): Int {
    val range = (70..100)
    return range.random()
}
fun randLowNum(): Int{
    val range = (0..30)
    return range.random()
}
fun randCustomNum(num: Int): Int {
    val range = (50..num)
    return range.random()
}
@Composable
fun getScreenWidth(): Int {
    return LocalConfiguration.current.screenWidthDp
}

fun checkAndRequestPermissions(
    context: Context,
    permissions: Array<String>,
    launcher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
) {

    /** 권한이 이미 있는 경우 **/
    if (permissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }) {
        Logger.d("권한이 이미 존재합니다.")
    }

    /** 권한이 없는 경우 **/
    else {
        launcher.launch(permissions)
        Logger.d("권한을 요청하였습니다.")
    }
}
@Composable
@Preview(showBackground = true)
fun Preview() {
    PartylogTheme() {
        Login()
    }
}