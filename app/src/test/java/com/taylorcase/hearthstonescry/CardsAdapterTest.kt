package com.taylorcase.hearthstonescry

import android.widget.FrameLayout
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.ImageLoader
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*
import java.util.Collections.*

@RunWith(RobolectricTestRunner::class)
class CardsAdapterTest {

    private val mockImageLoader = mock<ImageLoader>()
    private val mockViewHolder = mock<CardsViewHolder>()

    @Test
    fun testCreateViewHolderIsNotNull() {
        val adapter = CardsAdapter(mockImageLoader)

        val viewHolder = adapter.onCreateViewHolder(FrameLayout(application), 1)

        assertThat(viewHolder).isNotNull()
    }

    @Test
    fun testOnBindViewHolderCallsLoadCard() {
        val card = Card()
        val adapter = CardsAdapter(mockImageLoader)
        adapter.swapData(singletonList(card))

        adapter.onBindViewHolder(mockViewHolder, 0)

        verify(mockViewHolder).loadCard(card, 0)
    }

    @Test
    fun testGetItemCount() {
        val card = Card()
        val adapter = CardsAdapter(mockImageLoader)

        adapter.swapData(listOf(card, card))

        assertThat(adapter.itemCount).isEqualTo(2)
    }

    @Test
    fun testSwapData() {
        val card = Card()
        val adapter = CardsAdapter(mockImageLoader)

        assertThat(adapter.cards).hasSize(0)

        adapter.swapData(singletonList(card))

        assertThat(adapter.cards).hasSize(1)
    }
}
