package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.selecthero.SelectHeroActivity
import com.taylorcase.hearthstonescry.utils.HeroUtils
import kotlinx.android.synthetic.main.activity_splash.*
import org.assertj.android.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.RuntimeEnvironment.*
import org.robolectric.shadows.ShadowApplication
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class SplashActivityTest {

    @Inject lateinit var mockSplashPresenter: SplashContract.Presenter
    @Inject lateinit var mockHeroUtils: HeroUtils

    private lateinit var activity: SplashActivity

    @Before
    fun setUp() {
        ((application as TestScryApplication).getComponent() as TestAppComponent).inject(this)
        activity = buildActivity(SplashActivity::class.java).create().get()
    }

    @Test
    fun testOnCreateActivityPresenterCallsAttach() {
        verify(mockSplashPresenter).attach(activity)
    }

    @Test
    fun testOnCreateActivityLoaderIsVisible() {
        assertThat(activity.cards_loader).isVisible
    }

    @Test
    fun testCardsLoadedSuccessfullyCardsLoaderVisibilityGone() {
        activity.cardsLoadedSuccessfully()

        assertThat(activity.cards_loader).isGone
    }

    @Test
    fun testCardsLoadedSuccessfullyHasFavoriteHeroStartsCardsActivity() {
        doReturn(true).whenever(mockHeroUtils)?.hasFavoriteHero()

        activity.cardsLoadedSuccessfully()

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull.hasComponent(application, CardsActivity::class.java)
        verify(mockHeroUtils).hasFavoriteHero()
    }

    @Test
    fun testCardsLoadedSuccessfullyDoesNotHaveFavoriteHeroStartsSelectHeroActivity() {
        activity.cardsLoadedSuccessfully()

        val intent = ShadowApplication.getInstance().nextStartedActivity
        assertThat(intent).isNotNull.hasComponent(application, SelectHeroActivity::class.java)
        verify(mockHeroUtils).hasFavoriteHero()
    }

    @Test
    fun testOnCreateActivityCallsPresenterLoadCards() {
        verify(mockSplashPresenter).loadCards()
    }

    @Test
    fun testOnDestroyDetachesPresenter() {
        activity.onDestroy()

        verify(mockSplashPresenter).detach()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}
