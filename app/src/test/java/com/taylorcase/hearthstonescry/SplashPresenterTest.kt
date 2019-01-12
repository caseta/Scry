package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.SchedulerComposer
import com.taylorcase.hearthstonescry.utils.SchedulerProvider
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.Collections.*

class SplashPresenterTest : BasePresenterTest() {

    private val mockView = mock<SplashContract.View>()
    private val mockCardRepo = mock<CardRepository>()

    @Test
    fun testLoadCardsRefreshCardsFalseCallsLoadSuccess() {
        val presenter = demandSplashPresenter()

        presenter.loadCards()

        verify(mockView).cardsLoadedSuccessfully()
        verify(mockCardRepo).refreshCards()
    }

    @Test
    fun testLoadCardsRefreshCardsCallsViewLoadCardsSuccess() {
        doReturn(true).whenever(mockCardRepo).refreshCards()
        doReturn(Single.just(singletonList(Card()))).whenever(mockCardRepo).observeAllCardsWithApi()
        val presenter = demandSplashPresenter()

        presenter.loadCards()

        verify(mockView).cardsLoadedSuccessfully()
        verify(mockCardRepo).refreshCards()
    }

    private fun demandSplashPresenter(): SplashPresenter {
        val presenter = SplashPresenter(mockCardRepo, mockScheduleComposer)
        presenter.attach(mockView)
        return presenter
    }
}
