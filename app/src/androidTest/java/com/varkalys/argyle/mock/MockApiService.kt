package com.varkalys.argyle.mock

import com.varkalys.argyle.model.remote.ApiService
import com.varkalys.argyle.model.remote.response.LinkItemsResponse
import kotlinx.coroutines.delay

class MockApiService : ApiService {
    override suspend fun getLinks(query: String?, limit: Int): LinkItemsResponse {
        delay(REQUEST_DELAY)
        val filteredLinks =
            if (query == null) links else links.filter { it.name.lowercase().contains(query.lowercase()) }
        return LinkItemsResponse(filteredLinks.take(limit))
    }

    companion object {
        private const val REQUEST_DELAY = 1000L
        private val links = listOf(
            LinkItemsResponse.LinkItem(
                "amazon",
                "https://res.cloudinary.com/argyle-media/image/upload/v1598543068/partner-logos/amazon.png",
                "Amazon",
                LinkItemsResponse.KIND_EMPLOYER
            ),
            LinkItemsResponse.LinkItem(
                "grubhub",
                "https://res.cloudinary.com/argyle-media/image/upload/v1657284184/partner-logos/grubhub.png",
                "Grubhub",
                LinkItemsResponse.KIND_GIG_PLATFORM
            ),
            LinkItemsResponse.LinkItem(
                "doordash",
                "https://res.cloudinary.com/argyle-media/image/upload/v1602075723/partner-logos/doordash.png",
                "DoorDash",
                LinkItemsResponse.KIND_GIG_PLATFORM
            ),
            LinkItemsResponse.LinkItem(
                "gusto_platform",
                "https://res.cloudinary.com/argyle-media/image/upload/v1594718493/partner-logos/gusto_platform.png",
                "Gusto",
                LinkItemsResponse.KIND_PAYROLL_PLATFORM
            ),
            LinkItemsResponse.LinkItem(
                "homedepot",
                "https://res.cloudinary.com/argyle-media/image/upload/v1585923758/partner-logos/homedepot.png",
                "Home Depot",
                LinkItemsResponse.KIND_PAYROLL_PLATFORM
            ),
        )
    }
}