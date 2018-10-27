package com.taylorcase.hearthstonescry.savedcards

import com.taylorcase.hearthstonescry.base.MvpPresenter
import com.taylorcase.hearthstonescry.base.MvpView
import com.taylorcase.hearthstonescry.model.Card

interface SavedCardsContract {

    interface View : MvpView {

        fun displayCards(cards: List<Card>)
    }

    interface Presenter : MvpPresenter<View> {

        fun getSavedCardCount() : Int

        fun loadSavedCards()
    }

}