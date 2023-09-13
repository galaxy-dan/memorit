package com.galaxy.memorit.ui.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.galaxy.memorit.R
import com.galaxy.memorit.ui.main.stateholder.ChipState
import com.galaxy.memorit.ui.theme.maplestory
import com.galaxy.memorit.ui.theme.themecolor
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@Composable
fun MainLayout(modifier: Modifier = Modifier, viewmodel: MainViewmodel = hiltViewModel()) {


//    val url = "www.naver.com"
    val url = "http://moku--moku.vercel.app/"
    val webViewNavigator = rememberWebViewNavigator()
    val webViewState = rememberWebViewState(url = url)
    WebView(state = webViewState,
        navigator = webViewNavigator)
    BackHandler {
        if (webViewNavigator.canGoBack) {
            webViewNavigator.navigateBack()
        }
        else {

        }
    }




//    Column(modifier = modifier
//        .fillMaxSize()
//        .padding(horizontal = 16.dp)
//        .padding(top = 10.dp)) {
//        Column(modifier = modifier.weight(3f)) {
//            Row(modifier = Modifier.weight(1f)) {
//                Header()
//            }
//            Row(modifier = Modifier.weight(3f)) {
//                Info()
//            }
//            Row(modifier = Modifier.weight(1f)) {
//                InfoButtons()
//            }
//
//            Divider(modifier = Modifier.height(2.dp))
//        }
//        Column(modifier = modifier.weight(5f)) {
//        }
//    }
}

@Composable
fun Header() {
    Row(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = "logo",
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopStart)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.menu),
                contentDescription = "menu")
        }
    }
}

@Composable
fun Info() {
    Row {
        Column(modifier = Modifier
            .weight(2f)
            .fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.default_profile),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center)
        }
        Column(modifier = Modifier.weight(3f)) {
            DetailInfo()
        }
    }
}

@Composable
fun DetailInfo() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center) {
            Text(text = "testname",
                fontFamily = maplestory,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center)
        }
        Row(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "#testId",
            fontFamily = maplestory,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light)
        }
        Row(modifier = Modifier
            .weight(2f)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Follower()
            Followee()
        }
    }
}

@Composable
fun Follower() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("000",
            fontFamily = maplestory,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light)
        Text(text = stringResource(id = R.string.main_follower),
            fontFamily = maplestory,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light)
    }
}

@Composable
fun Followee() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("000",
            fontFamily = maplestory,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light)
        Text(text = stringResource(id = R.string.main_following),
            fontFamily = maplestory,
            fontSize = 20.sp,
            fontWeight = FontWeight.Light)
    }
}

@Composable
fun InfoButtons() {
    Row {
        Column(modifier = Modifier
            .weight(4.5f)
            .fillMaxSize()
            .padding(horizontal = 7.dp)
            .padding(vertical = 10.dp)) {
            Card(
                shape = RoundedCornerShape(999.dp),
                elevation = 4.dp,
                backgroundColor = themecolor,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = stringResource(id = R.string.main_follow),
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = maplestory,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Column(modifier = Modifier
            .weight(1.5f)
            .fillMaxSize()
            .padding(horizontal = 7.dp)
            .padding(vertical = 10.dp)) {
            Card (
            shape = RoundedCornerShape(999.dp),
            elevation = 4.dp,
            modifier = Modifier.fillMaxSize()
            ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.main_share),
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = maplestory,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center)
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chips(datas: List<ChipState>) {
    LazyRow() {
//        FilterChip(
//            selected = true,
//            onClick = {
//                Logger.d("filteredClick")
//            },
//            label =  {
//                Text(text = "test")
//            }
//        )
    }
}

@Composable
fun Content() {

}

@Composable
@Preview
fun MainLayoutPreview() {
    MainLayoutPreview()
}