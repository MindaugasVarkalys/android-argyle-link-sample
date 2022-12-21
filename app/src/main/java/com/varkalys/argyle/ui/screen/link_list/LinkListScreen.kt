package com.varkalys.argyle.ui.screen.link_list

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.varkalys.argyle.ui.theme.AppTheme

@Composable
fun LinkListScreen() {
    Text("Hello Argyle Link list!")
}

@Composable
@Preview
private fun LinkListScreenPreview() {
    AppTheme {
        LinkListScreen()
    }
}