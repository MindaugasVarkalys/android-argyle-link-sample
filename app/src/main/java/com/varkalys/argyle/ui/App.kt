package com.varkalys.argyle.ui

import androidx.compose.runtime.Composable
import com.varkalys.argyle.ui.nav_graph.AppNavGraph
import com.varkalys.argyle.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        AppNavGraph()
    }
}