package com.galaxy.memorit.util

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galaxy.memorit.R
import com.galaxy.memorit.ui.getbirth.Getbirth
import com.galaxy.memorit.ui.login.Login

enum class ScreenState(@StringRes val title: Int) {
    Login(title = R.string.app_login),
    Getbirth(title = R.string.app_getBirth),
    Main(title = R.string.app_main),
    Live(title= R.string.app_live)
}

@Composable
fun PartylogApp(navHostController: NavHostController = rememberNavController()) {
    Scaffold{
        NavHost(navController = navHostController, startDestination = ScreenState.Login.name, modifier =Modifier.padding(it)) {
            composable(route = ScreenState.Login.name) {
                Login(navToMain = {
                    navHostController.popBackStack()
                    navHostController.navigate(route = ScreenState.Main.name)},
                    navToGetbirth = {
                        navHostController.navigate(route= ScreenState.Getbirth.name) })
            }
            composable(route = ScreenState.Main.name) {
                BottomNavGraph()
            }
            composable(route = ScreenState.Getbirth.name) {
                Getbirth(navToMain= {
                    navHostController.popBackStack()
                    navHostController.navigate(route = ScreenState.Main.name)})
            }

        }
    }
}