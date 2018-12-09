package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.selecthero.DetailedSelectHeroActivity
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

    private var activity: CardsActivity? = null
    
    @Test fun testOnCreateActivityPresenterCallsAttach() {
        activity = buildActivity(CardsActivity::class.java).create().get()

        verify(mockCardsPresenter)?.attach(activity!!)
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
