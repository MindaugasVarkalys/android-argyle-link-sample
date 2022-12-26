package com.varkalys.argyle.model.repository

import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.model.remote.ApiService
import com.varkalys.argyle.model.remote.response.LinkItemsResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class LinkRepositoryTest {

    private val api: ApiService = mockk {
        coEvery { getLinks(any()) } returns LinkItemsResponse(
            listOf(
                LinkItemsResponse.LinkItem(
                    "1",
                    "https://google.com/logo.png",
                    "Google",
                    LinkItemsResponse.KIND_EMPLOYER
                ),
                LinkItemsResponse.LinkItem(
                    "2",
                    "https://amazon.com/logo.png",
                    "Amazon",
                    LinkItemsResponse.KIND_GIG_PLATFORM
                ),
                LinkItemsResponse.LinkItem(
                    "3",
                    "https://facebook.com/logo.png",
                    "Facebook",
                    LinkItemsResponse.KIND_PAYROLL_PLATFORM
                ),
            )
        )
    }
    private val linkRepository = LinkRepository(api)

    @Test
    fun getLinks_queryEmpty() = runBlocking {
        val links = linkRepository.getLinks("")
        assertEquals(
            listOf(
                Link(
                    "1",
                    "https://google.com/logo.png",
                    "Google",
                    Link.Category.EMPLOYER
                ),
                Link(
                    "2",
                    "https://amazon.com/logo.png",
                    "Amazon",
                    Link.Category.GIG_PLATFORM
                ),
                Link(
                    "3",
                    "https://facebook.com/logo.png",
                    "Facebook",
                    Link.Category.PAYROLL_PLATFORM
                ),
            ),
            links
        )
        coVerify { api.getLinks(null) }
    }

    @Test
    fun getLinks_queryNotEmpty() = runBlocking {
        val links = linkRepository.getLinks("abc")
        assertEquals(
            listOf(
                Link(
                    "1",
                    "https://google.com/logo.png",
                    "Google",
                    Link.Category.EMPLOYER
                ),
                Link(
                    "2",
                    "https://amazon.com/logo.png",
                    "Amazon",
                    Link.Category.GIG_PLATFORM
                ),
                Link(
                    "3",
                    "https://facebook.com/logo.png",
                    "Facebook",
                    Link.Category.PAYROLL_PLATFORM
                ),
            ),
            links
        )
        coVerify { api.getLinks("abc") }
    }

    @After
    fun tearDown() {
        confirmVerified(api)
    }
}