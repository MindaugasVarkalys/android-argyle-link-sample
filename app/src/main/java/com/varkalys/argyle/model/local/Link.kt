package com.varkalys.argyle.model.local

data class Link(
    val id: String,
    val logoUrl: String?,
    val name: String,
    val kind: String,
) {

    companion object {
        val sample = Link(
            id = "1",
            logoUrl = null,
            kind = "Employer",
            name = "Amazon"
        )
    }
}