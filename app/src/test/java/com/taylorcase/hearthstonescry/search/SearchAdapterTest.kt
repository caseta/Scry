package com.taylorcase.hearthstonescry.search

import android.widget.FrameLayout
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*
import java.util.Collections.*

@RunWith(RobolectricTestRunner::class)
class SearchAdapterTest {

    private val mockViewHolder = mock<SearchViewHolder>()
    private val mockListener = mock<SearchViewHolder.OnSuggestionClickListener>()

    @Test
    fun testCreateViewHolderIsNotNull() {
        val adapter = SearchAdapter()

        val viewHolder = adapter.onCreateViewHolder(FrameLayout(application), 1)

        assertThat(viewHolder).isNotNull()
    }

    @Test
    fun testOnBindViewHolderSetsSuggestionClickListener() {
        val adapter = SearchAdapter()
        adapter.listener = mockListener
        adapter.swapData(singletonList(CARD_NAME))

        adapter.onBindViewHolder(mockViewHolder, 0)

        assertThat(adapter.listener).isEqualTo(mockListener)
    }

    @Test
    fun testGetItemCount() {
        val adapter = SearchAdapter()

        adapter.swapData(listOf(CARD_NAME, CARD_NAME))

        assertThat(adapter.itemCount).isEqualTo(2)
    }

    @Test
    fun testSwapData() {
        val adapter = SearchAdapter()

        assertThat(adapter.itemCount).isEqualTo(0)

        adapter.swapData(singletonList(CARD_NAME))

        assertThat(adapter.itemCount).isEqualTo(1)
    }

    companion object {
        private const val CARD_NAME = "Ysera"
    }
}