package com.varkalys.argyle.model.repository

import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.model.remote.ApiService
import javax.inject.Inject

class LinkRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getLinks(query: String): List<Link> {
        return api.getLinks(query.ifEmpty { null })
            .results.map {
                Link(
                    id = it.id,
                    logoUrl = it.logoUrl,
                    name = it.name,
                    kind = it.kind.replaceFirstChar { char -> char.titlecase() }
                )
            }
    }
}