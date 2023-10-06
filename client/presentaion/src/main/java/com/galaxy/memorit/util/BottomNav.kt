package com.galaxy.memorit.util

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.galaxy.memorit.R
import com.galaxy.memorit.ui.friend.FriendLayout
import com.galaxy.memorit.ui.main.MainLayout
import com.galaxy.memorit.ui.search.SearchLayout
import com.galaxy.memorit.ui.theme.bottomNavColor
import com.galaxy.memorit.ui.theme.maplestory

sealed class BottomNavItem(
    val title: Int, val icon: Int, val route: String
) {
    private data class BottomNavItemRoute(
        val main: String = "main",
        val search: String = "search",
        val friend: String = "friend")

    object Main: BottomNavItem(R.string.app_main, R.drawable.main, BottomNavItemRoute().main)
    object Search: BottomNavItem(R.string.app_search, R.drawable.search, BottomNavItemRoute().search)
    object Friend: BottomNavItem(R.string.app_friend, R.drawable.friend, BottomNavItemRoute().friend)
}

@Composable
fun BottomNavGraph(navHostController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomNavigation(navHostController = navHostController) }
    ) {
        NavHost(navController = navHostController, startDestination = BottomNavItem.Main.route,  modifier =Modifier.padding(it)) {
            composable(BottomNavItem.Main.route) {
                MainLayout()
            }
            composable(BottomNavItem.Search.route) {
                SearchLayout()
            }
            composable(BottomNavItem.Friend.route) {
                FriendLayout()
            }
        }

    }
}


@Composable
fun BottomNavigation(navHostController: NavHostController) {
    val bottomNavItems = listOf<BottomNavItem>(
        BottomNavItem.Main,
        BottomNavItem.Search,
        BottomNavItem.Friend
    )

    NavigationBar(
        containerColor = bottomNavColor
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        bottomNavItems.forEach {it ->
            val selected = currentRoute == it.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navHostController.navigate(it.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = stringResource(id = it.title)
                    )
                },
                label = {
                    Text(text = stringResource(id = it.title),
                        fontSize = 12.sp,
                        fontFamily = maplestory,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Light,
                        color = Color.Black)
                },
                alwaysShowLabel = true,
                enabled = true
                )
        }
    }
}