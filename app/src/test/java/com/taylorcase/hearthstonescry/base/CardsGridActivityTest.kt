package com.taylorcase.hearthstonescry.base

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.CardsActivity
import com.taylorcase.hearthstonescry.CardsViewHolder.Companion.EXTRA_POSITION
import com.taylorcase.hearthstonescry.InjectingTest
import com.taylorcase.hearthstonescry.model.Card
import kotlinx.android.synthetic.main.activity_cards.*
import kotlinx.android.synthetic.main.decor_nav_drawer.*
import org.assertj.android.api.Assertions.*
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import java.util.Collections.*

@RunWith(RobolectricTestRunner::class)
class CardsGridActivityTest : InjectingTest() {

    private val mockCard = mock<Card>()
    private val mockRecycler = mock<RecyclerView>()

    private var activity: CardsActivity? = null

    @Test fun testOnCreateSetsLoadingToTrue() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        assertThat(activity!!.progress_bar).isVisible
    }

    @Test fun testOnCreateSetsUpRecyclerView() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        Assertions.assertThat(activity!!.adapter).isNotNull()
        Assertions.assertThat(activity!!.cards_recycler_view).isNotNull()
        Assertions.assertThat(activity!!.cards_recycler_view.layoutManager).isInstanceOf(GridLayoutManager::class.java)
        Assertions.assertThat(activity!!.cards_recycler_view.adapter).isEqualTo(activity!!.adapter)
    }

    @Test fun testShowCardsCallsAdapterSwapData() {
        val cards = singletonList(mockCard)
        activity = buildActivity(CardsActivity::class.java).create().get()

        activity!!.showCards(cards)

        verify(mockCardsAdapter)?.swapData(cards)
        assertThat(activity!!.progress_bar).isInvisible
    }

    @Test fun testOnActivityReenterScrollsRecyclerToPosition() {
        val intent = Intent().putExtra(EXTRA_POSITION, 1)
        activity = buildActivity(CardsActivity::class.java).create().get()
        activity!!.cardsRecyclerView = mockRecycler

        activity!!.onActivityReenter(RESULT_OK, intent)

        verify(mockRecycler).scrollToPosition(1)
    }

    @After
    fun destroyActivity() {
        activity!!.finish()
        activity = null
    }
}
