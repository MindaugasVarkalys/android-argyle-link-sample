package com.varkalys.argyle.ui.screen.link_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.ui.component.UrlImage
import com.varkalys.argyle.ui.theme.AppTheme

@Composable
fun LinkListItem(link: Link) {
    Row(
        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
    ) {
        UrlImage(modifier = Modifier.size(64.dp), url = link.logoUrl)
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(link.name, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
            Text(link.kind, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LinkListItemPreview() {
    AppTheme {
        LinkListItem(
            link = Link.sample
        )
    }
}