package com.taylorcase.hearthstonescry

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.ImageLoader
import org.assertj.android.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*

@RunWith(RobolectricTestRunner::class)
class CardsViewHolderTest {

    private val mockImageLoader = mock<ImageLoader>()
    private val mockView = mock<View>()

    @Test
    fun testLoadCardSetsOnClickListener() {
        val card = Card()
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_card, FrameLayout(application), false)
        val cardsViewHolder = CardsViewHolder(itemView, mockImageLoader)

        cardsViewHolder.loadCard(card, 0)

        assertThat(cardsViewHolder.itemView.hasOnClickListeners()).isTrue()
    }

    @Test
    fun testLoadCardSetsItemAsClickableAndEnabled() {
        val card = Card()
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_card, FrameLayout(application), false)
        val cardsViewHolder = CardsViewHolder(itemView, mockImageLoader)

        cardsViewHolder.loadCard(card, 0)

        Assertions.assertThat(cardsViewHolder.itemView).isClickable
        Assertions.assertThat(cardsViewHolder.itemView).isEnabled
    }

    @Ignore
    @Test
    fun testOnClickSetsItemAsNotClickableAndNotEnabled() {
        val card = Card(img = IMG_ID, cardId = CARD_ID)
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_card, FrameLayout(application), false)
        val cardsViewHolder = CardsViewHolder(itemView, mockImageLoader)
        cardsViewHolder.card = card
        cardsViewHolder.context = buildActivity(Activity::class.java).create().start().visible().get()

        cardsViewHolder.loadCard(card, 0)
        cardsViewHolder.itemView.performClick()

        Assertions.assertThat(cardsViewHolder.itemView).isNotClickable
        assertThat(cardsViewHolder.itemView.isEnabled).isFalse()
    }

    companion object {
        private const val IMG_ID = "id"
        private const val CARD_ID = "cardId"
    }
}
