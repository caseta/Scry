package com.taylorcase.hearthstonescry.base

import com.taylorcase.hearthstonescry.model.Card
import com.taylorcase.hearthstonescry.model.FilterItem
import com.taylorcase.hearthstonescry.model.enums.Hero
import com.taylorcase.hearthstonescry.model.enums.Rarity
import com.taylorcase.hearthstonescry.model.enums.Sets

interface CardsContract {

    interface View : MvpView {

        fun displayCards(cards: List<Card>?)

        fun displayNetworkError()
    }

    interface Presenter : MvpPresenter<View> {

        fun loadCards()

        fun refreshAllCards()

        fun shouldAskToRateApp(): Boolean

        fun userWasAskedToRateApp()

        fun loadCardsWithFilters(filterItem: FilterItem)
    }
}
