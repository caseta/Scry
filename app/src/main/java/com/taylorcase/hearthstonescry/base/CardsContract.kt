package com.taylorcase.hearthstonescry.base

import com.taylorcase.hearthstonescry.model.Card

interface CardsContract {

    interface View : MvpView {

        fun displayCards(cards: List<Card>?)
    }

    interface Presenter : MvpPresenter<View> {

        fun loadCards()

        fun refreshAllCards()
    }
}