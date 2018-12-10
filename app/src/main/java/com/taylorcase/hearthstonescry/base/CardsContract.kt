package com.taylorcase.hearthstonescry.base

import com.taylorcase.hearthstonescry.model.Card

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
    }
}
