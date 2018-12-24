package com.taylorcase.hearthstonescry.savedcards

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.InjectingTest
import com.taylorcase.hearthstonescry.model.Card
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.decor_nav_drawer.*
import org.assertj.android.api.Assertions
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import java.util.Collections.*

@RunWith(RobolectricTestRunner::class)
class SavedCardsActivityTest : InjectingTest() {

    private val mockCard = mock<Card>()

    private lateinit var activity: SavedCardsActivity

    @Test fun testOnCreateActivityPresenterCallsAttach() {
        activity = buildActivity(SavedCardsActivity::class.java).create().get()

        verify(mockSavedCardsPresenter).attach(activity!!)
    }

    @Test fun testOnCreateActivitySwipeRefreshIsDisabled() {
        activity = buildActivity(SavedCardsActivity::class.java).create().get()

        Assertions.assertThat(activity.cards_swipe_refresh).isDisabled
    }

    @Test fun testLoadCardsSavedCountIsGreaterThanZeroCallsLoadSavedCards() {
        doReturn(1).whenever(mockSavedCardsPresenter)?.getSavedCardCount()
        activity = buildActivity(SavedCardsActivity::class.java).create().get()

        activity.loadCards()

        verify(mockSavedCardsPresenter).getSavedCardCount()
        verify(mockSavedCardsPresenter).loadSavedCards()
    }

    @Test fun testLoadCardsWithZeroSavedCardsShowsEmptyList() {
        doReturn(-1).whenever(mockSavedCardsPresenter)?.getSavedCardCount()
        activity = buildActivity(SavedCardsActivity::class.java).create().get()

        activity.loadCards()

        verify(mockSavedCardsPresenter).getSavedCardCount()
        verify(mockSavedCardsPresenter, never()).loadSavedCards()
        verify(mockCardsAdapter).swapData(emptyList())
        Assertions.assertThat(activity.progress_bar).isNotVisible
        Assertions.assertThat(activity.cards_no_saves).isVisible
        Assertions.assertThat(activity.cards_recycler_view).isGone
    }

    @Test fun testDisplayCardsCallsAdapterSwapData() {
        val cards = singletonList(mockCard)
        activity = buildActivity(SavedCardsActivity::class.java).create().get()

        activity.displayCards(cards)

        Assertions.assertThat(activity.progress_bar).isNotVisible
        verify(mockCardsAdapter).swapData(cards)
    }

    @Test fun testOnDestroyDetachesPresenter() {
        activity = buildActivity(SavedCardsActivity::class.java).create().get()

        activity.onDestroy()

        verify(mockSavedCardsPresenter).detach()
    }

    @After
    fun destroyActivity() {
        activity.finish()
    }
}