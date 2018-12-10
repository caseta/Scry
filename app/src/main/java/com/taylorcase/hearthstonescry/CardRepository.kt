package com.taylorcase.hearthstonescry

import android.app.Application
import android.support.annotation.VisibleForTesting
import com.taylorcase.hearthstonescry.api.HearthstoneApi
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.room.CardDao
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CardRepository @Inject constructor() {
    
    @VisibleForTesting var cachedCards = emptyList<Card>()

    @Inject lateinit var api: HearthstoneApi
    @Inject lateinit var cardDao: CardDao
    @Inject lateinit var application: Application

    open fun refreshCards() = cachedCards.isEmpty() && !useDatabase()

    open fun observeCardsWithHero(hero: Hero): Single<List<Card>> {
        return when {
            cachedCards.isNotEmpty() -> {
                val list = ArrayList<Card>()
                for (card in cachedCards) {
                    if (isValidCard(card, hero)) {
                        list.add(card)
                    }
                }
                Single.just(list)
            }
            useDatabase() -> cardDao.observeAllCardsWithHero(hero.toString())
            else -> observeCardsWithHeroWithApi(hero)
        }
    }

    open fun observeCardsWithHeroWithApi(hero: Hero): Single<List<Card>> {
        return api.getAllCards(createApiHeaders()).flatMapIterable {
            it.listOfAllCards.filter {
                isValidCard(it, hero)
            }
        }.toList()
    }

    open fun observeCard(cardName: String): Observable<List<Card>> {
        return when {
            cachedCards.isNotEmpty() -> {
                val list = ArrayList<Card>()
                for (card in cachedCards) {
                    if (card.name == cardName) {
                        list.add(card)
                        break
                    }
                }
                Observable.just(list)
            }
            useDatabase() -> cardDao.observeCard(cardName).toObservable()
            else -> observeCardWithApi(cardName)
        }
    }

    private fun observeCardWithApi(cardName: String) = api.getCard(createApiHeaders(), cardName)

    open fun observeAllCards(): Single<List<Card>> {
        return when {
            cachedCards.isNotEmpty() -> Single.just(cachedCards)
            useDatabase() -> cardDao.observeAllCards()
            else -> observeAllCardsWithApi()
        }
    }

    open fun observeAllCardNames() : Single<List<String>> {
        return observeAllCards().toObservable().flatMapIterable { it }.map { it.name }.toList()
    }

    // Used to hit API on refresh
    open fun observeAllCardsWithApi(): Single<List<Card>> {
        return api.getAllCards(createApiHeaders()).flatMapIterable {
            it.listOfAllCards.filter {
                isValidCard(it)
            }
        }.toList().doAfterSuccess {
            storeCardsInCache(it)
            storeCardsInDatabase(it)
        }
    }

    @VisibleForTesting fun storeCardsInCache(cards: List<Card>) {
        cachedCards = cards
        Timber.d("Caching cards.")
    }

    @VisibleForTesting fun storeCardsInDatabase(cards: List<Card>) {
        cardDao.nukeTable()
        for (card in cards) {
            cardDao.insertAll(card)
        }
        Timber.d("Cards saved to database.")
    }

    private fun isValidCard(card: Card, hero: Hero): Boolean {
        return card.artist.isNotEmpty() && card.img.isNotEmpty() && hero.toString() == card.playerClass && card.rarity.isNotEmpty()
    }

    private fun isValidCard(card: Card): Boolean {
        return card.artist.isNotEmpty() && card.img.isNotEmpty() && card.rarity.isNotEmpty()
    }

    private fun useDatabase() = cardDao.countCards() > 0

    // Minor work around -- CI issues with adding header in HearthstoneApi.kt
    private fun createApiHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers[HEADER_KEY] = BuildConfig.HEARTHSTONE_API_KEY
        return headers
    }

    companion object {
        private const val HEADER_KEY = "X-Mashape-Key"
    }
}
