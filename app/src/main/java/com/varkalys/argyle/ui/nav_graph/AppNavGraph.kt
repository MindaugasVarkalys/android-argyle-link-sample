package com.varkalys.argyle.ui.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.varkalys.argyle.ui.screen.link_list.LinkListScreen

const val LINK_LIST = "link/list"

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = LINK_LIST
    ) {
        composable(LINK_LIST) {
            LinkListScreen()
        }
    }
}
