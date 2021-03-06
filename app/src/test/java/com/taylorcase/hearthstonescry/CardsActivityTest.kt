package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.filter.FilterActivity
import com.taylorcase.hearthstonescry.model.FilterItem
import com.taylorcase.hearthstonescry.model.enums.League
import kotlinx.android.synthetic.main.activity_cards.*
import org.assertj.android.api.Assertions
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
open class CardsActivityTest {

    private val mockFilterItem = mock<FilterItem>()

    @Inject lateinit var mockCardsPresenter: CardsContract.Presenter

    private lateinit var activity: CardsActivity

    @Before
    fun setUp() {
        ((application as TestScryApplication).getComponent() as TestAppComponent).inject(this)
    }

    @Test
    fun testOnCreateActivityPresenterCallsAttach() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        verify(mockCardsPresenter).attach(activity)
    }

    @Test
    fun testOnResumeEnabledFilterButton() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        Assertions.assertThat(activity.cards_filter_button).isEnabled
    }

    @Test
    fun testAskToRateAppLogsUserWasAsked() {
        doReturn(true).whenever(mockCardsPresenter).shouldAskToRateApp()
        activity = buildActivity(CardsActivity::class.java).create().get()

        verify(mockCardsPresenter).userWasAskedToRateApp()
        verify(mockCardsPresenter).shouldAskToRateApp()
    }

    @Test
    fun testAskToRateAppDoesNothingIfUserHasBeenAsked() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        verify(mockCardsPresenter, never()).userWasAskedToRateApp()
        verify(mockCardsPresenter).shouldAskToRateApp()
    }

    @Test
    fun testLoadCardsCallsPresenterLoadCardsWhenFilterItemIsNull() {
        activity = buildActivity(CardsActivity::class.java).create().get()
        activity.filterItem = null

        activity.loadCards()

        verify(mockCardsPresenter).loadCards()
    }

    @Test
    fun testLoadCardsCallsLoadCardsWhenFilterIsEmpty() {
        activity = buildActivity(CardsActivity::class.java).create().get()
        activity.filterItem = FilterItem()

        activity.loadCards()

        verify(mockCardsPresenter).loadCards()
    }

    @Test
    fun testLoadCardsCallsLoadCardsWithFilterItemWhenNotEmpty() {
        activity = buildActivity(CardsActivity::class.java).create().get()
        val filterItem = FilterItem(league = League.STANDARD.toString())
        activity.filterItem = filterItem

        activity.loadCards()

        verify(mockCardsPresenter).loadCardsWithFilters(filterItem)
    }

    @Test
    fun testOnClickFilterButtonStartsFilterActivity() {
        activity = buildActivity(CardsActivity::class.java).create().get()
        activity.filterItem = mockFilterItem

        activity.cards_filter_button.performClick()

        val intent = ShadowApplication.getInstance().nextStartedActivity
        Assertions.assertThat(intent).hasComponent(application, FilterActivity::class.java).hasExtra(FilterItem.FILTER_EXTRA, mockFilterItem)
        Assertions.assertThat(activity.cards_filter_button).isDisabled
    }

    @Test
    fun testOnDestroyDetachesPresenter() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        activity.onDestroy()

        verify(mockCardsPresenter).detach()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}
