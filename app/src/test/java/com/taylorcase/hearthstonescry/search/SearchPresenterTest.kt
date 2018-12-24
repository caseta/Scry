package com.taylorcase.hearthstonescry.search

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.CardRepository
import com.taylorcase.hearthstonescry.model.Card
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.Collections.*

class SearchPresenterTest {

    private val mockView = mock<SearchContract.View>()
    private val mockCardRepo = mock<CardRepository>()

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test fun populateCardNamesCallsViewSuccessfully() {
        val cardNames = singletonList(CARD_NAME)
        doReturn(Single.just(cardNames)).whenever(mockCardRepo).observeAllCardNames()
        val presenter = demandSearchPresenter()

        presenter.populateCardNames()

        verify(mockView).setCardNames(cardNames)
        verify(mockCardRepo).observeAllCardNames()
    }

    @Test fun testPopulateNamesThrowsError() {
        val cardNames = singletonList(CARD_NAME)
        val single: Single<List<String>> = Single.error(Throwable("error"))
        doReturn(single).whenever(mockCardRepo).observeAllCardNames()
        val presenter = demandSearchPresenter()

        presenter.populateCardNames()

        verify(mockView, never()).setCardNames(cardNames)
        verify(mockView).showError()
        verify(mockCardRepo).observeAllCardNames()
    }

    @Test fun performSearchCallsViewToNavigateSuccessfully() {
        val card = Card()
        val cards = singletonList(card)
        doReturn(Observable.just(cards)).whenever(mockCardRepo).observeCard(CARD_NAME)
        val presenter = demandSearchPresenter()

        presenter.performSearch(CARD_NAME)

        verify(mockCardRepo).observeCard(CARD_NAME)
        verify(mockView).navigateToProperDetailedCard(card)
    }

    private fun demandSearchPresenter() : SearchPresenter {
        val presenter = SearchPresenter(mockCardRepo)
        presenter.attach(mockView)
        return presenter
    }

    companion object {
        private const val CARD_NAME = "Ysera"
    }
}
