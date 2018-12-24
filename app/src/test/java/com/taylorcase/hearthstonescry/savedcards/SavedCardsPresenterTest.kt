package com.taylorcase.hearthstonescry.savedcards

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
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

class SavedCardsPresenterTest {

    private val mockView = mock<SavedCardsContract.View>()
    private val mockSharedPref = mock<SharedPreferencesHelper>()
    private val mockCardsRepo = mock<CardRepository>()

    @Before fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test fun testGetSavedCardCountCallsSharedPreferencesHelper() {
        val presenter = demandPresenter()

        presenter.getSavedCardCount()

        verify(mockSharedPref).getSavedCardsCount()
    }

    @Test fun testLoadSavedCardsDisplaysCardsSuccessfully() {
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

    private fun demandPresenter() : SavedCardsPresenter {
        val presenter = SavedCardsPresenter(mockSharedPref, mockCardsRepo)
        presenter.attach(mockView)
        return presenter
    }
}
