package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.utils.*
import io.reactivex.Single
import org.junit.Test
import java.util.Collections.*
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import net.bytebuddy.implementation.bytecode.Throw
import org.junit.Before

class CardsPresenterTest : BasePresenterTest() {

    private val mockView = mock<CardsContract.View>()
    private val mockHeroUtils = mock<HeroUtils>()
    private val mockCardRepo = mock<CardRepository>()
    private val mockNetworkManager = mock<NetworkManager>()
    private val mockSharedPreferencesHelper = mock<SharedPreferencesHelper>()

    @Test
    fun testLoadCardsSuccessfullyCallsViewDisplayCards() {
        val card = Card()
        val presenter = demandCardsPresenter()
        doReturn(WARLOCK).whenever(mockHeroUtils).getFavoriteHero()
        doReturn(Single.just(singletonList(card))).whenever(mockCardRepo).observeCardsWithHero(WARLOCK)

        presenter.loadCards()

        verify(mockHeroUtils).getFavoriteHero()
        verify(mockCardRepo).observeCardsWithHero(WARLOCK)
        verify(mockView).displayCards(singletonList(card))
    }

    @Test
    fun testLoadCardsShowsErrorProperly() {
        val error = IllegalStateException()
        val single: Single<List<Card>> = Single.error(error)
        val presenter = demandCardsPresenter()
        doReturn(WARLOCK).whenever(mockHeroUtils).getFavoriteHero()
        doReturn(single).whenever(mockCardRepo).observeCardsWithHero(WARLOCK)

        presenter.loadCards()

        verify(mockHeroUtils).getFavoriteHero()
        verify(mockCardRepo).observeCardsWithHero(WARLOCK)
        verify(mockView, never()).displayCards(any())
        verify(mockView).showError()
    }

    @Test
    fun testRefreshAllCardsSuccessfullyCallsDisplayCards() {
        val card = Card()
        val presenter = demandCardsPresenter()
        doReturn(true).whenever(mockNetworkManager).isConnected()
        doReturn(Single.just(singletonList(card))).whenever(mockCardRepo).observeAllCardsWithApi()
        doReturn(WARLOCK).whenever(mockHeroUtils).getFavoriteHero()
        doReturn(Single.just(singletonList(card))).whenever(mockCardRepo).observeCardsWithHero(WARLOCK)

        presenter.refreshAllCards()

        verify(mockNetworkManager).isConnected()
        verify(mockCardRepo).observeAllCardsWithApi()
        verify(mockHeroUtils).getFavoriteHero()
        verify(mockCardRepo).observeCardsWithHero(WARLOCK)
        verify(mockView).displayCards(singletonList(card))
    }

    @Test
    fun testRefreshAllCardsShowsErrorProperly() {
        val error = IllegalStateException()
        val single: Single<List<Card>> = Single.error(error)
        val presenter = demandCardsPresenter()
        doReturn(true).whenever(mockNetworkManager).isConnected()
        doReturn(single).whenever(mockCardRepo).observeAllCardsWithApi()

        presenter.refreshAllCards()

        verify(mockNetworkManager).isConnected()
        verify(mockCardRepo).observeAllCardsWithApi()
        verify(mockHeroUtils, never()).getFavoriteHero()
        verify(mockCardRepo, never()).observeCardsWithHero(WARLOCK)
        verify(mockView, never()).displayCards(any())
    }

    @Test
    fun testRefreshCardsCallsDisplayNetworkErrorWhenNotConnected() {
        val presenter = demandCardsPresenter()

        presenter.refreshAllCards()

        verify(mockNetworkManager).isConnected()
        verify(mockCardRepo, never()).observeAllCardsWithApi()
        verify(mockHeroUtils, never()).getFavoriteHero()
        verify(mockCardRepo, never()).observeCardsWithHero(WARLOCK)
        verify(mockView).displayNetworkError()
    }

    private fun demandCardsPresenter(): CardsPresenter {
        val presenter = CardsPresenter(mockHeroUtils, mockCardRepo, mockNetworkManager, mockSharedPreferencesHelper, mockScheduleComposer)
        presenter.attach(mockView)
        return presenter
    }
}
