package com.varkalys.argyle.model.remote.response

data class LinkItemsResponse(
    val results: List<LinkItem>
) {

    data class LinkItem(
        val id: String,
        val logoUrl: String?,
        val name: String,
        val kind: String,
    )
}