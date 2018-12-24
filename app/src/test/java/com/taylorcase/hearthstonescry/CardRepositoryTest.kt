package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.model.AllCards
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.room.CardDao
import io.reactivex.Observable
import io.reactivex.Single
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.*
import java.util.Collections.*

@RunWith(RobolectricTestRunner::class)
class CardRepositoryTest {

    private val mockApi = mock<HearthstoneApi>()
    private val mockCardDao = mock<CardDao>()

    @Test fun testRefreshCardsIsTrueWhenCachedIsEmpty() {
        doReturn(0).whenever(mockCardDao).countCards()
        val cardRepo = demandCardRepo()

        Assertions.assertThat(cardRepo.refreshCards()).isTrue()
        verify(mockCardDao).countCards()
    }

    @Test fun testRefreshCardsIsFalseWhenCachedIsNotEmpty() {
        val cardRepo = demandCardRepoUsingCachedCards()

        Assertions.assertThat(cardRepo.refreshCards()).isFalse()
    }

    @Test fun testRefreshCardsIsFalseWhenUseDatabaseIsTrue() {
        doReturn(1).whenever(mockCardDao).countCards()
        val cardRepo = demandCardRepo()

        Assertions.assertThat(cardRepo.refreshCards()).isFalse()
        verify(mockCardDao).countCards()
    }

    @Test fun testObserveCardsWithHeroUsingCached() {
        val card = demandValidCard()
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeCardsWithHero(Hero.WARLOCK)

        cardsObservable.test().assertValue(singletonList(card)).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveCardsWithHeroUsingDatabase() {
        val cardList = singletonList(demandValidCard())
        doReturn(Single.just(cardList)).whenever(mockCardDao).observeAllCardsWithHero(Hero.WARLOCK.toString())
        val cardRepo = demandCardRepoUsingDatabase()

        val cardsObservable = cardRepo.observeCardsWithHero(Hero.WARLOCK)

        verify(mockCardDao).observeAllCardsWithHero(Hero.WARLOCK.toString())
        cardsObservable.test().assertValue(cardList).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveCardsWithHeroUsingApi() {
        val card = demandValidCard()
        val allCards = AllCards(basicCards = singletonList(card))
        doReturn(Observable.just(allCards)).whenever(mockApi).getAllCards(any())
        val cardRepo = demandCardRepoUsingApi()

        val cardsObservable = cardRepo.observeCardsWithHero(Hero.WARLOCK)

        cardsObservable.test().assertValue(singletonList(card)).assertSubscribed().assertComplete().assertNoErrors()
        verify(mockApi).getAllCards(any())
    }

    @Test fun testObserveCardWithCachedCards() {
        val card = demandValidCard()
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeCard(CARD_NAME)

        cardsObservable.test().assertValue(singletonList(card)).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveCardWithDatabase() {
        val cardList = singletonList(demandValidCard())
        doReturn(Single.just(cardList)).whenever(mockCardDao).observeCard(CARD_NAME)
        val cardRepo = demandCardRepoUsingDatabase()

        val cardsObservable = cardRepo.observeCard(CARD_NAME)

        verify(mockCardDao).observeCard(CARD_NAME)
        cardsObservable.test().assertValue(cardList).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveCardWithApi() {
        val cardList = singletonList(demandValidCard())
        doReturn(Observable.just(cardList)).whenever(mockApi).getCard(any(), any())
        val cardRepo = demandCardRepoUsingApi()

        val cardsObservable = cardRepo.observeCard(CARD_NAME)

        cardsObservable.test().assertValue(cardList).assertSubscribed().assertComplete().assertNoErrors()
        verify(mockApi).getCard(any(), any())
    }

    @Test fun testObserveAllCardsWithCachedCards() {
        val card = demandValidCard()
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeAllCards()

        cardsObservable.test().assertValue(singletonList(card)).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveAllCardsWithDatabase() {
        val card = demandValidCard()
        doReturn(Single.just(singletonList(card))).whenever(mockCardDao).observeAllCards()
        val cardRepo = demandCardRepoUsingDatabase()

        val cardsObservable = cardRepo.observeAllCards()

        cardsObservable.test().assertValue(singletonList(card)).assertSubscribed().assertComplete().assertNoErrors()
        verify(mockCardDao).observeAllCards()
    }

    @Test fun testObserveAllCardsWithApi() {
        val card = demandValidCard()
        val allCards = AllCards(basicCards = singletonList(card))
        doReturn(Observable.just(allCards)).whenever(mockApi).getAllCards(any())
        val cardRepo = demandCardRepoUsingApi()

        val cardsObservable = cardRepo.observeAllCards()

        cardsObservable.test().assertValue(singletonList(card)).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveAllCardNames() {
        demandValidCard()
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeAllCardNames()

        cardsObservable.test().assertValue(singletonList(CARD_NAME)).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testSettingCachedCards() {
        val card = demandValidCard()
        val cardRepo = demandCardRepoUsingApi()
        Assertions.assertThat(cardRepo.cachedCards).isEmpty()

        cardRepo.storeCardsInCache(singletonList(card))

        Assertions.assertThat(cardRepo.cachedCards).isNotEmpty()
    }

    @Test fun testStoringCardsInDatabase() {
        val card = demandValidCard()
        val cardRepo = demandCardRepo()

        cardRepo.storeCardsInDatabase(singletonList(card))

        verify(mockCardDao).nukeTable()
        verify(mockCardDao).insertAll(card)
    }

    private fun demandCardRepoUsingDatabase(): CardRepository {
        val cardRepo = demandCardRepo()
        doReturn(1).whenever(mockCardDao).countCards()
        return cardRepo
    }

    private fun demandCardRepoUsingApi(): CardRepository {
        val cardRepo = demandCardRepo()
        doReturn(-1).whenever(mockCardDao).countCards()
        return cardRepo
    }

    private fun demandCardRepoUsingCachedCards(cards: List<Card> = singletonList(demandValidCard())): CardRepository {
        val cardRepo = demandCardRepo()
        cardRepo.cachedCards = cards
        return cardRepo
    }

    private fun demandCardRepo(): CardRepository {
        val cardRepo = CardRepository()
        cardRepo.api = mockApi
        cardRepo.cardDao = mockCardDao
        cardRepo.application = application
        return cardRepo
    }

    private fun demandValidCard() : Card {
        return Card(name = CARD_NAME, artist = "Artist", img = "img", playerClass = "Warlock", rarity = "Legendary")
    }

    companion object {
        private const val CARD_NAME = "Ysera"
    }
}
