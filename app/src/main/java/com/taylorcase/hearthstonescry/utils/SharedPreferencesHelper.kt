package com.taylorcase.hearthstonescry.utils

import android.app.Application
import android.content.Context
import android.content.Context.*
import android.content.SharedPreferences
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.enums.Hero
import java.util.HashSet
import javax.inject.Inject

open class SharedPreferencesHelper @Inject constructor(var context: Application) {

    companion object {
        const val SHARED_PREF = "shared preferences"
        const val SAVED_CARDS_SET = "saved_cards_set"
        const val SAVED_THEME = "theme"
        const val FAVORITE_HERO_KEY = "favorite hero"
    }

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREF, MODE_PRIVATE)

    /**
     * This takes in a list of Cards, so that the caller is responsible
     * for handling async of getting the card list.
     */
    open fun getSavedCards(cards: List<Card>): List<Card> {
        val savedCards = ArrayList<Card>()

        val cardNames = getSavedCardNames()

        for (name in cardNames) {
            for (card in cards) {
                if (name == card.name) {
                    savedCards.add(card)
                    break
                }
            }
        }
        return savedCards
    }

    open fun getSavedCardsCount() = getSavedCardNames().size

    open fun isCardSaved(card: Card): Boolean {
        val savedCardNames: Set<String>?

        savedCardNames = getSavedCardNames()

        for (cardName in savedCardNames) {
            if (cardName == card.name) {
                return true
            }
        }
        return false
    }

    open fun saveCard(card: Card) {
        val tempSavedCards: MutableSet<String>
        val savedCards = getSavedCardNames()
        tempSavedCards = savedCards
        tempSavedCards.add(card.name)
        writeSavedCards(tempSavedCards)
    }

    open fun removeCard(card: Card) {
        val tempSavedCards: MutableSet<String>
        val savedCards = getSavedCardNames()
        tempSavedCards = savedCards
        tempSavedCards.remove(card.name)
        writeSavedCards(tempSavedCards)
    }

    open fun getSavedCardNames(): MutableSet<String> = sharedPreferences.getStringSet(SAVED_CARDS_SET, HashSet<String>())

    private fun writeSavedCards(savedCarts: Set<String>) {
        val editor = sharedPreferences.edit()
        editor.putStringSet(SAVED_CARDS_SET, savedCarts)
        editor.apply()
    }

    open fun saveTheme(theme: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(SAVED_THEME, theme)
        editor.apply()
    }

    open fun saveHero(hero: Hero) {
        val editor = sharedPreferences.edit()
        editor.putString(FAVORITE_HERO_KEY, hero.toString())
        editor.apply()
    }

    open fun getSavedHeroString(): String = sharedPreferences.getString(FAVORITE_HERO_KEY, "")

    fun getTheme(): Int {
        // TODO: Temporary hack for unit testing.
        if (context == null) {
            return R.style.AppTheme_Warlock
        } else {
            return sharedPreferences.getInt(SAVED_THEME, R.style.AppTheme_Warlock)
        }
    }
}