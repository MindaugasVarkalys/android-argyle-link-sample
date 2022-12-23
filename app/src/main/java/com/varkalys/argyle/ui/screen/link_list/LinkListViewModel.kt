package com.varkalys.argyle.ui.screen.link_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.model.repository.LinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinkListViewModel @Inject constructor(
    private val linkRepository: LinkRepository
) : ViewModel() {

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
            _links.value = emptyList()
            return
        }
        queryResults()
    }

    private fun queryResults() {
        val query = _searchQuery.value.trim()
        _loading.value = true
        viewModelScope.launch {
            val results = linkRepository.getLinks(query)
            if (query != _searchQuery.value.trim()) {  // Query changed during request, so ignore results
                return@launch
            }
            _links.value = results
            _loading.value = false
        }
    }

    fun onSearchQueryClear() {
        if (_searchQuery.value.isEmpty()) {
            return
        }
        _searchQuery.value = ""
        queryResults()
    }
}