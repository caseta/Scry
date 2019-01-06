package com.taylorcase.hearthstonescry.savedcards

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.*
import com.taylorcase.hearthstonescry.model.Card
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.decor_nav_drawer.*
import org.assertj.android.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.util.Collections.*
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class SavedCardsActivityTest {

    @Inject lateinit var mockSavedCardsPresenter: SavedCardsContract.Presenter
    @Inject lateinit var mockCardsAdapter: CardsAdapter

    private lateinit var activity: SavedCardsActivity

    @Before
    fun setUp() {
        ((RuntimeEnvironment.application as TestScryApplication).getComponent() as TestAppComponent).inject(this)
        activity = buildActivity(SavedCardsActivity::class.java).create().get()
    }

    @Test
    fun testOnCreateActivityPresenterCallsAttach() {
        verify(mockSavedCardsPresenter).attach(activity)
    }

    @Test
    fun testOnCreateActivitySwipeRefreshIsDisabled() {
        Assertions.assertThat(activity.cards_swipe_refresh).isDisabled
    }

    @Test
    fun testLoadCardsSavedCountIsGreaterThanZeroCallsLoadSavedCards() {
        doReturn(1).whenever(mockSavedCardsPresenter)?.getSavedCardCount()

        activity.loadCards()

        verify(mockSavedCardsPresenter).getSavedCardCount()
        verify(mockSavedCardsPresenter).loadSavedCards()
    }

    @Test
    fun testLoadCardsWithZeroSavedCardsShowsEmptyList() {
        doReturn(-1).whenever(mockSavedCardsPresenter)?.getSavedCardCount()

        activity.loadCards()

        verify(mockSavedCardsPresenter).getSavedCardCount()
        verify(mockSavedCardsPresenter, never()).loadSavedCards()
        verify(mockCardsAdapter).swapData(emptyList())
        Assertions.assertThat(activity.progress_bar).isNotVisible
        Assertions.assertThat(activity.cards_no_saves).isVisible
        Assertions.assertThat(activity.cards_recycler_view).isGone
    }

    @Test
    fun testDisplayCardsCallsAdapterSwapData() {
        val cards = singletonList(Card())

        activity.displayCards(cards)

        Assertions.assertThat(activity.progress_bar).isNotVisible
        verify(mockCardsAdapter).swapData(cards)
    }

    @Test
    fun testOnDestroyDetachesPresenter() {
        activity.onDestroy()

        verify(mockSavedCardsPresenter).detach()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}