package com.varkalys.argyle.model.repository

import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.model.remote.ApiService
import com.varkalys.argyle.model.remote.response.LinkItemsResponse
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
                    category = when (it.kind) {
                        LinkItemsResponse.KIND_EMPLOYER -> Link.Category.EMPLOYER
                        LinkItemsResponse.KIND_GIG_PLATFORM -> Link.Category.GIG_PLATFORM
                        else -> Link.Category.PAYROLL_PLATFORM
                    }
                )
            }
    }
}