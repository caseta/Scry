package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.base.CardsContract
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.utils.HeroUtils
import com.taylorcase.hearthstonescry.utils.NetworkManager
import io.reactivex.Single
import org.junit.Test
import java.util.Collections.*
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before

class CardsPresenterTest {

    private val mockView = mock<CardsContract.View>()
    private val mockHeroUtils = mock<HeroUtils>()
    private val mockCard = mock<Card>()
    private val mockCardRepo = mock<CardRepository>()
    private val mockNetworkManager = mock<NetworkManager>()

    @Before fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test fun testLoadCardsSuccessfullyCallsViewDisplayCards() {
        val presenter = demandCardsPresenter()
        doReturn(WARLOCK).whenever(mockHeroUtils).getFavoriteHero()
        doReturn(Single.just(singletonList(mockCard))).whenever(mockCardRepo).observeCardsWithHero(WARLOCK)

        presenter.loadCards()

        verify(mockHeroUtils).getFavoriteHero()
        verify(mockCardRepo).observeCardsWithHero(WARLOCK)
        verify(mockView).displayCards(singletonList(mockCard))
    }

    @Test fun testRefreshAllCardsSuccessfullyCallsDisplayCards() {
        val presenter = demandCardsPresenter()
        doReturn(true).whenever(mockNetworkManager).isConnected()
        doReturn(Single.just(singletonList(mockCard))).whenever(mockCardRepo).observeAllCardsWithApi()
        doReturn(WARLOCK).whenever(mockHeroUtils).getFavoriteHero()
        doReturn(Single.just(singletonList(mockCard))).whenever(mockCardRepo).observeCardsWithHero(WARLOCK)

        presenter.refreshAllCards()

        verify(mockNetworkManager).isConnected()
        verify(mockCardRepo).observeAllCardsWithApi()
        verify(mockHeroUtils).getFavoriteHero()
        verify(mockCardRepo).observeCardsWithHero(WARLOCK)
        verify(mockView).displayCards(singletonList(mockCard))
    }

    @Test fun testRefreshCardsCallsDisplayNetworkErrorWhenNotConnected() {
        val presenter = demandCardsPresenter()

        presenter.refreshAllCards()

        verify(mockNetworkManager).isConnected()
        verify(mockCardRepo, never()).observeAllCardsWithApi()
        verify(mockHeroUtils, never()).getFavoriteHero()
        verify(mockCardRepo, never()).observeCardsWithHero(WARLOCK)
        verify(mockView).displayNetworkError()
    }

    private fun demandCardsPresenter() : CardsPresenter {
        val presenter = CardsPresenter(mockHeroUtils, mockCardRepo, mockNetworkManager)
        presenter.attach(mockView)
        return presenter
    }
}