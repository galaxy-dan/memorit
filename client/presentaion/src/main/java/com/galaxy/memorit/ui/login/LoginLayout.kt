package com.galaxy.memorit.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.galaxy.memorit.R
import com.galaxy.memorit.ui.login.stateholder.LoginViewModel
import com.galaxy.memorit.ui.theme.PartylogTheme
import com.galaxy.memorit.ui.theme.loginTextColor
import com.galaxy.memorit.ui.theme.maplestory
import com.galaxy.memorit.util.Util

@Composable
fun Login(
    modifier: Modifier = Modifier, font: FontFamily = maplestory,
    viewModel: LoginViewModel = hiltViewModel(),
    navToMain: () -> Unit = {}, navToGetbirth: () -> Unit = {}
) {

    val loginCode = viewModel.loginCode

    AutoLogin {viewModel.autoLogin(navToMain)}


    Image(
        painter = painterResource(id = R.drawable.bg_login_compose),
        contentDescription = "bg_login",
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Fit
    )
    Column() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom
        ) {
            TitleFrame(modifier, font)
        }

        Column(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth(), verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginFrame(modifier, viewModel, loginCode, navToGetbirth, navToMain)
        }

    }

}

@Composable
fun LoginFrame(modifier: Modifier, viewModel: LoginViewModel, loginCode: Int,
               navToGetbirth: () -> Unit, navToMain: () -> Unit) {
    val context = LocalContext.current
    when(loginCode) {
        200 -> navToMain()
        201 -> navToGetbirth()
        0 -> {}
        else -> {
            Toast.makeText(LocalContext.current, "에러", Toast.LENGTH_SHORT).show()
        }
    }
    viewModel.resetCode()
    Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_login_kakaologin),
        contentDescription = "kakao",
        modifier = modifier
            .padding(bottom = 124.dp)
            .clickable {
                Util(viewModel = viewModel).kakaoLogin(context)
            })
    }


@Composable
fun TitleFrame(modifier: Modifier = Modifier, font: FontFamily) {
    Image(
        painter = painterResource(id = R.drawable.ic_login_logo), contentDescription = "login",
        modifier = modifier
            .padding(bottom = 20.dp)
            .padding(horizontal = 47.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
    Text(
        text = stringResource(id = R.string.first_greeting),
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        fontFamily = font, fontWeight = FontWeight.Light,
        color = loginTextColor,
        modifier = modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
    )

}

@Composable
fun AutoLogin(autoLogin: () -> Unit)  {
    LaunchedEffect(key1 = true) {
        autoLogin()
    }
}

@Composable
@Preview(showBackground = true)
fun Preview() {
    PartylogTheme() {
        Login()
    }
}