package com.taylorcase.hearthstonescry.search

import android.view.LayoutInflater
import android.widget.FrameLayout
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.R
import org.assertj.android.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*

@RunWith(RobolectricTestRunner::class)
class SearchViewHolderTest {

    companion object {
        private const val CARD_NAME = "Ysera"
    }
    
    private val mockSuggestionListener = mock<SearchViewHolder.OnSuggestionClickListener>()

    @Test fun testCreateSetsTextViewWithClickListener() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_search_suggestion, FrameLayout(application), false)
        val searchViewHolder = SearchViewHolder(itemView)

        assertThat(searchViewHolder).isNotNull()
    }

    @Test fun testSetSuggestionListener() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_search_suggestion, FrameLayout(application), false)
        val searchViewHolder = SearchViewHolder(itemView)
        assertThat(searchViewHolder.listener).isNull()

        searchViewHolder.setOnSuggestionClickListener(mockSuggestionListener)

        assertThat(searchViewHolder.listener).isNotNull()
    }

    @Test fun testSetText() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_search_suggestion, FrameLayout(application), false)
        val searchViewHolder = SearchViewHolder(itemView)

        searchViewHolder.setText(CARD_NAME)

        Assertions.assertThat(searchViewHolder.searchSuggestionTextView).containsText(CARD_NAME)
    }

    @Test fun testOnClickCallsListenerSuggestionClicked() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_search_suggestion, FrameLayout(application), false)
        val searchViewHolder = SearchViewHolder(itemView)
        searchViewHolder.setOnSuggestionClickListener(mockSuggestionListener)
        searchViewHolder.setText(CARD_NAME)

        searchViewHolder.onClick(null)

        verify(mockSuggestionListener).onSuggestionClicked(CARD_NAME)
    }
}
