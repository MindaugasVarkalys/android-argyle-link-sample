package com.varkalys.argyle.ui.screen.link_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.varkalys.argyle.R
import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.ui.component.screen.Screen
import com.varkalys.argyle.ui.theme.AppTheme

@Composable
fun LinkListScreen(viewModel: LinkListViewModel = hiltViewModel()) {
    Screen(viewModel = viewModel) {
        LinkListScreenContent(
            searchQuery = viewModel.searchQuery.collectAsState().value,
            onSearchQueryChange = viewModel::onSearchQueryChange,
            onClearQueryClick = viewModel::onSearchQueryClear,
            loading = viewModel.loading.collectAsState().value,
            links = viewModel.links.collectAsState().value
        )
    }
}

@Composable
private fun LinkListScreenContent(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onClearQueryClick: () -> Unit,
    loading: Boolean,
    links: List<Link>
) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .testTag("query")
                .fillMaxWidth()
                .padding(16.dp),
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            trailingIcon = {
                IconButton(
                    modifier = Modifier.testTag("clear"),
                    onClick = onClearQueryClick
                ) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            },
        )
        when {
            loading -> Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            links.isNotEmpty() -> LazyColumn {
                items(links, key = { it.id }) {
                    LinkListItem(link = it)
                }
            }
            else -> Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = if (searchQuery.trim().length == 1) R.string.enter_at_least_two_chars else R.string.no_results),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LinkListScreenPreview() {
    AppTheme {
        LinkListScreenContent(
            searchQuery = "",
            onSearchQueryChange = {},
            onClearQueryClick = {},
            loading = false,
            links = listOf(Link.sample)
        )
    }
}