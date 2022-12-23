package com.varkalys.argyle.ui.component.screen

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun Screen(
    context: Context = LocalContext.current,
    viewModel: ScreenViewModel,
    content: @Composable () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(viewModel.snackbars) {
        viewModel.snackbars.collect {
            scaffoldState.snackbarHostState.showSnackbar(message = context.getString(it))
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Box(
            modifier = Modifier.padding(it)
        ) {
            content()
        }
    }
}