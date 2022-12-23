package com.varkalys.argyle.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.varkalys.argyle.R
import com.varkalys.argyle.ui.theme.Grey

@Composable
fun UrlImage(modifier: Modifier = Modifier, url: String?) {
    val error = remember { mutableStateOf(false) }
    if (!error.value) {
        AsyncImage(
            modifier = modifier,
            contentDescription = null,
            model = url,
            onError = {
                error.value = true
            }
        )
    } else {
        Box(
            modifier = modifier.background(Grey),
            contentAlignment = Alignment.Center
        ) {
            Icon(painterResource(id = R.drawable.ic_broken_image), contentDescription = null)
        }
    }
}