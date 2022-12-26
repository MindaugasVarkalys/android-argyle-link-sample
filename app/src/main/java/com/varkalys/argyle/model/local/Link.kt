package com.varkalys.argyle.model.local

data class Link(
    val id: String,
    val logoUrl: String?,
    val name: String,
    val category: Category,
) {

    enum class Category {
        EMPLOYER, PAYROLL_PLATFORM, GIG_PLATFORM
    }

    companion object {
        val sample = Link(
            id = "1",
            logoUrl = null,
            category = Category.EMPLOYER,
            name = "Amazon"
        )
    }
}