package com.taylorcase.hearthstonescry.selecthero

import android.view.LayoutInflater
import android.widget.FrameLayout
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.selecthero.SelectHeroViewHolder.Companion.EXTRA_HERO
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import com.taylorcase.hearthstonescry.utils.makeGone
import com.taylorcase.hearthstonescry.utils.makeVisible
import kotlinx.android.synthetic.main.item_select_hero_card.view.*
import org.assertj.android.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*
import org.robolectric.shadows.ShadowApplication
import org.robolectric.Robolectric

@RunWith(RobolectricTestRunner::class)
class SelectHeroViewHolderTest {

    private val mockImageLoader = mock<ImageLoader>()
    private val mockHeroUtils = mock<HeroUtils>()

    @Test fun testCreateSelectHeroViewHolderIsNotNull() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)

        assertThat(selectHeroViewHolder).isNotNull()
    }

    @Test fun testLoadHeroSetsClickListeners() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)

        selectHeroViewHolder.loadHero(WARLOCK)

        assertThat(itemView.hasOnClickListeners()).isTrue()
        assertThat(itemView.select_hero_description_toggle.hasOnClickListeners()).isTrue()
        assertThat(itemView.isEnabled).isTrue()
        assertThat(itemView.isClickable).isTrue()
    }

    @Test fun testLoadHeroLoadsHeroIcon() {
        doReturn(R.drawable.ic_warlock).whenever(mockHeroUtils).getHeroIconForHero(WARLOCK)
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)

        selectHeroViewHolder.loadHero(WARLOCK)

        verify(mockImageLoader).loadDrawableCenterCrop(R.drawable.ic_warlock, itemView.select_hero_icon_image)
        verify(mockHeroUtils).getHeroIconForHero(WARLOCK)
    }

    @Test fun testLoadHeroLoadsHeroImage() {
        doReturn(R.drawable.hero_warlock).whenever(mockHeroUtils).getHeroImageForHero(WARLOCK)
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)

        selectHeroViewHolder.loadHero(WARLOCK)

        verify(mockImageLoader).loadDrawableCenterCrop(R.drawable.hero_warlock, itemView.select_hero_image)
        verify(mockHeroUtils).getHeroImageForHero(WARLOCK)
    }

    @Test fun testLoadHeroSetsHeaderText() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)

        selectHeroViewHolder.loadHero(WARLOCK)

        assertThat(itemView.select_hero_header_text.text).isEqualTo(WARLOCK.toString())
    }

    @Test fun testLoadHeroSetsDescriptionText() {
        doReturn(WARLOCK_DESCRIPTION).whenever(mockHeroUtils).getDescriptionForHero(WARLOCK)
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)

        selectHeroViewHolder.loadHero(WARLOCK)

        assertThat(itemView.select_hero_sub_header_text.text).isEqualTo(WARLOCK_DESCRIPTION)
        verify(mockHeroUtils).getDescriptionForHero(WARLOCK)
    }

    @Test fun testOnClickDescriptionToggleHidesDescription() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)
        itemView.select_hero_sub_header_text.makeVisible()

        selectHeroViewHolder.onClick(itemView.select_hero_description_toggle)

        Assertions.assertThat(itemView.select_hero_sub_header_text).isGone
    }

    @Test fun testOnClickDescriptionToggleShowsDescription() {
        val itemView = LayoutInflater.from(application).inflate(R.layout.item_select_hero_card, FrameLayout(application), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)
        itemView.select_hero_sub_header_text.makeGone()

        selectHeroViewHolder.onClick(itemView.select_hero_description_toggle)

        Assertions.assertThat(itemView.select_hero_sub_header_text).isVisible
    }

    @Test fun testOnImageClickStartsDetailedSelectHeroActivity() {
        val activity = Robolectric.buildActivity(SelectHeroActivity::class.java).create().get()
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item_select_hero_card, FrameLayout(activity), false)
        val selectHeroViewHolder = SelectHeroViewHolder(itemView, mockImageLoader, mockHeroUtils)

        selectHeroViewHolder.loadHero(WARLOCK)
        selectHeroViewHolder.onClick(itemView.select_hero_image)

        val intent = ShadowApplication.getInstance().nextStartedActivity
        Assertions.assertThat(intent).isNotNull.hasComponent(activity, DetailedSelectHeroActivity::class.java).hasExtra(EXTRA_HERO, WARLOCK.toString())
    }

    companion object {
        private const val WARLOCK_DESCRIPTION = "Wow, I love warlocks!"
    }
}
