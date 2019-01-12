package com.taylorcase.hearthstonescry.savedcards

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.BasePresenterTest
import com.taylorcase.hearthstonescry.CardRepository
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.Collections.singletonList

class SavedCardsPresenterTest : BasePresenterTest() {

    private val mockView = mock<SavedCardsContract.View>()
    private val mockSharedPref = mock<SharedPreferencesHelper>()
    private val mockCardsRepo = mock<CardRepository>()

    @Test
    fun testGetSavedCardCountCallsSharedPreferencesHelper() {
        val presenter = demandPresenter()

        presenter.getSavedCardCount()

        verify(mockSharedPref).getSavedCardsCount()
    }

    @Test
    fun testLoadSavedCardsDisplaysCardsSuccessfully() {
        val card = Card()
        val secondCard = Card()
        val list = singletonList(card)
        val secondList = singletonList(secondCard)
        doReturn(Single.just(list)).whenever(mockCardsRepo).observeAllCards()
        doReturn(secondList).whenever(mockSharedPref).getSavedCards(list)
        val presenter = demandPresenter()

        presenter.loadSavedCards()

        verify(mockCardsRepo).observeAllCards()
        verify(mockSharedPref).getSavedCards(list)
        verify(mockView).displayCards(secondList)
    }

    @Test
    fun testLoadSavedCardsShowsErrorProperly() {
        val error = IllegalStateException()
        val single: Single<List<Card>> = Single.error(error)
        doReturn(single).whenever(mockCardsRepo).observeAllCards()
        val presenter = demandPresenter()

        presenter.loadSavedCards()

        verify(mockCardsRepo).observeAllCards()
        verify(mockSharedPref, never()).getSavedCards(any())
        verify(mockView, never()).displayCards(any())
    }

    private fun demandPresenter(): SavedCardsPresenter {
        val presenter = SavedCardsPresenter(mockSharedPref, mockCardsRepo, mockScheduleComposer)
        presenter.attach(mockView)
        return presenter
    }
}
