package com.taylorcase.hearthstonescry.base

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.*
import com.taylorcase.hearthstonescry.savedcards.SavedCardsActivity
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.ImageLoader
import kotlinx.android.synthetic.main.fragment_nav_drawer.*
import org.assertj.android.api.Assertions.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*
import org.robolectric.shadows.ShadowApplication
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class NavDrawerFragmentTest : FragmentTest() {

    @Inject lateinit var mockHeroUtils: HeroUtils
    @Inject lateinit var mockImageLoader: ImageLoader

    @Before
    fun setUp() {
        ((application as TestScryApplication).getComponent() as TestAppComponent).inject(this)
    }

    @Test
    fun testOnStartLoadsHeroIcon() {
        doReturn(R.drawable.ic_warlock).whenever(mockHeroUtils)?.getFavoriteHeroIcon()
        val fragment = startFragment(NavDrawerFragment())

        verify(mockHeroUtils).getFavoriteHeroIcon()
        verify(mockImageLoader).loadDrawableCenterCrop(R.drawable.ic_warlock, fragment.nav_drawer_hero_icon)
    }

    @Test
    fun testOnStartLoadsHeroImage() {
        doReturn(R.drawable.hero_warlock).whenever(mockHeroUtils)?.getFavoriteHeroImage()
        val fragment = startFragment(NavDrawerFragment())

        verify(mockHeroUtils).getFavoriteHeroIcon()
        verify(mockImageLoader).loadDrawableCenterInside(R.drawable.hero_warlock, fragment.nav_drawer_hero_image)
    }

    @Test
    fun testOnHomeClickStartsCardsActivity() {
        val fragment = startFragment(NavDrawerFragment()) as NavDrawerFragment

        fragment.onClick(fragment.nav_drawer_home)

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull.hasComponent(application, CardsActivity::class.java)
    }

    @Test
    fun testOnUpdateHeroClickStartsSelectHeroActivity() {
        val fragment = startFragment(NavDrawerFragment()) as NavDrawerFragment

        fragment.onClick(fragment.nav_drawer_update_favorite_hero)

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull.hasComponent(application, SelectHeroActivity::class.java)
    }

    @Test
    fun testOnSavedCardsClickStartsSavedCardsActivity() {
        val fragment = startFragment(NavDrawerFragment()) as NavDrawerFragment

        fragment.onClick(fragment.nav_drawer_saved_cards)

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull.hasComponent(application, SavedCardsActivity::class.java)
    }
}
