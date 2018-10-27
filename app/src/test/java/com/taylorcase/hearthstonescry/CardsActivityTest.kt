package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.model.Card
import kotlinx.android.synthetic.main.activity_cards.*
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.*
import org.robolectric.RobolectricTestRunner
import java.util.Collections.*

@RunWith(RobolectricTestRunner::class)
open class CardsActivityTest : InjectingTest() {

    private val mockCard = mock<Card>()

    private var activity: CardsActivity? = null
    
    @Test fun testOnCreateActivityPresenterCallsAttach() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        verify(mockCardsPresenter)?.attach(activity!!)
    }

    @Test fun testDisplayCardsSetsRefreshingToFalse() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        activity!!.displayCards(singletonList(mockCard))

        assertThat(activity!!.cards_swipe_refresh.isRefreshing).isFalse()
    }

    @Test fun testOnDestroyDetachesPresenter() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        activity!!.onDestroy()

        verify(mockCardsPresenter)?.detach()
    }

    @After
    fun destroyActivity() {
        activity!!.finish()
        activity = null
    }
}
