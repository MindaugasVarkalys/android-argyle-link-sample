package com.varkalys.argyle.ui.screen.link_list

import com.varkalys.argyle.model.local.Link
import com.varkalys.argyle.model.repository.LinkRepository
import com.varkalys.argyle.ui.screen.BaseViewModelTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class LinkListViewModelTest: BaseViewModelTest() {

    private val linkRepository: LinkRepository = mockk {
        coEvery { getLinks(any()) } returns listOf(Link.sample)
    }
    private lateinit var linkListViewModel: LinkListViewModel

    @Before
    fun init() = runTest {
        linkListViewModel = LinkListViewModel(linkRepository)
        testQueryResultsWhenConnectedToInternet("")
    }

    private fun TestScope.testQueryResultsWhenConnectedToInternet(querySentToRepository: String) {
        assertTrue(linkListViewModel.loading.value)
        advanceUntilIdle()
        assertEquals(listOf(Link.sample), linkListViewModel.links.value)
        assertFalse(linkListViewModel.loading.value)
        coVerify { linkRepository.getLinks(querySentToRepository) }
    }

    @Test
    fun onSearchQueryChange_empty() = runTest {
        linkListViewModel.onSearchQueryChange("")
        assertEquals("", linkListViewModel.searchQuery.value)
        testQueryResultsWhenConnectedToInternet("")
    }

    @Test
    fun onSearchQueryChange_spaces() = runTest {
        linkListViewModel.onSearchQueryChange("    ")
        assertEquals("    ", linkListViewModel.searchQuery.value)
        testQueryResultsWhenConnectedToInternet("")
    }

    @Test
    fun onSearchQueryChange_singleChar() = runTest {
        linkListViewModel.onSearchQueryChange("a")
        assertEquals("a", linkListViewModel.searchQuery.value)
        assertEquals(emptyList<Link>(), linkListViewModel.links.value)
    }

    @Test
    fun onSearchQueryChange_singleCharWithSpaces() = runTest {
        linkListViewModel.onSearchQueryChange("   a  ")
        assertEquals("   a  ", linkListViewModel.searchQuery.value)
        assertEquals(emptyList<Link>(), linkListViewModel.links.value)
    }

    @Test
    fun onSearchQueryChange_twoChars() = runTest {
        linkListViewModel.onSearchQueryChange("ab")
        assertEquals("ab", linkListViewModel.searchQuery.value)
        testQueryResultsWhenConnectedToInternet("ab")
    }

    @Test
    fun onSearchQueryChange_twoCharsWithSpaces() = runTest {
        linkListViewModel.onSearchQueryChange(" ab  ")
        assertEquals(" ab  ", linkListViewModel.searchQuery.value)
        testQueryResultsWhenConnectedToInternet("ab")
    }

    @Test
    fun onSearchQueryChange_noInternet() = runTest {
        coEvery { linkRepository.getLinks(any()) } throws IOException()
        linkListViewModel.onSearchQueryChange("ab")
        assertEquals("ab", linkListViewModel.searchQuery.value)
        assertTrue(linkListViewModel.loading.value)
        advanceUntilIdle()
        assertEquals(emptyList<Link>(), linkListViewModel.links.value)
        assertFalse(linkListViewModel.loading.value)
        coVerify { linkRepository.getLinks("ab") }
    }

    @Test
    fun onSearchQueryClear_empty() = runTest {
        linkListViewModel.onSearchQueryClear()
    }

    @Test
    fun onSearchQueryClear_notEmpty() = runTest {
        linkListViewModel.onSearchQueryChange("abc")
        testQueryResultsWhenConnectedToInternet("abc")
        linkListViewModel.onSearchQueryClear()
        assertEquals("", linkListViewModel.searchQuery.value)
        testQueryResultsWhenConnectedToInternet("")
    }

    @After
    fun tearDown() {
        confirmVerified(linkRepository)
    }
}