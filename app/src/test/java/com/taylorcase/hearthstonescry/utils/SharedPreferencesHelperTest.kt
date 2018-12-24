package com.taylorcase.hearthstonescry.utils

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.R
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.model.enums.Hero.*
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper.Companion.FAVORITE_HERO_KEY
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper.Companion.SHARED_PREF
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper.Companion.SAVED_CARDS_SET
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper.Companion.SAVED_THEME
import org.assertj.core.api.Assertions.*
import org.junit.Test
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class SharedPreferencesHelperTest {

    private val mockContext = mock<Application>()
    private val mockSharedPreferences = mock<SharedPreferences>()
    private val mockEditor = mock<SharedPreferences.Editor>()

    @Test fun testGetSavedCardNamesReturnsSavedSet() {
        val expectedSet = HashSet<String>()
        expectedSet.add(CARD_NAME)
        val helper = demandHelperWithSavedCardName()

        val cards = helper.getSavedCardNames()

        assertThat(cards).isEqualTo(expectedSet)
        verifySavedCardName()
    }

    @Test fun testGetSavedCardNameReturnsEmptySetWhenThereAreNoSavedCards() {
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        val expectedSet = HashSet<String>()
        val helper = SharedPreferencesHelper(mockContext)

        val cards = helper.getSavedCardNames()

        assertThat(cards).isEqualTo(expectedSet)
        verifySavedCardName()
    }

    @Test fun testGetSavedCardsWithOneCard() {
        val card = Card(name = CARD_NAME)
        val secondCard = Card(name = SECOND_CARD_NAME)
        val expectedSavedCardsList = ArrayList<Card>()
        expectedSavedCardsList.add(card)
        val helper = demandHelperWithSavedCardName()

        val savedCards = helper.getSavedCards(listOf(card, secondCard))

        verifySavedCardName()
        assertThat(savedCards).isEqualTo(expectedSavedCardsList)
        assertThat(savedCards).hasSize(1)
    }

    @Test fun testGetSavedCardsReturnsNoCardsWithZeroMatches() {
        val secondCard = Card(name = SECOND_CARD_NAME)
        val expectedSavedCardsList = ArrayList<Card>()
        val helper = demandHelperWithSavedCardName()

        val savedCards = helper.getSavedCards(listOf(secondCard))

        verifySavedCardName()
        assertThat(savedCards).isEqualTo(expectedSavedCardsList)
        assertThat(savedCards).hasSize(0)
    }

    @Test fun testGetSavedCardsCountIsOne() {
        val helper = demandHelperWithSavedCardName()

        assertThat(helper.getSavedCardsCount()).isEqualTo(1)
        verifySavedCardName()
    }

    @Test fun testGetSavedCardsCountIsZero() {
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        val helper = SharedPreferencesHelper(mockContext)

        assertThat(helper.getSavedCardsCount()).isEqualTo(0)
        verifySavedCardName()
    }

    @Test fun testIsCardSavedTrue() {
        val card = Card(name = CARD_NAME)
        val helper = demandHelperWithSavedCardName()

        assertThat(helper.isCardSaved(card)).isTrue()
        verifySavedCardName()
    }

    @Test fun testIsCardSavedFalse() {
        val card = Card(name = SECOND_CARD_NAME)
        val helper = demandHelperWithSavedCardName()

        assertThat(helper.isCardSaved(card)).isFalse()
        verifySavedCardName()
    }

    @Test fun testSaveCardWritesToSharedPreferencesWithNoPreviousCards() {
        val card = Card(name = CARD_NAME)
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        doReturn(mockEditor).whenever(mockSharedPreferences).edit()
        val expectedSet = HashSet<String>()
        expectedSet.add(CARD_NAME)
        val helper = SharedPreferencesHelper(mockContext)

        helper.saveCard(card)

        verifySavedCardName()
        verifyEditorStringSetAddition(expectedSet)
    }

    @Test fun testSaveCardWritesToSharedPreferencesWithPreviousCards() {
        val secondCard = Card(name = SECOND_CARD_NAME)
        doReturn(mockEditor).whenever(mockSharedPreferences).edit()
        val expectedSet = HashSet<String>()
        expectedSet.add(CARD_NAME)
        expectedSet.add(SECOND_CARD_NAME)
        val helper = demandHelperWithSavedCardName()

        helper.saveCard(secondCard)

        verifySavedCardName()
        verifyEditorStringSetAddition(expectedSet)
    }

    @Test fun testRemoveCard() {
        doReturn(mockEditor).whenever(mockSharedPreferences).edit()
        val card = Card(name = CARD_NAME)
        val helper = demandHelperWithSavedCardName()

        helper.removeCard(card)

        verifySavedCardName()
        verifyEditorStringSetAddition(HashSet())
    }

    @Test fun testSaveTheme() {
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        doReturn(mockEditor).whenever(mockSharedPreferences).edit()
        val helper = SharedPreferencesHelper(mockContext)

        helper.saveTheme(R.style.AppTheme_Warlock)

        verify(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        verify(mockSharedPreferences).edit()
        verify(mockEditor).putInt(SAVED_THEME, R.style.AppTheme_Warlock)
        verify(mockEditor).apply()
    }

    @Test fun testSaveHero() {
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        doReturn(mockEditor).whenever(mockSharedPreferences).edit()
        val helper = SharedPreferencesHelper(mockContext)

        helper.saveHero(WARLOCK)

        verify(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        verify(mockSharedPreferences).edit()
        verify(mockEditor).putString(FAVORITE_HERO_KEY, WARLOCK.toString())
        verify(mockEditor, times(2)).apply()
    }

    @Test fun testGetSavedHeroString() {
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        doReturn(Hero.WARLOCK.toString()).whenever(mockSharedPreferences).getString(FAVORITE_HERO_KEY, "")
        val helper = SharedPreferencesHelper(mockContext)

        assertThat(helper.getSavedHeroString()).isEqualTo(Hero.WARLOCK.toString())
        verify(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        verify(mockSharedPreferences).getString(FAVORITE_HERO_KEY, "")
    }

    @Test fun testGetSavedHeroStringReturnsEmptyWhenThereIsNoSavedHero() {
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        doReturn("").whenever(mockSharedPreferences).getString(FAVORITE_HERO_KEY, "")
        val helper = SharedPreferencesHelper(mockContext)

        assertThat(helper.getSavedHeroString()).isEmpty()
        verify(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        verify(mockSharedPreferences).getString(FAVORITE_HERO_KEY, "")
    }

    @Test fun testGetTheme() {
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        doReturn(R.style.AppTheme_Warrior).whenever(mockSharedPreferences).getInt(SAVED_THEME, R.style.AppTheme_Warlock)
        val helper = SharedPreferencesHelper(mockContext)

        assertThat(helper.getTheme()).isEqualTo(R.style.AppTheme_Warrior)
        verify(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        verify(mockSharedPreferences).getInt(SAVED_THEME, R.style.AppTheme_Warlock)
    }

    private fun verifyEditorStringSetAddition(set: Set<String>) {
        verify(mockSharedPreferences).edit()
        verify(mockEditor).putStringSet(SAVED_CARDS_SET, set)
        verify(mockEditor).apply()
    }

    private fun demandHelperWithSavedCardName(): SharedPreferencesHelper {
        val setOfCardNames = HashSet<String>()
        setOfCardNames.add(CARD_NAME)
        doReturn(mockSharedPreferences).whenever(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        doReturn(setOfCardNames).whenever(mockSharedPreferences).getStringSet(SAVED_CARDS_SET, HashSet<String>())
        return SharedPreferencesHelper(mockContext)
    }

    private fun verifySavedCardName() {
        verify(mockContext).getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        verify(mockSharedPreferences).getStringSet(SAVED_CARDS_SET, HashSet<String>())
    }

    companion object {
        private const val CARD_NAME = "Ysera"
        private const val SECOND_CARD_NAME = "Lich King"
    }
}
