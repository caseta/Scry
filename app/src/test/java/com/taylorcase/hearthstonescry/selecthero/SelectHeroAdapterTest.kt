package com.taylorcase.hearthstonescry.selecthero

import android.widget.FrameLayout
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*

@RunWith(RobolectricTestRunner::class)
class SelectHeroAdapterTest {

    private val mockImageLoader = mock<ImageLoader>()
    private val mockHeroUtils = mock<HeroUtils>()
    private val mockViewHolder = mock<SelectHeroViewHolder>()

    @Test
    fun testCreateViewHolderIsNotNull() {
        val adapter = SelectHeroAdapter(mockImageLoader, mockHeroUtils)

        val viewHolder = adapter.onCreateViewHolder(FrameLayout(application), 1)

        assertThat(viewHolder).isNotNull()
    }

    @Test
    fun testOnBindViewHolderLoadsHero() {
        val adapter = SelectHeroAdapter(mockImageLoader, mockHeroUtils)

        adapter.onBindViewHolder(mockViewHolder, 0)

        verify(mockViewHolder).loadHero(WARLOCK)
    }

    @Test
    fun testGetItemCount() {
        val adapter = SelectHeroAdapter(mockImageLoader, mockHeroUtils)

        assertThat(adapter.itemCount).isEqualTo(9)
    }

    @Test
    fun testGetSelectedItem() {
        val adapter = SelectHeroAdapter(mockImageLoader, mockHeroUtils)

        assertThat(adapter.getSelectedItem(3)).isEqualTo(MAGE)
    }
}
