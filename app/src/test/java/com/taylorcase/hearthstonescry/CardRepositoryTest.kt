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

    companion object {
        private const val CARD_NAME = "Ysera"
    }
    
    private val mockApi = mock<HearthstoneApi>()
    private val mockCardDao = mock<CardDao>()
    private val mockCard = mock<Card>()
    private val mockAllCards = mock<AllCards>()

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
        demandValidCard()
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeCardsWithHero(Hero.WARLOCK)

        cardsObservable.test().assertValue(singletonList(mockCard)).assertSubscribed().assertComplete().assertNoErrors()
        verifyValidCard()
    }

    @Test fun testObserveCardsWithHeroUsingDatabase() {
        val cardList = singletonList(mockCard)
        doReturn(Single.just(cardList)).whenever(mockCardDao).observeAllCardsWithHero(Hero.WARLOCK.toString())
        val cardRepo = demandCardRepoUsingDatabase()

        val cardsObservable = cardRepo.observeCardsWithHero(Hero.WARLOCK)

        verify(mockCardDao).observeAllCardsWithHero(Hero.WARLOCK.toString())
        cardsObservable.test().assertValue(cardList).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveCardsWithHeroUsingApi() {
        demandValidCard()
        doReturn(Observable.just(mockAllCards)).whenever(mockApi).getAllCards(any())
        doReturn(singletonList(mockCard)).whenever(mockAllCards).listOfAllCards
        val cardRepo = demandCardRepoUsingApi()

        val cardsObservable = cardRepo.observeCardsWithHero(Hero.WARLOCK)

        cardsObservable.test().assertValue(singletonList(mockCard)).assertSubscribed().assertComplete().assertNoErrors()
        verifyValidCard()
        verify(mockApi).getAllCards(any())
        verify(mockAllCards).listOfAllCards
    }

    @Test fun testObserveCardWithCachedCards() {
        doReturn(CARD_NAME).whenever(mockCard).name
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeCard(CARD_NAME)

        cardsObservable.test().assertValue(singletonList(mockCard)).assertSubscribed().assertComplete().assertNoErrors()
        verify(mockCard).name
    }

    @Test fun testObserveCardWithDatabase() {
        val cardList = singletonList(mockCard)
        doReturn(Single.just(cardList)).whenever(mockCardDao).observeCard(CARD_NAME)
        val cardRepo = demandCardRepoUsingDatabase()

        val cardsObservable = cardRepo.observeCard(CARD_NAME)

        verify(mockCardDao).observeCard(CARD_NAME)
        cardsObservable.test().assertValue(cardList).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveCardWithApi() {
        val cardList = singletonList(mockCard)
        doReturn(Observable.just(cardList)).whenever(mockApi).getCard(any(), any())
        val cardRepo = demandCardRepoUsingApi()

        val cardsObservable = cardRepo.observeCard(CARD_NAME)

        cardsObservable.test().assertValue(cardList).assertSubscribed().assertComplete().assertNoErrors()
        verify(mockApi).getCard(any(), any())
    }

    @Test fun testObserveAllCardsWithCachedCards() {
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeAllCards()

        cardsObservable.test().assertValue(singletonList(mockCard)).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveAllCardsWithDatabase() {
        doReturn(Single.just(singletonList(mockCard))).whenever(mockCardDao).observeAllCards()
        val cardRepo = demandCardRepoUsingDatabase()

        val cardsObservable = cardRepo.observeAllCards()

        cardsObservable.test().assertValue(singletonList(mockCard)).assertSubscribed().assertComplete().assertNoErrors()
        verify(mockCardDao).observeAllCards()
    }

    @Test fun testObserveAllCardsWithApi() {
        demandValidCard()
        doReturn(Observable.just(mockAllCards)).whenever(mockApi).getAllCards(any())
        doReturn(singletonList(mockCard)).whenever(mockAllCards).listOfAllCards
        val cardRepo = demandCardRepoUsingApi()

        val cardsObservable = cardRepo.observeAllCards()

        cardsObservable.test().assertValue(singletonList(mockCard)).assertSubscribed().assertComplete().assertNoErrors()
    }

    @Test fun testObserveAllCardNames() {
        doReturn(CARD_NAME).whenever(mockCard).name
        val cardRepo = demandCardRepoUsingCachedCards()

        val cardsObservable = cardRepo.observeAllCardNames()

        cardsObservable.test().assertValue(singletonList(CARD_NAME)).assertSubscribed().assertComplete().assertNoErrors()
        verify(mockCard).name
    }

    @Test fun testSettingCachedCards() {
        val cardRepo = demandCardRepoUsingApi()
        Assertions.assertThat(cardRepo.cachedCards).isEmpty()

        cardRepo.storeCardsInCache(singletonList(mockCard))

        Assertions.assertThat(cardRepo.cachedCards).isNotEmpty()
    }

    @Test fun testStoringCardsInDatabase() {
        val cardRepo = demandCardRepo()

        cardRepo.storeCardsInDatabase(singletonList(mockCard))

        verify(mockCardDao).nukeTable()
        verify(mockCardDao).insertAll(mockCard)
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

    private fun demandCardRepoUsingCachedCards(cards: List<Card> = singletonList(mockCard)): CardRepository {
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

    private fun demandValidCard() {
        doReturn("Artist").whenever(mockCard).artist
        doReturn("img").whenever(mockCard).img
        doReturn("Warlock").whenever(mockCard).playerClass
        doReturn("Legendary").whenever(mockCard).rarity
    }

    private fun verifyValidCard() {
        verify(mockCard).artist
        verify(mockCard).img
        verify(mockCard).playerClass
        verify(mockCard).rarity
    }
}
