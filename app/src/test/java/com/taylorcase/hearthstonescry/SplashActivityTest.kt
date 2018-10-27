package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.assertj.android.api.Assertions.*
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*
import org.robolectric.shadows.ShadowApplication

@RunWith(RobolectricTestRunner::class)
class SplashActivityTest : InjectingTest() {

    private var activity: SplashActivity? = null

    @Test fun testOnCreateActivityPresenterCallsAttach() {
        activity = buildActivity(SplashActivity::class.java).create().get()

        verify(mockSplashPresenter)?.attach(activity!!)
    }

    @Test fun testOnCreateActivityLoaderIsVisible() {
        activity = buildActivity(SplashActivity::class.java).create().get()

        assertThat(activity!!.cards_loader).isVisible
    }

    @Test fun testCardsLoadedSuccessfullyCardsLoaderVisibilityGone() {
        activity = buildActivity(SplashActivity::class.java).create().get()

        activity!!.cardsLoadedSuccessfully()

        assertThat(activity!!.cards_loader).isGone
    }

    @Test fun testCardsLoadedSuccessfullyHasFavoriteHeroStartsCardsActivity() {
        doReturn(true).whenever(mockHeroUtils)?.hasFavoriteHero()
        activity = buildActivity(SplashActivity::class.java).create().get()

        activity!!.cardsLoadedSuccessfully()

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull.hasComponent(application, CardsActivity::class.java)
        verify(mockHeroUtils)?.hasFavoriteHero()
    }

    @Test fun testCardsLoadedSuccessfullyDoesNotHaveFavoriteHeroStartsSelectHeroActivity() {
        activity = buildActivity(SplashActivity::class.java).create().get()

        activity!!.cardsLoadedSuccessfully()

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull.hasComponent(application, SelectHeroActivity::class.java)
        verify(mockHeroUtils)?.hasFavoriteHero()
    }

    @Test fun testOnCreateActivityCallsPresenterLoadCards() {
        buildActivity(SplashActivity::class.java).create().get()

        verify(mockSplashPresenter)?.loadCards()
    }

    @Test fun testOnDestroyDetachesPresenter() {
        activity = buildActivity(SplashActivity::class.java).create().get()

        activity!!.onDestroy()

        verify(mockSplashPresenter)?.detach()
    }
    
    @After
    fun destroyActivity() {
        activity?.finish()
        activity = null
    }
}
