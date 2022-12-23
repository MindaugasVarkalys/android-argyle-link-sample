package com.varkalys.argyle.ui.screen.link_list

import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.model.repository.LinkRepository
import com.varkalys.argyle.ui.component.screen.ScreenViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LinkListViewModel @Inject constructor(
    private val linkRepository: LinkRepository
) : ScreenViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading as StateFlow<Boolean>

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery as StateFlow<String>

    private val _links = MutableStateFlow<List<Link>>(emptyList())
    val links = _links as StateFlow<List<Link>>

    init {
        queryResults()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        if (query.trim().length == 1) {
            clearResults()
            return
        }
        queryResults()
    }

    private fun clearResults() {
        _links.value = emptyList()
    }

    private fun queryResults() {
        val query = _searchQuery.value.trim()
        _loading.value = true
        launchNetworkRequest(
            request = {
                val results = linkRepository.getLinks(query)
                if (query != _searchQuery.value.trim()) {  // Query changed during request, so ignore results
                    return@launchNetworkRequest
                }
                _links.value = results
                _loading.value = false
            },
            onError = {
                _loading.value = false
                clearResults()
            }
        )
    }

    fun onSearchQueryClear() {
        if (_searchQuery.value.isEmpty()) {
            return
        }
        _searchQuery.value = ""
        queryResults()
    }
}