package com.taylorcase.hearthstonescry

import com.nhaarman.mockito_kotlin.*
import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.utils.SharedPreferencesHelper
import org.junit.Test
import org.assertj.core.api.Assertions

class DetailedCardPresenterTest {

    private val mockSharedPref = mock<SharedPreferencesHelper>()
    private val mockView = mock<DetailedCardContract.View>()

    @Test fun testIsCardSavedCallsSharedPreferencesHelper() {
        val presenter = demandPresenter()
        val card = Card()

        Assertions.assertThat(presenter.isCardSaved(card)).isFalse()
        verify(mockSharedPref).isCardSaved(card)
    }

    @Test fun testSaveCardCallsSharedPreferencesHelper() {
        val presenter = demandPresenter()
        val card = Card()

        presenter.saveCard(card)

        verify(mockSharedPref).saveCard(card)
    }

    @Test fun testRemoveCardCallsSharedPreferencesHelper() {
        val presenter = demandPresenter()
        val card = Card()

        presenter.removeCard(card)

        verify(mockSharedPref).removeCard(card)
    }

    private fun demandPresenter() : DetailedCardPresenter {
        val presenter = DetailedCardPresenter(mockSharedPref)
        presenter.attach(mockView)
        return presenter
    }
}